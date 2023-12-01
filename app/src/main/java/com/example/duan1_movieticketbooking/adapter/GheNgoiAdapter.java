package com.example.duan1_movieticketbooking.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.duan1_movieticketbooking.R;
import com.example.duan1_movieticketbooking.model.GheNgoi;

import java.util.ArrayList;

public class GheNgoiAdapter extends RecyclerView.Adapter<GheNgoiAdapter.GheNgoiViewHolder> {
    ArrayList<GheNgoi> arrGheNgoi = new ArrayList<>();
    Context context;

    public GheNgoiAdapter(Context context, ArrayList<GheNgoi> arrGheNgoi) {
        this.context = context;
        this.arrGheNgoi = arrGheNgoi;
    }

    @NonNull
    @Override
    public GheNgoiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ghe_ngoi, parent, false);
        return new GheNgoiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GheNgoiViewHolder holder, int position) {
        GheNgoi gheNgoi = arrGheNgoi.get(position);
        holder.tvSoGhe.setText(gheNgoi.getSoGhe());

        if(gheNgoi.getTrangThai() == 2) {
            int color = context.getResources().getColor(R.color.daDat);
            holder.tvSoGhe.setBackgroundColor(color);
        }

        // Xử lý sự kiện khi người dùng nhấp vào số ghế
        holder.tvSoGhe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickListener != null) {
                    clickListener.onGheNgoiClick(gheNgoi);
                }
                int color;
                switch (gheNgoi.getTrangThai()) {
                    case 0: // Ghế trống
                        color = context.getResources().getColor(R.color.dangChon);
                        gheNgoi.setTrangThai(1); // Thay đổi trạng thái từ 0 sang 1
                        break;
                    case 1: // Ghế đang chọn
                        color = context.getResources().getColor(R.color.chuaDat);
                        gheNgoi.setTrangThai(0); // Thay đổi trạng thái từ 1 sang 0
                        break;
                    case 2: // Ghế đã đặt
                        return;
                    default:
                        color = context.getResources().getColor(R.color.white);
                        break;
                }
                holder.tvSoGhe.setBackgroundColor(color);
            }
        });
    }


    public class GheNgoiViewHolder extends RecyclerView.ViewHolder {
        private TextView tvSoGhe;
        public GheNgoiViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSoGhe = itemView.findViewById(R.id.tv_soghe);

        }

    }
    public interface OnGheNgoiClickListener {
        void onGheNgoiClick(GheNgoi gheNgoi);
    }

    private OnGheNgoiClickListener clickListener;

    public void setOnGheNgoiClickListener(OnGheNgoiClickListener clickListener) {
        this.clickListener = clickListener;
    }
    @Override
    public int getItemCount() {
        return arrGheNgoi.size();
    }
}
