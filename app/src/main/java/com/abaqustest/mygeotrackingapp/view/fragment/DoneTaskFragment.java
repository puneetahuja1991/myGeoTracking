package com.abaqustest.mygeotrackingapp.view.fragment;

import android.widget.Toast;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.abaqustest.mygeotrackingapp.R;
import com.abaqustest.mygeotrackingapp.base.BaseFragment;
import com.abaqustest.mygeotrackingapp.database.Task;
import com.abaqustest.mygeotrackingapp.databinding.LayoutDoneTaskFragmentBinding;
import com.abaqustest.mygeotrackingapp.utils.helper.DividerItemsDecoration;
import com.abaqustest.mygeotrackingapp.view.activity.MainActivity;
import com.abaqustest.mygeotrackingapp.view.adapter.TasksAdapter;
import com.abaqustest.mygeotrackingapp.viewmodel.MainViewModel;

import java.util.List;

/**
 * The type Done task fragment.
 *
 * @author Puneet Ahuja
 */
public class DoneTaskFragment extends BaseFragment<LayoutDoneTaskFragmentBinding> implements TasksAdapter.TasksAdapterListener {

    private MainViewModel mainViewModel;
    private TasksAdapter doneTasksAdapter;

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
    protected void onBindingDone() {
        setupViewModel();
        initObservers();
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
         mainViewModel.getDoneTasksMutableLiveData().observe(this, tasks -> setUpDoneTasksAdapter(tasks));
    }

    /**
     * Sets up doneTasks adapter.
     *
     * @param doneTasks
     */
    private void setUpDoneTasksAdapter(List<Task> doneTasks) {
        if(doneTasksAdapter == null){
            doneTasksAdapter = new TasksAdapter(doneTasks,this);
            mBinding.rvTasks.setItemAnimator(new DefaultItemAnimator());
            mBinding.rvTasks.addItemDecoration(new DividerItemsDecoration(getActivity(), LinearLayoutManager.VERTICAL));
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            mBinding.rvTasks.setAdapter(doneTasksAdapter);
            mBinding.rvTasks.setLayoutManager(layoutManager);
        }else
            doneTasksAdapter.setTasks(doneTasks);
    }

    @Override
    protected int getResourceLayout() {
        return R.layout.layout_done_task_fragment;
    }

    @Override
    public void deleteTask(Task task) {
        mainViewModel.deleteTask(task);
        Toast.makeText(getContext(),"Task has been deleted",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateTask(Task task) {
        ((MainActivity)getActivity()).showUndoDeleteMessage(task);
        mainViewModel.updateTask(task);
    }
}