package com.example.materiintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText NNama;
    private EditText N_A;
    private EditText N_B;
    private Button btSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NNama=(EditText) findViewById(R.id.etNama);
        N_A=(EditText) findViewById(R.id.etNilaiA);
        N_B=(EditText) findViewById(R.id.etNilaiB);
        btSend=(Button) findViewById(R.id.btKirim);

        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("MyName",NNama.getText().toString());
                intent.putExtra("ValA",Float.parseFloat(N_A.getText().toString()));
                intent.putExtra("ValB",Float.parseFloat(N_B.getText().toString()));
                startActivity(intent);
            }
        });
    }

}
