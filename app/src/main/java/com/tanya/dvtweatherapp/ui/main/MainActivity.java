package com.tanya.dvtweatherapp.ui.main;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.tanya.dvtweatherapp.R;
import com.tanya.dvtweatherapp.models.CurrentWeather;
import com.tanya.dvtweatherapp.ui.main.adapter.ViewPagerAdapter;
import com.tanya.dvtweatherapp.ui.main.today.TodayFragment;
import com.tanya.dvtweatherapp.utils.ToastUtil;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity implements
        TodayFragment.OnWeatherLoadedListener {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager2 viewPager;

    AppBarLayout appBarLayout;
    RelativeLayout splashScreen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.pager);
        splashScreen = findViewById(R.id.splash_screen);
        appBarLayout = findViewById(R.id.app_bar_layout);

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
                    changeTheme(R.color.purple_light, R.color.purple_primary, R.color.purple_dark);
                    break;
                case "Rain":
                    changeTheme(R.color.rainy_light, R.color.rainy_primary, R.color.rainy_dark);
                    break;
                default:
                    changeTheme(R.color.purple_sunny_light, R.color.purple_sunny_primary, R.color.purple_sunny_dark);
                    break;
            }
        }
    }

    /**
     * Decide whether to hide or show splash screen
     * @param status - is app loading data or not?
     */
    @Override
    public void onLoad(TodayFragment.LoadStatus status) {
        if (status == TodayFragment.LoadStatus.LOADING) {
            // Show splash screen
            showSplashScreen();
        }
        else if(status == TodayFragment.LoadStatus.ERROR) {
            showSplashScreen();
            ToastUtil.toast(this, "Something happened, check connection");
        }
        else {
            // If splash screen is showing, hide it after a second
            hideSplashScreen();
        }
    }

    /* START - Support methods*/

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

    private void changeStatusBar(int color) {
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        window.setStatusBarColor(color(color));
    }

    private void setTransparentStatusBar() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    private void setStatusBarTranslucent() {
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    /**
     * Manually change the color scheme for the app
     */
    private void changeTheme(int lightColor, int primaryColor, int darkColor) {
        setToolbarColor(primaryColor);
        setTabLayoutColors(primaryColor, lightColor);
        changeStatusBar(darkColor);
    }

    private void showSplashScreen() {
        appBarLayout.setVisibility(View.GONE);
        viewPager.setVisibility(View.GONE);
        splashScreen.setVisibility(View.VISIBLE);
        setTransparentStatusBar();
    }

    private void hideSplashScreen() {
        new Handler().postDelayed(() -> {
            appBarLayout.setVisibility(View.VISIBLE);
            viewPager.setVisibility(View.VISIBLE);
            splashScreen.setVisibility(View.GONE);
            setStatusBarTranslucent();
        }, 1000);
    }

    /*END - Support methods*/

}