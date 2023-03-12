package com.example.checkboxandi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button tampilkan;
    private CheckBox pagi;
    private CheckBox siang;
    private CheckBox malam;
    private TextView hasil;
    public ArrayList<Hari> simpan;
    public String convertPagi = "";
    public String convertSiang = "";
    public String convertMalam = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pagi = (CheckBox) findViewById(R.id.boxPagi);
        siang = (CheckBox) findViewById(R.id.boxSiang);
        malam = (CheckBox) findViewById(R.id.boxMalam);
        tampilkan = (Button) findViewById(R.id.show_btn);
        hasil = (TextView) findViewById(R.id.hasil);

        pagi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pagi.isChecked()){
                    convertPagi = pagi.getText().toString() + ", \t";
                }else{
                    convertPagi = "";
                }
            }
        });

        siang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(siang.isChecked()){
                    convertSiang = siang.getText().toString() + ", \t";
                }else{
                    convertSiang = "";
                }
            }
        });
        malam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(malam.isChecked()){
                    convertMalam = malam.getText().toString();
                }else{
                    convertMalam = "";
                }
            }
        });

        tampilkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                simpan.add(new Hari(convertPagi, convertSiang, convertMalam));
//                hasil.setText(simpan.get(0).pagi + ", " + simpan.get(0).siang + ", " + simpan.get(0).malam);
                hasil.setText(
                        "Hari yang Anda Pilih Yaitu: \t" +
                        convertPagi + convertSiang + convertMalam
                );
            }
        });
    }
//
//    public void convert(){
//        if(pagi.isChecked()){
//            convertPagi = pagi.getText().toString();
//        }
//        else{
//            convertPagi = "";
//        }
//
//        if(siang.isChecked()){
//            convertSiang = siang.getText().toString();
//        }else{
//            convertSiang = "";
//        }
//
//        if(malam.isChecked()){
//            convertMalam = malam.getText().toString();
//        }else{
//            convertMalam = "";
//        }
//    }
}

class Hari {
    final String pagi;
    final String siang;
    final String malam;

    Hari(String pagi, String siang, String malam){
        this.pagi = pagi;
        this.siang = siang;
        this.malam = malam;
    }
}
