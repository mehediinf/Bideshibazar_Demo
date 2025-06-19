package com.mtach.bideshibazar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.mtach.bideshibazar.account.AccountFragment;
import com.mtach.bideshibazar.cart.CartFragment;
import com.mtach.bideshibazar.databinding.ActivityMainBinding;
import com.mtach.bideshibazar.shop.ShopFragment;
import com.mtach.bideshibazar.store.StoreFragment;


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
                replaceFragment(new ShopFragment());
            } else if (id == R.id.nav_store) {
                replaceFragment(new StoreFragment());
            } else if (id == R.id.nav_cart) {
                replaceFragment(new CartFragment());
            } else if (id == R.id.nav_account) {
                replaceFragment(new AccountFragment());
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
