package com.example.datanilaisiswa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText etNim;
    private EditText etNama;
    private EditText etMath;
    private EditText etBiologi;
    private EditText etBahasa;
    private Button simpan;
    private Button view;
    private int jmlData = 0;
    ArrayList<Siswa> siswa = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNim = findViewById(R.id.edtNim);
        etNama = findViewById(R.id.edtNama);
        etMath = findViewById(R.id.edtMath);
        etBiologi = findViewById(R.id.edtBiologi);
        etBahasa = findViewById(R.id.edtBahasa);
        simpan = findViewById(R.id.simpan);
        view = findViewById(R.id.view);

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData(etNim.getText().toString(), etNama.getText().toString(),
                        Integer.parseInt(etMath.getText().toString()), Integer.parseInt(etBiologi.getText().toString()), Integer.parseInt(etBahasa.getText().toString()));
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putParcelableArrayListExtra("dataSiswa", siswa);
                startActivity(intent);
            }
        });
    }

    private void saveData(String nim, String nama, int math, int biologi, int bahasa){

        try {
            siswa.add(new Siswa(nim, nama, math, biologi, bahasa));
            Toast.makeText(getApplicationContext(), "Save Successfully", Toast.LENGTH_SHORT).show();
            jmlData = siswa.size();

            etNim.setText("");
            etNama.setText("");
            etMath.setText("");
            etBiologi.setText("");
            etBahasa.setText("");

        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Save Data Error", Toast.LENGTH_SHORT).show();
        }
    }
}
