package com.tanya.dvtweatherapp.ui.main.today;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.RequestManager;
import com.tanya.dvtweatherapp.R;
import com.tanya.dvtweatherapp.models.CurrentWeather;
import com.tanya.dvtweatherapp.ui.main.MainViewModel;
import com.tanya.dvtweatherapp.utils.DateFormatter;
import com.tanya.dvtweatherapp.utils.NetworkUtil;
import com.tanya.dvtweatherapp.utils.Util;
import com.tanya.dvtweatherapp.utils.WeatherIconManager;
import com.tanya.dvtweatherapp.viewmodel.ViewModelProviderFactory;

import java.util.Objects;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class TodayFragment extends DaggerFragment implements View.OnClickListener,
        SwipeRefreshLayout.OnRefreshListener {

    /*Objects*/

    private TodayViewModel viewModel;
    private MainViewModel mainViewModel;
    private CurrentWeather currentWeather;

    /*Dependency Injection*/

    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    RequestManager requestManager;

    /*Views*/

    private TextView mainTemperatureView;
    private TextView maxMinTemperatureView;
    private TextView feelsLikeView;
    private TextView weatherDescriptionView;
    private TextView dayOfWeekView;
    private TextView timeSinceLastUpdateView;

    private ImageView weatherIconView;
    private SwipeRefreshLayout swipeRefreshLayout;

    private Button saveLocationButton;

    /*Listeners*/

    OnWeatherLoadedListener weatherLoadedListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        weatherLoadedListener = (OnWeatherLoadedListener)context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_today, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /*Setup views*/

        mainTemperatureView = view.findViewById(R.id.current_temp);
        maxMinTemperatureView = view.findViewById(R.id.temp_min_max);
        feelsLikeView = view.findViewById(R.id.feels_like);
        weatherDescriptionView = view.findViewById(R.id.weather_description);
        dayOfWeekView = view.findViewById(R.id.day_of_week);
        timeSinceLastUpdateView = view.findViewById(R.id.time_since_last_update);

        weatherIconView = view.findViewById(R.id.weather_icon);

        swipeRefreshLayout = view.findViewById(R.id.today_fragment_container);
        swipeRefreshLayout.setColorSchemeColors(
                getResources().getColor(R.color.purple_sunny_primary),
                getResources().getColor(R.color.forest_sunny),
                getResources().getColor(R.color.teal_primary),
                getResources().getColor(R.color.rainy_primary));

        swipeRefreshLayout.setOnRefreshListener(this);

        saveLocationButton = view.findViewById(R.id.save_location_button);

        saveLocationButton.setOnClickListener(this);

        viewModel = new ViewModelProvider(this, providerFactory).get(TodayViewModel.class);
        mainViewModel = new ViewModelProvider(requireActivity(), providerFactory).get(MainViewModel.class);

        // Load weather data for current location
        // as soon as view is created
        observeLocation();

        // Observe changes on the search query
        subscribeToMain();

    }

    /**
     * Observe the current device location
     */
    private void observeLocation() {
        mainViewModel.getCoordinates().observe(getViewLifecycleOwner(), coordinates -> {
            if (coordinates != null) {
                subscribeObservers(coordinates);
            }
        });
    }

    /**
     * Observe changes on the weather data
     * for current device location
     */
    @SuppressLint("SetTextI18n")
    private void subscribeObservers(double[] coordinates) {
        viewModel.getCurrentWeather(coordinates, NetworkUtil
                .isConnected(Objects.requireNonNull(getActivity())))
                .observe(getViewLifecycleOwner(), currentWeatherResource -> {
            if (currentWeatherResource != null) {
                switch (currentWeatherResource.status) {
                    case LOADING:
                        if (swipeRefreshLayout.isRefreshing()) {
                            // Tells MainActivity not to show Splash Screen
                            weatherLoadedListener.onLoad(LoadStatus.REFRESHING);
                        }
                        else {
                            // Tells MainActivity to show Splash Screen
                            weatherLoadedListener.onLoad(LoadStatus.LOADING);
                        }
                        break;
                    case SUCCESS:
                        weatherLoadedListener.onLoad(LoadStatus.SUCCESS);
                        if (currentWeatherResource.data != null) {
                            weatherLoadedListener.onWeatherLoaded(currentWeatherResource.data);
                            displayWeatherData(currentWeatherResource.data);
                            currentWeather = currentWeatherResource.data;
                        }
                        if (swipeRefreshLayout.isRefreshing())
                        {
                            stopRefreshing();
                        }
                        break;
                    case ERROR:
                        Util.toast(getActivity(), "Error: " + currentWeatherResource.message);
                        weatherLoadedListener.onLoad(LoadStatus.ERROR);
                        break;
                }
            }
        });
    }

    /**
     * Observe changes on the search query
     * and get current weather
     */
    private void subscribeToMain() {
        mainViewModel.getSearchQuery().observe(getViewLifecycleOwner(), s -> {
            if (s != null) {
                getCurrentWeather(s);
            }
        });
    }

    /**
     * Get current weather for specified location
     */
    private void getCurrentWeather(String cityName) {
        viewModel.getCurrentWeather(cityName, NetworkUtil
                .isConnected(Objects.requireNonNull(getActivity())))
                .observe(getViewLifecycleOwner(), currentWeatherResource -> {
                    if (currentWeatherResource != null) {
                        switch (currentWeatherResource.status) {
                            case LOADING:
                                weatherLoadedListener.onLoad(LoadStatus.REFRESHING);
                                break;
                            case SUCCESS:
                                weatherLoadedListener.onLoad(LoadStatus.SUCCESS);
                                if (currentWeatherResource.data != null) {
                                    weatherLoadedListener.onWeatherLoaded(currentWeatherResource.data);
                                    displayWeatherData(currentWeatherResource.data);
                                    currentWeather = currentWeatherResource.data;
                                }
                                break;
                            case ERROR:
                                weatherLoadedListener.onLoad(LoadStatus.ERROR);
                                break;
                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.save_location_button) {
            Util.toast(getActivity(), "Location saved");
            viewModel.saveWeatherLocation(currentWeather);
        }
    }


    /**
     * Displays relevant weather data
     * @param currentWeather - weather data to display
     */
    @SuppressLint("SetTextI18n")
    private void displayWeatherData(CurrentWeather currentWeather) {

        changeBackground(currentWeather.getWeather().get(0).getMain());

        // Get temperature and load into view
        int temp = (int)(Math.round(currentWeather.getMain().getTemp()));
        mainTemperatureView.setText(temp + "\u00B0");

        // Display date
        dayOfWeekView.setText(DateFormatter.getDayOfWeek(currentWeather.getTimeStamp()));

        // Display time since last update
        timeSinceLastUpdateView.setText(DateFormatter.getTimeSinceLastUpdate(currentWeather.getTimeStamp()));

        // Get min and max temp then load into view
        int minTemp = (int)(Math.round(currentWeather.getMain().getMinTemp()));
        int maxTemp = (int)(Math.round(currentWeather.getMain().getMaxTemp()));

        String maxMin = "Max " + maxTemp + "\u00B0\u2191 \u00B7 Min " + minTemp + "\u00B0\u2193";
        maxMinTemperatureView.setText(maxMin);

        // Get feelsLike temp and load into view
        int feelsLike = (int)(Math.round(currentWeather.getMain().getFeelsLike()));
        feelsLikeView.setText("Feels like " + feelsLike + "\u00B0");

        // Load weather description into view
        String weatherDescription = currentWeather.getWeather().get(0).getDescription();
        weatherDescriptionView.setText(weatherDescription);

        // Get weather icon and load into view
        int weatherIcon = WeatherIconManager.getIcon(currentWeather
                .getWeather().get(0).getIcon(), weatherDescription);

        requestManager.load(weatherIcon).into(weatherIconView);

    }

    /**
     * Change background based on weather condition
     */
    @SuppressLint("UseCompatLoadingForDrawables")
    private void changeBackground(String weatherCondition) {
        switch (weatherCondition) {
            case "Clouds":
                swipeRefreshLayout.setBackground(getResources().getDrawable(R.drawable.grad_cloudy));
                saveLocationButton.setBackgroundColor(getResources().getColor(R.color.purple_primary));
                break;
            case "Rain":
                swipeRefreshLayout.setBackground(getResources().getDrawable(R.drawable.grad_rainy));
                saveLocationButton.setBackgroundColor(getResources().getColor(R.color.rainy_primary));
                break;
            default:
                swipeRefreshLayout.setBackground(getResources().getDrawable(R.drawable.grad_sunny));
                saveLocationButton.setBackgroundColor(getResources().getColor(R.color.purple_sunny_primary));
                break;
        }
    }

    @Override
    public void onRefresh() {
        Util.toast(getActivity(), "Refreshing");
        weatherLoadedListener.onLoad(LoadStatus.SWIPE_REFRESH); // Set new search query to whatever the toolbar says
    }

    private void stopRefreshing() {
        new Handler().postDelayed(() -> swipeRefreshLayout.setRefreshing(false), 2000);
    }

    /**
     * Responsible for communicating data to MainActivity
     */
    public interface OnWeatherLoadedListener {
        void onWeatherLoaded(CurrentWeather currentWeather);
        void onLoad(LoadStatus status);
    }

    /*Tell me if the app is still loading*/
    public enum LoadStatus {
        LOADING,
        SUCCESS,
        REFRESHING,
        ERROR,
        SWIPE_REFRESH // Tells MainActivity to change search query
    }

}