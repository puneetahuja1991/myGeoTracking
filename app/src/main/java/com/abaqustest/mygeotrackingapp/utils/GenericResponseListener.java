package com.abaqustest.mygeotrackingapp.utils;

/**
 * The interface Generic response listener.
 *
 * @param <T> the type parameter
 * @author Puneet Ahuja
 */
public interface GenericResponseListener<T> {


    /**
     * On error.
     *
     * @param error the error
     */
    void onError(String error);

    /**
     * On success.
     *
     * @param response the response
     */
    void onSuccess(T response);

}
