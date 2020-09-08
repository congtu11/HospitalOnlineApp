package com.example.loginpassport.model;

public class Review_model {
    private int Hinh;
    private String Mota;

    public Review_model(int hinh, String mota) {
        this.Hinh = hinh;
        Mota = mota;
    }


    public int getHinh() {
        return Hinh;
    }

    public void setHinh(int hinh) {
        this.Hinh = hinh;
    }

    public String getMota() {
        return Mota;
    }

    public void setMota(String mota) {
        Mota = mota;
    }
}
