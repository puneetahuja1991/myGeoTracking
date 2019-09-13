package com.abaqustest.mygeotrackingapp.view.fragment;

import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.abaqustest.mygeotrackingapp.R;
import com.abaqustest.mygeotrackingapp.base.BaseFragment;
import com.abaqustest.mygeotrackingapp.databinding.LayoutTasksFragmentBinding;
import com.abaqustest.mygeotrackingapp.model.Task;
import com.abaqustest.mygeotrackingapp.utils.Utils;
import com.abaqustest.mygeotrackingapp.utils.helper.DividerItemsDecoration;
import com.abaqustest.mygeotrackingapp.utils.helper.RecyclerItemClickListener;
import com.abaqustest.mygeotrackingapp.view.activity.MainActivity;
import com.abaqustest.mygeotrackingapp.view.adapter.TasksAdapter;
import com.abaqustest.mygeotrackingapp.view.dialog.AddNewTaskDialogFragment;
import com.abaqustest.mygeotrackingapp.viewmodel.MainViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

/**
 * The type Pending task fragment.
 *
 * @author Puneet Ahuja
 */
public class PendingTaskFragment extends BaseFragment<LayoutTasksFragmentBinding> {

    private MainViewModel mainViewModel;
    private TasksAdapter pendingTasksAdapter;
    private ActionModeCallback actionModeCallback;
    private ActionMode actionMode;
    private boolean isItemSelectedToDelete;

    /**
     * New instance pending tasks fragment.
     *
     * @return the pending tasks fragment
     */
    public static PendingTaskFragment newInstance() {
        PendingTaskFragment fragment = new PendingTaskFragment();
        return fragment;
    }

    @Override
    protected void onBindingDone() {
        setupViewModel();
        setupFloatingButton();
        initObservers();
    }


    /**
     * Method to set up floating button
     */
    private void setupFloatingButton() {
        mBinding.fab.setOnClickListener(view -> {
            AddNewTaskDialogFragment dialog = AddNewTaskDialogFragment.newInstance(mBinding.getRoot().getContext());
            Utils.openFragmentDialog(mBinding.getRoot().getContext(), AddNewTaskDialogFragment.class.getName(), dialog);
        });
    }

    /**
     * Sets view model.
     */
    private void setupViewModel() {
        mainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
    }

    /**
     * Init observers.
     */
    private void initObservers() {
        mainViewModel.getPendingTasksMutableLiveData().observe(this, tasks -> setUpPendingTasksAdapter(tasks));
        mainViewModel.getIsLoading().observe(this, aBoolean -> {
            if (aBoolean != null && aBoolean)
                showDialog("Loading....");
            else
                hideDialog();
        });
    }

    /**
     * Sets up pendingTasks adapter.
     *
     * @param pendingTasks
     */
    private void setUpPendingTasksAdapter(List<Task> pendingTasks) {
        pendingTasksAdapter = new TasksAdapter(pendingTasks);
        mBinding.rvTasks.setItemAnimator(new DefaultItemAnimator());
        mBinding.rvTasks.addItemDecoration(new DividerItemsDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        actionModeCallback = new ActionModeCallback();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mBinding.rvTasks.setAdapter(pendingTasksAdapter);
        mBinding.rvTasks.setLayoutManager(layoutManager);
        mBinding.rvTasks.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), mBinding.rvTasks, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onItemLongClick(View view, int position) {
                if (getActivity() instanceof MainActivity) {
                    if (!isItemSelectedToDelete)
                        enableActionMode(position);
                }
            }
        }));

    }

    @Override
    protected int getResourceLayout() {
        return R.layout.layout_tasks_fragment;
    }

    private void enableActionMode(int position) {
        if (actionMode == null) {
            actionMode = getActivity().startActionMode(actionModeCallback);
        }
        toggleSelection(position);
        isItemSelectedToDelete = true;
    }

    private void toggleSelection(int position) {
        pendingTasksAdapter.toggleSelection(position);
        int count = pendingTasksAdapter.getSelectedItemCount();

        if (count == 0) {
            isItemSelectedToDelete = false;
            actionMode.finish();
            actionMode = null;
        } else {
            // actionMode.setTitle(String.valueOf(count));
            actionMode.invalidate();
        }
    }

    private class ActionModeCallback implements ActionMode.Callback {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.menu_action_mode, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {

                case R.id.action_delete:
                    // delete  selected rows
                    isItemSelectedToDelete = false;
                    showUndoDeleteMessage(getActivity().findViewById(R.id.viewSnack));
                    mode.finish();
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            isItemSelectedToDelete = false;
            pendingTasksAdapter.clearSelections();
            actionMode = null;
        }
    }

    private void showUndoDeleteMessage(View view) {
        Snackbar.make(view, "1 task deleted", Snackbar.LENGTH_SHORT)
                .setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Undo delete operation
                    }
                })
                .setDuration(5000)
                .setActionTextColor(getResources().getColor(R.color.colorAccent))
                .show();
    }

}
