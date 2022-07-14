package net.fpl.phuongttph18428_ass.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


import com.google.android.material.tabs.TabLayout;

import net.fpl.phuongttph18428_ass.R;
import net.fpl.phuongttph18428_ass.adapter.PH18428_KhoanChi_ViewPagerAdapter;


public class PH18428_KhoanChi_Fragment extends Fragment {
    private ViewPager viewPager;
    public TabLayout tabLayout;
    View view;

    public PH18428_KhoanChi_Fragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.ph18428_fragment_khoan_chi_, container, false);
        viewPager=view.findViewById(R.id.viewpager_khoanchi);
        tabLayout=view.findViewById(R.id.tablayout_khoanchi);

        tabLayout.addTab(tabLayout.newTab().setText("KHOẢN CHI"));
        tabLayout.addTab(tabLayout.newTab().setText("LOẠI CHI"));

        PH18428_KhoanChi_ViewPagerAdapter adapter = new PH18428_KhoanChi_ViewPagerAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.getTabAt(0).setIcon(R.drawable.khoanchi);
        tabLayout.getTabAt(1).setIcon(R.drawable.loaichi);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }
}
