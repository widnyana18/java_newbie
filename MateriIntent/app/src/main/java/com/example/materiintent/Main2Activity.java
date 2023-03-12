package com.example.materiintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    private TextView TNama;
    private TextView TNilaiA;
    private TextView TNilaiB;
    private TextView THasil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TNama=(TextView) findViewById(R.id.tvNama);
        TNilaiA=(TextView) findViewById(R.id.tvNilaiA);
        TNilaiB=(TextView) findViewById(R.id.tvNilaiB);
        THasil=(TextView) findViewById(R.id.tvHasilTambah);

        Intent intent=getIntent();
        String NameP=intent.getStringExtra("MyName").toString();
        float Nilai_A=intent.getFloatExtra("ValA", 0);
        float Nilai_B=intent.getFloatExtra("ValA", 0);
        float Hasil= Nilai_A+Nilai_B;

        TNama.setText(NameP);
        TNilaiA.setText(String.valueOf(Nilai_A));
        TNilaiB.setText(String.valueOf(Nilai_B));
        THasil.setText(String.valueOf(Hasil));
    }
}
