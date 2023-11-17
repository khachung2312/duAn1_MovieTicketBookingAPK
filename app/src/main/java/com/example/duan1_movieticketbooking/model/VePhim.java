package com.example.duan1_movieticketbooking.model;

public class VePhim {
    private int id;
    private RapChieu rapChieu;
    private NguoiDung nguoiDung;
    private double tongGiaTri;

    public VePhim() {
    }

    public VePhim(int id, RapChieu rapChieu, NguoiDung nguoiDung, double tongGiaTri) {
        this.id = id;
        this.rapChieu = rapChieu;
        this.nguoiDung = nguoiDung;
        this.tongGiaTri = tongGiaTri;
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

    public NguoiDung getNguoiDung() {
        return nguoiDung;
    }

    public void setNguoiDung(NguoiDung nguoiDung) {
        this.nguoiDung = nguoiDung;
    }

    public double getTongGiaTri() {
        return tongGiaTri;
    }

    public void setTongGiaTri(double tongGiaTri) {
        this.tongGiaTri = tongGiaTri;
    }
}
