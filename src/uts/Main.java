package uts;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Tes Validasi Error ===");
        Mahasiswa mhsError = new Mahasiswa("Budi", "budigmail.com", "Bandung", "081", "12345", "TI", 5.0);
        System.out.println("--------------------------");

        System.out.println("\n=== Data Valid ===");
        MataKuliah mk1 = new MataKuliah("MK001", "PBO", 3);
        Mahasiswa mhs1 = new Mahasiswa("Rifky Saputra Maylandra", "rifky@utb.ac.id", "Jl. Cilengkrang", "081234567890", "24552011091", "TIF", 3.9);
        Dosen dosen1 = new Dosen("Siti Maryam, S.Kom", "siti@utb.ac.id", "Jl. Pahlawan", "089876543210", "D98765", "PBO");

        System.out.println("\n[Info Mata Kuliah]");
        mk1.tampilkanInfo();

        System.out.println("\n[Info Mahasiswa]");
        mhs1.tampilkanInfo();

        System.out.println("\n[Info Dosen]");
        dosen1.tampilkanInfo();

        System.out.println("\n[Interaksi]");
        mhs1.ambilMataKuliah(mk1);
        dosen1.mengajar();
    }
}
