package com.abaqustest.mygeotrackingapp.worker;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.abaqustest.mygeotrackingapp.R;
import com.abaqustest.mygeotrackingapp.database.Task;
import com.abaqustest.mygeotrackingapp.repository.TasksRepository;
import com.abaqustest.mygeotrackingapp.utils.helper.GenericResponseListener;
import com.abaqustest.mygeotrackingapp.view.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Worker Manager Class which runs in background and gets the latest task data from server.
 *
 * @author Puneet Ahuja
 */
public class TaskWorker extends Worker {

    private TasksRepository mTasksRepository;

    /**
     * Instantiates a new Task worker.
     *
     * @param context      the context
     * @param workerParams the worker params
     */
    public TaskWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        mTasksRepository = new TasksRepository();
    }

    @NonNull
    @Override
    public Result doWork() {
        mTasksRepository.getTaskFromServer(new GenericResponseListener<List<Task>>() {
            @Override
            public void onError(String error) {
                //Handle Error if required.
            }

            @Override
            public void onSuccess(List<Task> response) {
                if(response != null && response.size()> 0)
                    showNotification(response);
            }
        },true);

        return Result.success();

    }

    /**
     * Show notification.
     *
     * @param response the response
     */
    private void showNotification(List<Task> response) {

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        final PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        getApplicationContext(),
                        (int) System.currentTimeMillis(),
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        NotificationManager manager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        String title = "Task Status";
        String channelId = "task_channel";
        String channelName = "task_name";
        String message = getNotificationMessage(response);

        mTasksRepository.insertNotificationToDb(message, String.valueOf(System.currentTimeMillis()));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel = new
                    NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), channelId)
                .setContentTitle(title)
                .setContentText(message)
                .setContentIntent(resultPendingIntent)
                .setAutoCancel(true)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setSmallIcon(R.mipmap.ic_launcher);

        manager.notify((int) System.currentTimeMillis(), builder.build());

    }

    /**
     * Gets notification message.
     *
     * @param response the response
     * @return the notification message
     */
    private String getNotificationMessage(List<Task> response) {
        List<Task> pendingTask=new ArrayList<>();
        List<Task> doneTask=new ArrayList<>();

        for(int i=0; i<response.size();i++) {
            if(response.get(i).getState()==0) {
                pendingTask.add(response.get(i));
            }else if(response.get(i).getState()==1) {
                doneTask.add(response.get(i));
            }
        }

        return pendingTask.size() + " Pending  & " + doneTask.size() + " Done ";

    }

}