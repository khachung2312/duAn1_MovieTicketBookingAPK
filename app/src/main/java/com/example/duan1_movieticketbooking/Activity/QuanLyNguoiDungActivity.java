package com.example.duan1_movieticketbooking.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.duan1_movieticketbooking.R;
import com.example.duan1_movieticketbooking.adapter.QuanLyNguoiDungAdapter;
import com.example.duan1_movieticketbooking.dao.TaiKhoanDao;
import com.example.duan1_movieticketbooking.model.TaiKhoan;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class QuanLyNguoiDungActivity extends AppCompatActivity {
    ImageView imgBack;
    RecyclerView rcvNguoiDung;
    TaiKhoanDao taiKhoanDao;
    QuanLyNguoiDungAdapter quanLyNguoiDungAdapter;
    LayoutInflater inflater;
    FloatingActionButton fltAddNguoiDung;
    ArrayList<TaiKhoan> arrNguoiDung = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_nguoi_dung);

        imgBack = findViewById(R.id.img_back_atv_quanlynguoidung);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        rcvNguoiDung = findViewById(R.id.rcv_quanlynguoidung);

        inflater = getLayoutInflater();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcvNguoiDung.setLayoutManager(layoutManager);
        taiKhoanDao = new TaiKhoanDao(this);
        arrNguoiDung = (ArrayList<TaiKhoan>) taiKhoanDao.getAllNguoiDung();
        quanLyNguoiDungAdapter = new QuanLyNguoiDungAdapter(this, arrNguoiDung);
        rcvNguoiDung.setAdapter(quanLyNguoiDungAdapter);

        fltAddNguoiDung = findViewById(R.id.float_btn_add_nguoi_dung);

        fltAddNguoiDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QuanLyNguoiDungActivity.this, DangKyActivity.class));
                Toast.makeText(QuanLyNguoiDungActivity.this, "Bắt đầu tạo tài khoản !!!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}