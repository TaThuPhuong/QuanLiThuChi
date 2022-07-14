package net.fpl.phuongttph18428_ass.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import net.fpl.phuongttph18428_ass.database.PH18428_Database;
import net.fpl.phuongttph18428_ass.model.PH18428_TaikhoanMatKhau;

import java.util.ArrayList;

public class PH18428_DaoTaiKhoan {
    PH18428_Database dtbRegister;

    public PH18428_DaoTaiKhoan(Context context) {

        dtbRegister = new PH18428_Database(context);
    }

    public ArrayList<PH18428_TaikhoanMatKhau> getALl() {
        ArrayList<PH18428_TaikhoanMatKhau> listTK = new ArrayList<>();
        SQLiteDatabase dtb = dtbRegister.getReadableDatabase();
        Cursor cs = dtb.rawQuery("SELECT * FROM taiKhoan", null);
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            try {
                String tk = cs.getString(0);
                String mk = cs.getString(1);
                PH18428_TaikhoanMatKhau t = new PH18428_TaikhoanMatKhau(tk, mk);
                listTK.add(t);
                cs.moveToNext();
            } catch (Exception ex) {
                ex.printStackTrace();

            }
        }
        cs.close();
        return listTK;
    }
    public boolean Them(PH18428_TaikhoanMatKhau tk) {
        SQLiteDatabase db = dtbRegister.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenTaiKhoan", tk.getTenTaiKhoan());
        values.put("matKhau", tk.getMatKhau());
        long r = db.insert("taiKhoan", null, values);
        if (r <= 0) {
            return false;
        }
        return true;
    }
    public boolean doiMk(PH18428_TaikhoanMatKhau tk) {
        SQLiteDatabase db = dtbRegister.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("matKhau", tk.getMatKhau());
        int r = db.update("taiKhoan", values, "tenTaiKhoan=?", new String[]{tk.getTenTaiKhoan()});
        if (r <= 0) {
            return false;
        }
        return true;
    }
}
