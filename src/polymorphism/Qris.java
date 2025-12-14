package polymorphism;

public class Qris extends PaymentMethod {
    public Qris(double amount) {
        super(amount);
    }

    @Override
    public void paymentProcess() {
        System.out.println("Cash Payment \t\t\t : $" + this.getAmount());
    }
}
