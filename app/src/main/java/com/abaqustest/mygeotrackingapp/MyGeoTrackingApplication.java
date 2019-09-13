package com.abaqustest.mygeotrackingapp;

import android.app.Application;
import android.content.Context;
import androidx.appcompat.app.AppCompatDelegate;


/**
 * The type Contact application class.
 *
 * @author Puneet Ahuja
 */
public class MyGeoTrackingApplication extends Application {

    private static Context appContext;


    /**
     * Gets App Context.
     *
     * @return the App Context
     */
    public static Context getAppContext() {
        return appContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}
