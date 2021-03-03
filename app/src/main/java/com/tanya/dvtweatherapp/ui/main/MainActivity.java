package com.tanya.dvtweatherapp.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.tanya.dvtweatherapp.R;
import com.tanya.dvtweatherapp.ui.main.adapter.ViewPagerAdapter;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {

    TabLayout mTabLayout;
    ViewPager mViewPager;
    ViewPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTabLayout = findViewById(R.id.tabs);
        mViewPager = findViewById(R.id.pager);
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);

        mTabLayout.setSmoothScrollingEnabled(true);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}