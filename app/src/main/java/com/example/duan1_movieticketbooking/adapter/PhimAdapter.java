package com.example.duan1_movieticketbooking.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_movieticketbooking.R;
import com.example.duan1_movieticketbooking.dao.PhimDao;
import com.example.duan1_movieticketbooking.model.Phim;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PhimAdapter extends RecyclerView.Adapter<PhimAdapter.PhimViewHolder> {
    ArrayList<Phim> arrPhim = new ArrayList<>();
    Context context;
    PhimDao phimDao;
    LayoutInflater inflater;

    public PhimAdapter(Context context, ArrayList<Phim> arrPhim) {
        this.context = context;
        this.arrPhim = arrPhim;
    }

    @NonNull
    @Override
    public PhimViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phim, parent, false);
        return new PhimViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull PhimViewHolder holder, int position) {
        Phim phim = arrPhim.get(position);
        holder.tvTenPhim.setText(phim.getTenPhim());

        Picasso.get().load(phim.getAnhDaiDien()).into(holder.avtPhim);
    }




    public class PhimViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTenPhim;
        private ImageView avtPhim;

        public PhimViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenPhim = itemView.findViewById(R.id.tv_tenphim);
            avtPhim = itemView.findViewById(R.id.avt_phim);
        }

    }
    @Override
    public int getItemCount() {
        return arrPhim.size();
    }
}
