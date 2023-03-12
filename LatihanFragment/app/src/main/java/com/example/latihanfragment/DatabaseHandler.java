package com.example.latihanfragment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION=1; //Versi Database
    private static final String DATABASE_NAME="MahasiswaDB"; //Nama Database
    private static final String TABLE_MAHASISWA="tb_mahasiswa";
    private static final String KEY_ID="id"; private static final String KEY_NIM="nim"; private static final String KEY_NAMA="nama"; private static final String KEY_PRODI="prodi";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//Perintah untuk membuat Tabel
        String CREATE_TABLE_MAHASISWA="CREATE TABLE "+ TABLE_MAHASISWA + "("
                + KEY_ID + " INTEGER PRIMARY KEY, "+ KEY_NIM + " TEXT,	"
                + KEY_NAMA + " TEXT, " + KEY_PRODI + " TEXT" +")";
        sqLiteDatabase.execSQL(CREATE_TABLE_MAHASISWA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_MAHASISWA);
        onCreate(sqLiteDatabase);
    }

    public void save(Mahasiswa mahasiswa)
    {
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NIM, mahasiswa.getNimm());
        values.put(KEY_NAMA, mahasiswa.getNamaa());
        values.put(KEY_PRODI, mahasiswa.getProdi());
        db.insert(TABLE_MAHASISWA,null, values);
        db.close();
    }

    public void update(Mahasiswa mahasiswa)
    {
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NIM, mahasiswa.getNimm());
        values.put(KEY_NAMA, mahasiswa.getNamaa());
        values.put(KEY_PRODI, mahasiswa.getProdi());
        db.update(TABLE_MAHASISWA, values, KEY_ID+"=?",
            new String[]{String.valueOf(mahasiswa.getId())}); db.close();
    }
    public void delete(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MAHASISWA, KEY_ID+"=?", new String[]{id});
        db.close();
    }

    //Mengambil nilai mahasiswa
    public List<Mahasiswa> findAll()
    {
        List<Mahasiswa> mahasiswaList = new ArrayList<Mahasiswa>();
        String query="SELECT * FROM "+TABLE_MAHASISWA;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query,null);

        if(cursor.moveToFirst())
        {
            do {
                Mahasiswa mahasiswa = new Mahasiswa();
                mahasiswa.setId(Integer.valueOf(cursor.getString(0)));
                mahasiswa.setNimm(cursor.getString(1));
                mahasiswa.setNamaa(cursor.getString(2));
                mahasiswa.setProdi(cursor.getString(3));

                mahasiswaList.add(mahasiswa);

            }while (cursor.moveToNext());
        }
        return mahasiswaList;
    }

}


