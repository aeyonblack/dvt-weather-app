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

  /*  @SuppressWarnings("deprecation")
    public ViewPagerAdapter(
            @NonNull FragmentManager fm)
    {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position)
    {
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
    public int getCount()
    {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        String title = null;
        if (position == 0)
            title = "Today";
        else if (position == 1)
            title = "5 Days";
        else if (position == 2)
            title = "Places";
        return title;
    } */
}