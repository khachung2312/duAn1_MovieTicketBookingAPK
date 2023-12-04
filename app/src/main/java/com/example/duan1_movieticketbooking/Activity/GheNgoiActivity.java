package com.example.duan1_movieticketbooking.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1_movieticketbooking.R;
import com.example.duan1_movieticketbooking.adapter.GheNgoiAdapter;
import com.example.duan1_movieticketbooking.dao.GheNgoiDao;
import com.example.duan1_movieticketbooking.dao.RapChieuDao;
import com.example.duan1_movieticketbooking.dao.TaiKhoanDao;
import com.example.duan1_movieticketbooking.dao.VePhimDao;
import com.example.duan1_movieticketbooking.dao.XuatChieuDao;
import com.example.duan1_movieticketbooking.model.GheNgoi;
import com.example.duan1_movieticketbooking.model.RapChieu;
import com.example.duan1_movieticketbooking.model.VePhim;
import com.example.duan1_movieticketbooking.model.XuatChieu;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.time.LocalDate;
import java.util.ArrayList;

public class GheNgoiActivity extends AppCompatActivity {
    ImageView imgBack;
    TextView tvTenRap, tvGioChieu, tvNgayChieu, tvGiaVe, tvGheDat, tvTongTien;
    AppCompatButton btnDatVe;
    XuatChieuDao xuatChieuDao;
    RapChieuDao rapChieuDao;
    RecyclerView rcvGheNgoi;
    GheNgoiAdapter gheNgoiAdapter;

    GheNgoiDao gheNgoiDao;
    ArrayList<GheNgoi> arrGheNgoi = new ArrayList<>();
    Context mContext;
    LayoutInflater inflater;
    SharedPreferences sharedPreferences;
    VePhimDao vePhimDao;
    TaiKhoanDao taiKhoanDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghe_ngoi);
        mContext = GheNgoiActivity.this;
        imgBack = findViewById(R.id.img_back_atv_ghengoi);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tvTenRap = findViewById(R.id.tv_tenrap_atv_ghengoi);
        tvGioChieu = findViewById(R.id.tv_giochieu_atv_ghengoi);
        tvNgayChieu = findViewById(R.id.tv_ngaychieu_atv_ghengoi);
        tvGiaVe = findViewById(R.id.tv_gia_avt_ghengoi);
        tvGheDat = findViewById(R.id.tv_ghedat);
        tvTongTien = findViewById(R.id.tv_tongtien);
        btnDatVe = findViewById(R.id.btn_datve_atv_ghengoi);

        int rapChieuId = getIntent().getIntExtra("rapchieu_id", -1);
        int xuatChieuId = getIntent().getIntExtra("xuatchieu_id", -1);

        xuatChieuDao = new XuatChieuDao(this);
        rapChieuDao = new RapChieuDao(this);

        // Lấy thông tin rap chieu từ RapChieuDao dựa trên ID
        RapChieu rapChieu = rapChieuDao.getRapChieuById(rapChieuId);

        if (rapChieu != null) {
            tvTenRap.setText(rapChieu.getTenRap());
            tvNgayChieu.setText(rapChieu.getNgayChieu());
        }

        // Lấy thông tin xuat chieu từ XuatChieuDao dựa trên ID
        XuatChieu xuatChieu = xuatChieuDao.getXuatChieuById(xuatChieuId);

        if (xuatChieu != null) {
            tvGioChieu.setText(xuatChieu.getThoiGianBatDau());
            tvGiaVe.setText(xuatChieu.getGiaVe() + "");
        }

        rcvGheNgoi = findViewById(R.id.rcv_ghengoi);

        inflater = getLayoutInflater();
        int numberOfColumns = 5; // So cot hien thi
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(mContext, numberOfColumns);
        rcvGheNgoi.setLayoutManager(layoutManager);
        rcvGheNgoi.setLayoutManager(layoutManager);
        gheNgoiDao = new GheNgoiDao(mContext);
        arrGheNgoi = (ArrayList<GheNgoi>) gheNgoiDao.getGheNgoiByIdXuatChieu(xuatChieuId);
        gheNgoiAdapter = new GheNgoiAdapter(mContext, arrGheNgoi);
        rcvGheNgoi.setAdapter(gheNgoiAdapter);

        final int[] tongTien = {0};

        gheNgoiAdapter.setOnGheNgoiClickListener(new GheNgoiAdapter.OnGheNgoiClickListener() {

            @Override
            public void onGheNgoiClick(GheNgoi gheNgoi) {
                String currentGhe = tvGheDat.getText().toString();
                String soGhe = gheNgoi.getSoGhe(); // Chuỗi khác muốn thêm vào

                // Thực hiện phép cộng chuỗi
                int giaVe = xuatChieu.getGiaVe();
                String chuoiGhe = currentGhe + soGhe;

                if (gheNgoi.getTrangThai() == 1) {
                    // Cắt chuỗi vừa được thêm vào của một item
                    String[] chuoiGheArray = chuoiGhe.split(soGhe);
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < chuoiGheArray.length - 1; i++) {
                        stringBuilder.append(chuoiGheArray[i]);
                    }
                    tvGheDat.setText(stringBuilder.toString());

                    tongTien[0] -= giaVe;

                } else if (gheNgoi.getTrangThai() == 0) {
                    tvGheDat.setText(chuoiGhe + ", ");
                    tongTien[0] += giaVe;
                }
                tvTongTien.setText(tongTien[0] + " VND");

            }
        });


        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String username = sharedPreferences.getString("username", "");

        taiKhoanDao = new TaiKhoanDao(this);
        int userId = taiKhoanDao.getUserIdByUsername(username);


        vePhimDao = new VePhimDao(this);
        btnDatVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(GheNgoiActivity.this);
                View dialogView = getLayoutInflater().inflate(R.layout.dialog_dat_ve, null);
                builder.setView(dialogView);
                AlertDialog dialogSuccess = builder.create();


                boolean hasSelectedSeat = false;
                for (GheNgoi gheNgoi : arrGheNgoi) {
                    if (gheNgoi.getTrangThai() == 1) {
                        hasSelectedSeat = true;
                        // Cập nhật trạng thái ghế thành 2 là đã đặt
                        gheNgoiDao.setGheTrangThai(gheNgoi.getId(), 2);
                        dialogSuccess.show();
                        AppCompatButton btnOK = dialogSuccess.findViewById(R.id.btn_ok_dialog);
                        btnOK.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                VePhim vePhim = new VePhim();
                                vePhim.setIdXuatChieu(xuatChieu.getId());
                                vePhim.setIdTaiKhoan(userId);
                                vePhim.setTongGiaTri(tongTien[0]);
                                @SuppressLint({"NewApi", "LocalSuppress"})
                                LocalDate ngayHienTai = LocalDate.now();
                                vePhim.setNgayDatVe(String.valueOf(ngayHienTai));

                                vePhimDao.insertVePhim(vePhim);
                                dialogSuccess.dismiss();
                                finish();
                            }
                        });
                    }
                }
                if (!hasSelectedSeat) {
                    Toast.makeText(GheNgoiActivity.this, "Vui lòng chọn ghế ngồi!", Toast.LENGTH_SHORT).show();
                }

            }

        });

    }
}