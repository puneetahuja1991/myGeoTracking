package com.abaqustest.mygeotrackingapp.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

/**
 * The type Task.
 *
 * @author Puneet Ahuja
 */
@Entity
public class Task implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String task;
    private int state;

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }


    /**
     * Gets task.
     *
     * @return the task
     */
    public String getTask() {
        return task;
    }

    /**
     * Gets state.
     *
     * @return the state
     */
    public int getState() {
        return state;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets task.
     *
     * @param task the task
     */
    public void setTask(String task) {
        this.task = task;
    }

    /**
     * Sets state.
     *
     * @param state the state
     */
    public void setState(int state) {
        this.state = state;
    }
}
