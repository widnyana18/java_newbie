package com.example.passing_object_array;

import android.os.Parcel;
import android.os.Parcelable;

public class Mahasiswa implements Parcelable {

    private String Nim;
    private String Nama;
    private int Umur;

    public Mahasiswa(String Nim, String Nama, int Umur) {
        this.Nama = Nama;
        this.Nim = Nim;
        this.Umur = Umur;
    }

    public Mahasiswa(Parcel source) {
        Nim=source.readString();
        Nama=source.readString();
        Umur=source.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Nim);
        dest.writeString(Nama);
        dest.writeInt(Umur);
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public void setNim(String nim) {
        Nim = nim;
    }

    public void setUmur(int umur){
        Umur = umur;
    }

    public String getNama() {
        return Nama;
    }

    public int getUmur() {
        return Umur;
    }

    public String getNim() {
        return Nim;
    }

    public static final Creator<Mahasiswa> CREATOR=new Creator<Mahasiswa>(){
        @Override
        public Mahasiswa[] newArray(int size){
            return new Mahasiswa[size];
        }

        @Override
        public Mahasiswa createFromParcel(Parcel source){
            return new Mahasiswa(source);
        }

    };

}
