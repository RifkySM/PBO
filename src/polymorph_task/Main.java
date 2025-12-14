package polymorph_task;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("========================================");
        System.out.println("    ONLINE STORE DELIVERY SYSTEM");
        System.out.println("========================================\n");

        System.out.print("Enter package weight (kg)      : ");
        double weight = scanner.nextDouble();

        System.out.print("Enter delivery distance (km)   : ");
        double distance = scanner.nextDouble();

        System.out.println("\n--- Delivery Service Options ---");
        System.out.println("1. Regular  (Estimation: 3-5 days)");
        System.out.println("2. Express  (Estimation: 1-2 days)");
        System.out.println("3. Same Day (Estimation: 6-10 hours)");
        System.out.print("\nChoose service (1/2/3)         : ");
        int choice = scanner.nextInt();

        Delivery delivery;

        switch (choice) {
            case 1:
                delivery = new RegularDelivery(weight, distance);
                break;
            case 2:
                delivery = new ExpressDelivery(weight, distance);
                break;
            case 3:
                delivery = new SameDayDelivery(weight, distance);
                break;
            default:
                System.out.println("\nInvalid choice! Program terminated.");
                scanner.close();
                return;
        }

        delivery.showDetails();
        scanner.close();
    }
}
