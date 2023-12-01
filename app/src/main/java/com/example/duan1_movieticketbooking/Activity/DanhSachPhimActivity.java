package com.example.duan1_movieticketbooking.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.duan1_movieticketbooking.adapter.PhimDSAdapter;
import com.example.duan1_movieticketbooking.dao.PhimDao;
import com.example.duan1_movieticketbooking.model.Phim;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.example.duan1_movieticketbooking.R;

public class DanhSachPhimActivity extends AppCompatActivity {
    ImageView imgBack;
    SearchView searchPhim;
    RecyclerView rcvDanhSachPhim;
    PhimDSAdapter phimDSAdapter;

    PhimDao phimDao;
    ArrayList<Phim> arrPhim = new ArrayList<>();
    Context mContext;
    LayoutInflater inflater;
    FloatingActionButton floatingBtnAddPhim;
    SharedPreferences sharedPreferences;
    View ViewDialogAddPhim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_phim);
        mContext = this;

        imgBack = findViewById(R.id.img_back_atv_danhsachphim);
        floatingBtnAddPhim = findViewById(R.id.float_btn_add_phim);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String username = sharedPreferences.getString("username", "");
        if (!"admin".equals(username)) {
            floatingBtnAddPhim.setVisibility(View.GONE);
        }

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        searchPhim = findViewById(R.id.search_phim);

        int searchTextColor = Color.WHITE;
        EditText searchEditText = searchPhim.findViewById(androidx.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(searchTextColor);
        String queryHint = "Tìm kiếm phim";
        searchPhim.setQueryHint(queryHint);

        rcvDanhSachPhim = findViewById(R.id.rcv_danhsachphim);

        inflater = getLayoutInflater();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rcvDanhSachPhim.setLayoutManager(layoutManager);
        phimDao = new PhimDao(mContext);
        arrPhim = (ArrayList<Phim>) phimDao.getAllPhim();
        phimDSAdapter = new PhimDSAdapter(mContext, arrPhim);
        rcvDanhSachPhim.setAdapter(phimDSAdapter);

        searchPhim.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<Phim> filteredList = (ArrayList<Phim>) phimDao.searchPhimByTenPhim(newText);
                phimDSAdapter.setFilter(filteredList);
                return true;
            }
        });
        AlertDialog.Builder builder = new AlertDialog.Builder(DanhSachPhimActivity.this);
        View dialogViewAddPhim = getLayoutInflater().inflate(R.layout.dialog_them_phim, null);
        builder.setView(dialogViewAddPhim);
        AlertDialog dialogAddPhim = builder.create();

        floatingBtnAddPhim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText edTenPhim, edMoTa, edThoiLuong, edAnhDaiDien, edTrailer, edDoTuoiXem, edNgayPhatHanh;
                Button btn_add_phim, btn_cancel_phim;
                TextInputLayout layout_ed_tenphim, layout_edMota, layout_edThoiLuong, layout_edAnhDaiDien, layout_edTrailer, layout_edDoTuoiXem, layout_edNgayPhatHanh;

                layout_ed_tenphim = dialogViewAddPhim.findViewById(R.id.input_tenphim);
                edTenPhim = dialogViewAddPhim.findViewById(R.id.ed_tenphim);
                layout_edMota = dialogViewAddPhim.findViewById(R.id.input_mota);
                edMoTa = dialogViewAddPhim.findViewById(R.id.ed_mota);
                layout_edThoiLuong = dialogViewAddPhim.findViewById(R.id.input_thoiluong);
                edThoiLuong = dialogViewAddPhim.findViewById(R.id.ed_thoiluong);
                layout_edAnhDaiDien = dialogViewAddPhim.findViewById(R.id.input_anhdaidien);
                edAnhDaiDien = dialogViewAddPhim.findViewById(R.id.ed_anhdaidien);
                layout_edTrailer = dialogViewAddPhim.findViewById(R.id.input_trailer);
                edTrailer = dialogViewAddPhim.findViewById(R.id.ed_trailer);
                layout_edDoTuoiXem = dialogViewAddPhim.findViewById(R.id.input_dotuoi);
                edDoTuoiXem = dialogViewAddPhim.findViewById(R.id.ed_dotuoi);
                layout_edNgayPhatHanh = dialogViewAddPhim.findViewById(R.id.input_ngayphathanh);
                edNgayPhatHanh = dialogViewAddPhim.findViewById(R.id.ed_ngayphathanh);

                btn_cancel_phim = dialogViewAddPhim.findViewById(R.id.btn_dialog_cancle_add_phim);
                btn_add_phim = dialogViewAddPhim.findViewById(R.id.btn_dialog_add_phim);

                edNgayPhatHanh.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Calendar calendar = Calendar.getInstance();
                        DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                edNgayPhatHanh.setText(i2 + "/"+ (i1 + 1) + "/" + i);
                            }
                        }, calendar.get(Calendar.YEAR),
                                calendar.get(Calendar.MONTH),
                                calendar.get(Calendar.DAY_OF_MONTH));
                        datePickerDialog.show();
                    }
                });

                dialogAddPhim.show();
                btn_cancel_phim.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogAddPhim.dismiss();
                    }
                });

                btn_add_phim.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int check = 1;
                        if (edTenPhim.getText().toString().isEmpty()) {
                            layout_ed_tenphim.setError("Vui lòng thêm tên phim!");
                            check = -1;
                        } else {
                            layout_ed_tenphim.setError("");
                        }
                        if (edMoTa.getText().toString().isEmpty()) {
                            layout_edMota.setError("Vui lòng thêm mô tả!");
                            check = -1;
                        } else {
                            layout_edMota.setError("");
                        }
                        String thoiLuongText = edThoiLuong.getText().toString();
                        if (TextUtils.isEmpty(thoiLuongText)) {
                            layout_edThoiLuong.setError("Vui lòng thêm thời lượng phim!");
                            check = -1;
                        } else if (!TextUtils.isDigitsOnly(thoiLuongText)) {
                            layout_edThoiLuong.setError("Thời lượng phim phải là một số!");
                            check = -1;
                        } else {
                            layout_edThoiLuong.setError("");
                        }
                        if (edAnhDaiDien.getText().toString().isEmpty()) {
                            layout_edAnhDaiDien.setError("Vui lòng thêm ảnh đại diện!");
                            check = -1;
                        } else {
                            layout_edAnhDaiDien.setError("");
                        }
                        if (edTrailer.getText().toString().isEmpty()) {
                            layout_edTrailer.setError("Vui lòng thêm trailer phim!");
                            check = -1;
                        } else {
                            layout_edTrailer.setError("");
                        }
                        String doTuoiText = edDoTuoiXem.getText().toString();
                        if (TextUtils.isEmpty(doTuoiText)) {
                            layout_edDoTuoiXem.setError("Vui lòng thêm độ tuổi xem!");
                            check = -1;
                        } else if (!TextUtils.isDigitsOnly(doTuoiText)) {
                            layout_edDoTuoiXem.setError("Độ tuổi phải là số!");
                            check = -1;
                        }
                        else {
                            layout_edDoTuoiXem.setError("");
                        }
                        if (edNgayPhatHanh.getText().toString().isEmpty()) {
                            layout_edNgayPhatHanh.setError("Vui lòng thêm ngày phát hành!");
                            check = -1;
                        } else {
                            layout_edNgayPhatHanh.setError("");
                        }

                        if (check > 0) {
                            Phim phim = new Phim();
                            phim.setTenPhim(edTenPhim.getText().toString());
                            phim.setMoTa(edMoTa.getText().toString());
                            phim.setThoiLuong(Integer.parseInt(edThoiLuong.getText().toString()));
                            phim.setAnhDaiDien(edAnhDaiDien.getText().toString());
                            phim.setTrailer(edTrailer.getText().toString());
                            phim.setDoTuoiXem(Integer.parseInt(edDoTuoiXem.getText().toString()));
                            phim.setNgayPhatHanh(edNgayPhatHanh.getText().toString());
                            phimDao.insertPhim(phim);

                            arrPhim = new ArrayList<>();
                            arrPhim = (ArrayList<Phim>) phimDao.getAllPhim();
                            phimDSAdapter = new PhimDSAdapter(mContext, arrPhim);
                            rcvDanhSachPhim.setAdapter(phimDSAdapter);
                            Toast.makeText(DanhSachPhimActivity.this, "Thêm phim thành công!", Toast.LENGTH_SHORT).show();
                            dialogAddPhim.dismiss();

                            edTenPhim.setText("");
                            edMoTa.setText("");
                            edThoiLuong.setText("");
                            edAnhDaiDien.setText("");
                            edTrailer.setText("");
                            edDoTuoiXem.setText("");
                            edNgayPhatHanh.setText("");
                            layout_ed_tenphim.setError("");
                            layout_edMota.setError("");
                            layout_edThoiLuong.setError("");
                            layout_edAnhDaiDien.setError("");
                            layout_edTrailer.setError("");
                            layout_edDoTuoiXem.setError("");
                            layout_edNgayPhatHanh.setError("");
                        }

                    }
                });

            }
        });


    }


}