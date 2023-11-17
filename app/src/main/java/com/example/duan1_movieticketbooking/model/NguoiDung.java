package com.example.duan1_movieticketbooking.model;

public class NguoiDung {
    private int id;
    private String taiKhoan;
    private String hoTen;
    private String matKhau;

    public NguoiDung() {
    }

    public NguoiDung(int id, String taiKhoan, String hoTen, String matKhau) {
        this.id = id;
        this.taiKhoan = taiKhoan;
        this.hoTen = hoTen;
        this.matKhau = matKhau;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
}
