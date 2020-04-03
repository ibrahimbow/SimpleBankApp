package be.intecbrussel.dao_implementation;

import be.intecbrussel.dao.Dao;
import be.intecbrussel.entity.Account;
import be.intecbrussel.entity.Client;
import be.intecbrussel.entity.Transaction;
import be.intecbrussel.service.GenerateAccountNumber;

import javax.persistence.*;
import java.util.List;

public class AdminDaoImpl implements Dao<Account> {

    private static  EntityManagerFactory ENTITY_MANAGER_FACTORY =
            Persistence.createEntityManagerFactory("bank_accounts");
    private static EntityManager ENTITY_MNG = ENTITY_MANAGER_FACTORY.createEntityManager();

    @Override
    public void createNewAccount(String userName, String first_name, String last_name, String email, String password,double amount) {
        EntityTransaction entityTransaction = ENTITY_MNG.getTransaction();
        try{
            ENTITY_MNG.getTransaction().begin();
            Client client = new Client();
            client.setUsername(userName);
            client.setFirst_name(first_name);
            client.setLast_name(last_name);
            client.setEmail(email);
            client.setPassword(password);

            Account account = new Account(new GenerateAccountNumber().getAccountNumber(), amount);
            account.setClient(client);

            client.getAccountList().add(account);

            ENTITY_MNG.persist(account);
            ENTITY_MNG.persist(client);
            ENTITY_MNG.getTransaction();
            entityTransaction.commit();

        }catch (Exception e){
            if(entityTransaction !=null){
                entityTransaction.rollback();
            }
            System.out.println(e.getMessage());
        }finally {
            ENTITY_MNG.close();
        }
    }

    @Override
    public void update(Account accountClient) {
        EntityTransaction entityTransaction = ENTITY_MNG.getTransaction();
        try {
            entityTransaction.begin();
            Account account2 = ENTITY_MNG.find(Account.class,accountClient.getClient().getId_client());
            account2.getClient().setUsername(accountClient.getClient().getUsername());
            account2.getClient().setFirst_name(accountClient.getClient().getFirst_name());
            account2.getClient().setLast_name(accountClient.getClient().getLast_name());
            account2.getClient().setEmail(accountClient.getClient().getEmail());
            account2.getClient().setPassword(accountClient.getClient().getPassword());
            account2.setCurrent_balance(accountClient.getCurrent_balance());
            entityTransaction.commit();
        }catch (Exception e){
            if(entityTransaction !=null){
                entityTransaction.rollback();
            }
            System.out.println(e.getMessage());
        }finally {
            ENTITY_MNG.close();
        }

    }

    @Override
    public Account getById(int id) {
        EntityManager ent = ENTITY_MANAGER_FACTORY.createEntityManager();
        String sqlQueryTransfer = "select a from Account as a " +
                "left outer join Transaction c " +
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
                "join Transaction as t" +
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
