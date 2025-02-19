package com.example.noa_project;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentAdapter extends FragmentStateAdapter {

    public FragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // החזר פרגמנט שונה לכל טאבל
        if (position == 0) {
            return new BlankFragment();  // פרגמנט עבור טאבל 1
        } else {
            return new BlankFragment2();  // פרגמנט עבור טאבל 2
        }
    }

    @Override
    public int getItemCount() {
        return 2;  // מספר הטאבים
    }
}