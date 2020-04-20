package be.intecbrussel.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue
    private int id_account;

    @Column(name = "account_number" , nullable = false,unique = true)
    private int account_number;

    @Column(name = "current_balance", precision = 10, scale = 2)
    private double current_balance;


    @ManyToOne (cascade=CascadeType.ALL)
    private Client client ;


    @OneToMany(mappedBy="account", cascade=CascadeType.ALL, orphanRemoval = true)
    private List<TransactionsLog> transactionArrayList = new ArrayList<>();

    // constructor
    public Account() {
    }

    public List<TransactionsLog> getTransactionArrayList() {
        return transactionArrayList;
    }

    public void setTransactionArrayList(List<TransactionsLog> transactionArrayList) {
        this.transactionArrayList = transactionArrayList;
    }

    public Account(int account_number , double current_balance) {
        this.current_balance = current_balance;
        this.account_number = account_number;
    }

    public int getId_account() {
        return id_account;
    }

    public void setId_account(int id_account) {
        this.id_account = id_account;
    }

    public int getAccount_number() {
        return account_number;
    }

    public void setAccount_number(int account_number) {
        this.account_number = account_number;
    }

    public double getCurrent_balance() {
        return current_balance;
    }

    public void setCurrent_balance(double current_balance) {
        this.current_balance = current_balance;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id_account=" + id_account +
                ", account_number=" + account_number +
                ", current_balance=" + current_balance +
                ", client Name=" + client.getUsername() +
                ", client id=" + client.getId_client() +
                ", transactionArrayList=" + transactionArrayList +
                '}';
    }
}
