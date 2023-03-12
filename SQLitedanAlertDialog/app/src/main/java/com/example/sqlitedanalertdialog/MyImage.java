package com.example.sqlitedanalertdialog;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;

public class MyImage {
    protected Bitmap automatic_rotateImg(Bitmap img, String pathImg){
        Bitmap rotateBitmap=null;
        try{
            ExifInterface exifInterface = new ExifInterface(pathImg);
            int orientation=exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
            switch (orientation){
                case ExifInterface.ORIENTATION_ROTATE_90:
                rotateBitmap=rotateBitmap(img, 90);
                break;

                case ExifInterface.ORIENTATION_ROTATE_180:
                rotateBitmap=rotateBitmap(img, 180);
                break;

                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotateBitmap=rotateBitmap(img, 270);
                    break;

                case ExifInterface.ORIENTATION_NORMAL : default:
                    rotateBitmap=img;
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return rotateBitmap;
    }
    protected Bitmap rotateBitmap(Bitmap imgSource, float angle){
        Matrix matrix=new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(imgSource, 0,0, imgSource.getWidth(),imgSource.getHeight()
        ,matrix,true);
    }
}
