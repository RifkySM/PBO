package file_handling;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Latihan 5: Studi Kasus - Sistem Perpustakaan Sederhana
 * 
 * Fitur:
 * - Menyimpan data buku (judul, pengarang, tahun) ke file
 * - Membaca data buku dari file
 * - Mencari buku berdasarkan judul
 * - Menggunakan enum untuk status buku (TERSEDIA, DIPINJAM)
 */

// ===== ENUM STATUS BUKU =====
enum StatusBuku {
    TERSEDIA("Tersedia di rak", "✓"),
    DIPINJAM("Sedang dipinjam", "✗");
    
    private String deskripsi;
    private String simbol;
    
    StatusBuku(String deskripsi, String simbol) {
        this.deskripsi = deskripsi;
        this.simbol = simbol;
    }
    
    public String getDeskripsi() { return deskripsi; }
    public String getSimbol() { return simbol; }
}

// ===== CLASS BUKU =====
class Buku {
    private String id;
    private String judul;
    private String pengarang;
    private Integer tahun;  // Wrapper class
    private StatusBuku status;
    
    // Constructor
    public Buku(String id, String judul, String pengarang, Integer tahun, StatusBuku status) {
        this.id = id;
        this.judul = judul;
        this.pengarang = pengarang;
        this.tahun = tahun;
        this.status = status;
    }
    
    // Getter dan Setter
    public String getId() { return id; }
    public String getJudul() { return judul; }
    public String getPengarang() { return pengarang; }
    public Integer getTahun() { return tahun; }
    public StatusBuku getStatus() { return status; }
    
    public void setStatus(StatusBuku status) {
        this.status = status;
    }
    
    // Convert ke format CSV untuk disimpan ke file
    public String toCSV() {
        return id + "," + judul + "," + pengarang + "," + tahun + "," + status.name();
    }
    
    // Parse dari format CSV
    public static Buku fromCSV(String csv) {
        String[] data = csv.split(",");
        return new Buku(
            data[0],                          // id
            data[1],                          // judul
            data[2],                          // pengarang
            Integer.parseInt(data[3]),        // tahun (wrapper)
            StatusBuku.valueOf(data[4])       // status (enum)
        );
    }
    
    @Override
    public String toString() {
        return "┌─────────────────────────────────────────\n" +
               "│ ID       : " + id + "\n" +
               "│ Judul    : " + judul + "\n" +
               "│ Pengarang: " + pengarang + "\n" +
               "│ Tahun    : " + tahun + "\n" +
               "│ Status   : " + status.getSimbol() + " " + status.getDeskripsi() + "\n" +
               "└─────────────────────────────────────────";
    }
    
    // Format singkat untuk daftar
    public String toShortString() {
        return String.format("[%s] %s - %s (%d) %s %s", 
            id, judul, pengarang, tahun, status.getSimbol(), status);
    }
}

// ===== CLASS FILE MANAGER =====
class PerpustakaanFileManager {
    private String namaFile;
    
    public PerpustakaanFileManager(String namaFile) {
        this.namaFile = namaFile;
    }
    
    // Simpan semua buku ke file
    public void simpanBuku(ArrayList<Buku> daftarBuku) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(namaFile))) {
            for(Buku buku : daftarBuku) {
                writer.write(buku.toCSV());
                writer.newLine();
            }
            System.out.println("✓ Data berhasil disimpan ke " + namaFile);
        } catch(IOException e) {
            System.out.println("✗ Error menyimpan: " + e.getMessage());
        }
    }
    
    // Baca semua buku dari file
    public ArrayList<Buku> bacaBuku() {
        ArrayList<Buku> daftarBuku = new ArrayList<>();
        
        try(BufferedReader reader = new BufferedReader(new FileReader(namaFile))) {
            String baris;
            while((baris = reader.readLine()) != null) {
                if(!baris.trim().isEmpty()) {
                    try {
                        Buku buku = Buku.fromCSV(baris);
                        daftarBuku.add(buku);
                    } catch(Exception e) {
                        System.out.println("Warning: Baris tidak valid, dilewati.");
                    }
                }
            }
            System.out.println("✓ Berhasil membaca " + daftarBuku.size() + " buku");
        } catch(FileNotFoundException e) {
            System.out.println("! File belum ada, akan dibuat saat menyimpan");
        } catch(IOException e) {
            System.out.println("✗ Error membaca: " + e.getMessage());
        }
        
        return daftarBuku;
    }
}

