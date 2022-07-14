package net.fpl.phuongttph18428_ass.adapter;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import com.google.android.material.bottomsheet.BottomSheetDialog;

import net.fpl.phuongttph18428_ass.R;
import net.fpl.phuongttph18428_ass.dao.PH18428_DaoThuChi;
import net.fpl.phuongttph18428_ass.model.PH18428_ThuChi;

import java.util.ArrayList;



public class PH18428_LoaiThuAdapter extends RecyclerView.Adapter<PH18428_LoaiThuAdapter.ViewHolder> {
    private Context context;
    private ArrayList<PH18428_ThuChi> list;
    private PH18428_DaoThuChi PH18428DaoThuChi;
    private  int layout;
    public PH18428_LoaiThuAdapter() {
    }
    public PH18428_LoaiThuAdapter(Context context, ArrayList<PH18428_ThuChi> list) {
        this.context = context;
        this.list = list;
    }
    public PH18428_LoaiThuAdapter(Context context, int layout, ArrayList<PH18428_ThuChi> list) {
        this.context = context;
        this.list = list;
        this.layout=layout;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTenKhoan;
        private ImageView imgSua, imgXoa, img_avataitem;
        RelativeLayout relativeLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenKhoan = itemView.findViewById(R.id.textList);
            img_avataitem = itemView.findViewById(R.id.img_avataitem);
            relativeLayout = itemView.findViewById(R.id.relative_item);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.txtTenKhoan.setText(list.get(position).getTenKhoan());
        PH18428DaoThuChi = new PH18428_DaoThuChi(context);
        final PH18428_ThuChi tc = list.get(position);
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
                txtXemchiTiet.setVisibility(View.GONE);
                TextView txtSuaKhoanChi=bottomSheetView.findViewById(R.id.txt_SuaThuChi);
                TextView txtXoa=bottomSheetView.findViewById(R.id.txt_XoaThuChi);
                txtSuaKhoanChi.setText("Sửa loại thu");

                txtXemchiTiet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        bottomSheetDialog.dismiss();

                    }
                });
                txtSuaKhoanChi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                        final Dialog dialog = new Dialog(context);
                        dialog.setContentView(R.layout.ph18428_them_loai_thuchi);
                        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;
                        Window window = dialog.getWindow();
                        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        if (dialog != null && dialog.getWindow() != null) {
                            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        }                final EditText edtThemLoaiThu = dialog.findViewById(R.id.them_loai_thu);
                        Button xoa = dialog.findViewById(R.id.xoaTextLT);
                        final Button them = dialog.findViewById(R.id.btnThemLT);
                        final TextView title = dialog.findViewById(R.id.titleThemLoai);
                        title.setText("SỬA LOẠI THU");
                        edtThemLoaiThu.setHint("Nhập loại thu");
                        edtThemLoaiThu.setText(tc.getTenKhoan());
                        them.setText("SỬA");

                        them.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String themText = edtThemLoaiThu.getText().toString();
                                PH18428_ThuChi thuchi = new PH18428_ThuChi(tc.getMaKhoan(), themText, 0);
                                if (PH18428DaoThuChi.suaTC(thuchi) == true) {
                                    list.clear();
                                    list.addAll(PH18428DaoThuChi.getThuChi(0));
                                    notifyDataSetChanged();
                                    Toast.makeText(context, "Sửa thành công!", Toast.LENGTH_SHORT).show();

                                    dialog.dismiss();
                                } else {
                                    Toast.makeText(context, "Thêm thất bại!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                        xoa.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
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
                        dialog.setCancelable(false);
                        dialog.setContentView(R.layout.ph18428_dialog_xoa);
                        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;
                        Window window = dialog.getWindow();
                        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        if (dialog != null && dialog.getWindow() != null) {
                            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        }
                        final TextView txt_Massage = dialog.findViewById(R.id.txt_Titleconfirm);
                        Button xoa = dialog.findViewById(R.id.xoaTextLT);
                        final Button them = dialog.findViewById(R.id.btnThemLT);
                        final Button btn_Yes = dialog.findViewById(R.id.btn_yes);
                        final Button btn_No = dialog.findViewById(R.id.btn_no);
                        final ProgressBar progressBar = dialog.findViewById(R.id.progress_loadconfirm);
                        progressBar.setVisibility(View.INVISIBLE);
                        txt_Massage.setText("Bạn có muốn xóa " + list.get(position).getTenKhoan() + " hay không ? ");
                        btn_Yes.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (PH18428DaoThuChi.xoaTC(tc)) {
                                    txt_Massage.setText("");
                                    progressBar.setVisibility(View.VISIBLE);
                                    progressBar.getIndeterminateDrawable().setColorFilter(0xFFFF0000, android.graphics.PorterDuff.Mode.MULTIPLY);
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            list.clear();
                                            list.addAll(PH18428DaoThuChi.getThuChi(0));
                                            notifyDataSetChanged();
                                            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                            dialog.dismiss();
                                        }
                                    }, 2000);
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
