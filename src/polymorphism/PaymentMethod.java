package polymorphism;

public abstract class PaymentMethod {
    private double amount = 0;

    public PaymentMethod(double amount) {
        this.setAmount(amount);
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public abstract void paymentProcess();
}
