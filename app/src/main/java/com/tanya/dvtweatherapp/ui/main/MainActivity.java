package com.tanya.dvtweatherapp.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.tanya.dvtweatherapp.R;
import com.tanya.dvtweatherapp.ui.main.adapter.ViewPagerAdapter;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.pager);

        viewPager.setUserInputEnabled(true);
        viewPager.setAdapter(createAdapter());
        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
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
}