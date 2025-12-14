package polymorphism;

public class EWallet extends PaymentMethod{

    public EWallet(double amount) {
        super(amount);
    }

    public void paymentProcess() {
        System.out.println("E-Wallet Payment \t\t : $" + this.getAmount());
    }
}
