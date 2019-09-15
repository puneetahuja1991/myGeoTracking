package com.kisantest.contactsapp.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.kisantest.contactsapp.R;
import com.kisantest.contactsapp.database.Message;
import com.kisantest.contactsapp.databinding.ItemMessageBinding;
import com.kisantest.contactsapp.view.viewholder.MessagesViewHolder;

import java.util.List;


/**
 * The type Messages adapter.
 *
 * @author Puneet Ahuja
 */
public class MessagesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater layoutInflater;
    private List<Message> mMessages;
    private ItemMessageBinding itemMessageBinding;


    /**
     * Instantiates a new Messages adapter.
     *
     * @param messages the messages
     */
    public MessagesAdapter(List<Message> messages) {
        this.mMessages = messages;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }

        itemMessageBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_message, parent, false);
        return new MessagesViewHolder(itemMessageBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MessagesViewHolder) {
            MessagesViewHolder view = (MessagesViewHolder) holder;
            view.bind(mMessages.get(position));
        }
    }


    @Override
    public int getItemCount() {
        return (mMessages != null ? mMessages.size() : 0);
    }


    /**
     * Notify messages.
     *
     * @param messages the messages
     */
    public void notifyMessages(List<Message> messages) {
        this.mMessages = messages;
        notifyDataSetChanged();
    }
}