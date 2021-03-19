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
import com.tanya.dvtweatherapp.utils.ToastUtil;
import com.tanya.dvtweatherapp.viewmodel.ViewModelProviderFactory;

import java.util.Objects;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class ForecastFragment extends DaggerFragment {

    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    ForecastRecyclerAdapter adapter;

    private ForecastViewModel viewModel;

    private MainViewModel mainViewModel;

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

        setupRecyclerView();

        subscribeObservers();

        subscribeToMain();

    }

    private void subscribeObservers() {
        viewModel.getWeatherForecast(1020098, NetworkUtil
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
                        ToastUtil.toast(getActivity(), "Error: " + forecastResource.message);
                        break;
                }
            }
        });
    }

    private void subscribeToMain() {
        mainViewModel.getSearchQuery().observe(getViewLifecycleOwner(), s -> {
            if (s != null) {
                getWeatherForecast(s);
            }
        });
    }

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
                    ToastUtil.toast(getActivity(), "Error: " + forecastResource.message);
                }
            }
        });
    }

    private void setupRecyclerView() {
        forecastList.setLayoutManager(new LinearLayoutManager(getActivity()));
        forecastList.setAdapter(adapter);
    }

    private boolean connected() {
        return NetworkUtil.isConnected(Objects.requireNonNull(getActivity()));
    }

}