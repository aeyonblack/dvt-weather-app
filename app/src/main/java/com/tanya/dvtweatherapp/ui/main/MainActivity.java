package com.tanya.dvtweatherapp.ui.main;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.tanya.dvtweatherapp.R;
import com.tanya.dvtweatherapp.models.CurrentWeather;
import com.tanya.dvtweatherapp.ui.main.adapter.ViewPagerAdapter;
import com.tanya.dvtweatherapp.ui.main.today.TodayFragment;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity implements
        TodayFragment.OnWeatherLoadedListener {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.pager);

        viewPager.setUserInputEnabled(true);
        viewPager.setAdapter(createAdapter());
        tabLayout.setSmoothScrollingEnabled(true);
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Today");
                    break;
                case 1:
                    tab.setText("5 Days");
                    break;
                case 2:
                    tab.setText("Places");
                    break;
            }
        }).attach();

    }

    /**
     * Not the best approach for doing this
     * but coupling here is really not a big deal
     * @return the viewPager adapter
     */
    private ViewPagerAdapter createAdapter() {
        return new ViewPagerAdapter(this);
    }

    /**
     * Change app theme based on weather results
     * @param currentWeather - weather data
     */
    @Override
    public void onWeatherLoaded(CurrentWeather currentWeather) {
        if (currentWeather != null) {
            switch (currentWeather.getWeather().get(0).getMain()) {
                case "Clouds":
                    setToolbarColor(R.color.purple_primary);
                    setTabLayoutColors(R.color.purple_primary, R.color.purple_light);
                    break;
                case "Rain":
                    setToolbarColor(R.color.rainy_primary);
                    setTabLayoutColors(R.color.rainy_primary, R.color.rainy_light);
                    break;
                default:
                    setToolbarColor(R.color.blue_primary);
                    setTabLayoutColors(R.color.blue_primary, R.color.blue_light);
                    break;
            }
        }
    }

    /*Support methods*/

    private int color(int id) {
        return getResources().getColor(id);
    }

    private void setToolbarColor(int id) {
        toolbar.setBackgroundColor(color(id));
    }

    private void setTabLayoutColors(int primary, int light) {
        tabLayout.setBackgroundColor(color(primary));
        tabLayout.setTabTextColors(color(light), color(R.color.white));
    }

}