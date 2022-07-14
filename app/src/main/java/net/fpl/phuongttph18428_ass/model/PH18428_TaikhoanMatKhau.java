package net.fpl.phuongttph18428_ass.model;

import java.io.Serializable;

public class PH18428_TaikhoanMatKhau implements Serializable {
    private String tenTaiKhoan, matKhau;

    public PH18428_TaikhoanMatKhau() {
    }

    public PH18428_TaikhoanMatKhau(String tenTaiKhoan, String matKhau) {
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
    }

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }


}
