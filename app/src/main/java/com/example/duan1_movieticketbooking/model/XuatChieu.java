package com.example.duan1_movieticketbooking.model;

public class XuatChieu {
    private int id;
    private RapChieu rapChieu;
    private String thoiGianBatDau;
    private double giaVe;

    public XuatChieu() {

    }

    public XuatChieu(int id, RapChieu rapChieu, String thoiGianBatDau, double giaVe) {
        this.id = id;
        this.rapChieu = rapChieu;
        this.thoiGianBatDau = thoiGianBatDau;
        this.giaVe = giaVe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RapChieu getRapChieu() {
        return rapChieu;
    }

    public void setRapChieu(RapChieu rapChieu) {
        this.rapChieu = rapChieu;
    }

    public String getThoiGianBatDau() {
        return thoiGianBatDau;
    }

    public void setThoiGianBatDau(String thoiGianBatDau) {
        this.thoiGianBatDau = thoiGianBatDau;
    }

    public double getGiaVe() {
        return giaVe;
    }

    public void setGiaVe(double giaVe) {
        this.giaVe = giaVe;
    }
}
