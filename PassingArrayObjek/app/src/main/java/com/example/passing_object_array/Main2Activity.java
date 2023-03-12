package com.example.passing_object_array;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    ArrayList<Mahasiswa> ArrMhs;
    int jmlData=0;

    int index=0;
    private TextView tvMyNim;
    private TextView tvMyNama;
    private TextView tvMyUmur;
    private Button btPrev;
    private Button btNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tvMyNim = (TextView) findViewById(R.id.tvNim);
        tvMyNama = (TextView) findViewById(R.id.tvNama);
        tvMyUmur = (TextView) findViewById(R.id.tvUmur);
        btPrev = (Button) findViewById(R.id.btnPrev);
        btNext = (Button) findViewById(R.id.btnNext);

        Intent intent=getIntent();
        ArrMhs=intent.getParcelableArrayListExtra("MyData");
        jmlData=intent.getIntExtra("jumData",0);

        if (jmlData>0){
            showData(0);
        }
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index+1<jmlData){
                    index++;
                    showData(index);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Data sudah Paling Akhir",
                            Toast.LENGTH_SHORT).show();

                }
            }
        });

        btPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((index-1)<0){
                    Toast.makeText(getApplicationContext(), "Data Paling Awal",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    index--;
                    showData(index);
                }
            }
        });
    }

    private void showData(int idx){
        tvMyNim.setText("NIM   : "+ArrMhs.get(idx).getNim().toString());
        tvMyNama.setText("Nama : "+ArrMhs.get(idx).getNama().toString());
        tvMyUmur.setText("Umur : "+String.valueOf(ArrMhs.get(idx).getUmur()));
    }
}
