package com.abaqustest.mygeotrackingapp.network;


import com.abaqustest.mygeotrackingapp.model.Task;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * The interface api interface.
 * <p>
 * It will contain all the API calls.
 *
 * @author Puneet Ahuja
 */
public interface ApiInterface {

    /**
     * Gets tasks list.
     *
     * @return the tasks list
     */
    @GET(ApiEndpoint.GET_TASKS_LIST)
    Call<List<Task>> getTasksList();

}
