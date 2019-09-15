package com.abaqustest.mygeotrackingapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.abaqustest.mygeotrackingapp.base.BaseViewModel;
import com.abaqustest.mygeotrackingapp.database.Notification;
import com.abaqustest.mygeotrackingapp.repository.TasksRepository;

import java.util.List;

/**
 * The type Notifications View Model.
 *
 * @author Puneet Ahuja
 */
public class NotificationsViewModel extends BaseViewModel {

    private TasksRepository tasksRepository;


    /**
     * Instantiates a new Notifications view model.
     *
     * @param application the application
     */
    public NotificationsViewModel(@NonNull Application application) {
        super(application);
        init();
    }

    /**
     * Init.
     */
    private void init() {
        tasksRepository = new TasksRepository();
    }

    /**
     * Gets notification live data.
     *
     * @return the notification live data
     */
    public LiveData<List<Notification>> getNotificationLiveData() {
        return tasksRepository.getNotificationLiveData();
    }
}
