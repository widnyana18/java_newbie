public class Makanan{
    private int idMakanan, jumlah, harga;    
    private String nmMakanan, tambahan;
    private Jenis jenis;   

    public Makanan(int idMakanan, String nmMakanan, Jenis jenis,  int jumlah, int harga) {
        this.idMakanan = idMakanan;
        this.nmMakanan = nmMakanan;
        this.jenis = jenis;
        this.jumlah = jumlah;
        this.harga = harga;
        this.tambahan = "";
    }

    public void setId(int id) {
        this.idMakanan = id;
    }

    public int getId() {
        return this.idMakanan;
    }

    public String getNmMakanan() {
        return this.nmMakanan;
    }

    public void setNmMakanan(String nmMakanan) {
        this.nmMakanan = nmMakanan;
    }

    public String getTambahan() {
        return this.tambahan;
    }

    public void setTambahan(String tambahan) {
        this.tambahan = tambahan;
    }

    public Jenis getJenis() {
        return this.jenis;
    }

    public void setJenis(Jenis jenis) {
        this.jenis = jenis;
    }

    public int getJumlah() {
        return this.jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public int getHarga() {
        return this.harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }
}   