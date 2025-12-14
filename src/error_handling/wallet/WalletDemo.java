package error_handling.wallet;

public class WalletDemo {
    static void main() {

        Wallet wallet = new Wallet();

        try {
            wallet.setBalance(-500);
            System.out.println("Current balance: " + wallet.getBalance());
        } catch (InvalidBalanceException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("final");
        }

        System.out.println("Program finished");
    }
}