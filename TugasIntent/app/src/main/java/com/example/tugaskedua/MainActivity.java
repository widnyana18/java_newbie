package com.example.tugaskedua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText nim;
    private EditText nama;
    private EditText absen;
    private EditText tugas;
    private EditText kuis;
    private EditText uts;
    private EditText uas;
    private Button simpan;
    private Button hapus;

    public String convertNama;
    public String convertNim;
    public float convertAbsen = 0;
    public float convertTugas = 0;
    public float convertKuis = 0;
    public float convertUts = 0;
    public float convertUas = 0;
    public ArrayList<Item> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nim = (EditText) findViewById(R.id.edt_nim);
        nama = (EditText) findViewById(R.id.edt_nama);
        absen = (EditText) findViewById(R.id.edt_absen);
        tugas = (EditText) findViewById(R.id.edt_tugas);
        kuis = (EditText) findViewById(R.id.edt_kuis);
        uts = (EditText) findViewById(R.id.edt_uts);
        uas = (EditText) findViewById(R.id.edt_uas);
        simpan = (Button) findViewById(R.id.btn_simpan);
        hapus = (Button) findViewById(R.id.btn_hapus);

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convert();

                if(dataKosong()){
                    Toast.makeText(MainActivity.this, "Inputan tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                }
                else{
                    data.add(new Item(convertNama, convertNim, convertAbsen, convertTugas, convertKuis, convertUts, convertUas));
                    Toast.makeText(MainActivity.this, "Data Anda Telah Disimpan", Toast.LENGTH_SHORT).show();

                    Intent intent =  new Intent(MainActivity.this, TampilkanActivity.class);

                    Item item = data.get(data.size()-1);

                    intent.putExtra("Nim", item.nim);
                    intent.putExtra("Nama", item.nama);
                    intent.putExtra("Absen", item.absen);
                    intent.putExtra("Tugas", item.tugas);
                    intent.putExtra("Kuis", item.kuis);
                    intent.putExtra("Uts", item.uts);
                    intent.putExtra("Uas", item.uas);

                    startActivity(intent);
                }
            }
        });

        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(data.isEmpty() || dataKosong()){
                    Toast.makeText(MainActivity.this, "Anda tidak memiliki data", Toast.LENGTH_SHORT).show();
                }
                else{
                    data.remove(data.size() - 1);
                    Toast.makeText(MainActivity.this, "Data Telah Dihapus", Toast.LENGTH_SHORT).show();
                }
                nim.setText("");
                nama.setText("");
                absen.setText("");
                tugas.setText("");
                kuis.setText("");
                uts.setText("");
                uas.setText("");
            }
        });
    }

    public void convert(){
        convertNama = nama.getText().toString();
        convertNim = nim.getText().toString();
        convertAbsen = absen.getText().length() != 0 ? Float.parseFloat(absen.getText().toString()) : 0;
        convertTugas = tugas.getText().length() != 0 ? Float.parseFloat(tugas.getText().toString()) : 0;
        convertKuis = kuis.getText().length() != 0 ? Float.parseFloat(kuis.getText().toString()) : 0;
        convertUts = uts.getText().length() != 0 ? Float.parseFloat(uts.getText().toString()) : 0;
        convertUas = uas.getText().length() != 0 ? Float.parseFloat(uas.getText().toString()) : 0;
    }

    boolean dataKosong(){
        return convertNim.isEmpty() || convertNama.isEmpty() || convertAbsen == 0 || convertTugas == 0 || convertKuis == 0 || convertUts == 0 || convertUas == 0;
    }
}

class Item {
    final String nama;
    final String nim;
    final float absen;
    final float tugas;
    final float kuis;
    final float uts;
    final float uas;

    Item(String nama, String nim, float absen, float tugas, float kuis, float uts, float uas){
        this.nim = nim;
        this.nama = nama;
        this.absen = absen;
        this.tugas = tugas;
        this.kuis = kuis;
        this.uts = uts;
        this.uas = uas;
    };

    public String getNim() {
        return nim;
    }

    public String getNama() {
        return nama;
    }

    public float getAbsen() {
        return absen;
    }

    public float getKuis() {
        return kuis;
    }

    public float getTugas() {
        return tugas;
    }

    public float getUts() {
        return uts;
    }

    public float getUas() {
        return uas;
    }
}
