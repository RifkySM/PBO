package file_handling;

import java.util.ArrayList;

/**
 * Latihan 1: Wrapper Class
 * Program untuk mengkonversi ArrayList<String> berisi angka 
 * menjadi ArrayList<Integer> menggunakan wrapper class
 */
public class Latihan1_WrapperClass {
    public static void main(String[] args) {
        // ArrayList berisi String angka
        ArrayList<String> angkaString = new ArrayList<>();
        angkaString.add("10");
        angkaString.add("25");
        angkaString.add("50");
        angkaString.add("75");
        angkaString.add("100");
        
        System.out.println("=== KONVERSI STRING KE INTEGER ===\n");
        System.out.println("ArrayList<String> awal: " + angkaString);
        
        // Konversi ke ArrayList<Integer>
        ArrayList<Integer> angkaInteger = konversiKeInteger(angkaString);
        
        System.out.println("ArrayList<Integer> hasil: " + angkaInteger);
        
        // Demonstrasi operasi matematika setelah konversi
        System.out.println("\n--- Operasi Matematika ---");
        int total = 0;
        for(Integer angka : angkaInteger) {
            total += angka; // Auto-unboxing
        }
        System.out.println("Total semua angka: " + total);
        System.out.println("Rata-rata: " + (double) total / angkaInteger.size());
        
        // Demonstrasi method utility dari wrapper class
        System.out.println("\n--- Method Utility Integer ---");
        System.out.println("Nilai maksimal dalam list: " + findMax(angkaInteger));
        System.out.println("Nilai minimal dalam list: " + findMin(angkaInteger));
        
        // Konversi ke biner
        System.out.println("\n--- Konversi ke Biner ---");
        for(Integer angka : angkaInteger) {
            System.out.println(angka + " = " + Integer.toBinaryString(angka) + " (biner)");
        }
    }
    
    /**
     * Method untuk mengkonversi ArrayList<String> ke ArrayList<Integer>
     */
    public static ArrayList<Integer> konversiKeInteger(ArrayList<String> listString) {
        ArrayList<Integer> listInteger = new ArrayList<>();
        
        for(String str : listString) {
            try {
                // Menggunakan Integer.parseInt() dari wrapper class
                Integer nilai = Integer.parseInt(str); // Autoboxing
                listInteger.add(nilai);
            } catch(NumberFormatException e) {
                System.out.println("Warning: '" + str + "' bukan angka valid, dilewati.");
            }
        }
        
        return listInteger;
    }
    
    /**
     * Mencari nilai maksimal menggunakan Integer.compare()
     */
    public static Integer findMax(ArrayList<Integer> list) {
        if(list.isEmpty()) return null;
        
        Integer max = list.get(0);
        for(Integer angka : list) {
            if(Integer.compare(angka, max) > 0) {
                max = angka;
            }
        }
        return max;
    }
    
    /**
     * Mencari nilai minimal menggunakan Integer.compare()
     */
    public static Integer findMin(ArrayList<Integer> list) {
        if(list.isEmpty()) return null;
        
        Integer min = list.get(0);
        for(Integer angka : list) {
            if(Integer.compare(angka, min) < 0) {
                min = angka;
            }
        }
        return min;
    }
}
