package com.example.appfilm_2.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.appfilm_2.frament.FragmentAction;
import com.example.appfilm_2.frament.FragmentRomantic;
import com.example.appfilm_2.frament.FragmentComedy;
import com.example.appfilm_2.frament.FragmentDrama;
import com.example.appfilm_2.frament.FragmentSport;

public class ViewPagerListAdapter extends FragmentStatePagerAdapter {

    public ViewPagerListAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragmentAction();
            case 1:
                return new FragmentRomantic();
            case 2:
                return new FragmentDrama();
            case 3:
                return new FragmentComedy();
            default:
                return new FragmentSport();
        }
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Action";
            case 1:
                return "Adventure";
            case 2:
                return "Drama";
            case 3:
                return "Comedy";
            default:
                return "Sport";
        }
    }
}
