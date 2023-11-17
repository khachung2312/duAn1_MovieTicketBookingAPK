package com.example.duan1_movieticketbooking.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.duan1_movieticketbooking.database.DbHelper;
import com.example.duan1_movieticketbooking.model.Phim;

import java.util.ArrayList;
import java.util.List;

public class PhimDao {
    SQLiteDatabase sqLiteDatabase;
    Context context;
    public static  final  String TABLE_NAME_PHIM = DbHelper.TABLE_NAME_PHIM;
    public PhimDao(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
        this.context = context;
    }

    public List<Phim> getAllPhim() {
        String sql = "SELECT * FROM " + TABLE_NAME_PHIM;
        return getData(sql);
    }

    private List<Phim> getData(String sql, String... selectionArgs) {
        List<Phim> listPhim = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            Phim phim = new Phim();
            phim.setId(cursor.getInt(cursor.getColumnIndex("id")));
            phim.setTenPhim(cursor.getString(cursor.getColumnIndex("tenPhim")));
            phim.setMoTa(cursor.getString(cursor.getColumnIndex("moTa")));
            phim.setThoiLuong(cursor.getInt(cursor.getColumnIndex("thoiLuong")));
            phim.setAnhDaiDien(cursor.getString(cursor.getColumnIndex("anhDaiDien")));
            phim.setTrailer(cursor.getString(cursor.getColumnIndex("trailer")));
            phim.setDoTuoiXem(cursor.getInt(cursor.getColumnIndex("doTuoiXem")));
            listPhim.add(phim);
        }
        cursor.close();
        return listPhim;
    }
}
