package com.example.duan1_movieticketbooking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;


import com.example.duan1_movieticketbooking.databinding.ActivityMainBinding;
import com.example.duan1_movieticketbooking.fragment.TaiKhoanFragment;
import com.example.duan1_movieticketbooking.fragment.TrangChuFragment;
import com.example.duan1_movieticketbooking.fragment.VePhimFragment;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new TrangChuFragment());


        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home) {
                replaceFragment(new TrangChuFragment());
            } else if (item.getItemId() == R.id.my_ticket) {
                replaceFragment(new VePhimFragment());
            } else if (item.getItemId() == R.id.account) {
                replaceFragment(new TaiKhoanFragment());
            }

            return true;
        });



    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }


}