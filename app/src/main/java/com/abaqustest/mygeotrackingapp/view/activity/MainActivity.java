package com.abaqustest.mygeotrackingapp.view.activity;

import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.abaqustest.mygeotrackingapp.R;
import com.abaqustest.mygeotrackingapp.base.BaseActivity;
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
     * @param view the view
     */
    public void showUndoDeleteMessage(View view) {
        Snackbar.make(view, "Task Updated", Snackbar.LENGTH_SHORT)
                .setAction("Cancel", new View.OnClickListener() {
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
