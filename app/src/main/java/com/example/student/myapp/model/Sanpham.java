package com.example.student.myapp.model;

public class Sanpham {
    public int id;
    public String Tensanpham;
    public Integer Giasanpham;
    public String Hinhanhsanpham;
    public String Motasanpham;
    public int IDSanpham;

    public Sanpham(int id, String tensanpham, Integer giasanpham, String hinhanhsanpham, String motasanpham, int IDSanpham) {
        this.id = id;
        Tensanpham = tensanpham;
        Giasanpham = giasanpham;
        Hinhanhsanpham = hinhanhsanpham;
        Motasanpham = motasanpham;
        this.IDSanpham = IDSanpham;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTensanpham(String tensanpham) {
        Tensanpham = tensanpham;
    }

    public void setGiasanpham(Integer giasanpham) {
        Giasanpham = giasanpham;
    }

    public void setHinhanhsanpham(String hinhanhsanpham) {
        Hinhanhsanpham = hinhanhsanpham;
    }

    public void setMotasanpham(String motasanpham) {
        Motasanpham = motasanpham;
    }

    public void setIDSanpham(int IDSanpham) {
        this.IDSanpham = IDSanpham;
    }

    public int getId() {
        return id;
    }

    public String getTensanpham() {
        return Tensanpham;
    }

    public Integer getGiasanpham() {
        return Giasanpham;
    }

    public String getHinhanhsanpham() {
        return Hinhanhsanpham;
    }

    public String getMotasanpham() {
        return Motasanpham;
    }

    public int getIDSanpham() {
        return IDSanpham;
    }
}
