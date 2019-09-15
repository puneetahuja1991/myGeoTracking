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

package com.kisantest.contactsapp.view.viewholder;


import androidx.recyclerview.widget.RecyclerView;

import com.kisantest.contactsapp.database.Message;
import com.kisantest.contactsapp.databinding.ItemMessageBinding;

/**
 * Message view holder.
 *
 * @author Puneet Ahuja
 */
public class MessagesViewHolder extends RecyclerView.ViewHolder {
    private ItemMessageBinding itemMessageBinding;


    /**
     * Instantiates a new Messages view holder.
     *
     * @param itemMessageBinding the item message binding
     */
    public MessagesViewHolder(ItemMessageBinding itemMessageBinding) {
        super(itemMessageBinding.getRoot());
        this.itemMessageBinding = itemMessageBinding;
    }


    /**
     * Bind.
     *
     * @param mMessage the m message
     */
    public void bind(Message mMessage) {
        itemMessageBinding.setMessage(mMessage);
    }

}