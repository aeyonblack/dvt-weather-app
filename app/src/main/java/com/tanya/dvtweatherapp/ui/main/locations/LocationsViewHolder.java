package com.tanya.dvtweatherapp.ui.main.locations;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tanya.dvtweatherapp.R;
import com.tanya.dvtweatherapp.models.FavouriteLocation;

public class LocationsViewHolder extends RecyclerView.ViewHolder {

    private final TextView locationNameView;

    public LocationsViewHolder(@NonNull View itemView) {
        super(itemView);
        locationNameView = itemView.findViewById(R.id.location_name);
    }

    public void bind(FavouriteLocation location) {
        locationNameView.setText(location.getLocationName());
    }

}
