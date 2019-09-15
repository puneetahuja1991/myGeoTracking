/*
 * *
 *  *
 *   Copyright 2018-2019, eXtra; All Rights Reserved.
 *
 *   This software is the confidential and proprietary information of
 *   eXtra, ("Confidential Information"). You shall not
 *   disclose such Confidential Information and shall use it only in
 *   accordance with the terms of the license agreement you entered into
 *   with eXtra.
 *
 *  *
 *  *
 *
 */

package com.abaqustest.mygeotrackingapp.view.viewholder;


import androidx.recyclerview.widget.RecyclerView;

import com.abaqustest.mygeotrackingapp.database.Notification;
import com.abaqustest.mygeotrackingapp.databinding.ItemNotificationBinding;

/**
 * Notification view holder.
 *
 * @author Puneet Ahuja
 */
public class NotificationViewHolder extends RecyclerView.ViewHolder {
    private ItemNotificationBinding itemNotificationBinding;


    /**
     * Instantiates a new Notification view holder.
     *
     * @param itemNotificationBinding the item notification binding
     */
    public NotificationViewHolder(ItemNotificationBinding itemNotificationBinding) {
        super(itemNotificationBinding.getRoot());
        this.itemNotificationBinding = itemNotificationBinding;
    }


    /**
     * Bind.
     *
     * @param notification the notification
     */
    public void bind(Notification notification) {
        itemNotificationBinding.setNotification(notification);
    }

}