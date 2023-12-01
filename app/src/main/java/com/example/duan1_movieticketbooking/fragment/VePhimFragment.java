package com.example.duan1_movieticketbooking.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.duan1_movieticketbooking.R;
import com.example.duan1_movieticketbooking.adapter.VePhimAdapter;
import com.example.duan1_movieticketbooking.dao.TaiKhoanDao;
import com.example.duan1_movieticketbooking.dao.VePhimDao;
import com.example.duan1_movieticketbooking.model.VePhim;

import java.util.ArrayList;

public class VePhimFragment extends Fragment {
    RecyclerView rcvVePhim;
    VePhimAdapter vePhimAdapter;

    VePhimDao vePhimDao;
    ArrayList<VePhim> arrVePhim = new ArrayList<>();
    Context mContext;
    LayoutInflater inflater;
    SharedPreferences sharedPreferences;
    TaiKhoanDao taiKhoanDao;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ve_phim, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        rcvVePhim = view.findViewById(R.id.rcv_vephim);

        inflater = getLayoutInflater();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rcvVePhim.setLayoutManager(layoutManager);
        vePhimDao = new VePhimDao(mContext);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String username = sharedPreferences.getString("username", "");

        if (username.equals("admin")) {
            arrVePhim = (ArrayList<VePhim>) vePhimDao.getAllVePhim();
        } else {
            taiKhoanDao = new TaiKhoanDao(getContext());
            int userId = taiKhoanDao.getUserIdByUsername(username);
            arrVePhim = (ArrayList<VePhim>) vePhimDao.getVePhimByIdUser(userId);
        }

        vePhimAdapter = new VePhimAdapter(mContext, arrVePhim);
        rcvVePhim.setAdapter(vePhimAdapter);
    }
}