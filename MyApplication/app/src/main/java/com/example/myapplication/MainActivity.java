package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText name;
    private EditText password;
    private TextView info;
    private Button login;
    private int counter = 5;

    private void _validate(String username, String password){
        if(username.equals("Admin") && password.equals("Admin")){
            Intent intent = new Intent(MainActivity.this, SecondPage.class);
            startActivity(intent);
        }else{
            counter--;
            info.setText("Toleransi Jumlah kesalahan: " + String.valueOf(counter));

            if(counter == 0){
                login.setEnabled(false);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.etName);
        password = (EditText) findViewById(R.id.etPassword);
        info = (TextView) findViewById(R.id.tpInfo);
        login = (Button) findViewById(R.id.btnLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _validate(name.getText().toString(), password.getText().toString());

            }
        });
    }
}
