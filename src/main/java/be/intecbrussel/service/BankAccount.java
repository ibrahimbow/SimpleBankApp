package be.intecbrussel.service;

public class BankAccount {

    private double balance;
    private String name;

    public BankAccount(String name) {
        this.balance = 0.0;
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }


    public void deposit(double amount) {
        balance += amount;
        System.out.println(name + " " + balance);
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println(name + " " + balance);
        } else {
            System.out.println("widthdrawal by" + name + " fails");
        }
    }

    public void transfer(double amount, BankAccount acc) {
        if (this.balance < amount) {
            System.out.println("tranfer fails");
        } else {
            this.balance -= amount;
            acc.balance += amount;
            System.out.println("account of " + this.name + "becomes " + this.balance);
        }

    }
}