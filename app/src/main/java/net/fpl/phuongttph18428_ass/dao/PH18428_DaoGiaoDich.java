package net.fpl.phuongttph18428_ass.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import net.fpl.phuongttph18428_ass.database.PH18428_Database;
import net.fpl.phuongttph18428_ass.model.PH18428_GiaoDich;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PH18428_DaoGiaoDich {
    PH18428_Database dtb;
    SimpleDateFormat dfm = new SimpleDateFormat("dd/MM/yyyy");
    SQLiteDatabase dtGD;

    public PH18428_DaoGiaoDich(Context context) {
        dtb = new PH18428_Database(context);

    }


    public ArrayList<PH18428_GiaoDich> getGD(String sql, String... a) {
        ArrayList<PH18428_GiaoDich> list = new ArrayList<>();
        dtGD = dtb.getReadableDatabase();
        Cursor cs = dtGD.rawQuery(sql, a);
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            try {
                int ma = Integer.parseInt(cs.getString(0));
                String mota = cs.getString(1);
                String ngay = cs.getString(2);
                int tien = Integer.parseInt(cs.getString(3));
                int maK = Integer.parseInt(cs.getString(4));
                PH18428_GiaoDich gd = new PH18428_GiaoDich(ma, mota, dfm.parse(ngay), tien, maK);
                list.add(gd);
                cs.moveToNext();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        cs.close();
        return list;
    }


    public ArrayList<PH18428_GiaoDich> getAll() {
        String sql = "SELECT * FROM GIAODICH";
        return getGD(sql);
    }

    //Lấy giao dịch theo loại
    public ArrayList<PH18428_GiaoDich> getGDtheoTC(int loaiKhoan) {
        String sql = "SELECT * FROM GIAODICH as gd INNER JOIN THUCHI as tc ON gd.maKhoan = tc.maKhoan WHERE tc.loaiKhoan=?";
        ArrayList<PH18428_GiaoDich> list = getGD(sql, String.valueOf(loaiKhoan));
        return list;
    }

    public boolean themGD(PH18428_GiaoDich gd) {
        dtGD = dtb.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("moTaGd", gd.getMoTaGd());
        contentValues.put("ngayGd", dfm.format(gd.getNgayGd()));
        contentValues.put("soTien", gd.getSoTien());
        contentValues.put("maKhoan", gd.getMaKhoan());
        long r = dtGD.insert("GIAODICH", null, contentValues);
        if (r <= 0) {
            return false;
        }
        return true;
    }

    //Sửa giao dịch theo mã giao dịch
    public boolean suaGD(PH18428_GiaoDich gd) {
        SQLiteDatabase db = dtb.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("moTaGd", gd.getMoTaGd());
        contentValues.put("ngayGd", dfm.format(gd.getNgayGd()));
        contentValues.put("soTien", gd.getSoTien());
        contentValues.put("maKhoan", gd.getMaKhoan());
        int r = db.update("GIAODICH", contentValues, "maGd=?", new String[]{String.valueOf(gd.getMaGd())});
        if (r <= 0) {
            return false;
        }
        return true;
    }

    //Xóa giao dịch theo mã
    public boolean xoaGD(PH18428_GiaoDich gd) {
        SQLiteDatabase db = dtb.getWritableDatabase();
        int r = db.delete("GIAODICH", "maGd=?", new String[]{String.valueOf(gd.getMaGd())});
        if (r <= 0) {
            return false;
        }
        return true;
    }


}
