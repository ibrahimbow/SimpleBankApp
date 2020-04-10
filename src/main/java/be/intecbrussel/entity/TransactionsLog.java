package be.intecbrussel.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "transactions")
public class TransactionsLog {
    @Id
    @GeneratedValue
    private int id_transaction;

    @Column(name = "transaction_date_time")
    private LocalDateTime transaction_date_time ;

    @Column(name = "transaction_amount")
    private double transaction_amount;

    public int getForm_TO_account_number() {
        return form_TO_account_number;
    }

    public void setForm_TO_account_number(int form_TO_account_number) {
        this.form_TO_account_number = form_TO_account_number;
    }

    @Column(name ="to_account_number")
    private int form_TO_account_number;


    @ManyToOne (cascade=CascadeType.ALL)
    private Account account;


    @ManyToOne  (cascade=CascadeType.ALL)
    private TransactionType transactionType;



    public int getId_transaction() {
        return id_transaction;
    }

    public void setId_transaction(int id_transaction) {
        this.id_transaction = id_transaction;
    }

    public LocalDateTime getTransaction_date_time() {
        return this.transaction_date_time;
    }

    public void setTransaction_date_time(LocalDateTime transaction_date_time) {
        this.transaction_date_time = transaction_date_time;
    }

    public double getTransaction_amount() {
        return transaction_amount;
    }

    public void setTransaction_amount(double transaction_amount) {
        this.transaction_amount = transaction_amount;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id_transaction=" + id_transaction +
                ", transaction_date_time=" + transaction_date_time +
                ", transaction_amount=" + transaction_amount +
                ", account=" + account.getAccount_number() +
                ", transactionType=" + transactionType.getTransactionType() +
                '}';
    }
}
