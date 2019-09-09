package com.abaqustest.mygeotrackingapp.base;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

/**
 * Base view model. it handles the common functionality.
 *
 * @author Puneet Ahuja
 */
public class BaseViewModel extends AndroidViewModel {

    protected MutableLiveData<Boolean> isLoading;
    protected MutableLiveData<String> error;

    /**
     * Instantiates a new Base view model.
     *
     * @param application the application
     */
    public BaseViewModel(@NonNull Application application) {
        super(application);
        isLoading = new MutableLiveData<>();
        error = new MutableLiveData<>();
    }

    /**
     * Gets is loading.
     *
     * @return the is loading
     */
    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    /**
     * Gets error.
     *
     * @return the error
     */
    public MutableLiveData<String> getError() {
        return error;
    }
}
