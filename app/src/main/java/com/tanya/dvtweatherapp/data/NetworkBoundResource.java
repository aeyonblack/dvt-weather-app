package com.tanya.dvtweatherapp.data;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.tanya.dvtweatherapp.network.ApiResponse;
import com.tanya.dvtweatherapp.utils.AppExecutors;

/**
 * This is a modified/refactored version of the generic class provided by google
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public abstract class NetworkBoundResource<CachedData, RequestData> {

    // Responsible for threading
    private final AppExecutors appExecutors;

    private final MediatorLiveData<Resource<CachedData>> result = new MediatorLiveData<>();

    public NetworkBoundResource(AppExecutors appExecutors) {
        this.appExecutors = appExecutors;
        init();
    }

    private void init() {

        result.setValue(Resource.loading(null));

        LiveData<CachedData> dbSource = loadFromDb();

        result.addSource(dbSource, cachedData -> {
            result.removeSource(dbSource);
            if (shouldFetch(cachedData)) {
                fetchFromNetwork(dbSource);
            }
            else {
                result.addSource(dbSource, cachedData1 -> setValue(Resource.success(cachedData1)));
            }
        });

    }

    /**
     * Observe data from the REST Api and load it into local database
     */
    private void fetchFromNetwork(LiveData<CachedData> dbSource) {
        result.addSource(dbSource, cachedData -> setValue(Resource.loading(cachedData)));

        LiveData<ApiResponse<RequestData>> apiResponse = createCall();

        result.addSource(apiResponse, requestDataApiResponse -> {
            result.removeSource(dbSource);
            result.removeSource(apiResponse);
            if (requestDataApiResponse instanceof ApiResponse.ApiSuccessResponse) {
                appExecutors.diskIO().execute(() -> {
                    saveCallResult((RequestData) processResponse
                            ((ApiResponse.ApiSuccessResponse)requestDataApiResponse));
                    appExecutors.mainThread().execute(() -> result.addSource(loadFromDb(),
                            cachedData -> setValue(Resource.success(cachedData))));
                });
            }
            else if (requestDataApiResponse instanceof ApiResponse.ApiEmptyResponse) {
                appExecutors.mainThread().execute(() -> result.addSource(loadFromDb(),
                        cachedData -> setValue(Resource.success(cachedData))));
            }
            else if (requestDataApiResponse instanceof ApiResponse.ApiErrorResponse) {
                result.addSource(dbSource, cachedData -> setValue(Resource.error
                        (((ApiResponse.ApiErrorResponse)requestDataApiResponse)
                                .getErrorMessage(), cachedData)
                ));
            }
        });

    }

    private CachedData processResponse(ApiResponse.ApiSuccessResponse response) {
        return (CachedData) response.getBody();
    }

    private void setValue(Resource<CachedData> newValue) {
        if (result.getValue() != newValue) {
            result.setValue(newValue);
        }
    }

    // Called to save the result of the API response into the database.
    @WorkerThread
    protected abstract void saveCallResult(@NonNull RequestData item);

    // Called with the data in the database to decide whether to fetch
    // potentially updated data from the network.
    @MainThread
    protected abstract boolean shouldFetch(@Nullable CachedData data);

    // Called to get the cached data from the database.
    @NonNull @MainThread
    protected abstract LiveData<CachedData> loadFromDb();

    // Called to create the API call.
    @NonNull @MainThread
    protected abstract LiveData<ApiResponse<RequestData>> createCall();

    // Returns a LiveData object that represents the resource that's implemented
    // in the base class.
    public final LiveData<Resource<CachedData>> getAsLiveData(){
        return result;
    }

}