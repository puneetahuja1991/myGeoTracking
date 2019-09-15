package com.abaqustest.mygeotrackingapp.view.viewholder;

import android.view.MenuItem;
import android.view.View;

import com.abaqustest.mygeotrackingapp.R;
import com.abaqustest.mygeotrackingapp.databinding.ItemTasksBinding;
import com.abaqustest.mygeotrackingapp.database.Task;
import com.abaqustest.mygeotrackingapp.view.adapter.TasksAdapter;

import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

/**
 * The type Pending tasks view holder.
 *
 * @author Puneet Ahuja
 */
public class TasksViewHolder extends RecyclerView.ViewHolder{
    private ItemTasksBinding itemTasksBinding;
    private TasksAdapter.TasksAdapterListener mListener;
    private Task mTask;

    /**
     * Instantiates a new  tasks view holder.
     *
     * @param itemTasksBinding the item tasks  binding
     * @param listener
     */
    public TasksViewHolder(ItemTasksBinding itemTasksBinding, TasksAdapter.TasksAdapterListener listener) {
        super(itemTasksBinding.getRoot());
        this.itemTasksBinding = itemTasksBinding;
        this.mListener = listener;

        itemView.setOnLongClickListener(view -> {
            if(mTask.getState() == 1){
                PopupMenu popup = new PopupMenu(itemTasksBinding.getRoot().getContext(), view);
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(item -> {
                    listener.deleteTask(mTask);
                    return true;
                });
                popup.show();
            }
            return false;
        });

        itemView.setOnClickListener(view -> {
            if(mTask.getState() == 0)
                mTask.setState(1);
            else
                mTask.setState(0);
            mListener.updateTask(mTask);
        });
    }


    /**
     * Bind.
     *
     * @param tasks the tasks
     */
    public void bind(Task tasks) {
        mTask = tasks;
        itemTasksBinding.setTasks(tasks);
    }
}