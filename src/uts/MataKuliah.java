package uts;

public class MataKuliah {
    private String kode, namaMatkul;
    private int sks;

    public MataKuliah(String kode, String namaMatkul, int sks) {
        setKode(kode);
        setNamaMatkul(namaMatkul);
        setSks(sks);
    }

    public void setKode(String kode) {
        if (kode != null && kode.startsWith("MK")) {
            this.kode = kode;
        } else {
            System.out.println("Error: Kode harus diawali 'MK' (contoh: MK001).");
        }
    }

    public String getKode() { return kode; }

    public void setNamaMatkul(String namaMatkul) {
        if (namaMatkul != null && !namaMatkul.trim().isEmpty()) {
            this.namaMatkul = namaMatkul;
        } else {
            System.out.println("Error: Nama Matkul tidak boleh kosong.");
        }
    }

    public String getNamaMatkul() { return namaMatkul; }

    public void setSks(int sks) {
        if (sks >= 1 && sks <= 4) {
            this.sks = sks;
        } else {
            System.out.println("Error: SKS harus antara 1-4.");
        }
    }

    public int getSks() { return sks; }

    public void tampilkanInfo() {
        System.out.println("Kode Matkul : " + kode);
        System.out.println("Nama Matkul : " + namaMatkul);
        System.out.println("SKS         : " + sks);
    }
}
