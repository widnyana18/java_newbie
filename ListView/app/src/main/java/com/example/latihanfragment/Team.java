package com.example.latihanfragment;

public class Team {
    private String name;
    private String logo;
    private String deskripsi;

    public Team(String name, String logo, String deskripsi) {
        this.name = name;
        this.logo = logo;
        this.deskripsi = deskripsi;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}
