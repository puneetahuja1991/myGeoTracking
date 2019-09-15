package com.abaqustest.mygeotrackingapp.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.abaqustest.mygeotrackingapp.R;
import com.abaqustest.mygeotrackingapp.database.Notification;
import com.abaqustest.mygeotrackingapp.databinding.ItemNotificationBinding;
import com.abaqustest.mygeotrackingapp.view.viewholder.NotificationViewHolder;

import java.util.List;


/**
 * The type Notifications adapter.
 *
 * @author Puneet Ahuja
 */
public class NotificationsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater layoutInflater;
    private List<Notification> mNotifications;
    private ItemNotificationBinding itemNotificationBinding;


    /**
     * Instantiates a new Notifications adapter.
     *
     * @param notifications the notifications
     */
    public NotificationsAdapter(List<Notification> notifications) {
        this.mNotifications = notifications;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }

        itemNotificationBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_notification, parent, false);
        return new NotificationViewHolder(itemNotificationBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NotificationViewHolder) {
            NotificationViewHolder view = (NotificationViewHolder) holder;
            view.bind(mNotifications.get(position));
        }
    }


    @Override
    public int getItemCount() {
        return (mNotifications != null ? mNotifications.size() : 0);
    }


    /**
     * Notify notifications.
     *
     * @param notifications the notifications
     */
    public void notifyNotifications(List<Notification> notifications) {
        this.mNotifications = notifications;
        notifyDataSetChanged();
    }
}