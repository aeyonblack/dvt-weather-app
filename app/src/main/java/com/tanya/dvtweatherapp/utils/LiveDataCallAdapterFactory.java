package com.tanya.dvtweatherapp.utils;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.tanya.dvtweatherapp.network.ApiResponse;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import retrofit2.CallAdapter;
import retrofit2.Retrofit;

/**
 * Returns the response type for all Retrofit requests
 */
public class LiveDataCallAdapterFactory extends CallAdapter.Factory {

    @Override
    public CallAdapter<?, ?> get(@NonNull Type returnType, @NonNull Annotation[] annotations,
                                 @NonNull Retrofit retrofit) {

        // Check if CallAdapter is returning a type of LiveData
        if (CallAdapter.Factory.getRawType(returnType) != LiveData.class) {
            return null;
        }

        // The observable type that LiveData is wrapping
        Type observableType = CallAdapter.Factory
                .getParameterUpperBound(0, (ParameterizedType)returnType);

        // Check if the observable type is of ApiResponse
        Type rawObservableType = CallAdapter.Factory.getRawType(observableType);
        if (rawObservableType != ApiResponse.class) {
            throw new IllegalArgumentException("Type must be a defined resource");
        }

        // Check if ApiResponse is parameterized -> ApiResponse<T>
        // where T = Forecast or CurrentWeather
        if (!(observableType instanceof ParameterizedType)) {
            throw new IllegalArgumentException("Resource must be parameterized");
        }

        Type bodyType = CallAdapter.Factory.getParameterUpperBound(0, (ParameterizedType)observableType);
        return new LiveDataCallAdapter<Type>(bodyType);
    }

}