// ===== CLASS UTAMA PERPUSTAKAAN =====
class Perpustakaan {
    private ArrayList<Buku> daftarBuku;
    private PerpustakaanFileManager fileManager;
    private int counterID;
    
    public Perpustakaan(String namaFile) {
        this.fileManager = new PerpustakaanFileManager(namaFile);
        this.daftarBuku = fileManager.bacaBuku();
        this.counterID = daftarBuku.size() + 1;
    }
    
    // Generate ID baru
    private String generateID() {
        return String.format("BK%03d", counterID++);
    }
    
    // Tambah buku baru
    public void tambahBuku(String judul, String pengarang, Integer tahun) {
        String id = generateID();
        Buku bukuBaru = new Buku(id, judul, pengarang, tahun, StatusBuku.TERSEDIA);
        daftarBuku.add(bukuBaru);
        System.out.println("✓ Buku berhasil ditambahkan dengan ID: " + id);
    }
    
    // Tampilkan semua buku
    public void tampilkanSemuaBuku() {
        if(daftarBuku.isEmpty()) {
            System.out.println("Belum ada buku di perpustakaan.");
            return;
        }
        
        System.out.println("\n╔═══════════════════════════════════════════════════════════════╗");
        System.out.println("║                    DAFTAR BUKU PERPUSTAKAAN                   ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════╝\n");
        
        for(Buku buku : daftarBuku) {
            System.out.println(buku.toShortString());
        }
        
        // Statistik
        int tersedia = 0, dipinjam = 0;
        for(Buku buku : daftarBuku) {
            if(buku.getStatus() == StatusBuku.TERSEDIA) tersedia++;
            else dipinjam++;
        }
        
        System.out.println("\n─────────────────────────────────────────");
        System.out.println("Total: " + daftarBuku.size() + " buku | " +
                          "Tersedia: " + tersedia + " | Dipinjam: " + dipinjam);
    }
    
    // Cari buku berdasarkan judul
    public ArrayList<Buku> cariBuku(String keyword) {
        ArrayList<Buku> hasil = new ArrayList<>();
        String keywordLower = keyword.toLowerCase();
        
        for(Buku buku : daftarBuku) {
            if(buku.getJudul().toLowerCase().contains(keywordLower)) {
                hasil.add(buku);
            }
        }
        
        return hasil;
    }
    
    // Cari buku berdasarkan ID
    public Buku cariBukuByID(String id) {
        for(Buku buku : daftarBuku) {
            if(buku.getId().equalsIgnoreCase(id)) {
                return buku;
            }
        }
        return null;
    }
    
    // Pinjam buku
    public boolean pinjamBuku(String id) {
        Buku buku = cariBukuByID(id);
        
        if(buku == null) {
            System.out.println("✗ Buku dengan ID " + id + " tidak ditemukan!");
            return false;
        }
        
        if(buku.getStatus() == StatusBuku.DIPINJAM) {
            System.out.println("✗ Buku '" + buku.getJudul() + "' sedang dipinjam!");
            return false;
        }
        
        buku.setStatus(StatusBuku.DIPINJAM);
        System.out.println("✓ Buku '" + buku.getJudul() + "' berhasil dipinjam!");
        return true;
    }
    
    // Kembalikan buku
    public boolean kembalikanBuku(String id) {
        Buku buku = cariBukuByID(id);
        
        if(buku == null) {
            System.out.println("✗ Buku dengan ID " + id + " tidak ditemukan!");
            return false;
        }
        
        if(buku.getStatus() == StatusBuku.TERSEDIA) {
            System.out.println("✗ Buku '" + buku.getJudul() + "' tidak sedang dipinjam!");
            return false;
        }
        
        buku.setStatus(StatusBuku.TERSEDIA);
        System.out.println("✓ Buku '" + buku.getJudul() + "' berhasil dikembalikan!");
        return true;
    }
    
    // Simpan ke file
    public void simpan() {
        fileManager.simpanBuku(daftarBuku);
    }
    
