package com.tanya.dvtweatherapp.ui.main;

import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.ferfalk.simplesearchview.SimpleSearchView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.tanya.dvtweatherapp.R;
import com.tanya.dvtweatherapp.models.CurrentWeather;
import com.tanya.dvtweatherapp.ui.main.adapter.ViewPagerAdapter;
import com.tanya.dvtweatherapp.ui.main.today.TodayFragment;
import com.tanya.dvtweatherapp.utils.ToastUtil;
import com.tanya.dvtweatherapp.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity implements
        TodayFragment.OnWeatherLoadedListener, MaterialSearchBar.OnSearchActionListener, SimpleSearchView.OnQueryTextListener {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;

    private AppBarLayout appBarLayout;
    private RelativeLayout splashScreen;

    //private MaterialSearchBar searchBar;
    private SimpleSearchView searchView;

    private MainViewModel viewModel;

    @Inject
    ViewModelProviderFactory providerFactory;

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

        //searchBar = findViewById(R.id.search_bar);
        //searchBar.setOnSearchActionListener(this);
        searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(this);

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

        viewModel = new ViewModelProvider(this, providerFactory).get(MainViewModel.class);
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
            //searchBar.setPlaceHolder(currentWeather.getName());
            toolbar.setTitle(currentWeather.getName());
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_open_map) {
            ToastUtil.toast(this, "Feature in development");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (searchView.onBackPressed())
            return;
        super.onBackPressed();
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
        if (splashScreen.getVisibility() == View.VISIBLE) {
            new Handler().postDelayed(() -> {
                appBarLayout.setVisibility(View.VISIBLE);
                viewPager.setVisibility(View.VISIBLE);
                splashScreen.setVisibility(View.GONE);
                setStatusBarTranslucent();
            }, 1000);
        }
    }

    @Override
    public void onSearchStateChanged(boolean enabled) {

    }

    @Override
    public void onSearchConfirmed(CharSequence text) {
        viewModel.setSearchQuery(String.valueOf(text));
        //searchBar.closeSearch();
    }

    @Override
    public void onButtonClicked(int buttonCode) {

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        viewModel.setSearchQuery(query.toLowerCase());
        searchView.closeSearch(true);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public boolean onQueryTextCleared() {
        return false;
    }

    /*END - Support methods*/


}