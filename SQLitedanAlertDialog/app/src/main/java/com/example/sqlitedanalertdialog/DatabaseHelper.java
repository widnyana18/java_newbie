package com.example.sqlitedanalertdialog;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABSE_VERSION=1;
    private static final String DATABASE_NAME="Mhs_db";
    private static final  String TABLE_NAME="Mahasiswa";
    private static final  String Colum_Nim="Nim";
    private static final  String Colum_Nama="Nama";
    private static final  String Colum_Umur="Umur";
    private static final  String Colum_Path="PathFoto";

    SQLiteDatabase db;

    private static final String TABLE_CREATE="CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ("
            +Colum_Nim+" TEXT PRIMARY KEY, "
        + Colum_Nama+" TEXT, "
        + Colum_Path+" TEXT, "
        + Colum_Umur+" INTEGER "
        +")";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null,DATABSE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL(TABLE_CREATE);
        this.db=sqLiteDatabase;
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion){
        int result=0;
        String query=" DROP TABLE IF EXISTS "+TABLE_NAME;
        this.db.execSQL(query);
    }
    public int getCountData(){
        int result=0;
        this.db=this.getReadableDatabase();
        Cursor cursor=this.db.query(TABLE_NAME, new String[]{Colum_Nim},
                null,null,null,null,Colum_Nim+" ASC");

        result=cursor.getCount();
        db.close();
        return result;
    }
    public MyMahasiswa getDataExist(String key){
        MyMahasiswa mhs=null;
        String query="SELECT * FROM "+TABLE_NAME+
                "WHERE upper("+Colum_Nim+")='"+key.toUpperCase()+"' ";
        this.db=this.getReadableDatabase();
        Cursor cursor=this.db.rawQuery(query, null);
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            mhs=new MyMahasiswa(
                    cursor.getString(cursor.getColumnIndex(Colum_Nim)),
                    cursor.getString(cursor.getColumnIndex(Colum_Nama))
            );
        }
        db.close();
        return mhs;
    }
    public ArrayList<MyMahasiswa> transferArrayList(){
        this.db=this.getReadableDatabase();
        Cursor cursor=this.db.query(TABLE_NAME, new String[]{Colum_Nim,Colum_Nama,Colum_Path,Colum_Umur},
                null,null,null,null,Colum_Nim+" ASC");
        ArrayList<MyMahasiswa> mymhs=null;
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            mymhs=new ArrayList<MyMahasiswa>();
            do{
                mymhs.add(
                        new MyMahasiswa(cursor.getString(cursor.getColumnIndex(Colum_Nim)),
                                cursor.getString(cursor.getColumnIndex(Colum_Nama))
                        )
                );
            }while(cursor.moveToNext());
        }
        this.db.close();
        return mymhs;
    }
    public int deleteData(String nim){
        int jmldelete=-1;
        try{
            db=this.getWritableDatabase();
            String where="nim=?";
            jmldelete=db.delete(TABLE_NAME,where,new String[]{nim});
            db.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return jmldelete;
    }
    public boolean updateData(String nim, String nama, String path, int umur){
        boolean benar=false;
        try{
            ContentValues updatevalues=new ContentValues();
            updatevalues.put(Colum_Nama, nama);
            updatevalues.put(Colum_Path, path);
            updatevalues.put(Colum_Umur, umur);
            String where="nim=?";
            db=this.getReadableDatabase();
            db.update(TABLE_NAME,updatevalues,where,new String[]{nim});
            db.close();
            benar=true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return benar;
    }
    public boolean insertData(MyMahasiswa mhs){
        boolean benar=false;
        try{
            ContentValues newvalues = new ContentValues();
            newvalues.put(Colum_Nim, mhs.getNim());
            newvalues.put(Colum_Nama, mhs.getNama());
            newvalues.put(Colum_Path, mhs.getPath());
            newvalues.put(Colum_Umur, mhs.getUmur());

            db = this.getWritableDatabase();
            long result = db.insert(TABLE_NAME, null, newvalues);
            benar = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return benar;
    }
}
