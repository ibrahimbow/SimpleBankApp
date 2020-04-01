package be.intecbrussel.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "accounts")
public class Account {
    @Id

    //@Column(name = "id_account" , updatable = false, nullable = false)
    @GeneratedValue
    private int id_account;

    @Column(name = "account_number")
    private int account_number;

    @Column(name = "current_balance")
    private double current_balance;


    @ManyToOne (cascade=CascadeType.ALL)
    private Client client ;



    @OneToMany(mappedBy="account",cascade=CascadeType.ALL)
    private List<Transaction> transactionArrayList = new ArrayList<>();

    public List<Transaction> getTransactionArrayList() {
        return transactionArrayList;
    }

    public void setTransactionArrayList(List<Transaction> transactionArrayList) {
        this.transactionArrayList = transactionArrayList;
    }

    public Account() {
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
