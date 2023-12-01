package com.example.duan1_movieticketbooking.Activity;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1_movieticketbooking.adapter.RapChieuAdapter;
import com.example.duan1_movieticketbooking.dao.PhimDao;
import com.example.duan1_movieticketbooking.dao.RapChieuDao;
import com.example.duan1_movieticketbooking.model.Phim;
import com.example.duan1_movieticketbooking.model.RapChieu;
import com.harrywhewell.scrolldatepicker.DayScrollDatePicker;
import com.harrywhewell.scrolldatepicker.OnDateSelectedListener;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.graphics.drawable.ColorDrawable;

import com.example.duan1_movieticketbooking.R;

public class RapChieuActivity extends AppCompatActivity {

    DayScrollDatePicker mPicker;
    RecyclerView rcvDanhSachRap;
    RapChieuAdapter rapChieuAdapter;

    RapChieuDao rapChieuDao;
    ArrayList<RapChieu> arrRapChieu = new ArrayList<>();
    Context mContext;
    LayoutInflater inflater;
    ImageView imgBack;
    TextView tvTenPhim;
    PhimDao phimDao;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rap_chieu);
        mContext = this;
        tvTenPhim = findViewById(R.id.tv_tenphim_atv_rapchieu);
        int phimId = getIntent().getIntExtra("phim_id", 0);
        phimDao = new PhimDao(this);
        Phim phim = phimDao.getPhimById(phimId);
        tvTenPhim.setText(phim.getTenPhim());

        imgBack = findViewById(R.id.img_back_atv_rapchieu);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mPicker = findViewById(R.id.calendar_lichchieu);

        LocalDate currentDate = LocalDate.now();

        int startDay = currentDate.minusDays(5).getDayOfMonth();
        int startMonth = currentDate.minusDays(5).getMonthValue();
        int startYear = currentDate.minusDays(5).getYear();


        mPicker.setStartDate(startDay, startMonth, startYear);

        int endDay = currentDate.plusDays(5).getDayOfMonth();
        int endMonth = currentDate.plusDays(5).getMonthValue();
        int endYear = currentDate.plusDays(5).getYear();

        mPicker.setEndDate(endDay, endMonth, endYear);


        mPicker.getSelectedDate(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@Nullable Date date) {
                if (date != null) {
                    // Chuyển đổi Date thành LocalDate
                    Instant instant = date.toInstant();
                    LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();

                    // Chuyển đổi LocalDate thành chuỗi có định dạng 'dd-MM-yyyy'
                    String ngayChieu = localDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    LocalDate ngayChieuLocalDate = LocalDate.parse(ngayChieu, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    // Gọi phương thức getRapChieuTheoNgay() để lấy danh sách rạp chiếu dựa trên ngày chọn
                    RapChieuDao rapChieuDao = new RapChieuDao(mContext);
                    List<RapChieu> danhSachRapChieu = rapChieuDao.getRapChieuTheoNgay(ngayChieuLocalDate);

                    // Cập nhật danh sách rạp chiếu trong adapter và hiển thị lại RecyclerView
                    rapChieuAdapter.updateData(danhSachRapChieu);
                    rcvDanhSachRap.setAdapter(rapChieuAdapter);

                }
            }
        });


        rcvDanhSachRap = findViewById(R.id.rcv_danhsachrap);

        inflater = getLayoutInflater();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rcvDanhSachRap.setLayoutManager(layoutManager);
        rapChieuDao = new RapChieuDao(mContext);
        arrRapChieu = (ArrayList<RapChieu>) rapChieuDao.getAllRapChieu();
        rapChieuAdapter = new RapChieuAdapter(mContext, arrRapChieu);


    }


}