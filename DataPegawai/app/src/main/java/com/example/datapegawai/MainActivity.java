package com.example.datapegawai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText etNip;
    private EditText etNama;
    private EditText etAlamat;
    private EditText etGaji;
    private Button simpan;
    private Button view;
    private int jmlData = 0;
    ArrayList<Pegawai> pegawai = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNip = findViewById(R.id.edtNip);
        etNama = findViewById(R.id.edtNama);
        etAlamat = findViewById(R.id.edtAlamat);
        etGaji = findViewById(R.id.edtGaji);
        simpan = findViewById(R.id.simpan);
        view = findViewById(R.id.view);

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData(etNip.getText().toString(), etNama.getText().toString(), etAlamat.getText().toString(), etGaji.getText().toString());
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putParcelableArrayListExtra("dataPegawai", pegawai);
                startActivity(intent);
            }
        });
    }

    private void saveData(String nip, String nama, String alamat, String gaji){
        if(jmlData < 3){
            try {
                pegawai.add(new Pegawai(nip, nama, alamat, gaji));
                Toast.makeText(getApplicationContext(), "Save Successfully", Toast.LENGTH_SHORT).show();
                jmlData = pegawai.size();

                etNip.setText("");
                etNama.setText("");
                etAlamat.setText("");
                etGaji.setText("");

            }catch (Exception e){
                Toast.makeText(getApplicationContext(), "Save Data Error", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(getApplicationContext(), "Data Full", Toast.LENGTH_SHORT).show();
        }
    }
}
