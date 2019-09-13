package com.abaqustest.mygeotrackingapp.base;

import android.app.Application;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
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

    /**
     * Sets error.
     *
     * @param editText   the edit text
     * @param strOrResId the str or res id
     */
    @BindingAdapter("error")
    public static void setError(TextInputLayout editText, Object strOrResId) {
        if (strOrResId instanceof Integer) {
            editText.setError(
                    editText.getContext().getString((Integer) strOrResId));
        } else {
            editText.setError((String) strOrResId);
        }
    }

    /**
     * Bind focus change.
     *
     * @param editText              the edit text
     * @param onFocusChangeListener the on focus change listener
     */
    @BindingAdapter("onFocus")
    public static void bindFocusChange(TextInputEditText editText, View.OnFocusChangeListener onFocusChangeListener) {
        if (editText.getOnFocusChangeListener() == null) {
            editText.setOnFocusChangeListener(onFocusChangeListener);
        }
    }
}
