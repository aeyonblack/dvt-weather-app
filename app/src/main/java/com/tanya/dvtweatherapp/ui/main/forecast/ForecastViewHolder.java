package com.tanya.dvtweatherapp.ui.main.forecast;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tanya.dvtweatherapp.R;
import com.tanya.dvtweatherapp.models.WeatherList;

public class ForecastViewHolder extends RecyclerView.ViewHolder {

    private final TextView dateTextView, weatherConditionTextView, temperatureTextView;

    public ForecastViewHolder(@NonNull View itemView) {
        super(itemView);
        dateTextView = itemView.findViewById(R.id.date_forecast);
        weatherConditionTextView = itemView.findViewById(R.id.weather_condition_forecast);
        temperatureTextView = itemView.findViewById(R.id.temp_forecast);
    }

    @SuppressLint("SetTextI18n")
    public void bind(WeatherList weatherList) {
        dateTextView.setText("Date: " + weatherList.getDt());
        weatherConditionTextView.setText(weatherList.getWeather().get(0).getDescription());
        temperatureTextView.setText(weatherList.getMain().getTemp() + " degrees");
    }

}
