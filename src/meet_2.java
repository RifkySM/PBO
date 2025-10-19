public class meet_2 {
    public static void main(String[] args){
        MataKuliah mk_1 = new MataKuliah("PBO", "matkul-1", 3);
        System.out.println("getter kode matkul "+mk_1.getKode());
        System.out.println("getter sks matkul "+mk_1.getSks());
        mk_1.showInfo();
    }
}

class MataKuliah{
    // attributes
    private String nama;
    private String kode;
    private int sks;

    //constructor
    public MataKuliah(String nama, String kode,  int sks){
        this.nama = nama;
        this.kode = kode;
        this.sks = sks;
    }

    //getter setter
    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public String getKode() {
        return kode;
    }
    public void setKode(String kode) {
        this.kode = kode;
    }
    public int getSks() {
        return sks;
    }
    public void setSks(int sks) {
        this.sks = sks;
    }

    // method
    public void showInfo(){
        System.out.println("ingfo matkul");
        System.out.println("Nama: " + this.nama);
        System.out.println("Kode: " + this.kode);
        System.out.println("Sks: " + this.sks);
    }
}