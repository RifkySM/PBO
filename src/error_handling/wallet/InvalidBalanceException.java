package error_handling.wallet;

public class InvalidBalanceException extends Exception {
    public InvalidBalanceException(String message) {
        super(message);
    }
}
