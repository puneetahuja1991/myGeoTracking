package com.abaqustest.mygeotrackingapp.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.abaqustest.mygeotrackingapp.R;
import com.abaqustest.mygeotrackingapp.databinding.ItemPendingTasksBinding;
import com.abaqustest.mygeotrackingapp.model.Tasks;
import com.abaqustest.mygeotrackingapp.view.viewholder.PendingTasksViewHolder;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

/**
 * The type Tasks adapter.
 *
 * @author Puneet Ahuja
 */
public class TasksAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private LayoutInflater layoutInflater;
    private List<Tasks> mPendingTasks;
    private ItemPendingTasksBinding itemPendingTasksBinding;


    /**
     * Instantiates a new pending tasks adapter.
     *
     * @param pendingTasks the pendingTasks
     */
    public TasksAdapter(List<Tasks> pendingTasks) {
        this.mPendingTasks = pendingTasks;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        itemPendingTasksBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_pending_tasks, parent, false);
        return new PendingTasksViewHolder(itemPendingTasksBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof PendingTasksViewHolder) {
            PendingTasksViewHolder view = (PendingTasksViewHolder) holder;
           // view.bind(mContacts.get(position));
        }
    }


    @Override
    public int getItemCount() {
        return (mPendingTasks != null ? mPendingTasks.size() : 0);
    }
}
