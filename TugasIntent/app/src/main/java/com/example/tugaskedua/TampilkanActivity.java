package com.example.tugaskedua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class TampilkanActivity extends AppCompatActivity {
    private TextView tNama;
    private TextView tNim;
    private TextView tAbsen;
    private TextView tTugas;
    private TextView tKuis;
    private TextView tUts;
    private TextView tUas;
    private TextView tNilai;
    private TextView tPredikat;
    private TextView tKesimpulan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampilkan);

        tNama = (TextView) findViewById(R.id.tvNama);
        tNim = (TextView) findViewById(R.id.tvNim);
        tAbsen = (TextView) findViewById(R.id.tvAbsensi);
        tKuis = (TextView) findViewById(R.id.tvKuis);
        tTugas = (TextView) findViewById(R.id.tvTugas);
        tUts = (TextView) findViewById(R.id.tvUts);
        tUas = (TextView) findViewById(R.id.tvUas);
        tNilai = (TextView) findViewById(R.id.tvNilai);
        tPredikat = (TextView) findViewById(R.id.tvPredikat);
        tKesimpulan = (TextView) findViewById(R.id.tvKesimpulan);



        Intent intent = getIntent();
        String predikat = "";
        String keterangan = "";
        String nim = intent.getStringExtra("Nim").toString();
        String nama = intent.getStringExtra("Nama").toString();
        int absen =  (int) (intent.getFloatExtra("Absen", 0) * 0.15);
        int kuis = (int) (intent.getFloatExtra("Kuis", 0) * 0.2);
        int tugas = (int) (intent.getFloatExtra("Tugas", 0) * 0.15);
        int uts = (int) (intent.getFloatExtra("Uts", 0) * 0.2);
        int uas = (int) (intent.getFloatExtra("Uas", 0) * 0.3);
        int nilai = (int) (absen + kuis + tugas + uts + uas);

        if(nilai > 58){
            keterangan = "LULUS";
        }
        else{
            keterangan = "TIDAK LULUS";
        }


        if(nilai > 85 && nilai <= 100){
            predikat = "A";
        }
        if(nilai > 80 && nilai <= 85){
            predikat = "AB";
        }
        if(nilai > 70 && nilai <= 80){
            predikat = "B";
        }
        if(nilai > 65 && nilai <= 70){
            predikat = "BC";
        }
        if(nilai > 55 && nilai <= 65){
            predikat = "C";
        }
        if(nilai > 40 && nilai <= 55){
            predikat = "D";
        }
        if(nilai > 0 && nilai <= 40){
            predikat = "E";
        }

        tNim.setText(nim);
        tNama.setText(nama);
        tAbsen.setText(String.valueOf(absen));
        tKuis.setText(String.valueOf(kuis));
        tTugas.setText(String.valueOf(tugas));
        tUts.setText(String.valueOf(uts));
        tUas.setText(String.valueOf(uas));
        tNilai.setText(String.valueOf(nilai));
        tAbsen.setText(String.valueOf(absen));
        tPredikat.setText(predikat);
        tKesimpulan.setText(keterangan);

    }
}
