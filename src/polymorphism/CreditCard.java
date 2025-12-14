package polymorphism;

public class CreditCard extends PaymentMethod {

    public CreditCard(double amount) {
        super(amount);
    }

    @Override
    public void paymentProcess() {
        System.out.println("Credit Card Payment \t : $" + this.getAmount());
    }
}
