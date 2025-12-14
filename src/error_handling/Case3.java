package error_handling;

import java.util.Scanner;

public class Case3 {
    static void main(String[] args) {
        String[] nama = {"Alya", "Bima", "Citra"};
        Scanner sc = new Scanner(System.in);

        System.out.print("Pilih indeks (0-2): ");
        int idx = sc.nextInt();

        try {
            if (idx < 0 || idx > 2) {
                throw new ArrayIndexOutOfBoundsException();
            }
            System.out.println("Nama: " + nama[idx]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Indeks tidak valid");
        }

        sc.close();
    }
}
