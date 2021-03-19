package com.tanya.dvtweatherapp.ui.main.forecast;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tanya.dvtweatherapp.R;
import com.tanya.dvtweatherapp.data.Resource;
import com.tanya.dvtweatherapp.ui.main.MainViewModel;
import com.tanya.dvtweatherapp.utils.NetworkUtil;
import com.tanya.dvtweatherapp.utils.Util;
import com.tanya.dvtweatherapp.viewmodel.ViewModelProviderFactory;

import java.util.Objects;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class ForecastFragment extends DaggerFragment {

    /*Dependency Injection*/

    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    ForecastRecyclerAdapter adapter;

    /*ViewModels*/

    private ForecastViewModel viewModel;

    private MainViewModel mainViewModel;

    /*Views*/

    private RecyclerView forecastList;

    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forecast, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        forecastList = view.findViewById(R.id.forecast_list);

        progressBar = view.findViewById(R.id.progress_bar);

        viewModel = new ViewModelProvider(this, providerFactory).get(ForecastViewModel.class);

        mainViewModel = new ViewModelProvider(requireActivity(), providerFactory).get(MainViewModel.class);

        // Setup recycler with adapters and layout manager
        setupRecyclerView();

        // Listen for changes on the device location
        observeLocation();

        // Listen for changes on the search query
        subscribeToMain();

    }

    /**
     * Listen for changes on the device location and get coordinates
     */
    private void observeLocation() {
        mainViewModel.getCoordinates().observe(getViewLifecycleOwner(), coordinates -> {
            if (coordinates != null) {
                subscribeObservers(coordinates);
            }
        });
    }

    /**
     * Query weather data for the current location
     * using it's coordinates
     * @param coordinates - current location coordinates
     */
    private void subscribeObservers(double[] coordinates) {
        viewModel.getWeatherForecast(coordinates, NetworkUtil
                .isConnected(Objects.requireNonNull(getActivity())))
                .observe(getViewLifecycleOwner(), forecastResource -> {
            if (forecastResource != null) {
                switch (forecastResource.status) {
                    case LOADING:
                        progressBar.setVisibility(View.VISIBLE);
                        break;
                    case SUCCESS:
                        progressBar.setVisibility(View.GONE);
                        if (forecastResource.data != null) {
                            adapter.setWeatherForecast(forecastResource.data.getList());
                        }
                        break;
                    case ERROR:
                        progressBar.setVisibility(View.GONE);
                        Util.toast(getActivity(), "Error: " + forecastResource.message);
                        break;
                }
            }
        });
    }

    /**
     * Listen for changes on the search query
     * It has dual use cases!
     * A search query changes when the user actually searches
     * for a city name or when the user triggers a swipe to refresh action
     */
    private void subscribeToMain() {
        mainViewModel.getSearchQuery().observe(getViewLifecycleOwner(), s -> {
            if (s != null) {
                getWeatherForecast(s);
            }
        });
    }

    /**
     * Get the five day weather forecast for specified city
     * @param cityName - name of city whose weather we are querying
     */
    private void getWeatherForecast(String cityName) {
        viewModel.getWeatherForecast(cityName, connected()).observe(getViewLifecycleOwner(),
                forecastResource -> {
            if (forecastResource != null) {
                if (forecastResource.status == Resource.Status.SUCCESS) {
                    if (forecastResource.data != null) {
                        adapter.setWeatherForecast(forecastResource.data.getList());
                    }
                }
                else if (forecastResource.status == Resource.Status.ERROR) {
                    Util.toast(getActivity(), "Error: " + forecastResource.message);
                }
            }
        });
    }

    /*--- SUPPORT METHODS ----*/

    private void setupRecyclerView() {
        forecastList.setLayoutManager(new LinearLayoutManager(getActivity()));
        forecastList.setAdapter(adapter);
    }

    private boolean connected() {
        return NetworkUtil.isConnected(Objects.requireNonNull(getActivity()));
    }

}