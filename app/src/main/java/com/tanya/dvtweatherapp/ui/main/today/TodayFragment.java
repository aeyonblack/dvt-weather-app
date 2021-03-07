package com.tanya.dvtweatherapp.ui.main.today;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.tanya.dvtweatherapp.R;
import com.tanya.dvtweatherapp.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class TodayFragment extends DaggerFragment implements View.OnClickListener {

    private TodayViewModel viewModel;

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

        view.findViewById(R.id.test_search_button).setOnClickListener(this);

        viewModel = new ViewModelProvider(this, providerFactory).get(TodayViewModel.class);

        subscribeObservers();

    }

    private void subscribeObservers() {
        viewModel.observeCurrentWeather().observe(getViewLifecycleOwner(), currentWeather -> {
            if (currentWeather != null) {
                Toast.makeText(getActivity(), "City Name: " + currentWeather.getCity().getName(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public void onClick(View view) {

    }
}