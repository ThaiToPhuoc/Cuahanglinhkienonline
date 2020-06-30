package vn.edu.ntu.phuocthai.cuahanglinhkienonline.Model;

public class loailinhkien {
    public int ID;
    public String Tenloai;
    public String HinhAnh;

    public loailinhkien(int ID, String tenloai, String hinhAnh) {
        this.ID = ID;
        Tenloai = tenloai;
        HinhAnh = hinhAnh;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenloai() {
        return Tenloai;
    }

    public void setTenloai(String tenloai) {
        Tenloai = tenloai;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }
}
