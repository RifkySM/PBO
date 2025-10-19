public class meet_3 {
    public static void main(String[] args) {
        Mahasiswa mhs_1 = new Mahasiswa("20230101", "Budi Santoso", "Teknik Informatika");
        mhs_1.tampilkanInfo();
    }
}

//level 1
class Mahasiswa{
    public String nim, nama, jurusan;

    public Mahasiswa(String nim, String nama, String jurusan) {
        this.nim = nim;
        this.nama = nama;
        this.jurusan = jurusan;
    }

    public void tampilkanInfo(){
        System.out.println("Nama: " + this.nama);
        System.out.println("NIM: " + this.nim);
        System.out.println("Jurusan: " + this.jurusan);
    }
}

//level 2