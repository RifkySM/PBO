package error_handling.wallet;

public class Wallet {
    private int balance;

    public void setBalance(int balance) throws InvalidBalanceException {
        if (balance < 0) {
            throw new InvalidBalanceException("Balance cannot be negative: " + balance);
        }
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }
}