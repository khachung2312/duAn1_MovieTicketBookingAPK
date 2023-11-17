package com.example.duan1_movieticketbooking.model;

public class GheNgoi {
    private int id;
    private XuatChieu xuatChieu;
    private String soGhe;
    private String trangThai;

    public GheNgoi() {
    }

    public GheNgoi(int id, XuatChieu xuatChieu, String soGhe, String trangThai) {
        this.id = id;
        this.xuatChieu = xuatChieu;
        this.soGhe = soGhe;
        this.trangThai = trangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public XuatChieu getXuatChieu() {
        return xuatChieu;
    }

    public void setXuatChieu(XuatChieu xuatChieu) {
        this.xuatChieu = xuatChieu;
    }

    public String getSoGhe() {
        return soGhe;
    }

    public void setSoGhe(String soGhe) {
        this.soGhe = soGhe;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
