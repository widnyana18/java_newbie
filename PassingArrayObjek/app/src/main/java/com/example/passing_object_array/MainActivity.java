package com.example.passing_object_array;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Mahasiswa> Arrmhs=new ArrayList<>();
    private int jmlData=0;

    private EditText etNim;
    private EditText etNama;
    private EditText etUmur;
    private Button btMySave;
    private Button btMyView;
    private TextView tvJmData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNim = (EditText) findViewById(R.id.edNim);
        etNama = (EditText) findViewById(R.id.edNama);
        etUmur = (EditText) findViewById(R.id.edUmur);
        btMySave = (Button) findViewById(R.id.btnSave);
        btMyView = (Button) findViewById(R.id.btnView);
        tvJmData = (TextView) findViewById(R.id.tvJmlData);

        btMyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);

                intent.putParcelableArrayListExtra("MyData",Arrmhs);
                intent.putExtra("jumData",jmlData);
                startActivity(intent);
            }
        });

        btMySave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Save(etNim.getText().toString(),etNama.getText().toString(),Integer.parseInt(etUmur.getText().toString()));
            }
        });
    }

    private void Save(String Nim, String Nama, int Umur){
        if (jmlData<=100) {
            try {
                Arrmhs.add(new Mahasiswa(Nim, Nama, Umur));
                Toast.makeText(getApplicationContext(), "Save Successfully", Toast.LENGTH_SHORT).show();
                jmlData = Arrmhs.size();

                //Clear EditText
                etNim.setText("");
                etNama.setText("");
                etUmur.setText("");

                tvJmData.setText("Jumlah Data : " + String.valueOf(jmlData));
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Save Error !", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(getApplicationContext(), "Data Full", Toast.LENGTH_SHORT).show();
        }

    }
}

