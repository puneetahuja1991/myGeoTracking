package com.abaqustest.mygeotrackingapp.view.adapter;

import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.abaqustest.mygeotrackingapp.R;
import com.abaqustest.mygeotrackingapp.databinding.ItemTasksBinding;
import com.abaqustest.mygeotrackingapp.model.Task;
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
    private SparseBooleanArray selectedItems;

    /**
     * Instantiates a new pending tasks adapter.
     *
     * @param pendingTasks the pendingTasks
     */
    public TasksAdapter(List<Task> pendingTasks) {
        this.mTasks = pendingTasks;
        selectedItems = new SparseBooleanArray();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        itemTasksBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_tasks, parent, false);
        return new TasksViewHolder(itemTasksBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TasksViewHolder) {
            TasksViewHolder view = (TasksViewHolder) holder;
            view.bind(mTasks.get(position));
            holder.itemView.setActivated(selectedItems.get(position, false));
        }
    }


    @Override
    public int getItemCount() {
        return (mTasks != null ? mTasks.size() : 0);
    }

    /**
     * Toggle selection.
     *
     * @param pos the pos
     */
    public void toggleSelection(int pos) {
        if (selectedItems.get(pos, false)) {
            selectedItems.delete(pos);
        } else {
            selectedItems.put(pos, true);
        }
        notifyItemChanged(pos);
    }

    /**
     * Clear selections.
     */
    public void clearSelections() {
        selectedItems.clear();
        notifyDataSetChanged();
    }

    /**
     * Gets selected item count.
     *
     * @return the selected item count
     */
    public int getSelectedItemCount() {
        return selectedItems.size();
    }

}
