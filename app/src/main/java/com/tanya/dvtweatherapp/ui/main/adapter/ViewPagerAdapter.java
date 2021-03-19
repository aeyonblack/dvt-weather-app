package com.tanya.dvtweatherapp.ui.main.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.tanya.dvtweatherapp.ui.main.forecast.ForecastFragment;
import com.tanya.dvtweatherapp.ui.main.locations.LocationsFragment;
import com.tanya.dvtweatherapp.ui.main.today.TodayFragment;

/**
 * Fragment adapter for TabLayout fragments and ViewPager
 */
public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        if (position == 0)
            fragment = new TodayFragment();
        else if (position == 1)
            fragment = new ForecastFragment();
        else if (position == 2)
            fragment = new LocationsFragment();

        assert fragment != null;
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 3;
    }

}