package com.example.datanilaisiswa;

import android.os.Parcel;
import android.os.Parcelable;

public class Siswa implements Parcelable {
    private String nim;
    private String nama;
    private int math;
    private int biologi;
    private int bahasa;

    public Siswa(String nim, String nama, int math, int biologi, int bahasa) {
        this.nim = nim;
        this.nama = nama;
        this.math = math;
        this.biologi =  biologi;
        this.bahasa = bahasa;
    }


    public Siswa(Parcel in) {
        nim = in.readString();
        nama = in.readString();
        math = in.readInt();
        biologi = in.readInt();
        bahasa = in.readInt();
    }

    public static final Creator<Siswa> CREATOR = new Creator<Siswa>() {
        @Override
        public Siswa createFromParcel(Parcel in) {
            return new Siswa(in);
        }

        @Override
        public Siswa[] newArray(int size) {
            return new Siswa[size];
        }
    };

    public String getNama() {
        return nama;
    }

    public String getNim() {
        return nim;
    }

    public int getBahasa() {
        return bahasa;
    }

    public int getMath() {
        return math;
    }

    public int getBiologi() {
        return biologi;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public void setMath(int math) {
        this.math = math;
    }

    public void setBiologi(int biologi) {
        this.biologi = biologi;
    }

    public void setBahasa(int bahasa) {
        this.bahasa = bahasa;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nim);
        dest.writeString(nama);
        dest.writeInt(math);
        dest.writeInt(biologi);
        dest.writeInt(bahasa);
    }
}
