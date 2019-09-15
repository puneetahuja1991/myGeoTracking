package com.abaqustest.mygeotrackingapp.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * The type Notification.
 *
 * @author Puneet Ahuja
 */
@Entity
public class Notification implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String message;
    private String date;

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
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
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(String date) {
        this.date = date;
    }


    /**
     * Gets formatted date.
     *
     * @return the formatted date
     */
    public String getFormattedDate() {
        Date date = new Date(Long.valueOf(getDate()));
        SimpleDateFormat formatter= new SimpleDateFormat("EEE, MMM d 'at' HH:mm:ss" );
        formatter.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
        return formatter.format(date);
    }

}
