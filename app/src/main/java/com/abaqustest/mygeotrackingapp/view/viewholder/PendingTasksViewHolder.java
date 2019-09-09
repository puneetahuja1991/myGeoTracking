package com.abaqustest.mygeotrackingapp.view.viewholder;

import androidx.recyclerview.widget.RecyclerView;

import com.abaqustest.mygeotrackingapp.databinding.ItemPendingTasksBinding;

/**
 * The type Pending tasks view holder.
 *
 * @author Puneet Ahuja
 */
public class PendingTasksViewHolder extends RecyclerView.ViewHolder {
    private ItemPendingTasksBinding itemPendingTasksBinding;

    /**
     * Instantiates a new pending tasks view holder.
     *
     * @param itemPendingTasksBinding the item pending binding
     */
    public PendingTasksViewHolder(ItemPendingTasksBinding itemPendingTasksBinding) {
        super(itemPendingTasksBinding.getRoot());
        this.itemPendingTasksBinding = itemPendingTasksBinding;
        itemView.setOnClickListener(view -> {
        });
    }


    /**
     * Bind.
     */
    public void bind() {
    }
}