package com.tanya.dvtweatherapp.ui.main.forecast;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tanya.dvtweatherapp.R;
import com.tanya.dvtweatherapp.models.WeatherList;

import java.util.ArrayList;
import java.util.List;

public class ForecastRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<WeatherList> weatherForecast = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.forecast_list_item, parent, false);
        return new ForecastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // Any view holder that extends RecyclerView.ViewHolder can be used here, #SOLID
        ((ForecastViewHolder)holder).bind(weatherForecast.get(position));
    }

    @Override
    public int getItemCount() {
        return weatherForecast.size();
    }

    public void setWeatherForecast(List<WeatherList> weatherForecast) {
        this.weatherForecast = weatherForecast;
        notifyDataSetChanged();
    }

}
