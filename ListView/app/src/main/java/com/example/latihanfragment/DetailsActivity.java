package com.example.latihanfragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {
    public ImageView pict;
    public TextView deskripsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        pict = (ImageView) findViewById(R.id.logo_hero);
        deskripsi = (TextView) findViewById(R.id.text_desc);

        Intent intent = getIntent();
        int img = Integer.parseInt(intent.getStringExtra("hero"));
        String desc = intent.getStringExtra("deskripsi");

        pict.setImageResource(img);
        deskripsi.setText(desc);
    }
}
