package error_handling;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Case2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Masukkan umur: ");

        try {
            int umur = sc.nextInt();
            System.out.println("Umur kamu: " + umur);
        } catch (InputMismatchException e) {
            System.out.println("Input harus angka");
        }

        sc.close();
    }
}
