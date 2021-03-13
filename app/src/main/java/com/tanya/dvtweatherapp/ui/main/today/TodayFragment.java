package com.tanya.dvtweatherapp.ui.main.today;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.tanya.dvtweatherapp.R;
import com.tanya.dvtweatherapp.models.CurrentWeather;
import com.tanya.dvtweatherapp.utils.NetworkUtil;
import com.tanya.dvtweatherapp.viewmodel.ViewModelProviderFactory;

import java.util.Objects;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class TodayFragment extends DaggerFragment implements View.OnClickListener {

    private TodayViewModel viewModel;

    private TextView errorTextView;

    private CurrentWeather currentWeather;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_today, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.test_save_location_button).setOnClickListener(this);

        errorTextView = view.findViewById(R.id.test_city_name);

        viewModel = new ViewModelProvider(this, providerFactory).get(TodayViewModel.class);

        subscribeObservers();

        //viewModel.getCurrentWeather(1020098);

        toast(viewModel.getMsg());

    }

    @SuppressLint("SetTextI18n")
    private void subscribeObservers() {
        viewModel.getCurrentWeather(1020098, NetworkUtil
                .isConnected(Objects.requireNonNull(getActivity())))
                .observe(getViewLifecycleOwner(), currentWeatherResource -> {
            if (currentWeatherResource != null) {
                switch (currentWeatherResource.status) {
                    case LOADING:
                        //toast("Loading");
                        break;
                    case SUCCESS:
                        //toast("Success");
                        if (currentWeatherResource.data != null) {
                            displayWeatherData(currentWeatherResource.data);
                            currentWeather = currentWeatherResource.data;
                        }
                        break;
                    case ERROR:
                        //toast("Error " + currentWeatherResource.message);
                        errorTextView.setText("Error: " + currentWeatherResource.message);
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.test_save_location_button) {
            toast("Saving location");
            viewModel.saveWeatherLocation(currentWeather);
        }
    }

    private void toast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("SetTextI18n")
    private void displayWeatherData(CurrentWeather currentWeather) {
        errorTextView.setText("City Name: " + currentWeather.getName());
    }

}