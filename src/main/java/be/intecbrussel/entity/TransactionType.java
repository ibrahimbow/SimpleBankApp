package be.intecbrussel.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "transaction_types")
public class TransactionType {
    @Id
    @Column(name = "id_transactionType" , updatable = false, nullable = false)
    private int id_transactionType;
    @Column(name = "transaction_type")
    private String transactionType;


    @OneToMany(mappedBy="transactionType",cascade=CascadeType.ALL)
    private List<Transaction> transactionArrayList = new ArrayList<>();

    public List<Transaction> getTransactionArrayList() {
        return transactionArrayList;
    }

    public void setTransactionArrayList(List<Transaction> transactionArrayList) {
        this.transactionArrayList = transactionArrayList;
    }

    public int getId_transactionType() {
        return id_transactionType;
    }

    public void setId_transactionType(int id_transactionType) {
        this.id_transactionType = id_transactionType;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    @Override
    public String toString() {
        return "TransactionType{" +
                "id_transactionType=" + id_transactionType +
                ", transactionType='" + transactionType + '\'' +
                ", transactionArrayList=" + transactionArrayList +
                '}';
    }
}
