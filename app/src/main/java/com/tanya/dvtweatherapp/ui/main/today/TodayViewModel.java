package com.tanya.dvtweatherapp.ui.main.today;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.tanya.dvtweatherapp.data.WeatherRepository;
import com.tanya.dvtweatherapp.models.CurrentWeather;
import com.tanya.dvtweatherapp.data.remote.WeatherApi;
import com.tanya.dvtweatherapp.data.Resource;

import javax.inject.Inject;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class TodayViewModel extends ViewModel {

    //private final WeatherApi weatherApi;

    //private final MediatorLiveData<Resource<CurrentWeather>> currentWeather = new MediatorLiveData<>();

    private final WeatherRepository weatherRepository;

    private LiveData<Resource<CurrentWeather>> currentWeather;


    @Inject
    public TodayViewModel(WeatherRepository weatherRepository) {
        //this.weatherApi = weatherApi;
        this.weatherRepository = weatherRepository;
    }

    public LiveData<Resource<CurrentWeather>> getCurrentWeather(int id) {
        return weatherRepository.getCurrentWeather(id);
    }

   /* public void getCurrentWeather(int id) {

        currentWeather.setValue(Resource.loading(null));

        final LiveData<Resource<CurrentWeather>> source = LiveDataReactiveStreams.fromPublisher(
                weatherApi.getCurrentWeather(id)
                .onErrorReturn(throwable -> {
                    CurrentWeather errorWeather = new CurrentWeather();

                    errorWeather.setDt(-10);
                    errorWeather.setErrorMessage(throwable.getMessage());
                    return errorWeather;
                })
                .map((Function<CurrentWeather, Resource<CurrentWeather>>) currentWeather -> {
                    if (currentWeather.getDt() ==  -10)
                        return Resource.error(currentWeather.getErrorMessage(), null);
                    return Resource.success(currentWeather);
                })
                .subscribeOn(Schedulers.io())
        );

        currentWeather.addSource(source, currentWeatherResource -> {
            currentWeather.setValue(currentWeatherResource);
            currentWeather.removeSource(source);
        });

    } */


    public LiveData<Resource<CurrentWeather>> observeCurrentWeather() {
        return currentWeather;
    }

    public String getMsg() {
        if (weatherRepository != null) {
            return weatherRepository.getMsg();
        }
        else {
            return "Weather repository is null";
        }
    }

}
