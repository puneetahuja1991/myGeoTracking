package com.abaqustest.mygeotrackingapp.viewmodel;

import android.app.Application;

import com.abaqustest.mygeotrackingapp.model.Tasks;
import com.abaqustest.mygeotrackingapp.repository.TasksRepository;
import com.abaqustest.mygeotrackingapp.utils.GenericResponseListener;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

/**
 * Main view model class.
 *
 * @author Puneet Ahuja
 */
public class MainViewModel extends AndroidViewModel {


    private TasksRepository mTaskRepository;
    private MutableLiveData<List<Tasks>> tasksMutableLiveData;

    /**
     * Instantiates a new Main view model.
     *
     * @param application the application
     */
    public MainViewModel(@NonNull Application application) {
        super(application);
        init();
    }

    /**
     * Init.
     */
    private void init() {
        mTaskRepository = new TasksRepository();
        tasksMutableLiveData = new MutableLiveData<>();
    }

    /**
     * Gets tasks mutable live data.
     *
     * @return the tasks mutable live data
     */
    public MutableLiveData<List<Tasks>> getTasksMutableLiveData() {
        return tasksMutableLiveData;
    }

    /**
     * Get Tasks.
     */
    public void getTasks(){
        mTaskRepository.getTaskList(new GenericResponseListener<Tasks>() {
            @Override
            public void onError(String error) {
                //handle the error here. As of now this is not required. Becuase Json is static.
            }

            @Override
            public void onSuccess(Tasks response) {
            }
        });
    }
}