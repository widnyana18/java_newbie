package com.example.accesskamera;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btCap, btRec, btPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btCap=(Button) findViewById(R.id.buttonCapture);
        btPlay=(Button) findViewById(R.id.buttonPlyVideo);
        btRec=(Button) findViewById(R.id.buttonRecVid);

        btCap.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(MainActivity.this, CaptureImage.class);
                startActivity(intent);
            }
        });
        btPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,playMovActivity.class);
                startActivity(intent);
            }
        });
        btRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,playMovActivity.class);
                startActivity(intent);
            }
        });

     /*   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, 1000);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE
            }, 1001);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{
                    Manifest.permission.CAMERA
            }, 1002);
        } */

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1000:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission Granted Write External Storage", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Permission Denied Write External Strorage", Toast.LENGTH_LONG).show();
                }
                break;
            case 1001:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission Granted Read External Storage", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Permission Danied Read External Storage", Toast.LENGTH_LONG).show();
                }
                break;
            case 1002:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission Granted Access Camera", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Permission Danied Read Access Camera", Toast.LENGTH_LONG).show();
                }
                break;
        }

    }
}