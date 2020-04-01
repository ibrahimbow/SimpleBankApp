package be.intecbrussel.custom_exception;

public class BankTransactionException extends Exception {
    public BankTransactionException() {
        super();
    }

    public BankTransactionException(String message) {
        super(message);
    }
}
