
import java.util.ArrayList;
public class mini_library {
    public static void main(String[] args) {
        Perpustakaan perpus = new Perpustakaan("Perpus Sederhana");

        perpus.tambahBuku(new Buku("111", "Pemrograman Java Dasar", "Andi"));
        perpus.tambahBuku(new Buku("222", "Struktur Data", "Budi"));
        perpus.tambahBuku(new Buku("333", "Basis Data", "Citra"));

        System.out.println("== KOLEKSI AWAL ==");
        perpus.tampilkanKoleksi();
        System.out.println();

        Anggota a1 = new Anggota("AGT01", "Rina");
        Anggota a2 = new Anggota("AGT02", "Doni");

        // Peminjaman: berhasil
        perpus.pinjamBuku(a1.id, "111");
        // Peminjaman: gagal karena tidak ada
        perpus.pinjamBuku(a1.id, "999");
        // Peminjaman: gagal karena sudah dipinjam
        perpus.pinjamBuku(a2.id, "111");

        System.out.println("\n== KOLEKSI SETELAH PEMINJAMAN ==");
        perpus.tampilkanKoleksi();

        System.out.println("\n== LOG PEMINJAMAN ==");
        perpus.tampilkanLog();
    }
}

class Buku {
    String isbn;
    String judul;
    String pengarang;
    boolean dipinjam = false;

    Buku(String isbn, String judul, String pengarang) {
        this.isbn = isbn;
        this.judul = judul;
        this.pengarang = pengarang;
    }
}

class Anggota {
    String id;
    String nama;

    Anggota(String id, String nama) {
        this.id = id;
        this.nama = nama;
    }
}

class Perpustakaan {
    String nama;
    ArrayList<Buku> koleksi = new ArrayList<>();
    ArrayList<String> logPeminjaman = new ArrayList<>(); // "ID - Judul"

    Perpustakaan(String nama) {
        this.nama = nama;
    }

    void tambahBuku(Buku b) {
        koleksi.add(b);
    }

    // bantu cari buku
    Buku cariBuku(String isbn) {
        for (Buku b : koleksi) {
            if (b.isbn.equals(isbn)) return b;
        }
        return null;
    }

    void pinjamBuku(String idAnggota, String isbn) {
        Buku b = cariBuku(isbn);
        if (b == null) {
            System.out.println("Gagal: buku tidak ditemukan.");
            return;
        }
        if (b.dipinjam) {
            System.out.println("Gagal: buku sudah dipinjam.");
            return;
        }
        b.dipinjam = true;
        logPeminjaman.add(idAnggota + " - " + b.judul);
        System.out.println("Berhasil: " + b.judul + " dipinjam oleh " + idAnggota);
    }

    void tampilkanKoleksi() {
        if (koleksi.isEmpty()) {
            System.out.println("(Koleksi kosong)");
            return;
        }
        int i = 1;
        for (Buku b : koleksi) {
            System.out.printf("%d) %s - \"%s\" (%s) | %s%n",
                    i++, b.isbn, b.judul, b.pengarang, b.dipinjam ? "Dipinjam" : "Tersedia");
        }
    }

    void tampilkanLog() {
        if (logPeminjaman.isEmpty()) {
            System.out.println("(Belum ada peminjaman)");
            return;
        }
        int i = 1;
        for (String l : logPeminjaman) {
            System.out.println(i++ + ") " + l);
        }
    }
}