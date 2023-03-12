package com.example.latihanfragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddMahasiswaActivity extends AppCompatActivity {

    private EditText edtNim, edtNama;
    private Spinner spProdi;
    private Button btnTambah;
    private String pilProdi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mahasiswa);

        edtNim=findViewById(R.id.text_input_nim);
        edtNama=findViewById(R.id.text_input_nama);
        spProdi=findViewById(R.id.sp_prodi);
        btnTambah=findViewById(R.id.btn_tambah);

// Mengambil data array dari file strings.xml
        final ArrayAdapter<CharSequence>
                adapter = ArrayAdapter.createFromResource(this,
                R.array.daftar_prodi, android.R.layout.simple_spinner_item);


        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spProdi.setAdapter(adapter);

        spProdi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pilProdi = parent.getItemAtPosition(position).toString();
        }

        @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View v) {
//Menyimpan data
            DatabaseHandler databaseHandler = new
                    DatabaseHandler(getApplicationContext());
            Mahasiswa mahasiswa = new Mahasiswa(edtNim.getText().toString(),
                    edtNama.getText().toString(), pilProdi);
            databaseHandler.save(mahasiswa); finish();
        }
        });
    }
}

