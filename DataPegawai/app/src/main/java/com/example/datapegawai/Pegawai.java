package com.example.datapegawai;

import android.os.Parcel;
import android.os.Parcelable;

public class Pegawai implements Parcelable {
    private String nip;
    private String nama;
    private  String alamat;
    private String gaji;

    public Pegawai(String nip, String nama, String alamat, String gaji) {
        this.nip = nip;
        this.nama = nama;
        this.alamat = alamat;
        this.gaji =  gaji;
    }

    public Pegawai(Parcel in) {
        nip = in.readString();
        nama = in.readString();
        alamat = in.readString();
        gaji = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nip);
        dest.writeString(nama);
        dest.writeString(alamat);
        dest.writeString(gaji);
    }

    public static final Creator<Pegawai> CREATOR = new Creator<Pegawai>() {
        @Override
        public Pegawai createFromParcel(Parcel in) {
            return new Pegawai(in);
        }

        @Override
        public Pegawai[] newArray(int size) {
            return new Pegawai[size];
        }
    };

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNip() {
        return nip;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setGaji(String gaji) {
        this.gaji = gaji;
    }

    public String getGaji() {
        return gaji;
    }
}
