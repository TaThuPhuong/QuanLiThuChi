package net.fpl.phuongttph18428_ass.adapter;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import net.fpl.phuongttph18428_ass.R;
import net.fpl.phuongttph18428_ass.dao.PH18428_DaoGiaoDich;
import net.fpl.phuongttph18428_ass.dao.PH18428_DaoThuChi;
import net.fpl.phuongttph18428_ass.model.PH18428_GiaoDich;
import net.fpl.phuongttph18428_ass.model.PH18428_ThuChi;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class PH18428_KhoanChiAdapter extends RecyclerView.Adapter<PH18428_KhoanChiAdapter.ViewHolder> {
    private Context context;
    public static ArrayList<PH18428_GiaoDich> list;
    private PH18428_DaoGiaoDich PH18428DaoGiaoDich;
    private ArrayList<PH18428_ThuChi> listTC = new ArrayList<>();
    private PH18428_DaoThuChi PH18428DaoThuChi;
    private DatePickerDialog datePickerDialog;
    int layout;
    SimpleDateFormat dfm = new SimpleDateFormat("dd/MM/yyyy");

    public PH18428_KhoanChiAdapter() {
    }

    public PH18428_KhoanChiAdapter(Context context, ArrayList<PH18428_GiaoDich> list) {
        this.context = context;
        this.list = list;
    }

    public PH18428_KhoanChiAdapter(Context context, int layout, ArrayList<PH18428_GiaoDich> list) {
        this.context = context;
        this.list = list;
        this.layout=layout;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView text;
        private ImageView img_avataitem;
        RelativeLayout relativeLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.textList);
            img_avataitem = itemView.findViewById(R.id.img_avataitem);
            relativeLayout = itemView.findViewById(R.id.relative_item);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.text.setText(list.get(position).getMoTaGd());
        PH18428DaoGiaoDich = new PH18428_DaoGiaoDich(context);
        final PH18428_GiaoDich gd = list.get(position);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                            context, R.style.BottomSheetDialogTheme
                    );

                    View bottomSheetView = LayoutInflater.from(context).inflate(
                            R.layout.ph18428_bottom_sheet_khoahoc,
                            (LinearLayout) bottomSheetDialog.findViewById(R.id.bottomSheetContainer)
                    );
                    TextView txtXemchiTiet=bottomSheetView.findViewById(R.id.txt_XemChiTiet);
                    TextView txtSuaKhoanChi=bottomSheetView.findViewById(R.id.txt_SuaThuChi);
                    TextView txtXoa=bottomSheetView.findViewById(R.id.txt_XoaThuChi);
                    txtSuaKhoanChi.setText("S???a kho???n chi");
                    txtXemchiTiet.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            bottomSheetDialog.dismiss();
                            PH18428_GiaoDich gd = list.get(position);
                            //Format d???ng ti???n
                            NumberFormat fm = new DecimalFormat("#,###");
                            //Hi???n th??ng tin giao d???ch khi click v??o item
                            Dialog dialog = new Dialog(context);
                            dialog.setContentView(R.layout.ph18428_thong_tin_gd);
                            dialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;
                            TextView mota, ngay, tien, loai, title;
                            title = dialog.findViewById(R.id.thongtinGD);
                            mota = dialog.findViewById(R.id.mota_gd);
                            ngay = dialog.findViewById(R.id.ngay_gd);
                            tien = dialog.findViewById(R.id.tien_gd);
                            loai = dialog.findViewById(R.id.loai_gd);
                            title.setText("TH??NG TIN CHI");
                            mota.setText(gd.getMoTaGd());
                            ngay.setText(String.valueOf(gd.getNgayGd()));
                            tien.setText(fm.format(Math.abs(gd.getSoTien())) + " VND");
                            PH18428DaoThuChi = new PH18428_DaoThuChi(context);
                            loai.setText(PH18428DaoThuChi.getTen(gd.getMaKhoan()));
                            dialog.show();
                        }
                    });
                    txtSuaKhoanChi.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            bottomSheetDialog.dismiss();
                            final Dialog dialog = new Dialog(context);
                            dialog.setCancelable(false);
                            dialog.setContentView(R.layout.ph18428_them_khoan_thuchi);
                            dialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;
                            Window window = dialog.getWindow();
                            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            if (dialog != null && dialog.getWindow() != null) {
                                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            }                final TextView moTaGd = dialog.findViewById(R.id.them_mota_gd);
                            final TextView ngayGd = dialog.findViewById(R.id.them_ngay_gd);
                            final TextView tienGd = dialog.findViewById(R.id.them_tien_gd);
                            final Spinner spLoaiGd = dialog.findViewById(R.id.spLoaiGd);
                            final TextView title = dialog.findViewById(R.id.titleThemKhoan);
                            final Button xoa = dialog.findViewById(R.id.xoaTextGD);
                            final Button them = dialog.findViewById(R.id.btnThemGD);
                            PH18428DaoThuChi = new PH18428_DaoThuChi(context);
                            listTC = PH18428DaoThuChi.getThuChi(1);
                            //Set ti??u ?????, text
                            title.setText("S???A KHO???N CHI");
                            them.setText("S???A");
                            moTaGd.setText(gd.getMoTaGd());
                            ngayGd.setText(dfm.format(gd.getNgayGd()));
                            tienGd.setText(String.valueOf(gd.getSoTien()));
                            final ArrayAdapter sp = new ArrayAdapter(context, R.layout.ph18428_spiner, listTC);
                            spLoaiGd.setAdapter(sp);
                            int vitri = -1;
                            for (int i = 0; i < listTC.size(); i++) {
                                if (listTC.get(i).getTenKhoan().equalsIgnoreCase(PH18428DaoThuChi.getTen(gd.getMaKhoan()))) {
                                    vitri = i;
                                    break;
                                }
                            }
                            spLoaiGd.setSelection(vitri);


                            //Khi nh???n ng??y hi???n l??n l???a ch??? ng??y
                            ngayGd.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    final Calendar calendar = Calendar.getInstance();
                                    int d = calendar.get(Calendar.DAY_OF_MONTH);
                                    int m = calendar.get(Calendar.MONTH);
                                    int y = calendar.get(Calendar.YEAR);
                                    datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                                        @Override
                                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                            final String NgayGD = dayOfMonth + "/" + month + "/" + year;
                                            ngayGd.setText(NgayGD);
                                        }
                                    }, y, m, d);
                                    datePickerDialog.show();
                                }
                            });

                            //Khi nh???n n??t x??a
                            xoa.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                    spLoaiGd.setAdapter(sp);
                                }
                            });

                            //Khi nh???n n??t s???a
                            them.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String mota = moTaGd.getText().toString();
                                    String ngay = ngayGd.getText().toString();
                                    String tien = tienGd.getText().toString();
                                    PH18428_ThuChi tc = (PH18428_ThuChi) spLoaiGd.getSelectedItem();
                                    int ma = tc.getMaKhoan();
                                    //Check l???i
                                    if (mota.isEmpty() && ngay.isEmpty() && tien.isEmpty()) {
                                        Toast.makeText(context, "C??c tr?????ng kh??ng ???????c ????? tr???ng!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        try {
                                            PH18428_GiaoDich PH18428GiaoDich = new PH18428_GiaoDich(gd.getMaGd(), mota, dfm.parse(ngay), Integer.parseInt(tien), ma);
                                            if (PH18428DaoGiaoDich.suaGD(PH18428GiaoDich) == true) {
                                                list.clear();
                                                list.addAll(PH18428DaoGiaoDich.getGDtheoTC(1));
                                                notifyDataSetChanged();
                                                Toast.makeText(context, "S???a th??nh c??ng!", Toast.LENGTH_SHORT).show();
                                                dialog.dismiss();
                                            } else {
                                                Toast.makeText(context, "S???a th???t b???i!", Toast.LENGTH_SHORT).show();
                                                dialog.dismiss();
                                            }
                                        } catch (Exception ex) {
                                            ex.printStackTrace();
                                        }
                                    }

                                }
                            });

                            dialog.show();


                        }
                    });

                    txtXoa.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            bottomSheetDialog.dismiss();
                            final Dialog dialog = new Dialog(context);
                            dialog.setContentView(R.layout.ph18428_dialog_xoa);
                            dialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;
                            Window window = dialog.getWindow();
                            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            if (dialog != null && dialog.getWindow() != null) {
                                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            }
                            final TextView txt_Massage = dialog.findViewById(R.id.txt_Titleconfirm);
                            final Button btn_Yes = dialog.findViewById(R.id.btn_yes);
                            final Button btn_No = dialog.findViewById(R.id.btn_no);
                            final ProgressBar progressBar = dialog.findViewById(R.id.progress_loadconfirm);
                            progressBar.setVisibility(View.INVISIBLE);
                            txt_Massage.setText("B???n c?? mu???n x??a " + list.get(position).getMoTaGd() + " hay kh??ng ? ");
                            btn_Yes.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (PH18428DaoGiaoDich.xoaGD(gd) == true) {
                                        txt_Massage.setText("");
                                        progressBar.setVisibility(View.VISIBLE);
                                        progressBar.getIndeterminateDrawable().setColorFilter(0xFFFF0000, android.graphics.PorterDuff.Mode.MULTIPLY);
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                list.clear();
                                                list.addAll(PH18428DaoGiaoDich.getGDtheoTC(1));
                                                notifyDataSetChanged();
                                                Toast.makeText(context, "X??a th??nh c??ng", Toast.LENGTH_SHORT).show();
                                                dialog.dismiss();
                                            }
                                        },2000);
                                    }
                                }
                            });
                            btn_No.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                }
                            });
                            dialog.show();
                        }
                    });
                    bottomSheetView.findViewById(R.id.txt_Huy).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            bottomSheetDialog.dismiss();
                        }
                    });
                    bottomSheetDialog.setContentView(bottomSheetView);
                    bottomSheetDialog.show();
                }
            });

        holder.img_avataitem.setAnimation(AnimationUtils.loadAnimation(context, R.anim.ph18428_fade_transition_animation));

        holder.relativeLayout.setAnimation(AnimationUtils.loadAnimation(context, R.anim.ph18428_fade_scale_animation));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}