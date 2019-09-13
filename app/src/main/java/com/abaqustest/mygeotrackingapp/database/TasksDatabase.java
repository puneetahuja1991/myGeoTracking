package com.abaqustest.mygeotrackingapp.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.abaqustest.mygeotrackingapp.MyGeoTrackingApplication;

/**
 * Message database class.
 *
 * @author Puneet Ahuja
 */
@Database(entities = {Task.class}, version = 1, exportSchema = false)
public abstract class TasksDatabase extends RoomDatabase {

    private static TasksDatabase tasksDatabase;

    /**
     * Dao access dao access.
     *
     * @return the dao access
     */
    public abstract DaoAccess daoAccess();


    /**
     * Gets message database.
     *
     * @return the message database
     */
    public static TasksDatabase getTasksDatabase() {
        if (tasksDatabase == null) {
            tasksDatabase = Room.databaseBuilder(MyGeoTrackingApplication.getAppContext(), TasksDatabase.class, "tasks_db").build();
        }
        return tasksDatabase;
    }

    /**
     * Destroy instance.
     */
    public static void destroyInstance() {
        tasksDatabase = null;
    }
}