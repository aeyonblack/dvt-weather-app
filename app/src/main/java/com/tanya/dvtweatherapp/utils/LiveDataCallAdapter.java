package com.tanya.dvtweatherapp.utils;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.tanya.dvtweatherapp.network.ApiResponse;

import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressWarnings("unchecked")
public class LiveDataCallAdapter<R> implements CallAdapter<R, LiveData<ApiResponse<R>>> {

    private final Type responseType;

    public LiveDataCallAdapter(Type responseType) {
        this.responseType = responseType;
    }

    @NonNull
    @Override
    public Type responseType() {
        return responseType;
    }

    @NonNull
    @Override
    public LiveData<ApiResponse<R>> adapt(@NonNull Call<R> call) {
        return new LiveData<ApiResponse<R>>() {
            @SuppressWarnings("rawtypes")
            @Override
            protected void onActive() {
                super.onActive();
                ApiResponse apiResponse = new ApiResponse();
                if (!call.isExecuted()) {
                    call.enqueue(new Callback<R>() {
                        @Override
                        public void onResponse(@NonNull Call<R> call, @NonNull Response<R> response) {
                            postValue(apiResponse.create(response));
                        }

                        @Override
                        public void onFailure(@NonNull Call<R> call, @NonNull Throwable t) {
                            postValue(apiResponse.create(t));
                        }
                    });
                }
            }
        };
    }

}
