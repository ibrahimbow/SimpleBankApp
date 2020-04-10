package be.intecbrussel.controller;

import be.intecbrussel.custom_exception.BankTransactionException;
import be.intecbrussel.entity.Account;
import be.intecbrussel.entity.Client;
import be.intecbrussel.entity.TransactionsLog;
import be.intecbrussel.entity.TransactionType;
import be.intecbrussel.service.GenerateAccountNumber;
import be.intecbrussel.service.GenerateAmount;

import javax.persistence.*;
import java.util.List;

// TODO: 3/29/2020
//  show all the transaction for each client apart
//  make new class for administration CRUD
//  make Web-page mvc for controlling the data
//  change the transaction money with sender the amount


public class MyController_Backup {

    private  Client client = new Client();
    private  Account account ;


    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public MyController_Backup() {

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

// this method to return the type of transaction
    private TransactionType getTransactionType(int transactionType_ID){
        EntityManager ENTITY_MGR = ENTITY_MANAGER_FACTORY.createEntityManager();
        TransactionType transactionType = new TransactionType();
        ENTITY_MGR = ENTITY_MANAGER_FACTORY.createEntityManager();
        String sqlQueryTransferType = "select t" +
                "                       from TransactionType as t" +
                "                       where t.id_transactionType = :type";
        TypedQuery<TransactionType> typedQueryTransferType = ENTITY_MGR.createQuery(sqlQueryTransferType, TransactionType.class);
        typedQueryTransferType.setParameter("type", transactionType_ID);
        transactionType.setTransactionType(typedQueryTransferType.getSingleResult().getTransactionType());
        return transactionType;
    }

    private TransactionsLog setTransaction(double money, Account bankAccount, TransactionType transactionType){
        TransactionsLog transaction = new TransactionsLog();

        transaction.setTransaction_amount(money);
        transaction.setTransaction_date_time(transaction.getTransaction_date_time());
        transaction.setAccount(bankAccount);
        transaction.setTransactionType(transactionType);

        bankAccount.getTransactionArrayList().add(transaction);
        transactionType.getTransactionArrayList().add(transaction);

        return transaction;
    }

    private Account getAccount_From(int from_BankAccount, double money){
       Account account1 = findById(from_BankAccount);
        account1.setCurrent_balance(account1.getCurrent_balance() - money);
        return account1;
    }

    private Account getAccount_To(int to_BankAccount,double money){
        Account account_To = findById(to_BankAccount);
        account_To.setCurrent_balance(account_To.getCurrent_balance() + money);
        return account_To;
    }

    // the client who send the money
    public void transactionMoney( int from_BankAccount , double money) {
        EntityManager ENTITY_MGR = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = null;
        try {
            entityTransaction = ENTITY_MGR.getTransaction();
            entityTransaction.begin();

            ENTITY_MGR.persist(setTransaction(money, getAccount_From(from_BankAccount, money), getTransactionType(66)));

            entityTransaction.commit();

        }catch (Exception e){
            if(entityTransaction !=null){
                entityTransaction.rollback();
            }
            System.out.println(e.getMessage());
        } finally {
            ENTITY_MGR.close();
        }
    }

    // the client who receive the money
    public void receiveMoney(int to_BankAccount, double money) {
        EntityManager ENTITY_MGR = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = null;
        try {
            entityTransaction = ENTITY_MGR.getTransaction();
            entityTransaction.begin();
            ENTITY_MGR.persist(setTransaction(money, getAccount_To(to_BankAccount, money), getTransactionType(99)));
            entityTransaction.commit();
        }catch (Exception e){
            if(entityTransaction !=null){
                entityTransaction.rollback();
            }
            System.out.println(e.getMessage());
        } finally {
            ENTITY_MGR.close();
        }
    }

//    private void transactionGetMoney(int bankAccountNumber, double money) throws BankTransactionException {
//
//        if (findById(bankAccountNumber) == null) {
//            throw new BankTransactionException("Bank Account number is not found " + bankAccountNumber);
//        }
//
//        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
//        String sqlQueryTransfer = "select a from Account as a " +
//                "left outer join Transaction c " +
//                "on c.account.id_account = a.id_account " +
//                "left outer join TransactionType tt " +
//                "on c.transactionType.id_transactionType = tt.id_transactionType" +
//                " where a.account_number = :bankAccount";
//        TypedQuery<Account> typedQueryTransfer = entityManager.createQuery(sqlQueryTransfer, Account.class);
//        typedQueryTransfer.setParameter("bankAccount", bankAccountNumber);
//
//        String sqlQueryTransferType = "select t" +
//                "                       from TransactionType as t" +
//                "                       where t.id_transactionType =99";
//        TypedQuery<TransactionType> typedQueryTransferType = entityManager.createQuery(sqlQueryTransferType, TransactionType.class);
//
//
//        EntityTransaction entityTransaction = null;
//        Transaction transaction = new Transaction();
//        try {
//            entityTransaction = entityManager.getTransaction();
//            entityTransaction.begin();
//
//
//            Account accountTransfer = typedQueryTransfer.getSingleResult();
//            //      Account accountTransfer = findById(bankAccountNumber);
//            double newBalance = accountTransfer.getCurrent_balance() + money;
//            if (accountTransfer.getCurrent_balance() + money < 0) {
//                throw new BankTransactionException(
//                        "The money in the account '" + bankAccountNumber + "' is not enough (" + account.getCurrent_balance() + ")");
//            }
//            System.out.println("before add the amount : ");
//            System.out.println(accountTransfer.getCurrent_balance());
//            System.out.println("after add the amount:");
//            accountTransfer.setCurrent_balance(newBalance);
//            System.out.println(accountTransfer.getCurrent_balance());
//            System.out.println("-------------------------");
//
//            // here we get the type of transaction to sort in the transaction table
//            TransactionType transactionTypeTransfer = typedQueryTransferType.getSingleResult();
//            transactionTypeTransfer.setTransactionType(transactionTypeTransfer.getTransactionType());
//
//            // here we have the transaction object to store the data of transaction
//
//
//            transaction.setTransaction_amount(money);
//
//            transaction.setTransaction_date_time(transaction.getTransaction_date_time());
//            transaction.setAccount(accountTransfer);
//            transaction.setTransactionType(transactionTypeTransfer);
//
//            accountTransfer.getTransactionArrayList().add(transaction);
//            transactionTypeTransfer.getTransactionArrayList().add(transaction);
//
//            //account.setCurrent_balance(account.getCurrent_balance() - money);
//
//
//            entityManager.persist(accountTransfer);
//            entityManager.persist(transactionTypeTransfer);
//            entityManager.persist(transaction);
//
//
//            entityTransaction.commit();
//        }catch (Exception e){
//            if(entityTransaction !=null){
//                entityTransaction.rollback();
//            }
//            System.out.println(e.getMessage());
//        } finally {
//            entityManager.close();
//        }
//
//
//    }

    public Account findById(int bankAccountNumber) {
        EntityManager ENTITY_MGR = ENTITY_MANAGER_FACTORY.createEntityManager();
        String sqlQueryTransfer = "select a from Account as a " +
                "left outer join TransactionsLog c " +
                "on c.account.id_account = a.id_account " +
                "left outer join TransactionType tt " +
                "on c.transactionType.id_transactionType = tt.id_transactionType" +
                " where a.account_number = :bankAccount";
        TypedQuery<Account> typedQueryTransfer = ENTITY_MGR.createQuery(sqlQueryTransfer, Account.class);
        typedQueryTransfer.setParameter("bankAccount", bankAccountNumber);

        return ENTITY_MGR.find(Account.class,typedQueryTransfer.getSingleResult().getId_account());

    }


     // Do not catch BankTransactionException in this method.
    public void sendMoney(int fromBankAccountNumber, int toBankAccountNumber, double amount)throws BankTransactionException  {
        transactionMoney(fromBankAccountNumber, - amount);
      //  receiveMoney(toBankAccountNumber,amount);
//       transactionSendMoney(fromBankAccountNumber, -amount);
//        transactionGetMoney(toBankAccountNumber, amount);
    }


    public boolean checkEmptyText(String text1, String text2){

        return text1 ==null || text2 == null;
    }


    public Account findByBankAccountNumber(int bankAccountNumber){
        EntityManager ENTITY_MGR = ENTITY_MANAGER_FACTORY.createEntityManager();
    String sqlQueryTransfer = "select a from Account as a " +
            "left outer join TransactionsLog c " +
            "on c.account.id_account = a.id_account " +
            "left outer join TransactionType tt " +
            "on c.transactionType.id_transactionType = tt.id_transactionType" +
            " where a.account_number = :bankAccount";
    TypedQuery<Account> typedQueryTransfer = ENTITY_MGR.createQuery(sqlQueryTransfer, Account.class);
    typedQueryTransfer.setParameter("bankAccount", bankAccountNumber);
        return typedQueryTransfer.getSingleResult();
        //return ENTITY_MGR.find(Account.class,typedQueryTransfer.getSingleResult().getId_account());
    //return typedQueryTransfer.getSingleResult();
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


    public void showTransactionLog(int account_id) throws BankTransactionException{
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = em.getTransaction();
            et.begin();
            String sqlQueryShowTransactions = "SELECT t FROM TransactionsLog as t " +
                    "join Account as a" +
                    " on t.account.id_account = a.id_account" +
                    "            join TransactionType as tt" +
                    "            on t.transactionType.id_transactionType = tt.id_transactionType" +
                    "            where t.account.id_account = :id_Account";
            TypedQuery<TransactionsLog> transactionTypedQuery =
                    em.createQuery(sqlQueryShowTransactions, TransactionsLog.class);
            transactionTypedQuery.setParameter("id_Account",account_id);
            List<TransactionsLog> messagesResult = transactionTypedQuery.getResultList();
            messagesResult.forEach(System.out::println);
            et.commit();
            em.close();
    }


}



