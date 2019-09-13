package com.abaqustest.mygeotrackingapp.view.viewholder;

import com.abaqustest.mygeotrackingapp.databinding.ItemTasksBinding;
import com.abaqustest.mygeotrackingapp.database.Task;

import androidx.recyclerview.widget.RecyclerView;

/**
 * The type Pending tasks view holder.
 *
 * @author Puneet Ahuja
 */
public class TasksViewHolder extends RecyclerView.ViewHolder{
    private ItemTasksBinding itemTasksBinding;

    /**
     * Instantiates a new  tasks view holder.
     *
     * @param itemTasksBinding the item tasks  binding
     */
    public TasksViewHolder(ItemTasksBinding itemTasksBinding) {
        super(itemTasksBinding.getRoot());
        this.itemTasksBinding = itemTasksBinding;
    }


    /**
     * Bind.
     *
     * @param tasks the tasks
     */
    public void bind(Task tasks) {
        itemTasksBinding.setTasks(tasks);
    }
}