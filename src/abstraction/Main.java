package abstraction;

interface Payment {
    boolean processPayment(double amount);
}

interface Shipping {
    void ship(String address);
}

interface Notification {
    void sendNotification(String message);
}

class OrderService implements Payment, Shipping, Notification {

    @Override
    public boolean processPayment(double amount) {
        System.out.println("Payment successful: Rp " + String.format("%.2f", amount));
        return true;
    }

    @Override
    public void ship(String address) {
        System.out.println("Item shipped to: " + address);
    }

    @Override
    public void sendNotification(String message) {
        System.out.println("Notification: " + message);
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Order Processing System ===\n");

        OrderService order = new OrderService();
        order.processPayment(150000);
        order.ship("Jl. Merdeka No. 10, Bandung");
        order.sendNotification("Your order is being processed!");

        System.out.println("\n=== Order Complete ===");
    }
}
