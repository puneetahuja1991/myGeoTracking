package com.abaqustest.mygeotrackingapp.repository;

import com.abaqustest.mygeotrackingapp.model.Task;
import com.abaqustest.mygeotrackingapp.network.ApiInterface;
import com.abaqustest.mygeotrackingapp.network.RestClient;
import com.abaqustest.mygeotrackingapp.utils.helper.GenericResponseListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import androidx.annotation.NonNull;
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

    /**
     * Instantiates a new Task repository.
     */
    public TasksRepository() {
        apiInterface = RestClient.getInstance().getNetworkApiInterface();
    }

    /**
     * Gets Task list.
     *
     * @param listener the listener
     */
    public void getTaskList( @NonNull GenericResponseListener<List<Task>> listener) {
        Call<List<Task>> call1 = apiInterface.getTasksList();
        call1.enqueue(new Callback<List<Task>>() {
            @Override
            public void onResponse(Call<List<Task>> call, Response<List<Task>> response) {
                if(response.isSuccessful()){
                    listener.onSuccess(response.body());
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
}