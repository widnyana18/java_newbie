package com.example.utszodiakmu;

public class Zodiak {
    private String name;
    private String photo;
    private String kelahiran;
    private String deskripsi;

    public Zodiak(String name, String photo, String kelahiran, String deskripsi) {
        this.name = name;
        this.photo = photo;
        this.kelahiran = kelahiran;
        this.deskripsi = deskripsi;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String logo) {
        this.photo = photo;
    }

    public String getKelahiran() {
        return kelahiran;
    }

    public void setKelahiran(String kelahiran) {
        this.kelahiran = kelahiran;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}
