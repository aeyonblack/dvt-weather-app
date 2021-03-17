package com.tanya.dvtweatherapp.ui.main.forecast;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.tanya.dvtweatherapp.R;
import com.tanya.dvtweatherapp.models.WeatherList;
import com.tanya.dvtweatherapp.utils.DateFormatter;
import com.tanya.dvtweatherapp.utils.ToastUtil;
import com.tanya.dvtweatherapp.utils.WeatherIconManager;

import java.util.List;

import javax.inject.Inject;

public class ForecastViewHolder extends RecyclerView.ViewHolder {

    private final TextView
            dateTextView,
            weatherConditionTextView,
            temperatureTextView,
            minTempTextView;

    private final ImageView weatherIconView;

    public ForecastViewHolder(@NonNull View itemView) {
        super(itemView);
        dateTextView = itemView.findViewById(R.id.date_forecast);
        weatherConditionTextView = itemView.findViewById(R.id.weather_condition_forecast);
        temperatureTextView = itemView.findViewById(R.id.temp_forecast);
        minTempTextView = itemView.findViewById(R.id.min_temp_forecast);
        weatherIconView = itemView.findViewById(R.id.weather_icon_forecast);
    }

    @SuppressLint("SetTextI18n")
    public void bind(List<WeatherList> weatherList, int position, Context context) {

        String[] date = DateFormatter.getDayOfWeek(weatherList.get(0).getDtTxt(),
                position + 1).split(" ");

        dateTextView.setText(date[1]);

        int i = getDatePosition(date[0] + " 12:00:00", weatherList);

        //dateTextView.setText("Index: " + i);

        weatherConditionTextView.setText(weatherList.get(i).getWeather().get(0).getDescription());

        int temp = (int)Math.round(weatherList.get(i).getMain().getMaxTemp());
        temperatureTextView.setText(temp + "\u00B0");

        int minTemp = (int)Math.round(weatherList.get(i).getMain().getMinTemp());
        minTempTextView.setText(minTemp + "\u00B0");

        int weatherIcon = WeatherIconManager.getIcon(weatherList.get(i).getWeather().get(0).getIcon(),
                weatherList.get(i).getWeather().get(0).getDescription());

        Glide.with(context).load(weatherIcon).into(weatherIconView);

    }

    private int getDatePosition(String date, List<WeatherList> weatherList) {
        int index = 0;
        for (int i = 0; i < weatherList.size(); i++) {
            if (date.equals(weatherList.get(i).getDtTxt())) {
                index = i;
                break;
            }
        }
        return index;
    }

}
