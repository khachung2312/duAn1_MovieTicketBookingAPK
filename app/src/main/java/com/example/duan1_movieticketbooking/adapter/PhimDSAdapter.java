package com.example.duan1_movieticketbooking.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_movieticketbooking.PhimActivity;
import com.example.duan1_movieticketbooking.R;
import com.example.duan1_movieticketbooking.dao.PhimDao;
import com.example.duan1_movieticketbooking.model.Phim;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PhimDSAdapter extends RecyclerView.Adapter<PhimDSAdapter.PhimViewHolder> {
    ArrayList<Phim> arrPhim = new ArrayList<>();
    Context context;

    public PhimDSAdapter(Context context, ArrayList<Phim> arrPhim) {
        this.context = context;
        this.arrPhim = arrPhim;
    }

    public void setFilter(List<Phim> filteredList) {
        arrPhim.clear();
        arrPhim.addAll(filteredList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PhimViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phim_atv_danhsachphim, parent, false);
        return new PhimViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull PhimViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Phim phim = arrPhim.get(position);
        holder.tvTenPhim.setText(phim.getTenPhim());
        holder.tvDoTuoi.setText("P-G "+ String.valueOf(phim.getDoTuoiXem()) + "+");
        holder.tvThoiLuong.setText(String.valueOf(phim.getThoiLuong()) + " min");
        Picasso.get().load(phim.getAnhDaiDien()).into(holder.avtPhim);

        holder.tvTenPhim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển sang màn hình phim và truyền dữ liệu phim
                Phim phim = arrPhim.get(position);
                Intent intent = new Intent(context, PhimActivity.class);
                intent.putExtra("phim_id", phim.getId());
                context.startActivity(intent);
            }
        });

        holder.avtPhim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Phim phim = arrPhim.get(position);
                Intent intent = new Intent(context, PhimActivity.class);
                intent.putExtra("phim_id", phim.getId());
                context.startActivity(intent);
            }
        });

        holder.btnDatVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Phim phim = arrPhim.get(position);
                Intent intent = new Intent(context, PhimActivity.class);
                intent.putExtra("phim_id", phim.getId());
                context.startActivity(intent);
            }
        });

        PhimDao phimDao = new PhimDao(context);
        holder.imgDeletePhim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xác nhận xóa");
                builder.setMessage("Bạn có chắc chắn muốn xóa phim này?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Phim phimToDelete = arrPhim.get(position);
                        int phimId = phimToDelete.getId();
                        phimDao.deletePhim(phimId);
                        arrPhim.remove(position);
                        Toast.makeText(context, "Xoá thành công!", Toast.LENGTH_SHORT).show();
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Hủy", null);

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }


    public class PhimViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTenPhim, tvDoTuoi, tvThoiLuong;
        private ImageView avtPhim, imgDeletePhim;
        private AppCompatButton btnDatVe;
        private SharedPreferences sharedPreferences;

        public PhimViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenPhim = itemView.findViewById(R.id.tv_tenphim_atv_danhsachphim);
            tvDoTuoi = itemView.findViewById(R.id.tv_dotuoi_atv_danhsachphim);
            tvThoiLuong = itemView.findViewById(R.id.tv_thoiluong_atv_danhsachphim);
            avtPhim = itemView.findViewById(R.id.img_anhdaidien_atv_danhsachphim);
            btnDatVe = itemView.findViewById(R.id.btn_datve_atv_danhsachphim);
            imgDeletePhim = itemView.findViewById(R.id.img_delete_phim);

            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            String username = sharedPreferences.getString("username", "");
            if (!"admin".equals(username)) {
                imgDeletePhim.setVisibility(View.GONE);
            }
        }

    }
    @Override
    public int getItemCount() {
        return arrPhim.size();
    }
}
