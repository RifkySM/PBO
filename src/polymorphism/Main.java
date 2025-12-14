package polymorphism;

public class Main {
    public static void main(String[] args) {
        Cash cashPayment                =  new Cash(100);
        EWallet eWalletPayment          = new EWallet(200);
        CreditCard creditCardPayment    = new CreditCard(300);
        Qris qrisPayment                = new Qris(400);

        System.out.println("Payment Process.... ");
        cashPayment.paymentProcess();
        eWalletPayment.paymentProcess();
        creditCardPayment.paymentProcess();
        qrisPayment.paymentProcess();
    }
}
