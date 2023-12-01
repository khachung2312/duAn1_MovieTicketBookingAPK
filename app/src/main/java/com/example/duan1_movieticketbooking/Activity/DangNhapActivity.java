package com.example.duan1_movieticketbooking.Activity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.duan1_movieticketbooking.R;
import com.example.duan1_movieticketbooking.dao.TaiKhoanDao;


public
class DangNhapActivity extends AppCompatActivity {
    EditText edTenTaiKhoan, edMatKhau;
    CheckBox cbxGhiNhoPass;
    AppCompatButton btnDangNhap;
    TextView tvDangKy, tvGoToTrangChu;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);

        edTenTaiKhoan = findViewById(R.id.ed_tentaikhoan_atv_dangnhap);
        edMatKhau = findViewById(R.id.ed_matkhau_atv_dangnhap);
        cbxGhiNhoPass = findViewById(R.id.cbx_ghinhopass);
        btnDangNhap = findViewById(R.id.btn_dangnhap);
        tvDangKy = findViewById(R.id.tv_dangky);
        tvGoToTrangChu = findViewById(R.id.tv_goto_tranchu);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        edTenTaiKhoan.setText(sharedPreferences.getString("username_remember", ""));
        edMatKhau.setText(sharedPreferences.getString("password_remember", ""));
        cbxGhiNhoPass.setChecked(sharedPreferences.getBoolean("cbx_remember", false));

        TaiKhoanDao taiKhoanDao = new TaiKhoanDao(this);

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenTaiKhoan = edTenTaiKhoan.getText().toString();
                String matKhau = edMatKhau.getText().toString();

                if (taiKhoanDao.checkdangnhap(tenTaiKhoan, matKhau)) {
                    sharedPreferences.edit().putString("username", tenTaiKhoan).apply();
                    if (cbxGhiNhoPass.isChecked()) {
                        sharedPreferences.edit().putString("username_remember", tenTaiKhoan).apply();
                        sharedPreferences.edit().putString("password_remember", matKhau).apply();
                        sharedPreferences.edit().putBoolean("cbx_remember", true).apply();
                    } else {
                        sharedPreferences.edit().clear().apply();
                    }

                    startActivity(new Intent(DangNhapActivity.this, MainActivity.class));
                    Toast.makeText(DangNhapActivity.this, "Đăng nhập thành công !!!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DangNhapActivity.this, "Đăng nhập thất bại !!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        tvDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DangNhapActivity.this, DangKyActivity.class));
                Toast.makeText(DangNhapActivity.this, "Bắt đầu tạo tài khoản !!!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        tvGoToTrangChu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DangNhapActivity.this, MainActivity.class));
                finish();
            }
        });

    }
}