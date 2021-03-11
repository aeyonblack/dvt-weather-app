package com.tanya.dvtweatherapp.ui.main.forecast;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.tanya.dvtweatherapp.R;
import com.tanya.dvtweatherapp.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class ForecastFragment extends DaggerFragment {

    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    ForecastRecyclerAdapter adapter;

    private ForecastViewModel viewModel;

    private RecyclerView forecastList;

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

        viewModel = new ViewModelProvider(this, providerFactory).get(ForecastViewModel.class);

        //setupRecyclerView();

        //subscribeObservers();

        //viewModel.getWeatherForecast(1020098);

    }

    private void subscribeObservers() {
        viewModel.observeForecast().observe(getViewLifecycleOwner(), forecastResource -> {
            if (forecastResource != null) {
                switch (forecastResource.status) {
                    case LOADING:
                        toast("Loading");
                        break;
                    case SUCCESS:
                        toast("Success");
                        adapter.setWeatherForecast(forecastResource.data.getList());
                        break;
                    case ERROR:
                        toast("Error: " + forecastResource.message);
                        break;
                }
            }
        });
    }

    private void setupRecyclerView() {
        forecastList.setLayoutManager(new LinearLayoutManager(getActivity()));
        forecastList.setAdapter(adapter);
    }

    private void toast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

}