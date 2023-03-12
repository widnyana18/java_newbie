package com.example.utsandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class detailmobil extends AppCompatActivity {
    public ImageView foto_mobil;
    public TextView nama_mobil;
    public TextView harga_mobil;
    public TextView dp_mobil;
    public TextView angsuran_mobil;
    public TextView tenor_mobil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailmobil);

        foto_mobil = (ImageView) findViewById(R.id.foto_mobil);
        nama_mobil = (TextView) findViewById(R.id.nama_mobil);
        harga_mobil = (TextView) findViewById(R.id.harga);
        dp_mobil = (TextView) findViewById(R.id.dp);
        angsuran_mobil = (TextView) findViewById(R.id.angsuran);
        tenor_mobil = (TextView) findViewById(R.id.tenor);

        Intent intent = getIntent();
        int img = Integer.parseInt(intent.getStringExtra("foto"));
        String desc = intent.getStringExtra("mobil");
        String deskripsi = intent.getStringExtra("harga");
        String descr = intent.getStringExtra("dp");
        String deskrip = intent.getStringExtra("angsuran");
        String desk = intent.getStringExtra("tenor");

        foto_mobil.setImageResource(img);
        nama_mobil.setText(desc);
        harga_mobil.setText(desc);
        dp_mobil.setText(desc);
        angsuran_mobil.setText(desc);
        tenor_mobil.setText(desc);
    }
}

