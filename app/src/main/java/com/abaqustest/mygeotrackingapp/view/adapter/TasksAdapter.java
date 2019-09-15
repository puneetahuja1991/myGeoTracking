package com.abaqustest.mygeotrackingapp.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.abaqustest.mygeotrackingapp.R;
import com.abaqustest.mygeotrackingapp.databinding.ItemTasksBinding;
import com.abaqustest.mygeotrackingapp.database.Task;
import com.abaqustest.mygeotrackingapp.view.viewholder.TasksViewHolder;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

/**
 * The type Task adapter.
 *
 * @author Puneet Ahuja
 */
public class TasksAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private LayoutInflater layoutInflater;
    private List<Task> mTasks;
    private ItemTasksBinding itemTasksBinding;
    private TasksAdapterListener mListener;

    /**
     * Instantiates a new pending tasks adapter.
     *
     * @param pendingTasks the pendingTasks
     * @param listener     the listener
     */
    public TasksAdapter(List<Task> pendingTasks, TasksAdapterListener listener) {
        this.mTasks = pendingTasks;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        itemTasksBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_tasks, parent, false);
        return new TasksViewHolder(itemTasksBinding, mListener);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TasksViewHolder) {
            TasksViewHolder view = (TasksViewHolder) holder;
            view.bind(mTasks.get(position));
        }
    }


    @Override
    public int getItemCount() {
        return (mTasks != null ? mTasks.size() : 0);
    }


    /**
     * Sets tasks.
     *
     * @param tasks the tasks
     */
    public void setTasks(List<Task> tasks) {
        this.mTasks = tasks;
        notifyDataSetChanged();
    }

    /**
     * The interface Tasks adapter listner.
     */
    public interface TasksAdapterListener {
        /**
         * Delete task.
         *
         * @param task the task
         */
        void deleteTask(Task task);

        /**
         * Update task.
         *
         * @param task the task
         */
        void updateTask(Task task);
    }

}
