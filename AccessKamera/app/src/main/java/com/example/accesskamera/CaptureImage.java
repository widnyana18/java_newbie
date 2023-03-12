package com.example.accesskamera;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;

public class CaptureImage extends AppCompatActivity {

    final int CAMERA_RESULT=1;
    Uri imgUri=null;

    Button btTakePic;
    ImageView imgVPic;

    String pathImg=null, nameImg=null, dirImg=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_image);
        btTakePic = (Button)findViewById(R.id.buttonTakeFoto);
        imgVPic = (ImageView)findViewById(R.id.imageView3);

        btTakePic.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                CaptureImageUri();
            }
        });
    }
    private void CaptureImageUri(){
        dirImg= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath();

                File folder=new File(dirImg+File.separator+"MELI");
                boolean success=true;
                if (!folder.exists()){
                    try {
                        success=folder.mkdir();
                        Toast.makeText(this,"Folder Create Successfull", Toast.LENGTH_LONG).show();
                    }catch (Exception e){
                        success=!success;
                        e.printStackTrace();
                    }
                }

                if (success){
                    dirImg=dirImg+File.separator+"MELI";
                }
        Calendar calendar=Calendar.getInstance();
         nameImg="MELI"+calendar.getTimeInMillis()+".jpg";
         File file=new File(dirImg, nameImg);

         pathImg=dirImg+File.separator+nameImg;

         if (!file.exists()){
             try {
                 file.createNewFile();
                 imgUri= FileProvider.getUriForFile(CaptureImage.this, BuildConfig.APPLICATION_ID,file);
                 Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                 intent.putExtra(MediaStore.EXTRA_OUTPUT,imgUri);
                 startActivityForResult(intent, CAMERA_RESULT);
             }catch (Exception e){
                 e.printStackTrace();
             }
         }else{
             try{
                 file.delete();
                 file.createNewFile();
                 imgUri=FileProvider.getUriForFile(CaptureImage.this, BuildConfig.APPLICATION_ID,file);
                 Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                 intent.putExtra(MediaStore.EXTRA_OUTPUT,imgUri);
                 startActivityForResult(intent, CAMERA_RESULT);
             }catch (Exception e){
                 e.printStackTrace();
             }
         }
    }

    protected Bitmap automatic_rotateImg(Bitmap img){
        Bitmap rotatedBitmap=null;
        try {
            ExifInterface exifInterface = new ExifInterface(pathImg);
            int orientation=exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
            switch (orientation){
                case ExifInterface.ORIENTATION_ROTATE_90 :
                    rotatedBitmap=rotateBitmap(img, 90);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180 :
                    rotatedBitmap=rotateBitmap(img, 180);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270 :
                    rotatedBitmap=rotateBitmap(img, 270);
                    break;
                case ExifInterface.ORIENTATION_NORMAL : default:
                    rotatedBitmap=img;
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return rotatedBitmap;
    }

    protected Bitmap rotateBitmap(Bitmap imgSurce, float angel){
        Matrix matrix=new Matrix();
        matrix.postRotate(angel);
        return Bitmap.createBitmap(imgSurce, 0, 0, imgSurce.getWidth(), imgSurce.getHeight()
        ,matrix, true);
    }

    protected void replace_fileImagetoFixImage(Bitmap img){
        File file=new File(dirImg, nameImg);
        if (file.exists()){
            file.delete();
            try {
                FileOutputStream out=new FileOutputStream(file);
                img.compress(Bitmap.CompressFormat.JPEG, 90, out);
                out.flush();
                out.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==CAMERA_RESULT){
            try {
                Bitmap bitmap=MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(),
                        imgUri);
                bitmap=automatic_rotateImg(bitmap);
                imgVPic.setImageBitmap(bitmap);
                replace_fileImagetoFixImage(bitmap);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
