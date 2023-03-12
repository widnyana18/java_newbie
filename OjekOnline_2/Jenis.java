public class Jenis{
    // private int noJenis;
    private String namaJenis;
    private String keterangan;

    public Jenis( String namaJenis, String keterangan) {
        // this.noJenis = noJenis;
        this.namaJenis = namaJenis;
        this.keterangan = keterangan;
    }

    // public int getKodeJenis() {
    //     return this.noJenis;
    // }

    // public void setKodeJenis(int noJenis) {
    //     this.noJenis = noJenis;
    // }

    public String getNamaJenis() {
        return this.namaJenis;
    }

    public void setNamaJenis(String namaJenis) {
        this.namaJenis = namaJenis;
    }

    public String getKeterangan() {
        return this.keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}