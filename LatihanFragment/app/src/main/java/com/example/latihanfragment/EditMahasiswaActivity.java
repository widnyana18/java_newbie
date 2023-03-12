package com.example.latihanfragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class EditMahasiswaActivity extends AppCompatActivity {

    private EditText edtNim, edtNama;
    private Spinner spProdi;
    private Button btnUpdate; private int id;
    private String pilProdi,nim, nama,prodiLama;
    private ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_mahasiswa);

        edtNim=findViewById(R.id.text_input_nim);
        edtNama=findViewById(R.id.text_input_nama);
        spProdi=findViewById(R.id.sp_prodi);
        btnUpdate=findViewById(R.id.btn_update);

        adapter = ArrayAdapter.createFromResource(this, R.array.daftar_prodi, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); spProdi.setAdapter(adapter);

        getDataIntent();

        spProdi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            pilProdi=parent.getItemAtPosition(position).toString();

}
        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    });

btnUpdate.setOnClickListener(new View.OnClickListener() {
    @Override
        public void onClick(View v) {
        DatabaseHandler databaseHandler = new
                DatabaseHandler(getApplicationContext());
            Mahasiswa mahasiswa = new Mahasiswa(id,edtNim.getText().toString(), edtNama.getText().toString(), pilProdi);
            databaseHandler.update(mahasiswa); finish();
        }
    });

}

    private void getDataIntent()
    {
        Intent intent = getIntent();
        id= intent.getIntExtra("ID",0);
        nim=intent.getStringExtra("NIM");
        nama=intent.getStringExtra("NAMA");
        prodiLama=intent.getStringExtra("PRODI");

        edtNim.setText(nim); edtNama.setText(nama);
        int posSpiner= adapter.getPosition(prodiLama); spProdi.setSelection(posSpiner);
    }

}

