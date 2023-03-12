package com.example.latihanfragment;

public class Mahasiswa {
    private int id;
    private String nimm, namaa, prodi;

    public Mahasiswa(int id, String nim, String nama, String prodi) {
        this.id = id; this.nimm = nim; this.namaa = nama; this.prodi = prodi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNimm() {
        return nimm;
    }
    public void setNimm(String nimm) {
        this.nimm = nimm;
    }

    public String getNamaa() {
        return namaa;
    }

    public void setNamaa(String namaa) {
        this.namaa = namaa;
    }

    public String getProdi() {
        return prodi;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

    public Mahasiswa() {
    }

    public Mahasiswa(String nimm, String namaa, String prodi) {
        this.nimm = nimm; this.namaa = namaa; this.prodi = prodi;
    }


}
