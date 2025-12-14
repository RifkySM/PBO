package file_handling;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Latihan 3: File Reading
 * Program yang membaca file "nilai.txt" berisi nama dan nilai mahasiswa
 * Format: Nama,Nilai
 * Kemudian menampilkan mahasiswa dengan nilai tertinggi
 */

// Class untuk menyimpan data mahasiswa
class NilaiMahasiswa {
    private String nama;
    private Integer nilai;
    
    public NilaiMahasiswa(String nama, Integer nilai) {
        this.nama = nama;
        this.nilai = nilai;
    }
    
    // Getter
    public String getNama() { return nama; }
    public Integer getNilai() { return nilai; }
    
    // Parse dari CSV
    public static NilaiMahasiswa fromCSV(String csv) {
        String[] data = csv.split(",");
        return new NilaiMahasiswa(
            data[0].trim(),
            Integer.parseInt(data[1].trim())
        );
    }
    
    @Override
    public String toString() {
        return nama + " - Nilai: " + nilai;
    }
}

public class Latihan3_FileReading {
    private static final String NAMA_FILE = "nilai.txt";
    
    public static void main(String[] args) {
        System.out.println("=== SISTEM PENCARI NILAI TERTINGGI ===\n");
        
        // Buat file contoh terlebih dahulu
        buatFileContoh();
        
        // Baca data dari file
        ArrayList<NilaiMahasiswa> daftarMahasiswa = bacaDataMahasiswa();
        
        if(daftarMahasiswa.isEmpty()) {
            System.out.println("Tidak ada data mahasiswa!");
            return;
        }
        
        // Tampilkan semua data
        System.out.println("--- Daftar Semua Mahasiswa ---");
        for(NilaiMahasiswa mhs : daftarMahasiswa) {
            System.out.println("• " + mhs);
        }
        
        // Cari mahasiswa dengan nilai tertinggi
        NilaiMahasiswa terbaik = cariNilaiTertinggi(daftarMahasiswa);
        
        System.out.println("\n========================================");
        System.out.println("🏆 MAHASISWA DENGAN NILAI TERTINGGI 🏆");
        System.out.println("========================================");
        System.out.println("Nama  : " + terbaik.getNama());
        System.out.println("Nilai : " + terbaik.getNilai());
        System.out.println("========================================");
        
        // Tampilkan statistik
        tampilkanStatistik(daftarMahasiswa);
    }
    
    /**
     * Membuat file contoh nilai.txt
     */
    private static void buatFileContoh() {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(NAMA_FILE))) {
            writer.write("Budi,85"); writer.newLine();
            writer.write("Ani,92"); writer.newLine();
            writer.write("Citra,78"); writer.newLine();
            writer.write("Dedi,88"); writer.newLine();
            writer.write("Eka,95"); writer.newLine();
            writer.write("Fani,80"); writer.newLine();
            writer.write("Gita,92"); writer.newLine();
            
            System.out.println("✓ File contoh 'nilai.txt' berhasil dibuat\n");
        } catch(IOException e) {
            System.out.println("Error membuat file: " + e.getMessage());
        }
    }
    
    /**
     * Membaca data mahasiswa dari file
     */
    private static ArrayList<NilaiMahasiswa> bacaDataMahasiswa() {
        ArrayList<NilaiMahasiswa> daftar = new ArrayList<>();
        
        try(BufferedReader reader = new BufferedReader(new FileReader(NAMA_FILE))) {
            String baris;
            int nomorBaris = 0;
            
            while((baris = reader.readLine()) != null) {
                nomorBaris++;
                try {
                    // Skip baris kosong
                    if(baris.trim().isEmpty()) continue;
                    
                    NilaiMahasiswa mhs = NilaiMahasiswa.fromCSV(baris);
                    daftar.add(mhs);
                } catch(Exception e) {
                    System.out.println("Warning: Baris " + nomorBaris + 
                                     " format salah, dilewati.");
                }
            }
            
            System.out.println("✓ Berhasil membaca " + daftar.size() + " data mahasiswa\n");
            
        } catch(IOException e) {
            System.out.println("Error membaca file: " + e.getMessage());
        }
        
        return daftar;
    }
    
    /**
     * Mencari mahasiswa dengan nilai tertinggi
     */
    private static NilaiMahasiswa cariNilaiTertinggi(ArrayList<NilaiMahasiswa> daftar) {
        NilaiMahasiswa terbaik = daftar.get(0);
        
        for(NilaiMahasiswa mhs : daftar) {
            if(mhs.getNilai() > terbaik.getNilai()) {
                terbaik = mhs;
            }
        }
        
        return terbaik;
    }
    
    /**
     * Menampilkan statistik nilai
     */
    private static void tampilkanStatistik(ArrayList<NilaiMahasiswa> daftar) {
        int total = 0;
        int min = daftar.get(0).getNilai();
        int max = daftar.get(0).getNilai();
        
        for(NilaiMahasiswa mhs : daftar) {
            int nilai = mhs.getNilai();
            total += nilai;
            if(nilai < min) min = nilai;
            if(nilai > max) max = nilai;
        }
        
        double rataRata = (double) total / daftar.size();
        
        System.out.println("\n--- Statistik Nilai ---");
        System.out.println("Jumlah Mahasiswa : " + daftar.size());
        System.out.println("Nilai Tertinggi  : " + max);
        System.out.println("Nilai Terendah   : " + min);
        System.out.printf("Rata-rata        : %.2f%n", rataRata);
        
        // Hitung yang lulus (nilai >= 75)
        int lulus = 0;
        for(NilaiMahasiswa mhs : daftar) {
            if(mhs.getNilai() >= 75) lulus++;
        }
        System.out.println("Jumlah Lulus     : " + lulus + " mahasiswa");
    }
}
