package polymorphism;

public class Cash extends PaymentMethod {

    public Cash(double amount) {
        super(amount);
    }

    @Override
    public void paymentProcess() {
        System.out.println("Cash Payment \t\t\t : $" + this.getAmount());
    }
}
