package be.intecbrussel.dao_implementation;

import be.intecbrussel.custom_exception.BankTransactionException;
import be.intecbrussel.dao.Dao;
import be.intecbrussel.entity.Account;
import be.intecbrussel.entity.Client;
import be.intecbrussel.service.GenerateAccountNumber;

import javax.persistence.*;
import java.util.List;

public class AdminDaoImpl implements Dao<Account> {

    private static  EntityManagerFactory ENTITY_MANAGER_FACTORY =
            Persistence.createEntityManagerFactory("bank_accounts");
    private static EntityManager ENTITY_MNG = ENTITY_MANAGER_FACTORY.createEntityManager();

    @Override
    public void createNewAccount(String userName, String first_name, String last_name, String email, String password,double amount) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try{
            if(userName.equals("") || first_name.equals("") || last_name.equals("") || password.equals("") || email.equals("")) {
                throw new BankTransactionException("Not allowed empty values");
            }else {
                entityTransaction.begin();
                Client client = new Client();
                client.setUsername(userName);
                client.setFirst_name(first_name);
                client.setLast_name(last_name);
                client.setEmail(email);
                client.setPassword(password);

                Account account = new Account(new GenerateAccountNumber().getAccountNumber(), amount);
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
        }finally {
            entityManager.close();
        }
    }

    @Override
    public void update(Account accountClient) {
        EntityManager entityManagerUpdate = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = entityManagerUpdate.getTransaction();
        try {
            entityTransaction.begin();
//            accountClient = ENTITY_MNG.find(Account.class,accountClient.getClient().getId_client());
            entityManagerUpdate.merge(accountClient);
//            accountClient.getClient().setUsername(accountClient.getClient().getUsername());
//            accountClient.getClient().setFirst_name(accountClient.getClient().getFirst_name());
//            accountClient.getClient().setLast_name(accountClient.getClient().getLast_name());
//            accountClient.getClient().setEmail(accountClient.getClient().getEmail());
//            accountClient.getClient().setPassword(accountClient.getClient().getPassword());
//            accountClient.setCurrent_balance(accountClient.getCurrent_balance());
            entityTransaction.commit();
        }catch (Exception e){
            if(entityTransaction !=null){
                entityTransaction.rollback();
            }
            System.out.println(e.getMessage());
        }finally {
            entityManagerUpdate.close();
        }
    }

    @Override
    public Account getById(int id) {
        EntityManager ent = ENTITY_MANAGER_FACTORY.createEntityManager();
        String sqlQueryTransfer = "select a from Account as a " +
                "left outer join TransactionsLog c " +
                "on c.account.id_account = a.id_account " +
                "left outer join TransactionType tt " +
                "on c.transactionType.id_transactionType = tt.id_transactionType" +
                " where a.client.id_client =:client_id";
        TypedQuery<Account> typedQueryTransfer = ent.createQuery(sqlQueryTransfer, Account.class);
        typedQueryTransfer.setParameter("client_id", id);

        return typedQueryTransfer.getSingleResult();

    }

    @Override
    public List<Account> getAll() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String sqlQuery = "SELECT a FROM Account as a " +
                "join TransactionsLog as t" +
                "    on t.account.id_account = a.id_account" +
                " join TransactionType as tt" +
                "    on t.transactionType.id_transactionType = tt.id_transactionType";
        TypedQuery<Account> clientTypedQuery = em.createQuery(sqlQuery,Account.class);
        return clientTypedQuery.getResultList();
    }

    @Override
    public void delete(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            em.getTransaction().begin();
            Client client = em.find(Client.class, id);
            if (client == null) {
                throw new EntityNotFoundException("Can't find client for this ID " + id);
            }else {
                em.remove(client);
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            if(em.getTransaction() != null){
                em.getTransaction().rollback();
            }
            System.out.println(e.getMessage());
        }finally {
            em.close();
        }
    }
}
