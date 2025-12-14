package file_handling;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Latihan 4: File Writing - Log Aktivitas
 * Program untuk mencatat log aktivitas program ke file "log.txt"
 * Format: [Waktu] - [Level] - Pesan
 */

// Enum untuk level log
enum LogLevel {
    INFO("INFO"),
    WARNING("WARNING"),
    ERROR("ERROR"),
    DEBUG("DEBUG");
    
    private String label;
    
    LogLevel(String label) {
        this.label = label;
    }
    
    public String getLabel() {
        return label;
    }
}

// Class Logger untuk mengelola log
class Logger {
    private String namaFile;
    private DateTimeFormatter formatter;
    
    public Logger(String namaFile) {
        this.namaFile = namaFile;
        this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }
    
    /**
     * Method utama untuk menulis log
     */
    public void log(LogLevel level, String pesan) {
        String waktu = LocalDateTime.now().format(formatter);
        String logEntry = "[" + waktu + "] - [" + level.getLabel() + "] - " + pesan;
        
        // Tulis ke file (append mode)
        try(BufferedWriter writer = new BufferedWriter(
                new FileWriter(namaFile, true))) {
            
            writer.write(logEntry);
            writer.newLine();
            
            // Tampilkan juga di console
            System.out.println("✓ Log dicatat: " + logEntry);
            
        } catch(IOException e) {
            System.out.println("Error menulis log: " + e.getMessage());
        }
    }
    
    // Method shortcut untuk setiap level
    public void info(String pesan) {
        log(LogLevel.INFO, pesan);
    }
    
    public void warning(String pesan) {
        log(LogLevel.WARNING, pesan);
    }
    
    public void error(String pesan) {
        log(LogLevel.ERROR, pesan);
    }
    
    public void debug(String pesan) {
        log(LogLevel.DEBUG, pesan);
    }
    
    /**
     * Membaca dan menampilkan semua log
     */
    public void tampilkanSemuaLog() {
        System.out.println("\n=== ISI FILE LOG ===");
        
        try(BufferedReader reader = new BufferedReader(new FileReader(namaFile))) {
            String baris;
            int nomor = 1;
            
            while((baris = reader.readLine()) != null) {
                System.out.println(nomor + ". " + baris);
                nomor++;
            }
            
            if(nomor == 1) {
                System.out.println("(File log kosong)");
            }
            
        } catch(IOException e) {
            System.out.println("File log belum ada atau tidak bisa dibaca.");
        }
    }
    
    /**
     * Menghapus semua log (reset file)
     */
    public void hapusSemuaLog() {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(namaFile))) {
            // File dikosongkan (overwrite dengan kosong)
            System.out.println("✓ Semua log berhasil dihapus!");
        } catch(IOException e) {
            System.out.println("Error menghapus log: " + e.getMessage());
        }
    }
}

// Main class
public class Latihan4_FileWriting {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Logger logger = new Logger("log.txt");
        
        // Log awal program
        logger.info("Program dimulai");
        
        System.out.println("\n=== SISTEM PENCATATAN LOG ===");
        
        while(true) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Tambah Log INFO");
            System.out.println("2. Tambah Log WARNING");
            System.out.println("3. Tambah Log ERROR");
            System.out.println("4. Tambah Log DEBUG");
            System.out.println("5. Tampilkan Semua Log");
            System.out.println("6. Hapus Semua Log");
            System.out.println("7. Simulasi Aktivitas");
            System.out.println("0. Keluar");
            System.out.print("Pilih: ");
            
            int pilihan;
            try {
                pilihan = Integer.parseInt(input.nextLine());
            } catch(NumberFormatException e) {
                logger.warning("Input menu tidak valid");
                continue;
            }
            
            switch(pilihan) {
                case 1:
                    System.out.print("Masukkan pesan INFO: ");
                    String pesanInfo = input.nextLine();
                    logger.info(pesanInfo);
                    break;
                    
                case 2:
                    System.out.print("Masukkan pesan WARNING: ");
                    String pesanWarning = input.nextLine();
                    logger.warning(pesanWarning);
                    break;
                    
                case 3:
                    System.out.print("Masukkan pesan ERROR: ");
                    String pesanError = input.nextLine();
                    logger.error(pesanError);
                    break;
                    
                case 4:
                    System.out.print("Masukkan pesan DEBUG: ");
                    String pesanDebug = input.nextLine();
                    logger.debug(pesanDebug);
                    break;
                    
                case 5:
                    logger.tampilkanSemuaLog();
                    break;
                    
                case 6:
                    System.out.print("Yakin hapus semua log? (y/n): ");
                    String konfirmasi = input.nextLine();
                    if(konfirmasi.equalsIgnoreCase("y")) {
                        logger.hapusSemuaLog();
                    }
                    break;
                    
                case 7:
                    simulasiAktivitas(logger);
                    break;
                    
                case 0:
                    logger.info("Program selesai");
                    System.out.println("\nProgram selesai. Sampai jumpa!");
                    input.close();
                    return;
                    
                default:
                    logger.warning("Menu tidak valid: " + pilihan);
            }
        }
    }
    
    /**
     * Simulasi berbagai aktivitas program
     */
    private static void simulasiAktivitas(Logger logger) {
        System.out.println("\n>> Menjalankan simulasi aktivitas...\n");
        
        logger.info("User melakukan login");
        sleep(500);
        
        logger.debug("Memuat data dari database");
        sleep(500);
        
        logger.info("Data berhasil dimuat: 150 records");
        sleep(500);
        
        logger.warning("Memory usage tinggi: 85%");
        sleep(500);
        
        logger.info("User mengakses halaman dashboard");
        sleep(500);
        
        logger.error("Gagal mengirim email notifikasi");
        sleep(500);
        
        logger.info("User melakukan logout");
        
        System.out.println("\n>> Simulasi selesai!");
    }
    
    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch(InterruptedException e) {
            // Ignore
        }
    }
}
