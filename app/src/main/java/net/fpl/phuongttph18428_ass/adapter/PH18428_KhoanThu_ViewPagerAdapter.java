package net.fpl.phuongttph18428_ass.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import net.fpl.phuongttph18428_ass.fragment.PH18428_Tab_KhoanThu_Fragment;
import net.fpl.phuongttph18428_ass.fragment.PH18428_Tab_LoaiThu_Fragment;


public class PH18428_KhoanThu_ViewPagerAdapter extends FragmentStatePagerAdapter {
    int numberTab = 2;


    public PH18428_KhoanThu_ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                PH18428_Tab_KhoanThu_Fragment PH18428Tab_khoanThu_fragment = new PH18428_Tab_KhoanThu_Fragment();
                return PH18428Tab_khoanThu_fragment;
            case 1:
                PH18428_Tab_LoaiThu_Fragment PH18428Tab_loaiThu_fragment = new PH18428_Tab_LoaiThu_Fragment();
                return PH18428Tab_loaiThu_fragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return numberTab;
    }

}