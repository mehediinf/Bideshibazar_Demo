package com.mtach.bideshibazar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.mtach.bideshibazar.databinding.ActivityMainBinding;



public class MainActivity extends BaseActivity {


    ActivityMainBinding binding;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        replaceFragment(new HomeFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_home) {
                replaceFragment(new HomeFragment());
            } else if (id == R.id.nav_shop) {
                replaceFragment(new HomeFragment());
            } else if (id == R.id.nav_store) {
                replaceFragment(new HomeFragment()); // পরবর্তীতে StoreFragment ব্যবহার করতে পারেন
            } else if (id == R.id.nav_cart) {
                replaceFragment(new HomeFragment()); // পরবর্তীতে CartFragment ব্যবহার করতে পারেন
            } else if (id == R.id.nav_account) {
                replaceFragment(new HomeFragment()); // পরবর্তীতে AccountFragment ব্যবহার করতে পারেন
            }

            return true;
        });


    }

    private void replaceFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mainActivityFrameLayoutId,fragment);
        fragmentTransaction.commit();






    }
}
