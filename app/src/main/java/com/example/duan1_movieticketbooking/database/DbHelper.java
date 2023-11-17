package com.example.duan1_movieticketbooking.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    static final String dbName = "MTBA";
    static final int dbVersion = 3;
    public static final String TABLE_NAME_PHIM = "phim";
    public static final String TABLE_NAME_NGUOIDUNG = "nguoidung";
    public static final String TABLE_NAME_RAPCHIEU = "rapchieu";
    public static final String TABLE_NAME_XUATCHIEU = "xuatchieu";
    public static final String TABLE_NAME_GHENGOI = "ghengoi";
    public static final String TABLE_NAME_VEPHIM = "vephim";

    public DbHelper(@Nullable Context context) {
        super(context, dbName, null, dbVersion);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTablePhim =
                "CREATE TABLE " + TABLE_NAME_PHIM + "(" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "tenPhim TEXT NOT NULL, " +
                        "moTa TEXT NOT NULL, " +
                        "thoiLuong INTEGER NOT NULL, " +
                        "anhDaiDien TEXT NOT NULL, " +
                        "trailer TEXT NOT NULL, " +
                        "doTuoiXem INTEGER NOT NULL)";
        db.execSQL(createTablePhim);

        String setPhim1 = "INSERT INTO phim VALUES(1, 'Phim 1', 'Mota 1', 120, 'https://d1j8r0kxyu9tj8.cloudfront.net/images/1566809230YFLaGg3GI3SV21M.jpg', 'linktrailer1', 13)";
        db.execSQL(setPhim1);

        String setPhim2 = "INSERT INTO phim VALUES(2, 'Phim 2', 'Mota 2', 110,'https://arena.fpt.edu.vn/wp-content/uploads/2021/04/poster-phim-la-gi.jpg', 'linktrailer2', 16)";
        db.execSQL(setPhim2);

        String setPhim3 = "INSERT INTO phim VALUES(3, 'Phim 3', 'Mota 3', 130, 'https://youthvietnam.vn/wp-content/uploads/2021/06/Cac-yeu-to-giup-poster-phim-thanh-cong.jpg', 'linktrailer3', 13)";
        db.execSQL(setPhim3);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String dropTablePhim = "drop table if exists phim";
        db.execSQL(dropTablePhim);
        onCreate(db);

    }
}
