package error_handling;

import java.util.Scanner;

public class Case1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Masukkan a: ");
        int a = sc.nextInt();

        System.out.print("Masukkan b: ");
        int b = sc.nextInt();

        try {
            if (b == 0) {
                throw new ArithmeticException();
            }
            int hasil = a / b;
            System.out.println("Hasil: " + hasil);
        } catch (ArithmeticException e) {
            System.out.println("Tidak boleh membagi dengan nol");
        }

        sc.close();
    }
}
