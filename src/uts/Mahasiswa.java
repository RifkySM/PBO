package uts;

public class Mahasiswa extends User {
    private String nim;
    private String jurusan;
    private double ipk;

    public Mahasiswa(String nama, String email, String alamat, String noHP, String nim, String jurusan, double ipk) {
        super(nama, email, alamat, noHP);
        setNim(nim);
        setJurusan(jurusan);
        setIpk(ipk);
    }

    public void setNim(String nim) {
        if (nim != null && nim.startsWith("M") && nim.substring(1).matches("\\d+")) {
            this.nim = nim;
        } else {
            System.out.println("Error: NIM harus diawali huruf besar 'M' diikuti angka.");
        }
    }

    public String getNim() { return nim; }

    public void setJurusan(String jurusan) {
        if (jurusan != null && !jurusan.trim().isEmpty()) {
            this.jurusan = jurusan;
        } else {
            System.out.println("Error: Jurusan tidak boleh kosong.");
        }
    }

    public String getJurusan() { return jurusan; }

    public void setIpk(double ipk) {
        if (ipk >= 0.00 && ipk <= 4.00) {
            this.ipk = ipk;
        } else {
            System.out.println("Error: IPK harus antara 0.00 - 4.00.");
        }
    }

    public double getIpk() { return ipk; }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("NIM    : " + nim);
        System.out.println("Jurusan: " + jurusan);
        System.out.println("IPK    : " + ipk);
    }

    public void ambilMataKuliah(MataKuliah mk) {
        if (mk != null) {
            System.out.println("Mahasiswa " + getNama() + " mengambil mata kuliah " + mk.getNamaMatkul());
        }
    }
}
