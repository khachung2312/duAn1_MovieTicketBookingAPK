package com.example.duan1_movieticketbooking.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.duan1_movieticketbooking.R;
import com.example.duan1_movieticketbooking.dao.PhimDao;
import com.example.duan1_movieticketbooking.model.Phim;
import com.squareup.picasso.Picasso;
import android.widget.Toast;

import java.util.ArrayList;

public class PhimActivity extends AppCompatActivity {

    VideoView videoTrailer;
    ImageView imgAnhDaiDien, imgTrailer;
    TextView tvTenPhim, tvNgayPhatHanh, tvThoiLuong, tvDoTuoi, tvMota;
    AppCompatButton btnDatVe;
    ImageView imgBack;
    PhimDao phimDao;
    ArrayList<Phim> arrPhim = new ArrayList<>();
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phim);

        imgBack = findViewById(R.id.img_back_atv_phim);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        videoTrailer = findViewById(R.id.video_trailer);
        imgTrailer = findViewById(R.id.img_trailer);
        imgAnhDaiDien = findViewById(R.id.img_anhdaidien);
        tvTenPhim = findViewById(R.id.tv_tenphim);
        tvNgayPhatHanh = findViewById(R.id.tv_ngayphathanh);
        tvThoiLuong = findViewById(R.id.tv_thoiluong);
        tvDoTuoi = findViewById(R.id.tv_dotuoi);
        tvMota = findViewById(R.id.tv_mota);

        btnDatVe = findViewById(R.id.btn_datve_atv_phim);

        int phimId = getIntent().getIntExtra("phim_id", -1);

        phimDao = new PhimDao(this);

        // Lấy thông tin phim từ PhimDao dựa trên ID
        Phim phim = phimDao.getPhimById(phimId);

        if (phim != null) {
            Picasso.get().load(phim.getAnhDaiDien()).into(imgAnhDaiDien);
            Picasso.get().load(phim.getAnhDaiDien()).into(imgTrailer);
            tvTenPhim.setText(phim.getTenPhim());
            tvNgayPhatHanh.setText(phim.getNgayPhatHanh());
            tvThoiLuong.setText(String.valueOf(phim.getThoiLuong()) + " phút");
            tvDoTuoi.setText(String.valueOf(phim.getDoTuoiXem()) + " +");
            tvMota.setText(phim.getMoTa());
        }


        final String trailerUrl = phim.getTrailer();
        videoTrailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(trailerUrl));
                startActivity(intent);
            }
        });

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String username = sharedPreferences.getString("username", "");

        if ("admin".equals(username)) {
            btnDatVe.setVisibility(View.GONE);
        }
        btnDatVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = sharedPreferences.getString("username", "");

                if (username.isEmpty()) {
                    // Hiển thị thông báo "Vui lòng đăng nhập"
                    Toast.makeText(PhimActivity.this, "Vui lòng đăng nhập", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(PhimActivity.this, RapChieuActivity.class);
                    intent.putExtra("phim_id", phim.getId());
                    startActivity(intent);
                }
            }
        });
    }
}