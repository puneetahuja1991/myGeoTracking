package com.abaqustest.mygeotrackingapp.view.fragment;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.abaqustest.mygeotrackingapp.R;
import com.abaqustest.mygeotrackingapp.base.BaseFragment;
import com.abaqustest.mygeotrackingapp.database.Task;
import com.abaqustest.mygeotrackingapp.databinding.LayoutTasksFragmentBinding;
import com.abaqustest.mygeotrackingapp.utils.Utils;
import com.abaqustest.mygeotrackingapp.utils.helper.DividerItemsDecoration;
import com.abaqustest.mygeotrackingapp.view.adapter.TasksAdapter;
import com.abaqustest.mygeotrackingapp.view.dialog.AddNewTaskDialogFragment;
import com.abaqustest.mygeotrackingapp.viewmodel.MainViewModel;

import java.util.List;

/**
 * The type Pending task fragment.
 *
 * @author Puneet Ahuja
 */
public class PendingTaskFragment extends BaseFragment<LayoutTasksFragmentBinding> implements TasksAdapter.TasksAdapterListener {

    private MainViewModel mainViewModel;
    private TasksAdapter pendingTasksAdapter;

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
        mainViewModel.getPendingTasksMutableLiveData().observe(this, tasks -> {
            setUpPendingTasksAdapter(tasks);
        });
    }

    /**
     * Sets up pendingTasks adapter.
     *
     * @param pendingTasks
     */
    private void setUpPendingTasksAdapter(List<Task> pendingTasks) {
        if (pendingTasksAdapter == null) {
            pendingTasksAdapter = new TasksAdapter(pendingTasks, this);
            mBinding.rvTasks.setItemAnimator(new DefaultItemAnimator());
            mBinding.rvTasks.addItemDecoration(new DividerItemsDecoration(getActivity(), LinearLayoutManager.VERTICAL));
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            mBinding.rvTasks.setAdapter(pendingTasksAdapter);
            mBinding.rvTasks.setLayoutManager(layoutManager);

        } else {
            pendingTasksAdapter.setTasks(pendingTasks);
        }
    }

    @Override
    protected int getResourceLayout() {
        return R.layout.layout_tasks_fragment;
    }

    @Override
    public void deleteTask(Task task) {

    }

    @Override
    public void updateTask(Task task) {
        mainViewModel.updateTask(task);
    }
}
