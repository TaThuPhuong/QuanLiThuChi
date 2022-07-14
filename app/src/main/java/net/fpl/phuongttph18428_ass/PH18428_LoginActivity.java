package net.fpl.phuongttph18428_ass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;



import net.fpl.phuongttph18428_ass.dao.PH18428_DaoTaiKhoan;
import net.fpl.phuongttph18428_ass.model.PH18428_TaikhoanMatKhau;

import java.util.ArrayList;

public class PH18428_LoginActivity extends AppCompatActivity {
    private Button btReg, btLogin;
    EditText edtTaiKhoan, edtMatKhau;
    CheckBox cbLuuThongTin;
    PH18428_DaoTaiKhoan tkDao;
    ArrayList<PH18428_TaikhoanMatKhau> listTK = new ArrayList<>();
    LinearLayout linearLayout;
    Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ph18428_activity_login);
        linearLayout = findViewById(R.id.linearLayoutlogin);
        init();
        animation = AnimationUtils.loadAnimation(this, R.anim.ph18428_dangnhap_dangky_animation);
        linearLayout.startAnimation(animation);
        layThongTin();

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean xetTk = false;
                tkDao = new PH18428_DaoTaiKhoan(PH18428_LoginActivity.this);
                String tenTK = edtTaiKhoan.getText().toString();
                String mk = edtMatKhau.getText().toString();
                listTK = tkDao.getALl();
                for (int i = 0; i < listTK.size(); i++) {
                    PH18428_TaikhoanMatKhau tkx = listTK.get(i);
                    if (tkx.getTenTaiKhoan().matches(tenTK) && tkx.getMatKhau().matches(mk)) {
                        xetTk = true;
                        break;
                    }
                }
                if (xetTk == true) {
                    Toast.makeText(PH18428_LoginActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                    luuThongTin();
                    startActivity(new Intent(PH18428_LoginActivity.this, PH18428_MainActivity.class));
                    overridePendingTransition(R.anim.ph18428_ani_intent, R.anim.ph18428_ani_intenexit);

                } else {
                    Toast.makeText(PH18428_LoginActivity.this, "Tên tài khoản hoặc mật khẩu không chính xác!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PH18428_LoginActivity.this, PH18428_RegisterActivity.class);
                startActivityForResult(i, 999);
                overridePendingTransition(R.anim.ph18428_ani_intent, R.anim.ph18428_ani_intenexit);
            }
        });



    }

    private void luuThongTin() {
        SharedPreferences sharedPreferences = getSharedPreferences("sinhvien", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String ten = edtTaiKhoan.getText().toString();
        String pass = edtMatKhau.getText().toString();
        boolean check = cbLuuThongTin.isChecked();
        if (!check) {
            editor.clear();
        } else {
            editor.putString("tennguoidung", ten);
            editor.putString("matkhau", pass);
            editor.putBoolean("checkstatus", check);
        }
        editor.commit();

    }

    private void layThongTin() {
        SharedPreferences sharedPreferences = getSharedPreferences("sinhvien", MODE_PRIVATE);

        boolean check = sharedPreferences.getBoolean("checkstatus", false);
        if (check) {
            String tenNguoiDung = sharedPreferences.getString("tennguoidung", "");
            String matKhau = sharedPreferences.getString("matkhau", "");
            edtTaiKhoan.setText(tenNguoiDung);
            edtMatKhau.setText(matKhau);
        } else {
            edtTaiKhoan.setText("");
            edtMatKhau.setText("");
        }
        cbLuuThongTin.setChecked(check);
    }

    private void init() {
        edtTaiKhoan = findViewById(R.id.edtUserName);
        edtMatKhau = findViewById(R.id.edtPassword);
        btLogin = findViewById(R.id.btnLogin);
        btReg = findViewById(R.id.btnRegister);
        cbLuuThongTin = findViewById(R.id.cbLuuThongTin);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 999 && resultCode == RESULT_OK) {
            String tk = data.getStringExtra("taikhoan");
            String mk = data.getStringExtra("matkhau");
            edtTaiKhoan.setText(tk);
            edtMatKhau.setText(mk);
        }
    }
}


