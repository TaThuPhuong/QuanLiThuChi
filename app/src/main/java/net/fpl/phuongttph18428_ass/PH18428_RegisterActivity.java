package net.fpl.phuongttph18428_ass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;


import net.fpl.phuongttph18428_ass.dao.PH18428_DaoTaiKhoan;
import net.fpl.phuongttph18428_ass.model.PH18428_TaikhoanMatKhau;

import java.util.ArrayList;



public class PH18428_RegisterActivity extends AppCompatActivity {
    private RelativeLayout rlayout;
    private Animation animation;
    EditText txtRegTk, txtRegMk, txtRegMkk;
    Button btDangKy, btNhapLai;
    ArrayList<PH18428_TaikhoanMatKhau> listTk = new ArrayList<>();
    PH18428_DaoTaiKhoan tkDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ph18428_activity_register);
        init();
        rlayout = findViewById(R.id.rlayout);
        animation = AnimationUtils.loadAnimation(this, R.anim.ph18428_dangnhap_dangky_animation);
        rlayout.setAnimation(animation);

        btDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tkDao = new PH18428_DaoTaiKhoan(PH18428_RegisterActivity.this);

                String tk = txtRegTk.getText().toString();
                String mk = txtRegMk.getText().toString();
                String mkk = txtRegMkk.getText().toString();
                boolean xetTk = true, xetMk = false;
                PH18428_TaikhoanMatKhau tkmk = new PH18428_TaikhoanMatKhau(tk, mk);

                listTk = tkDao.getALl();

                if (mk.matches(mkk)) {
                    xetMk = true;
                } else {
                    xetMk = false;
                }

                for (int i = 0; i < listTk.size(); i++) {
                    PH18428_TaikhoanMatKhau tkx = listTk.get(i);
                    if (tkx.getTenTaiKhoan().matches(tk)) {
                        xetTk = false;
                        break;
                    }
                }

                if (tk.isEmpty()) {
                    Toast.makeText(PH18428_RegisterActivity.this, "T??n t??i kho???n kh??ng ???????c ????? tr???ng!", Toast.LENGTH_SHORT).show();
                } else {
                    if (mk.isEmpty() || mkk.isEmpty()) {
                        Toast.makeText(PH18428_RegisterActivity.this, "M???t kh???u kh??ng ???????c ????? tr???ng!", Toast.LENGTH_SHORT).show();
                    } else {
                        if (xetTk == true) {
                            if (xetMk == true) {
                                tkDao.Them(tkmk);
                                Toast.makeText(PH18428_RegisterActivity.this, "Th??m t??i kho???n th??nh c??ng!", Toast.LENGTH_SHORT).show();

                                Intent i = new Intent();
                                i.putExtra("taikhoan",tk);
                                i.putExtra("matkhau",mk);
                                setResult(RESULT_OK,i);
                                finish();

                            } else {
                                Toast.makeText(PH18428_RegisterActivity.this, "M???t kh???u kh??ng kh???p nhau!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(PH18428_RegisterActivity.this, "T??n t??i kho???n k ???????c tr??ng!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }
    private void init() {

        txtRegTk = findViewById(R.id.edtRegUser);
        txtRegMk = findViewById(R.id.edtRegPassword);
        txtRegMkk = findViewById(R.id.edtRePassword);
        btDangKy = findViewById(R.id.btnReg);
        btNhapLai = findViewById(R.id.btnRelay);
    }
}