    // Tambah data contoh
    public void tambahDataContoh() {
        if(!daftarBuku.isEmpty()) {
            System.out.println("Data sudah ada. Hapus file untuk reset.");
            return;
        }
        
        tambahBuku("Laskar Pelangi", "Andrea Hirata", 2005);
        tambahBuku("Bumi Manusia", "Pramoedya Ananta Toer", 1980);
        tambahBuku("Sang Pemimpi", "Andrea Hirata", 2006);
        tambahBuku("Negeri 5 Menara", "Ahmad Fuadi", 2009);
        tambahBuku("Ayat-Ayat Cinta", "Habiburrahman El Shirazy", 2004);
        tambahBuku("Perahu Kertas", "Dee Lestari", 2009);
        
        System.out.println("✓ Data contoh berhasil ditambahkan!");
    }
}

// ===== MAIN CLASS =====
public class Latihan5_SistemPerpustakaan {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Perpustakaan perpus = new Perpustakaan("perpustakaan.txt");
        
        System.out.println("\n╔═══════════════════════════════════════╗");
        System.out.println("║   SISTEM PERPUSTAKAAN SEDERHANA       ║");
        System.out.println("╚═══════════════════════════════════════╝");
        
        while(true) {
            System.out.println("\n┌─── MENU ───────────────────────┐");
            System.out.println("│ 1. Tambah Buku                 │");
            System.out.println("│ 2. Tampilkan Semua Buku        │");
            System.out.println("│ 3. Cari Buku                   │");
            System.out.println("│ 4. Pinjam Buku                 │");
            System.out.println("│ 5. Kembalikan Buku             │");
            System.out.println("│ 6. Tambah Data Contoh          │");
            System.out.println("│ 7. Simpan & Keluar             │");
            System.out.println("└────────────────────────────────┘");
            System.out.print("Pilih menu: ");
            
            int pilihan;
            try {
                pilihan = Integer.parseInt(input.nextLine());
            } catch(NumberFormatException e) {
                System.out.println("Input tidak valid!");
                continue;
            }
            
            switch(pilihan) {
                case 1:
                    menuTambahBuku(input, perpus);
                    break;
                    
                case 2:
                    perpus.tampilkanSemuaBuku();
                    break;
                    
                case 3:
                    menuCariBuku(input, perpus);
                    break;
                    
                case 4:
                    menuPinjamBuku(input, perpus);
                    break;
                    
                case 5:
                    menuKembalikanBuku(input, perpus);
                    break;
                    
                case 6:
                    perpus.tambahDataContoh();
                    break;
                    
                case 7:
                    perpus.simpan();
                    System.out.println("\nTerima kasih telah menggunakan Sistem Perpustakaan!");
                    input.close();
                    return;
                    
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }
    
    private static void menuTambahBuku(Scanner input, Perpustakaan perpus) {
        System.out.println("\n--- Tambah Buku Baru ---");
        
        System.out.print("Judul    : ");
        String judul = input.nextLine();
        
        System.out.print("Pengarang: ");
        String pengarang = input.nextLine();
        
        System.out.print("Tahun    : ");
        Integer tahun;
        try {
            tahun = Integer.parseInt(input.nextLine());
        } catch(NumberFormatException e) {
            System.out.println("Tahun tidak valid!");
            return;
        }
        
        perpus.tambahBuku(judul, pengarang, tahun);
    }
    
    private static void menuCariBuku(Scanner input, Perpustakaan perpus) {
        System.out.print("\nMasukkan kata kunci judul: ");
        String keyword = input.nextLine();
        
        ArrayList<Buku> hasil = perpus.cariBuku(keyword);
        
        if(hasil.isEmpty()) {
            System.out.println("✗ Tidak ada buku dengan judul '" + keyword + "'");
        } else {
            System.out.println("\n✓ Ditemukan " + hasil.size() + " buku:\n");
            for(Buku buku : hasil) {
                System.out.println(buku);
            }
        }
    }
    
    private static void menuPinjamBuku(Scanner input, Perpustakaan perpus) {
        System.out.print("\nMasukkan ID buku yang akan dipinjam: ");
        String id = input.nextLine();
        perpus.pinjamBuku(id);
    }
    
    private static void menuKembalikanBuku(Scanner input, Perpustakaan perpus) {
        System.out.print("\nMasukkan ID buku yang akan dikembalikan: ");
        String id = input.nextLine();
        perpus.kembalikanBuku(id);
    }
}
