package com.abaqustest.mygeotrackingapp.view.fragment;

import android.os.Bundle;

import com.abaqustest.mygeotrackingapp.R;
import com.abaqustest.mygeotrackingapp.base.BaseFragment;
import com.abaqustest.mygeotrackingapp.databinding.LayoutTasksFragmentBinding;
import com.abaqustest.mygeotrackingapp.model.Tasks;
import com.abaqustest.mygeotrackingapp.view.adapter.TasksAdapter;
import com.abaqustest.mygeotrackingapp.viewmodel.MainViewModel;

import java.util.List;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

/**
 * The type Done task fragment.
 *
 * @author Puneet Ahuja
 */
public class DoneTaskFragment extends BaseFragment<LayoutTasksFragmentBinding> {

    private MainViewModel mainViewModel;

    /**
     * New instance done tasks fragment.
     *
     * @return the done tasks fragment
     */
    public static DoneTaskFragment newInstance() {
        DoneTaskFragment fragment = new DoneTaskFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupViewModel();
    }

    @Override
    protected void onBindingDone() {
        initObservers();
        mainViewModel.getTasks();
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
        // mainViewModel.getContactsMutableLiveData().observe(this, contacts -> setUpDoneTasksAdapter(contacts));
    }

    /**
     * Sets up doneTasks adapter.
     *
     * @param doneTasks
     */
    private void setUpDoneTasksAdapter(List<Tasks> doneTasks) {
        TasksAdapter doneTasksAdapter = new TasksAdapter(doneTasks);
        mBinding.rvTasks.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mBinding.rvTasks.setAdapter(doneTasksAdapter);
        mBinding.rvTasks.setLayoutManager(layoutManager);

    }

    @Override
    protected int getResourceLayout() {
        return R.layout.layout_tasks_fragment;
    }
}