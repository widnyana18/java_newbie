package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Kalkulator extends AppCompatActivity {

    private EditText bil1, bil2, hasil;
    private Button dotBtn, plusBtn, kaliBtn, modBtn, devideBtn, pangkatBtn;
    private double hasilTemp;

    private void pembagian(double angka1, double angka2){
        hasilTemp = angka1 / angka2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalkulator);

        bil1 = (EditText) findViewById(R.id.bil1);
        bil2 = (EditText) findViewById(R.id.bil2);
        dotBtn = (Button) findViewById(R.id.dotBtn);
        plusBtn = (Button) findViewById(R.id.plusBtn);
        kaliBtn = (Button) findViewById(R.id.kaliBtn);
        modBtn = (Button) findViewById(R.id.modBtn);
        devideBtn = (Button) findViewById(R.id.devideBtn);
        pangkatBtn = (Button) findViewById(R.id.pangkatBtn);

    }
}
