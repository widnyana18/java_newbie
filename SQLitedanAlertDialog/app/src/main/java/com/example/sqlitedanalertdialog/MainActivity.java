package com.example.sqlitedanalertdialog;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.midi.MidiDeviceService;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btAddData;
    GridView gridViewData;
    ArrayList<MyMahasiswa> myMahasiswa=new ArrayList<MyMahasiswa>();
    DatabaseHelper dbhelperMain=new DatabaseHelper(this);

    private class MylistAdapter extends ArrayAdapter<MyMahasiswa>{

        public MylistAdapter(){super(MainActivity.this, R.layout.ls_item,myMahasiswa); }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
            View itemview=convertView;
            if (itemview==null){
                itemview=getLayoutInflater().inflate(R.layout.ls_item,parent,false);
            }
            MyMahasiswa mhs=myMahasiswa.get(position);
            ImageView imgfoto=(ImageView) itemview.findViewById(R.id.imageViewItem);
            BitmapFactory.Options options=new BitmapFactory.Options();
            options.inSampleSize=16;
            Bitmap img=BitmapFactory.decodeFile(mhs.getPath(),options);
            MyImage mi=new MyImage();
            img=mi.automatic_rotateImg(img,mhs.getPath());
            imgfoto.setImageBitmap(img);

            TextView txNim=(TextView) itemview.findViewById(R.id.textViewNim);
            txNim.setText(mhs.getNim());
            TextView txNama=(TextView) itemview.findViewById(R.id.textViewNama);
            txNim.setText(mhs.getNama());
            TextView txUmur=(TextView) itemview.findViewById(R.id.textViewUmur);
            txNim.setText(mhs.getUmur());
            TextView txPath=(TextView) itemview.findViewById(R.id.textViewPath);
            txNim.setText(mhs.getPath());
            return itemview;
        }
    }

    private void setAdapterListView(){
        ArrayAdapter<MyMahasiswa> adapter=new MylistAdapter();
        gridViewData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                String Nim, Nama, Path;
                int Umur;

                Nim=((TextView) view.findViewById(R.id.textViewNim)).getText().toString();
                Nama=((TextView) view.findViewById(R.id.textViewNama)).getText().toString();
                Path=((TextView) view.findViewById(R.id.textViewPath)).getText().toString();
                Umur=Integer.parseInt(((TextView) view.findViewById(R.id.textViewUmur)).getText().toString());

                intent.putExtra("Mode", "UpdateDelete");
                intent.putExtra("Nim", Nim);
                intent.putExtra("Nama", Nama);
                intent.putExtra("Umur", Umur);
                intent.putExtra("Path", Path);
                startActivity(intent);
            }
        });
        gridViewData.setAdapter(adapter);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridViewData=(GridView) findViewById(R.id.gridViewDataAll);
        btAddData=(Button) findViewById(R.id.buttonAddData);

        if (Build.VERSION.SDK_INT<=Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
        != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1001);
        }
        if (dbhelperMain.getCountData()>0){
            myMahasiswa=dbhelperMain.transferArrayList();
            if (myMahasiswa.size()>0){
                setAdapterListView();
            }
        }
        btAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("Mode", "insert");
                startActivity(intent);
            }
        });
    }
}
