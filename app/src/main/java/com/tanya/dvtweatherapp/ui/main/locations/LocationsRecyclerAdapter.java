package com.tanya.dvtweatherapp.ui.main.locations;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tanya.dvtweatherapp.R;
import com.tanya.dvtweatherapp.models.FavouriteLocation;

import java.util.ArrayList;
import java.util.List;

public class LocationsRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<FavouriteLocation> favouriteLocations = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.location_list_item, parent, false);
        return new LocationsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((LocationsViewHolder)holder).bind(favouriteLocations.get(position));
    }

    public void setFavouriteLocations(List<FavouriteLocation> favouriteLocations) {
        this.favouriteLocations = favouriteLocations;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return favouriteLocations.size();
    }

}
