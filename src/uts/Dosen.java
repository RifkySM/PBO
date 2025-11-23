package uts;

public class Dosen extends User {
    private String nidn;
    private String mataKuliahAjar;

    public Dosen(String nama, String email, String alamat, String noHP, String nidn, String mataKuliahAjar) {
        super(nama, email, alamat, noHP);
        setNidn(nidn);
        setMataKuliahAjar(mataKuliahAjar);
    }

    public void setNidn(String nidn) {
        if (nidn != null && nidn.startsWith("D") && nidn.substring(1).matches("\\d+")) {
            this.nidn = nidn;
        } else {
            System.out.println("Error: NIDN harus diawali huruf besar 'D' diikuti angka.");
        }
    }

    public String getNidn() { return nidn; }

    public void setMataKuliahAjar(String mataKuliahAjar) {
        if (mataKuliahAjar != null && !mataKuliahAjar.trim().isEmpty()) {
            this.mataKuliahAjar = mataKuliahAjar;
        } else {
            System.out.println("Error: Mata Kuliah Ajar tidak boleh kosong.");
        }
    }

    public String getMataKuliahAjar() { return mataKuliahAjar; }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("NIDN   : " + nidn);
        System.out.println("Mengajar: " + mataKuliahAjar);
    }

    public void mengajar() {
        System.out.println("Dosen " + getNama() + " sedang mengajar mata kuliah " + mataKuliahAjar);
    }
}