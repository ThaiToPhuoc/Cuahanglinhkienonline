package vn.edu.ntu.phuocthai.cuahanglinhkienonline.Model;

public class linhkien {
    public int ID;
    public String TenLinhKien;
    public int DonGia;
    public String HinhAnh;
    public String MoTa;
    public int IDLoaiLK;

    public linhkien(int ID, String tenLinhKien, int donGia, String hinhAnh, String moTa, int IDLoaiLK) {
        this.ID = ID;
        TenLinhKien = tenLinhKien;
        DonGia = donGia;
        HinhAnh = hinhAnh;
        MoTa = moTa;
        this.IDLoaiLK = IDLoaiLK;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenLinhKien() {
        return TenLinhKien;
    }

    public void setTenLinhKien(String tenLinhKien) {
        TenLinhKien = tenLinhKien;
    }

    public int getDonGia() {
        return DonGia;
    }

    public void setDonGia(int donGia) {
        DonGia = donGia;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }

    public int getIDLoaiLK() {
        return IDLoaiLK;
    }

    public void setIDLoaiLK(int IDLoaiLK) {
        this.IDLoaiLK = IDLoaiLK;
    }
}
