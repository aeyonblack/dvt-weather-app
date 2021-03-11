package com.tanya.dvtweatherapp.ui.main.forecast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.tanya.dvtweatherapp.models.Forecast;
import com.tanya.dvtweatherapp.data.remote.WeatherApi;
import com.tanya.dvtweatherapp.data.Resource;

import javax.inject.Inject;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Performs query for the 5 day forecast weather data
 */
public class ForecastViewModel extends ViewModel {

    private final WeatherApi weatherApi;

    private final MediatorLiveData<Resource<Forecast>> weatherForecast = new MediatorLiveData<>();

    @Inject
    public ForecastViewModel(WeatherApi weatherApi) {
        this.weatherApi = weatherApi;
    }

  /*  public void getWeatherForecast(int id) {

        // Sets status to loading since data = null
        weatherForecast.setValue(Resource.loading(null));

        final LiveData<Resource<Forecast>> source = LiveDataReactiveStreams.fromPublisher(
                weatherApi.getFiveDayForecast(id)
                .onErrorReturn(throwable -> {
                    Forecast forecast = new Forecast();
                    forecast.setError(true);
                    forecast.setErrorMessage(throwable.getMessage());
                    return forecast;
                })
                .map((Function<Forecast, Resource<Forecast>>) forecast -> {
                    if (forecast.isError())
                        return Resource.error(forecast.getErrorMessage(), null);
                    return Resource.success(forecast);
                })
                .subscribeOn(Schedulers.io())
        );

        weatherForecast.addSource(source, forecastResource -> {
            weatherForecast.setValue(forecastResource);
            weatherForecast.removeSource(source);
        });

    } */

    public LiveData<Resource<Forecast>> observeForecast() {
        return weatherForecast;
    }

}
