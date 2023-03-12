package com.example.datapegawai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {
    private TextView data1;
    private TextView data2;
    ArrayList<Pegawai> pegawai = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        data1 = findViewById(R.id.data1);
        data2 = findViewById(R.id.data2);

        Intent intent = getIntent();
        pegawai = intent.getParcelableArrayListExtra("dataPegawai");

//        String dataList = "";
//
//            for(int idx = 0; idx < pegawai.size(); idx++){
//
//            }

//        if(pegawai.size() > 1){
            data1.setText(pegawai.get(0).getNip().toString() + "        " + pegawai.get(0).getNama().toString() + "         "
                    + pegawai.get(0).getGaji().toString() + "\n");
            data2.setText(pegawai.size() == 1 ? "" : pegawai.get(1).getNip().toString() + "\t" + pegawai.get(1).getNama().toString() + "\t" + pegawai.get(1).getGaji().toString());
//        }else{
//            data2.setText("");
//        }
    }
}
