package net.fpl.phuongttph18428_ass;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

import net.fpl.phuongttph18428_ass.fragment.PH18428_ChangepasswordFragment;
import net.fpl.phuongttph18428_ass.fragment.PH18428_GioiThieu_Fragment;
import net.fpl.phuongttph18428_ass.fragment.PH18428_KhoanChi_Fragment;
import net.fpl.phuongttph18428_ass.fragment.PH18428_KhoanThu_Fragment;
import net.fpl.phuongttph18428_ass.fragment.PH18428_ThongKe_Fragment;


public class PH18428_MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;

    FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ph18428_activity_main);
        frameLayout = findViewById(R.id.frame_layout);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);


        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setupDrawerContent(navigationView);
        PH18428_ThongKe_Fragment searchFragment = new PH18428_ThongKe_Fragment();
        replaceFragment(searchFragment);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).commit();
    }

    private void selectedItemDrawer(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.khoanthu:
                setTitle("KHOẢN THU");
                PH18428_KhoanThu_Fragment PH18428KhoanThu_fragment = new PH18428_KhoanThu_Fragment();
                replaceFragment(PH18428KhoanThu_fragment);
                break;
            case R.id.khoanchi:
                setTitle("KHOẢN CHI");
                PH18428_KhoanChi_Fragment PH18428KhoanChi_fragment = new PH18428_KhoanChi_Fragment();
                replaceFragment(PH18428KhoanChi_fragment);
                break;
            case R.id.thongke:
                setTitle("THỐNG KÊ");
                PH18428_ThongKe_Fragment searchFragment = new PH18428_ThongKe_Fragment();
                replaceFragment(searchFragment);
                break;
            case R.id.gioithieu:
                setTitle("GIỚI THIỆU");
                PH18428_GioiThieu_Fragment settingsFragment = new PH18428_GioiThieu_Fragment();
                replaceFragment(settingsFragment);
                break;

            case R.id.doimatkhau:
                setTitle("ĐỔI MẬT KHẨU");
                PH18428_ChangepasswordFragment PH18428ChangepasswordFragment = new PH18428_ChangepasswordFragment();
                replaceFragment(PH18428ChangepasswordFragment);
                break;
            case R.id.logout:
                startActivity(new Intent(PH18428_MainActivity.this, PH18428_LoginActivity.class));
                finish();
                break;

        }

        item.setChecked(true);

        drawerLayout.closeDrawers();
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                selectedItemDrawer(item);
                return true;
            }
        });
    }

}
