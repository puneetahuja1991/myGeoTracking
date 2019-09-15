package com.abaqustest.mygeotrackingapp;

import android.app.Application;
import android.content.Context;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.abaqustest.mygeotrackingapp.worker.TaskWorker;

import java.util.concurrent.TimeUnit;


/**
 * The type Contact application class.
 *
 * @author Puneet Ahuja
 */
public class MyGeoTrackingApplication extends Application {

    private static Context appContext;
    private PeriodicWorkRequest periodicWorkRequest;


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
        setUpTaskWorkerThread();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    /**
     * Sets up task worker thread.
     */
    private void setUpTaskWorkerThread() {
        periodicWorkRequest = new PeriodicWorkRequest.Builder(TaskWorker.class, 15, TimeUnit.MINUTES)
                .setInitialDelay(15,TimeUnit.MINUTES)
                .build();
        WorkManager.getInstance(this).enqueueUniquePeriodicWork("my_task_work", ExistingPeriodicWorkPolicy.KEEP,periodicWorkRequest);
    }
}
