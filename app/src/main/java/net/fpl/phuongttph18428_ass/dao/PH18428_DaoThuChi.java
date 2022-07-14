package net.fpl.phuongttph18428_ass.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import net.fpl.phuongttph18428_ass.database.PH18428_Database;
import net.fpl.phuongttph18428_ass.model.PH18428_ThuChi;

import java.util.ArrayList;

public class PH18428_DaoThuChi {
    PH18428_Database dtb;
    SQLiteDatabase dtTC;

    public PH18428_DaoThuChi(Context context) {
        dtb = new PH18428_Database(context);
        dtTC = dtb.getReadableDatabase();
    }


    public ArrayList<PH18428_ThuChi> getTC(String sql, String... a) {
        ArrayList<PH18428_ThuChi> list = new ArrayList<>();
        Cursor cs = dtTC.rawQuery(sql, a);
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            int maTc = Integer.parseInt(cs.getString(0));
            String tenTc = cs.getString(1);
            int loaiTc = Integer.parseInt(cs.getString(2));

            PH18428_ThuChi tc = new PH18428_ThuChi(maTc, tenTc, loaiTc);
            list.add(tc);
            cs.moveToNext();
        }
        cs.close();
        return list;
    }

    //Thêm các khoản thu chi
    public boolean themTC(PH18428_ThuChi tc) {
        SQLiteDatabase db = dtb.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenKhoan", tc.getTenKhoan());
        contentValues.put("loaiKhoan", tc.isLoaiKhoan());
        long r = db.insert("THUCHI", null, contentValues);
        if (r <= 0) {
            return false;
        }
        return true;
    }

    //Xóa khoản thu chi theo mã, khi xóa khoản thu chi, các dữ liệu bên giao dịch thuộc khoản thu chi cũng phải xóa theo
    public boolean xoaTC(PH18428_ThuChi tc) {
        SQLiteDatabase db = dtb.getWritableDatabase();
        int r = db.delete("THUCHI", "maKhoan=?", new String[]{String.valueOf(tc.getMaKhoan())});
        int s = db.delete("GIAODICH", "maKhoan=?", new String[]{String.valueOf(tc.getMaKhoan())});
        if (r <= 0) {
            return false;
        }
        return true;
    }

    //Sửa khoản thu chi theo mã thu chi
    public boolean suaTC(PH18428_ThuChi tc) {
        SQLiteDatabase db = dtb.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenKhoan", tc.getTenKhoan());
        contentValues.put("loaiKhoan", tc.isLoaiKhoan());
        int r = db.update("THUCHI", contentValues, "maKhoan=?", new String[]{String.valueOf(tc.getMaKhoan())});
        if (r <= 0) {
            return false;
        }
        return true;
    }

    //lấy toàn bộ ds thu chi
    public ArrayList<PH18428_ThuChi> getAll() {
        String sql = "SELECT * FROM THUCHI";
        return getTC(sql);
    }

    //show danh sách theo thu hoăc chi
    public ArrayList<PH18428_ThuChi> getThuChi(int loaiKhoan) {
        String sql = "SELECT * FROM THUCHI WHERE loaiKhoan=?";
        ArrayList<PH18428_ThuChi> list = getTC(sql, String.valueOf(loaiKhoan));
        return list;
    }

    //Lấy tên theo mã khoản
    public String getTen(int maKhoan) {
        String tenKhoan = "";

        String sql = "SELECT * FROM THUCHI WHERE maKhoan=?";
        ArrayList<PH18428_ThuChi> list = getTC(sql, String.valueOf(maKhoan));
        return list.get(0).getTenKhoan();
    }
}
