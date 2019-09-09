package com.abaqustest.mygeotrackingapp;

import android.app.Application;
import android.content.Context;


/**
 * The My application class.
 *
 * @author Puneet Ahuja
 */
public class MyApplication extends Application {

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
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}
