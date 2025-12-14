package file_handling;

/**
 * Latihan 2: Enum Type
 * Enum Level dengan nilai BEGINNER, INTERMEDIATE, EXPERT
 * Setiap level memiliki poin minimum (0, 100, 500)
 */

// Definisi Enum Level
enum Level {
    BEGINNER("Pemula", 0),
    INTERMEDIATE("Menengah", 100),
    EXPERT("Ahli", 500);
    
    // Field
    private String deskripsi;
    private int poinMinimum;
    
    // Constructor (harus private)
    Level(String deskripsi, int poinMinimum) {
        this.deskripsi = deskripsi;
        this.poinMinimum = poinMinimum;
    }
    
    // Getter
    public String getDeskripsi() {
        return deskripsi;
    }
    
    public int getPoinMinimum() {
        return poinMinimum;
    }
    
    // Method untuk mendapatkan level berdasarkan poin
    public static Level getLevelByPoin(int poin) {
        Level hasil = BEGINNER; // Default
        
        for(Level level : Level.values()) {
            if(poin >= level.getPoinMinimum()) {
                hasil = level;
            }
        }
        return hasil;
    }
    
    // Method untuk cek apakah poin cukup untuk naik level
    public Level getNextLevel() {
        switch(this) {
            case BEGINNER:
                return INTERMEDIATE;
            case INTERMEDIATE:
                return EXPERT;
            case EXPERT:
                return EXPERT; // Sudah level tertinggi
            default:
                return BEGINNER;
        }
    }
    
    public int getPoinUntukNaikLevel() {
        Level nextLevel = getNextLevel();
        if(this == nextLevel) {
            return 0; // Sudah level tertinggi
        }
        return nextLevel.getPoinMinimum();
    }
}

// Class Player untuk demonstrasi
class Player {
    private String nama;
    private int poin;
    private Level level;
    
    public Player(String nama) {
        this.nama = nama;
        this.poin = 0;
        this.level = Level.BEGINNER;
    }
    
    public void tambahPoin(int jumlah) {
        this.poin += jumlah;
        updateLevel();
    }
    
    private void updateLevel() {
        Level levelBaru = Level.getLevelByPoin(this.poin);
        if(levelBaru != this.level) {
            System.out.println("🎉 " + nama + " naik level ke " + levelBaru + "!");
            this.level = levelBaru;
        }
    }
    
    public void tampilkanInfo() {
        System.out.println("Nama: " + nama);
        System.out.println("Poin: " + poin);
        System.out.println("Level: " + level + " (" + level.getDeskripsi() + ")");
        
        if(level != Level.EXPERT) {
            int poinDibutuhkan = level.getPoinUntukNaikLevel() - poin;
            System.out.println("Poin untuk naik level: " + poinDibutuhkan);
        } else {
            System.out.println("Status: Level Maksimal! 🏆");
        }
    }
    
    // Getter
    public String getNama() { return nama; }
    public int getPoin() { return poin; }
    public Level getLevel() { return level; }
}

// Main class
public class Latihan2_EnumLevel {
    public static void main(String[] args) {
        System.out.println("=== DEMONSTRASI ENUM LEVEL ===\n");
        
        // Tampilkan semua level
        System.out.println("--- Daftar Semua Level ---");
        for(Level level : Level.values()) {
            System.out.println(level + " - " + level.getDeskripsi() + 
                             " (Min: " + level.getPoinMinimum() + " poin)");
        }
        
        // Demonstrasi dengan Player
        System.out.println("\n--- Simulasi Player ---");
        Player player = new Player("Budi");
        
        System.out.println("\nKondisi awal:");
        player.tampilkanInfo();
        
        System.out.println("\n>> Menambah 50 poin...");
        player.tambahPoin(50);
        player.tampilkanInfo();
        
        System.out.println("\n>> Menambah 60 poin...");
        player.tambahPoin(60);
        player.tampilkanInfo();
        
        System.out.println("\n>> Menambah 400 poin...");
        player.tambahPoin(400);
        player.tampilkanInfo();
        
        // Demonstrasi getLevelByPoin
        System.out.println("\n--- Cek Level Berdasarkan Poin ---");
        int[] testPoin = {0, 50, 100, 250, 500, 1000};
        for(int poin : testPoin) {
            Level level = Level.getLevelByPoin(poin);
            System.out.println(poin + " poin = Level " + level);
        }
        
        // Switch dengan Enum
        System.out.println("\n--- Contoh Switch dengan Enum ---");
        Level levelAktif = Level.INTERMEDIATE;
        switch(levelAktif) {
            case BEGINNER:
                System.out.println("Selamat datang pemula! Terus berlatih!");
                break;
            case INTERMEDIATE:
                System.out.println("Bagus! Kamu sudah di level menengah!");
                break;
            case EXPERT:
                System.out.println("Hebat! Kamu sudah jadi ahli!");
                break;
        }
    }
}
