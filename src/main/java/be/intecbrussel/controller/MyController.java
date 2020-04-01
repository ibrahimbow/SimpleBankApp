package be.intecbrussel.controller;

import be.intecbrussel.custom_exception.BankTransactionException;
import be.intecbrussel.entity.Account;
import be.intecbrussel.entity.Client;
import be.intecbrussel.entity.Transaction;
import be.intecbrussel.entity.TransactionType;
import be.intecbrussel.service.GenerateAccountNumber;
import be.intecbrussel.service.GenerateAmount;
import com.mysql.cj.Session;
import org.hibernate.Criteria;

import javax.persistence.*;
import java.util.List;

// TODO: 3/29/2020
//  show all the transaction for each client apart
//  make new class for administration CRUD
//  make Web-page mvc for controlling the data
//  change the transaction money with sender the amount





public class MyController {

    private  Client client = new Client();
    private  Account account ;


    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public MyController() {

    }

    private static EntityManagerFactory ENTITY_MANAGER_FACTORY
            = Persistence.createEntityManagerFactory("bank_accounts");

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
                "on a.id_client = c.client.id_client where c.client.id_client = (select id_client from Client as c where c.username = :usr and c.password = :pwd)";
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

    // add new client
    public void add(String userName, String first_name, String last_name, String email, String password) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();

        EntityTransaction entityTransaction = null;
        try {
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

            // check if the email is already existed
//            if(checkLogin(email)) {
//                entityManager.persist(client);
//                entityManager.getTransaction();
//                entityTransaction.commit();
//            } else{
//                entityTransaction.rollback();
//            }

        }catch (Exception e){
            if(entityTransaction !=null){
                entityTransaction.rollback();
            }
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }

    }


    private void transactionMoney(int from_bankAccountNumber,int to_bank, double money, int id_transactionType) throws BankTransactionException {

        if (findById(from_bankAccountNumber) == null) {
            throw new BankTransactionException("Bank Account number is not found " + from_bankAccountNumber);
        }

        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        String sqlQueryTransfer = "select a from Account as a " +
                "left outer join Transaction c " +
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
        Transaction transaction = new Transaction();
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
            transaction.setTransaction_date_time(transaction.getTransaction_date_time());
            transaction.setAccount(accountTransfer);
            transaction.setTransactionType(transactionTypeTransfer);
            transaction.setForm_TO_account_number(to_bank);

            accountTransfer.getTransactionArrayList().add(transaction);
            transactionTypeTransfer.getTransactionArrayList().add(transaction);

            //account.setCurrent_balance(account.getCurrent_balance() - money);


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
    
    public Account findById(int bankAccountNumber) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        String sqlQueryTransfer = "select a from Account as a " +
                "left outer join Transaction c " +
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


    public Account findByBankAccountNumber(int bankAccountNumber){
    EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
    String sqlQueryTransfer = "select a from Account as a " +
            "left outer join Transaction c " +
            "on c.account.id_account = a.id_account " +
            "left outer join TransactionType tt " +
            "on c.transactionType.id_transactionType = tt.id_transactionType" +
            " where a.account_number = :bankAccount";
    TypedQuery<Account> typedQueryTransfer = entityManager.createQuery(sqlQueryTransfer, Account.class);
    typedQueryTransfer.setParameter("bankAccount", bankAccountNumber);


    return this.account = typedQueryTransfer.getSingleResult();
        //return account;

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


    public void addNewClient(){
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();

        EntityTransaction entityTransaction = null;
        try {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            // first get the information of the client

            //step 1
            Client client1 = new Client();
            client1.setUsername("bbg");
            client1.setFirst_name("ibrahim");
            client1.setLast_name("alolofi");
            client1.setEmail("bbg@gmoa.com");
            client1.setPassword("b");



            //step 2;
            GenerateAmount generateAmount = new GenerateAmount();

            GenerateAccountNumber generateAccountNumber = new GenerateAccountNumber();

            Account account = new Account(123,6);
            account.setClient(client1);


            Account account1 = new Account(321,7);
            account1.setClient(client1);
//            account.setAccount_number(generateAccountNumber.getAccountNumber());
//            account.setCurrent_balance(generateAmount.getGeneratedAmount());

            client1.getAccountList().add(account);
            client1.getAccountList().add(account1);

            entityManager.persist(client1);
            entityManager.persist(account);


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


    public List<Transaction> showTransactionLog(int account_id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

            String sqlQueryShowTransactions = "SELECT t FROM Transaction as t " +
                    "join Account as a" +
                    "    on t.account.id_account = a.id_account" +
                    " join TransactionType as tt" +
                    "    on t.transactionType.id_transactionType = tt.id_transactionType where t.account.id_account = :id_Account";
            TypedQuery<Transaction> transactionTypedQuery =
                    em.createQuery(sqlQueryShowTransactions,Transaction.class);
            transactionTypedQuery.setParameter("id_Account",account_id);
        return transactionTypedQuery.getResultList();
    }




}


