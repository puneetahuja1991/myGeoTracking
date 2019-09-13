package com.abaqustest.mygeotrackingapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**
 * The interface Dao access.
 *
 * @author Puneet Ahuja
 */
@Dao
public interface DaoAccess {

    /**
     * Insert task long.
     *
     * @param task the task
     * @return the long
     */
    @Insert
    Long insertTask(Task task);

    /**
     * Insert tasks long.
     *
     * @param tasks the tasks
     * @return the long
     */
    @Insert
    Long[] insertTasks(List<Task> tasks);

    /**
     * Fetch all taks live data.
     *
     * @return the live data
     */
    @Query("SELECT * FROM Task ORDER BY id DESC")
    List<Task> fetchAllTasks();

    /**
     * Gets task count.
     *
     * @return the task count
     */
    @Query("SELECT count(*) FROM Task")
    Long getTaskCount();

}
