package com.abaqustest.mygeotrackingapp.viewmodel;

import android.app.Application;
import android.widget.Toast;

import com.abaqustest.mygeotrackingapp.base.BaseViewModel;
import com.abaqustest.mygeotrackingapp.database.Task;
import com.abaqustest.mygeotrackingapp.repository.TasksRepository;
import com.abaqustest.mygeotrackingapp.utils.helper.GenericResponseListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

/**
 * Main view model class.
 *
 * @author Puneet Ahuja
 */
public class MainViewModel extends BaseViewModel {

    private TasksRepository mTaskRepository;
    private MutableLiveData<List<Task>> pendingTasksMutableLiveData;
    private MutableLiveData<List<Task>> doneTasksMutableLiveData;

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
        pendingTasksMutableLiveData = new MutableLiveData<>();
        doneTasksMutableLiveData = new MutableLiveData<>();
    }

    /**
     * Get Task.
     *
     * @param isRefreshForcefully the is refresh forcefully
     */
    public void getTasks(boolean isRefreshForcefully){
        isLoading.setValue(true);
        mTaskRepository.getTaskList(new GenericResponseListener<List<Task>>() {
            @Override
            public void onError(String error) {
                isLoading.setValue(false);
                Toast.makeText(getApplication(),error,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSuccess(List<Task> response) {
                isLoading.setValue(false);
            }
        },isRefreshForcefully);
    }

    /**
     * Populate tasks.
     *
     * @param response the response
     */
    public void populateTasks(List<Task> response) {
        List<Task> pendingTask=new ArrayList<>();
        List<Task> doneTask=new ArrayList<>();

        for(int i=0; i<response.size();i++) {
            if(response.get(i).getState()==0) {
                pendingTask.add(response.get(i));
            }else if(response.get(i).getState()==1) {
                doneTask.add(response.get(i));
            }
        }
        pendingTasksMutableLiveData.setValue(pendingTask);
        doneTasksMutableLiveData.setValue(doneTask);
    }

    /**
     * Gets pending tasks mutable live data.
     *
     * @return the pending tasks mutable live data
     */
    public MutableLiveData<List<Task>> getPendingTasksMutableLiveData() {
        return pendingTasksMutableLiveData;
    }

    /**
     * Gets done tasks mutable live data.
     *
     * @return the done tasks mutable live data
     */
    public MutableLiveData<List<Task>> getDoneTasksMutableLiveData() {
        return doneTasksMutableLiveData;
    }

    /**
     * Get task live data live data.
     *
     * @return the live data
     */
    public LiveData<List<Task>> getTaskLiveData(){
        return mTaskRepository.getTasksLiveData();
    }

    /**
     * Delete task.
     *
     * @param task the task
     */
    public void deleteTask(Task task) {
        mTaskRepository.deleteTask(task);
    }
}