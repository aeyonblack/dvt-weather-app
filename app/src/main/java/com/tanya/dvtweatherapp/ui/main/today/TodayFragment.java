package com.tanya.dvtweatherapp.ui.main.today;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
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
import com.tanya.dvtweatherapp.utils.DateFormatter;
import com.tanya.dvtweatherapp.utils.NetworkUtil;
import com.tanya.dvtweatherapp.utils.ToastUtil;
import com.tanya.dvtweatherapp.utils.WeatherIconManager;
import com.tanya.dvtweatherapp.viewmodel.ViewModelProviderFactory;

import java.util.Objects;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class TodayFragment extends DaggerFragment implements View.OnClickListener {

    /*Objects*/

    private TodayViewModel viewModel;
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

        mainTemperatureView = view.findViewById(R.id.current_temp);
        maxMinTemperatureView = view.findViewById(R.id.temp_min_max);
        feelsLikeView = view.findViewById(R.id.feels_like);
        weatherDescriptionView = view.findViewById(R.id.weather_description);
        dayOfWeekView = view.findViewById(R.id.day_of_week);
        timeSinceLastUpdateView = view.findViewById(R.id.time_since_last_update);

        weatherIconView = view.findViewById(R.id.weather_icon);
        swipeRefreshLayout = view.findViewById(R.id.today_fragment_container);

        saveLocationButton = view.findViewById(R.id.save_location_button);

        saveLocationButton.setOnClickListener(this);

        viewModel = new ViewModelProvider(this, providerFactory).get(TodayViewModel.class);

        // Load weather data as soon as view is created
        subscribeObservers();

    }

    /**
     * Observe changes on the weather data
     */
    @SuppressLint("SetTextI18n")
    private void subscribeObservers() {
        viewModel.getCurrentWeather(1020098, NetworkUtil
                .isConnected(Objects.requireNonNull(getActivity())))
                .observe(getViewLifecycleOwner(), currentWeatherResource -> {
            if (currentWeatherResource != null) {
                switch (currentWeatherResource.status) {
                    case LOADING:
                        //toast("Loading");
                        weatherLoadedListener.onLoad(LoadStatus.LOADING);
                        break;
                    case SUCCESS:
                        //toast("Success");
                        weatherLoadedListener.onLoad(LoadStatus.SUCCESS);
                        if (currentWeatherResource.data != null) {
                            weatherLoadedListener.onWeatherLoaded(currentWeatherResource.data);
                            displayWeatherData(currentWeatherResource.data);
                            currentWeather = currentWeatherResource.data;
                        }
                        break;
                    case ERROR:
                        //toast("Error " + currentWeatherResource.message);
                        weatherLoadedListener.onLoad(LoadStatus.ERROR);
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.save_location_button) {
            ToastUtil.toast(getActivity(), "Location saved");
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
        ERROR
    }

}