package com.example.sqlitedanalertdialog;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    public static final int PICKFILE_RESULT_CODE = 100;

    Button btInsert, btUpdate, btDelete, btView;
    EditText etNim, etNama, etUmur;
    ImageView imgFoto;

    DatabaseHelper dbhelper=new DatabaseHelper(this);
    String path=null;

    private String getRealPathFromURI(Uri contentURI){
        String result;
        Cursor cursor = getContentResolver().query(contentURI,null,null,null,null);
        if (cursor == null){
            result = contentURI.getPath();
        }else{
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

    private void CleanComponent(){
        etUmur.setText("");
        etNim.setText("");
        etNama.setText("");
        imgFoto.setImageResource(R.mipmap.ic_launcher_round);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (data==null){
            return;
        }
        switch (requestCode){
            case PICKFILE_RESULT_CODE:
                Uri fileUri=data.getData();
                path=getRealPathFromURI(fileUri);
                try {
                    BitmapFactory.Options options=new BitmapFactory.Options();
                    options.inSampleSize=0;
                    Bitmap myPic=BitmapFactory.decodeFile(path,options);
                    MyImage mi=new MyImage();
                    myPic=mi.automatic_rotateImg(myPic,path);
                    imgFoto.setImageBitmap(myPic);
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
        }
    }

    private void showDialogAlert(final String mode){
        AlertDialog.Builder alBuilder=new AlertDialog.Builder(this);
        int buttonpic=0;
        String message=null;
        switch (mode){
            case "insert":
                buttonpic=R.drawable.save_icon;
                alBuilder.setTitle("Do You Sure Save Data");
                message="Click Yes to Save Data";
                break;
            case "update" :
                buttonpic=R.drawable.update_icon;
                alBuilder.setTitle("Do You Sure Update Data");
                message="Click Yes to Update Data";
                break;
            case "delete" :
                buttonpic=R.drawable.delete_icon;
                alBuilder.setTitle("Do You Sure Delete Data");
                message="Click Yes to Delete Data";
                break;
        }

        alBuilder
                .setMessage(message)
                .setIcon(buttonpic)
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (mode){
                            case "insert" :
                                MyMahasiswa mhs=dbhelper.getDataExist(etNim.getText().toString());
                                if (mhs==null){
                                    MyMahasiswa mhs2=new MyMahasiswa(etNim.getText().toString(),etNama.getText().toString()
                                            ,path, Integer.parseInt(etUmur.getText().toString()));
                                    boolean benar=dbhelper.insertData(mhs2);
                                    if (benar){
                                        Toast.makeText(getBaseContext(),"Insert Successfully", Toast.LENGTH_LONG).show();
                                        CleanComponent();
                                    }else {
                                        Toast.makeText(getBaseContext(),"Insert Failed", Toast.LENGTH_LONG).show();
                                    }
                                }else{
                                    Toast.makeText(getBaseContext(),"Nim dimiliki :"+
                                            mhs.getNama(),Toast.LENGTH_LONG).show();
                                }
                                break;
                            case "update" :
                                if (dbhelper.updateData(etNim.getText().toString(),etNama.getText().toString(),
                                        path,Integer.parseInt(etUmur.getText().toString()))){
                                    Toast.makeText(getBaseContext(),"Update Successfully", Toast.LENGTH_LONG).show();
                                    CleanComponent();
                                }else{
                                    Toast.makeText(getBaseContext(),"Update Failed", Toast.LENGTH_LONG).show();
                                }
                                break;
                            case "delete" :
                                if (dbhelper.deleteData(etNim.getText().toString())>0){
                                    Toast.makeText(getBaseContext(),"Delete Successfully", Toast.LENGTH_LONG).show();
                                    CleanComponent();
                                }else {
                                    Toast.makeText(getBaseContext(),"Delete Failed", Toast.LENGTH_LONG).show();
                                }
                                break;
                        }
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (mode){
                            case "insert" :
                                Toast.makeText(getBaseContext(), "Insert Cancelled", Toast.LENGTH_LONG).show();
                                break;
                            case "update" :
                                Toast.makeText(getBaseContext(),"Update Cancelled", Toast.LENGTH_LONG).show();
                                break;
                            case "delete" :
                                Toast.makeText(getBaseContext(),"Insert Delete", Toast.LENGTH_LONG).show();
                                break;
                        }
                    }
                });
        AlertDialog alertDialog=alBuilder.create();
        alertDialog.show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btInsert=(Button) findViewById(R.id.buttonInsert);
        btUpdate=(Button) findViewById(R.id.buttonUpdate);
        btDelete=(Button) findViewById(R.id.buttonDelete);
        btView=(Button) findViewById(R.id.buttonView);
        etNim=(EditText) findViewById(R.id.editTextNim);
        etNama=(EditText) findViewById(R.id.editTextNama);
        etUmur=(EditText) findViewById(R.id.editTextUmur);
        imgFoto= (ImageView) findViewById(R.id.imageViewFoto);

        final Intent intent=getIntent();
        String ModeResult=intent.getStringExtra("Mode");

        if (ModeResult.equalsIgnoreCase("UpdateDelete")){
            etNim.setText(intent.getStringExtra("Nim"));
            etNama.setText(intent.getStringExtra("Nama"));
            etUmur.setText(String.valueOf(intent.getIntExtra("Umur", 0)));
            path=intent.getStringExtra("path");

            BitmapFactory.Options options=new BitmapFactory.Options();
            Bitmap pic=BitmapFactory.decodeFile(path,options);
            MyImage mi=new MyImage();
            pic=mi.automatic_rotateImg(pic,path);
            imgFoto.setImageBitmap(pic);
            etNim.setEnabled(false);
            btInsert.setEnabled(false);
        }else if (ModeResult.equalsIgnoreCase("insert")){
            btInsert.setEnabled(true);
            btDelete.setEnabled(false);
            btUpdate.setEnabled(false);
            imgFoto.setImageResource(R.mipmap.ic_launcher_round);
        }
        imgFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chooseFile=new Intent(Intent.ACTION_GET_CONTENT);
                chooseFile.setType("image/jpeg|image/png");
                chooseFile=Intent.createChooser(chooseFile,"Choose File :");
                startActivityForResult(chooseFile,PICKFILE_RESULT_CODE);
            }
        });
        btInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogAlert("insert");
            }
        });
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogAlert("update");
            }
        });
        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogAlert("delete");
            }
        });
        btView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main2Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
