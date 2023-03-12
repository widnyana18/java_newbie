package com.example.datanilaisiswa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {
    ArrayList<Siswa> siswa = new ArrayList<>();
    private TextView nama;
    private TextView math;
    private TextView biologi;
    private TextView bahasa;
    private TextView total;
    private TextView status;
    private EditText nim;
    private Button cari;
    private String parsenim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        nama = findViewById(R.id.view_nama);
        math = findViewById(R.id.view_math);
        biologi = findViewById(R.id.view_biologi);
        bahasa = findViewById(R.id.view_bahasa);
        total = findViewById(R.id.total_nilai);
        status = findViewById(R.id.status);
        nim = findViewById(R.id.search_siswa);
        cari = findViewById(R.id.search_btn);

        init();
        Intent intent = getIntent();
        siswa = intent.getParcelableArrayListExtra("dataSiswa");

        cari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int idx=0; idx<siswa.size(); idx++){
                    parsenim = nim.getText().toString();
                    if(siswa.get(idx).getNim().equals(nim.getText().toString())){
                        tampilkanSiswa(0);
                        Toast.makeText(getApplicationContext(), "Data Ditemukan", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        init();
                        Toast.makeText(getApplicationContext(), "Data Tidak Ditemukan", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void tampilkanSiswa(int idx){
        int nilaiMath = (int) (siswa.get(idx).getMath() * 0.6);
        int nilaiBiologi = (int) (siswa.get(idx).getBiologi() * 0.3);
        int nilaiBahasa = (int) (siswa.get(idx).getBahasa() * 0.2);
        String ket;

        int nilaiTotal = nilaiMath + nilaiBiologi + nilaiBahasa;

        if(nilaiTotal < 60){
            ket = "ANDA TIDAK LULUS";
        }
        else{
            ket = "ANDA LULUS";
        }

        nama.setText("Nama : " + siswa.get(idx).getNama().toString());
        math.setText("N : " + String.valueOf(nilaiMath) + " (Matematika 60%)");
        biologi.setText("N : " + String.valueOf(nilaiBiologi) + " (Biologi 30%)");
        bahasa.setText("N : " + String.valueOf(nilaiBahasa) + " (Bahasa 20%)");
        total.setText("Total Nilai : " + String.valueOf(nilaiTotal));
        status.setText(ket);
    }

    private void init(){
        nama.setText("");
        math.setText("");
        biologi.setText("");
        bahasa.setText("");
        total.setText("");
        status.setText("");
    }
}
