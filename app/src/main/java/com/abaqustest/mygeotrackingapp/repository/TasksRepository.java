package com.abaqustest.mygeotrackingapp.repository;

import android.os.AsyncTask;

import com.abaqustest.mygeotrackingapp.database.Task;
import com.abaqustest.mygeotrackingapp.database.TasksDatabase;
import com.abaqustest.mygeotrackingapp.network.ApiInterface;
import com.abaqustest.mygeotrackingapp.network.RestClient;
import com.abaqustest.mygeotrackingapp.utils.helper.GenericResponseListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Tasks repository class which does all the operations for Task .
 *
 * @author Puneet Ahuja
 */
public class TasksRepository {

    private ApiInterface apiInterface;
    private TasksDatabase tasksDatabase;

    /**
     * Instantiates a new Task repository.
     */
    public TasksRepository() {
        apiInterface = RestClient.getInstance().getNetworkApiInterface();
        tasksDatabase = TasksDatabase.getTasksDatabase();
    }

    /**
     * Gets Task list.
     *
     * @param listener            the listener
     * @param isRefreshForcefully the is refresh forcefully
     */
    public void getTaskList(@NonNull GenericResponseListener<List<Task>> listener, boolean isRefreshForcefully) {
        if(isRefreshForcefully)
            getTaskFromServer(listener);
        else
            fetchTasks(listener);
    }

    /**
     * Gets task from server.
     *
     * @param listener the listener
     */
    private void getTaskFromServer(@NonNull GenericResponseListener<List<Task>> listener) {
        Call<List<Task>> call1 = apiInterface.getTasksList();
        call1.enqueue(new Callback<List<Task>>() {
            @Override
            public void onResponse(Call<List<Task>> call, Response<List<Task>> response) {
                if(response.isSuccessful()){
                    listener.onSuccess(response.body());
                    deleteTasks(response.body());
                }else {
                    try {
                        JSONObject errorObject  = new JSONObject(response.errorBody().string());
                        listener.onError(errorObject.optString("message"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                        listener.onError("Parsing Went Wrong");
                    } catch (IOException e) {
                        listener.onError("Please check your internet connection");
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<List<Task>> call, Throwable t) {
                if(t instanceof IOException)
                    listener.onError("Please check your internet connection");
                else
                    listener.onError("Something went wrong");
                call.cancel();
            }
        });
    }

    /**
     * Insert task.
     *
     * @param task the message
     */
    public void insertTask(Task task) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                tasksDatabase.daoAccess().insertTask(task);
                return null;
            }
        }.execute();
    }

    /**
     * Insert tasks.
     *
     * @param tasks the tasks
     */
    private void insertTasks(List<Task> tasks) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                tasksDatabase.daoAccess().insertTasks(tasks);
                return null;
            }
        }.execute();
    }

    /**
     * Delete tasks.
     *
     * @param tasks the tasks
     */
    private void deleteTasks(List<Task> tasks) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                tasksDatabase.clearAllTables();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                insertTasks(tasks);
            }
        }.execute();
    }

    /**
     * Fetch tasks.
     * @param listener
     */
    private void fetchTasks(@NonNull GenericResponseListener<List<Task>> listener) {
        new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground(Void... voids) {
                Long count = tasksDatabase.daoAccess().getTaskCount();
                return count;
            }

            @Override
            protected void onPostExecute(Long count) {
                super.onPostExecute(count);
                if(count == 0)
                    getTaskFromServer(listener);
                else
                    fetchTasksFromDB(listener);
            }
        }.execute();
    }


    /**
     * Fetch tasks from db.
     *
     * @param listener the listener
     */
    private void fetchTasksFromDB(@NonNull GenericResponseListener<List<Task>> listener) {
        new AsyncTask<Void, Void, List<Task>>() {
            @Override
            protected List<Task> doInBackground(Void... voids) {
                List<Task> tasks= tasksDatabase.daoAccess().fetchAllTasks().getValue();
                return tasks;
            }

            @Override
            protected void onPostExecute(List<Task> tasks) {
                super.onPostExecute(tasks);
               listener.onSuccess(tasks);

            }
        }.execute();
    }


    /**
     * Insert task to db.
     *
     * @param taskName the task name
     */
    public void insertTaskToDb(String taskName) {
        Task task = new Task();
        task.setState(0);
        task.setTask(taskName);
        insertTask(task);
    }

    /**
     * Get tasks live data live data.
     *
     * @return the live data
     */
    public LiveData<List<Task>> getTasksLiveData(){
        return tasksDatabase.daoAccess().fetchAllTasks();
    }

    /**
     * Delete task.
     *
     * @param task the task
     */
    public void deleteTask(Task task) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                tasksDatabase.daoAccess().deleteTask(task);
                return null;
            }
        }.execute();
    }

    /**
     * Update task.
     *
     * @param task the task
     */
    public void updateTask(Task task) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                tasksDatabase.daoAccess().updateTask(task);
                return null;
            }
        }.execute();
    }
}