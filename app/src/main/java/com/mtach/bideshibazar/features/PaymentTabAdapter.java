package com.mtach.bideshibazar.features;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PaymentTabAdapter extends FragmentStateAdapter {

    public PaymentTabAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new PaymentsFragment();
        } else {
            return new RefundsFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
