package net.fpl.phuongttph18428_ass.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


import net.fpl.phuongttph18428_ass.R;
import net.fpl.phuongttph18428_ass.dao.PH18428_DaoTaiKhoan;
import net.fpl.phuongttph18428_ass.model.PH18428_TaikhoanMatKhau;

import java.util.ArrayList;


public class PH18428_ChangepasswordFragment extends Fragment {
    EditText txtCTk, txtCpass, txtNewPass;
    Button btChangePass, btNhapLai;
    PH18428_DaoTaiKhoan tkDao;
    Animation animation;
    LinearLayout linearLayout;
    ArrayList<PH18428_TaikhoanMatKhau> listTk = new ArrayList<>();
    View view;
    public PH18428_ChangepasswordFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.ph18428_fragment_changepassword, container, false);
        init();

        animation = AnimationUtils.loadAnimation(getContext(), R.anim.ph18428_dangnhap_dangky_animation);
        linearLayout.setAnimation(animation);
        btChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean xetTk = false, xetMk = true;
                tkDao = new PH18428_DaoTaiKhoan(getContext());
                String tk = txtCTk.getText().toString();
                String mk = txtCpass.getText().toString();
                String mkk = txtNewPass.getText().toString();
                PH18428_TaikhoanMatKhau tkmkMoi = new PH18428_TaikhoanMatKhau(tk,mkk);
                listTk = tkDao.getALl();

                //Check tk, mk c?? kh???p vs tk trong list k
                for (int i = 0; i < listTk.size(); i++) {
                    PH18428_TaikhoanMatKhau tkx = listTk.get(i);
                    if (tkx.getTenTaiKhoan().matches(tk)&&tkx.getMatKhau().matches(mk)) {
                        xetTk = true;
                        break;
                    }
                }

                if(mk.matches(mkk)){
                    xetMk=false;
                }
                else {
                    xetMk=true;
                }

                if (tk.isEmpty()) {
                    Toast.makeText(getContext(), "T??n t??i kho???n kh??ng ???????c ????? tr???ng!", Toast.LENGTH_SHORT).show();
                } else {
                    if (mk.isEmpty() || mkk.isEmpty()) {
                        Toast.makeText(getContext(), "M???t kh???u kh??ng ???????c ????? tr???ng!", Toast.LENGTH_SHORT).show();
                    } else {
                        if (xetTk == true) {
                            if (xetMk == true) {
                                tkDao.doiMk(tkmkMoi);
                                Toast.makeText(getContext(), "?????i m???t kh???u th??nh c??ng!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getContext(), "M???t kh???u c?? kh??ng ???????c tr??ng v???i m???t kh???u m???i!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getContext(), "T??n t??i kho???n ho???c m???t kh???u kh??ng ????ng!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

            }
        });

        btNhapLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtCTk.setText("");
                txtCpass.setText("");
                txtNewPass.setText("");
            }
        });
        return view;
    }
    private void init(){
        txtCTk = view.findViewById(R.id.edtCUser);
        txtCpass = view.findViewById(R.id.edtCPass);
        txtNewPass = view.findViewById(R.id.edtNewPass);
        btChangePass  =view.findViewById(R.id.btnChange);
        btNhapLai = view.findViewById(R.id.btnRelay);
        linearLayout=view.findViewById(R.id.linearLayoutchange);
    }

}
