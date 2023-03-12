package com.example.utsandroid;

public class mobil {
    private String nama;
    private String foto;
    private String harga;
    private String dp;
    private String angsuran;
    private String tenor;

    public mobil(String nama, String foto, String harga, String dp, String angsuran, String tenor){
        this.nama = nama;
        this.foto = foto;
        this.harga = harga;
        this.dp = dp;
        this.angsuran = angsuran;
        this.tenor = tenor;
    }

    public String getNama(){
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getFoto(){
        return foto;
    }
    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getHarga(){
        return harga;
    }
    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getDp(){
        return dp;
    }
    public void setDp(String dp) {
        this.dp = dp;
    }

    public String getAngsuran(){
        return angsuran;
    }
    public void setAngsuran(String angsuran) {
        this.angsuran = angsuran;
    }

    public String getTenor(){
        return tenor;
    }
    public void setTenor(String tenor) {
        this.tenor = tenor;
    }
}
