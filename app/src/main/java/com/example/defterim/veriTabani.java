package com.example.defterim;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class veriTabani extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Defter";
    private static final String TABLO_VERILER = "Veriler";
    private static final String ROW_ID = "id";
    private static final String ROW_BASLIK = "baslik";
    private static final String ROW_ICERIK = "icerik";
    private static final int DATABASE_VERSION = 1;


    public veriTabani(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLO_VERILER + "("
                + ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ROW_BASLIK + " TEXT NOT NULL, "
                + ROW_ICERIK + " TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLO_VERILER);
        onCreate(db);
    }

    public void veriEkle(String baslik, String icerik) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues cv = new ContentValues();
            cv.put(ROW_BASLIK, baslik);
            cv.put(ROW_ICERIK, icerik);
            db.insert(TABLO_VERILER, null, cv);
        } catch (Exception e) {
        }
        db.close();
    }

    public void veriSil(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            String where = ROW_ID + " = " + id;
            db.delete(TABLO_VERILER, where, null);
        } catch (Exception e) {
        }
        db.close();
    }

    public void veriGuncelle(int id, String baslik, String icerik) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues cv = new ContentValues();
            cv.put(ROW_BASLIK, baslik);
            cv.put(ROW_ICERIK, icerik);
            String where = ROW_ID + " = '" + id + "'";
            db.update(TABLO_VERILER, cv, where, null);
        } catch (Exception e) {
        }
        db.close();
    }

    public List<String> VeriListele() {
        List<String> veriler = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            String[] stunlar = {ROW_ID, ROW_BASLIK, ROW_ICERIK};
            Cursor cursor = db.query(TABLO_VERILER, stunlar, null, null, null, null, null);
            while (cursor.moveToNext()) {
                veriler.add(cursor.getInt(0)
                        + " - "
                        + cursor.getString(1)
                + " - "
                + cursor.getString(2));
            }

        } catch (Exception e) {
        }
        db.close();
        return veriler;
    }
}