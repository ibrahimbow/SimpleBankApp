package be.intecbrussel.controller;

import be.intecbrussel.custom_exception.BankTransactionException;
import be.intecbrussel.dao_implementation.AdminDaoImpl;
import be.intecbrussel.entity.*;
import be.intecbrussel.service.GenerateAccountNumber;
import be.intecbrussel.service.GenerateAmount;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

// TODO: 3/29/2020
//  show all the transaction for each client apart
//  make new class for administration CRUD
//  make Web-page mvc for controlling the data
//  change the transaction money with sender the amount - done

public class MyController {

    private static EntityManagerFactory ENTITY_MANAGER_FACTORY
            = Persistence.createEntityManagerFactory("bank_accounts");

    private  Account account ;
    private Client client;
    private Admin admin;
    private AdminDaoImpl adminDao = new AdminDaoImpl();
    private LogFile log;


    public AdminDaoImpl getAdminDao() {
        return adminDao;
    }

    public void setAdminDao(AdminDaoImpl adminDao) {
        this.adminDao = adminDao;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public MyController() {
    }


    // This method is only to show only the information of all clients
    public void showAll (){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        TypedQuery<Client> sqlQuery = em.createQuery("SELECT c FROM Client c",Client.class);
        List<Client> messagesResult = sqlQuery.getResultList();
        messagesResult.forEach(System.out::println);
        et.commit();
        em.close();
    }

    // this method to check if the client exist on database or not
    public boolean checkLogin(String usr, String pwd)  {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        String sqlQuery = "Select c from Client as c where c.username = :usr and c.password = :pwd";
        TypedQuery<Client> typedQuery = entityManager.createQuery(sqlQuery,Client.class);
        typedQuery.setParameter("usr",usr);
        typedQuery.setParameter("pwd",pwd);

        try{
            if(typedQuery.getSingleResult()!=null){  // to see if there's result or not
                return true;
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());

        }finally {
            entityManager.close();
        }
        return false;
    }

    // here i used object from client to see if the username and password is exist or not and then
    // I can be able to retrieve the data of the same object and use it again in the web page
    public Account checkForLoginAccount(String usr, String pwd){
        //Account account=null;
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        String sqlQuery = "Select c from Account as c " +
                "join Client as a " +
                "on a.id_client = c.client.id_client " +
                "where c.client.id_client = " +
                    "(select id_client from Client as c " +
                    "where c.username = :usr and c.password = :pwd)";
        TypedQuery<Account> typedQuery = entityManager.createQuery(sqlQuery, Account.class);
        typedQuery.setParameter("usr", usr);
        typedQuery.setParameter("pwd", pwd);
        try{
            this.account = typedQuery.getSingleResult();

        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }finally {
            entityManager.close();
        }
        return this.account;
    }

    // This method is to add new client via the client self
    public void add(String userName, String first_name, String last_name, String email, String password) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = null;
        try {
            if(userName.equals("") || first_name.equals("") || last_name.equals("") || password.equals("") || email.equals("")) {
                throw new BankTransactionException("Not allowed empty values");
            }else {
                entityTransaction = entityManager.getTransaction();
                entityTransaction.begin();

                Client client = new Client();

                client.setUsername(userName);
                client.setFirst_name(first_name);
                client.setLast_name(last_name);
                client.setEmail(email);
                client.setPassword(password);
                GenerateAmount generateAmount = new GenerateAmount();
                GenerateAccountNumber generateAccountNumber = new GenerateAccountNumber();
                Account account = new Account(generateAccountNumber.getAccountNumber(), generateAmount.generatedAmount());
                account.setClient(client);

                client.getAccountList().add(account);

                entityManager.persist(account);
                entityManager.persist(client);
                entityManager.getTransaction();
                entityTransaction.commit();
            }
        }catch (Exception e){
            if(entityTransaction !=null){
                entityTransaction.rollback();
            }
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    // This is responsible for  operations of transaction money
    private void transactionMoney(int from_bankAccountNumber,int to_bank, double money, int id_transactionType) throws BankTransactionException {

        if (findById(from_bankAccountNumber) == null) {
            throw new BankTransactionException("Bank Account number is not found " + from_bankAccountNumber);
        }

        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        String sqlQueryTransfer = "select a from Account as a " +
                "left outer join TransactionsLog c " +
                "on c.account.id_account = a.id_account " +
                "left outer join TransactionType tt " +
                "on c.transactionType.id_transactionType = tt.id_transactionType" +
                " where a.account_number = :bankAccount";
        TypedQuery<Account> typedQueryTransfer = entityManager.createQuery(sqlQueryTransfer, Account.class);
        typedQueryTransfer.setParameter("bankAccount", from_bankAccountNumber);

        String sqlQueryTransferType = "select t" +
                "                       from TransactionType as t" +
                "                       where t.id_transactionType =:id_Transaction";
        TypedQuery<TransactionType> typedQueryTransferType = entityManager.createQuery(sqlQueryTransferType, TransactionType.class);
        typedQueryTransferType.setParameter("id_Transaction",id_transactionType);


        EntityTransaction entityTransaction = null;
        TransactionsLog transaction = new TransactionsLog();
        try {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();

            Account accountTransfer = typedQueryTransfer.getSingleResult();
      //      Account accountTransfer = findById(from_bankAccountNumber);
            double newBalance = accountTransfer.getCurrent_balance() + money;
            if (accountTransfer.getCurrent_balance() + money < 0) {
                throw new BankTransactionException(
                        "The money in the account '" + from_bankAccountNumber + "' is not enough (" + account.getCurrent_balance() + ")");
            }
            System.out.println("before add the amount : ");
            System.out.println(accountTransfer.getCurrent_balance());
            System.out.println("after add the amount:");
            accountTransfer.setCurrent_balance(newBalance);
            System.out.println(accountTransfer.getCurrent_balance());
            System.out.println("-------------------------");

            // here we get the type of transaction to sort in the transaction table
            TransactionType transactionTypeTransfer = typedQueryTransferType.getSingleResult();
            transactionTypeTransfer.setTransactionType(transactionTypeTransfer.getTransactionType());

            // here we have the transaction object to store the data of transaction
            transaction.setTransaction_amount(money);
            transaction.setTransaction_date_time(LocalDateTime.now());
            transaction.setAccount(accountTransfer);
            transaction.setTransactionType(transactionTypeTransfer);
            transaction.setForm_TO_account_number(to_bank);

            accountTransfer.getTransactionArrayList().add(transaction);
            transactionTypeTransfer.getTransactionArrayList().add(transaction);

            entityManager.persist(accountTransfer);
            entityManager.persist(transactionTypeTransfer);
            entityManager.persist(transaction);

            entityTransaction.commit();
        }catch (Exception e){
            if(entityTransaction !=null){
                entityTransaction.rollback();
            }
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    // this method return the query of object
    public Account findById(int bankAccountNumber) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        String sqlQueryTransfer = "select a from Account as a " +
                "left outer join TransactionsLog c " +
                "on c.account.id_account = a.id_account " +
                "left outer join TransactionType tt " +
                "on c.transactionType.id_transactionType = tt.id_transactionType" +
                " where a.account_number = :bankAccount";
        TypedQuery<Account> typedQueryTransfer = entityManager.createQuery(sqlQueryTransfer, Account.class);
        typedQueryTransfer.setParameter("bankAccount", bankAccountNumber);

        return entityManager.find(Account.class,typedQueryTransfer.getSingleResult().getId_account());
    }

     // Do not catch BankTransactionException in this method.
    public void sendMoney(int fromBankAccountNumber, int toBankAccountNumber, double amount)throws BankTransactionException  {
       transactionMoney(fromBankAccountNumber,toBankAccountNumber, -amount,99); // sender
        transactionMoney(toBankAccountNumber,fromBankAccountNumber, amount,66); // receiver
    }

    public boolean checkEmptyText(String text1, String text2){

        return text1 ==null || text2 == null;
    }

    //This method return an object of the account
    public Account findByBankAccountNumber(int bankAccountNumber){
    EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
    String sqlQueryTransfer = "select a from Account as a " +
            "left outer join TransactionsLog c " +
            "on c.account.id_account = a.id_account " +
            "left outer join TransactionType tt " +
            "on c.transactionType.id_transactionType = tt.id_transactionType" +
            " where a.account_number = :bankAccount";
    TypedQuery<Account> typedQueryTransfer = entityManager.createQuery(sqlQueryTransfer, Account.class);
    typedQueryTransfer.setParameter("bankAccount", bankAccountNumber);
        try{
            this.account =typedQueryTransfer.getSingleResult();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }finally {
            entityManager.close();
        }
    return this.account;
}

    // check if the email is already exist in database
    public Account checkIfBankAccountNumberIsExist(int bankAccountNumber)  {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();

        String sqlQuery = "Select c from Account as c where c.account_number = :bankAccount";
        TypedQuery<Account> typedQuery = entityManager.createQuery(sqlQuery,Account.class);
        typedQuery.setParameter("bankAccount",bankAccountNumber);
        try{
            this.account = typedQuery.getSingleResult();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }finally {
            entityManager.close();
        }
        return this.account;

        //return entityManager.find(Account.class,typedQuery.getSingleResult().getId_account());
    }

    //This is for show all the transaction for the selected client by id
    public List<TransactionsLog> showTransactionLog(int account_id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

            String sqlQueryShowTransactions = "SELECT t FROM TransactionsLog as t " +
                    "join Account as a" +
                    "    on t.account.id_account = a.id_account" +
                    " join TransactionType as tt" +
                    "    on t.transactionType.id_transactionType = tt.id_transactionType where t.account.id_account = :id_Account";
            TypedQuery<TransactionsLog> transactionTypedQuery =
                    em.createQuery(sqlQueryShowTransactions, TransactionsLog.class);
            transactionTypedQuery.setParameter("id_Account",account_id);
        return transactionTypedQuery.getResultList();
    }

    // This is to responsible admin login
    public Admin checkForLoginAccountAdmin(String admin_usr, String admin_pwd) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        String sqlQuery = "Select a from Admin as a where a.adminUserName = :usr and a.admin_password = :pwd";
        TypedQuery<Admin> typedQuery = entityManager.createQuery(sqlQuery, Admin.class);
        typedQuery.setParameter("pwd", admin_pwd);
        typedQuery.setParameter("usr", admin_usr);
        try{
    this.admin = typedQuery.getSingleResult();

        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }finally {
            entityManager.close();
        }
        return this.admin;
    }

    // check the admin username to avoid register with the same username
    public Admin checkAdminUserName(String user ){
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        String sqlQuery = "SELECT c FROM Admin as c WHERE c.adminUserName =: userAdmin";
        TypedQuery<Admin> adminTypedQuery = entityManager.createQuery(sqlQuery,Admin.class);
        adminTypedQuery.setParameter("userAdmin",user);
        try{
            this.admin =  entityManager.find(Admin.class,adminTypedQuery.getSingleResult().getAdmin_id());
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return this.admin;
    }

    // check if the username is already exists in order to register new client
    public Client checkUserName(String userName){
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        String sqlQueryUserName = "SELECT c FROM Client as c where c.username =: username";
        TypedQuery<Client> clientTypedQuery = entityManager.createQuery(sqlQueryUserName,Client.class);
        clientTypedQuery.setParameter("username",userName);
        try{
            this.client =  entityManager.find(Client.class,clientTypedQuery.getSingleResult().getId_client());
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return this.client;
    }

    // check if the Email is already exists in order to register new client
    public Client checkEmail(String email){
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        String sqlQueryUserName = "SELECT c FROM Client as c where c.email =: email";
        TypedQuery<Client> clientTypedQuery = entityManager.createQuery(sqlQueryUserName,Client.class);
        clientTypedQuery.setParameter("email",email);
        try{
            this.client =  entityManager.find(Client.class,clientTypedQuery.getSingleResult().getId_client());
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return this.client;

    }

    //log registration file
    public void addLogsLogin(int id){
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = null;
        try {
                entityTransaction = entityManager.getTransaction();
                entityTransaction.begin();

                Client client = entityManager.find(Client.class,id);

                LogFile logFile = new LogFile();
                logFile.setDateTime(LocalDateTime.now());
                logFile.setClient_log(client);

                client.getLogFileArrayList().add(logFile);

                entityManager.persist(logFile);
                entityManager.persist(client);
                entityManager.getTransaction();
                entityTransaction.commit();
        }catch (Exception e){
            if(entityTransaction !=null){
                entityTransaction.rollback();
            }
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    //Show Client information to be updated
    public List<Client> showClientInfo(int bankAccount) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String sqlQueryShowClientInfo = "SELECT c FROM Client c " +
                "join Account a " +
                "on c.id_client = c.id_client " +
                "WHERE a.account_number =: accountNumber";
        TypedQuery<Client> transactionTypedQuery =
                em.createQuery(sqlQueryShowClientInfo, Client.class);
        transactionTypedQuery.setParameter("accountNumber",bankAccount);
        return transactionTypedQuery.getResultList();
    }

}
