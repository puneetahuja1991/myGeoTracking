package com.abaqustest.mygeotrackingapp.view.activity;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.abaqustest.mygeotrackingapp.R;
import com.abaqustest.mygeotrackingapp.base.BaseActivity;
import com.abaqustest.mygeotrackingapp.database.Task;
import com.abaqustest.mygeotrackingapp.databinding.ActivityMainBinding;
import com.abaqustest.mygeotrackingapp.view.adapter.SectionsPagerAdapter;
import com.abaqustest.mygeotrackingapp.viewmodel.MainViewModel;
import com.google.android.material.snackbar.Snackbar;

/**
 * The type Main activity.
 *
 * @author Puneet Ahuja
 */
public class MainActivity  extends BaseActivity<ActivityMainBinding> implements SwipeRefreshLayout.OnRefreshListener {

    private MainViewModel mainViewModel;

    @Override
    protected void onBindingDone() {
        setupViewModel();
        setupToolBar();
        setupTabBar();
        initObservers();
        mBinding.pullToRefresh.setOnRefreshListener(this);
        mainViewModel.getTasks(false);
    }

    /**
     * Sets view model.
     */
    private void setupViewModel() {
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    }

    @Override
    public void onRefresh() {
        mainViewModel.getTasks(true);
        mBinding.pullToRefresh.setRefreshing(false);
    }

    /**
     *  Method to set tab bar
     */
    private void setupTabBar() {
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        mBinding.viewPager.setAdapter(sectionsPagerAdapter);
        mBinding.tabs.setupWithViewPager(mBinding.viewPager);
    }

    /**
     * Init observers.
     */
    private void initObservers() {
        mainViewModel.getIsLoading().observe(this, aBoolean -> {
            if (aBoolean != null && aBoolean)
                showDialog("Loading....");
            else
                hideDialog();
        });

        mainViewModel.getTaskLiveData().observe(this, tasks -> {
            if(tasks != null && tasks.size() > 0)
                mainViewModel.populateTasks(tasks);

        });
    }

    /**
     *  Method to set tool bar
     */
    private void setupToolBar() {
        Toolbar mToolbar = mBinding.layoutAppBar.toolbar;
        mToolbar.setTitle(R.string.txt_title_my_tasks);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
    }

    @Override
    protected int getResourceLayout() {
        return R.layout.activity_main;
    }

    /**
     * Show undo delete message.
     *
     * @param task the task
     */
    public void showUndoDeleteMessage(Task task) {
        Snackbar.make(mBinding.viewSnack, "Task Updated", Snackbar.LENGTH_SHORT)
                .setAction("Cancel", v -> {
                    if(task.getState() == 0)
                        task.setState(1);
                    else
                        task.setState(0);
                    mainViewModel.updateTask(task);
                })
                .setDuration(5000)
                .setActionTextColor(getResources().getColor(R.color.colorAccent))
                .show();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_notifications:
                startActivity(new Intent(MainActivity.this,NotificationsActivity.class));
                break;
        }
        return false;
    }

}
