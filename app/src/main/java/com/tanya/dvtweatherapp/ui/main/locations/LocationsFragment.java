package com.tanya.dvtweatherapp.ui.main.locations;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tanya.dvtweatherapp.R;
import com.tanya.dvtweatherapp.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class LocationsFragment extends DaggerFragment {

    private LocationsViewModel viewModel;

    private RecyclerView locationsList;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    LocationsRecyclerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_locations, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        locationsList = view.findViewById(R.id.location_list);

        viewModel = new ViewModelProvider(this, providerFactory).get(LocationsViewModel.class);

        setupRecyclerView();

        subscribeObservers();
    }

    private void subscribeObservers() {
        viewModel.getFavouriteLocations().observe(getViewLifecycleOwner(), favouriteLocations -> {
            if (favouriteLocations != null) {
                // Do something
                adapter.setFavouriteLocations(favouriteLocations);
            }
        });
    }

    private void setupRecyclerView() {
        locationsList.setLayoutManager(new LinearLayoutManager(getActivity()));
        locationsList.setAdapter(adapter);
    }

}