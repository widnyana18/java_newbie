package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button calBtn;
    private Button minMaxBtn;
    private Button pythagorasBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calBtn = (Button)findViewById(R.id.calBtn);
        minMaxBtn = (Button)findViewById(R.id.minMaxBtn);
        pythagorasBtn = (Button)findViewById(R.id.pythagorasBtn);

        calBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(MainActivity.this, Kalkulator.class);
                startActivity(intent);
            }
        });

        minMaxBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(MainActivity.this, MinMax.class);
                startActivity(intent);
            }
        });

        pythagorasBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(MainActivity.this, Pythagoras.class);
                startActivity(intent);
            }
        });
    }
}
