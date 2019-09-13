package com.abaqustest.mygeotrackingapp.view.activity;

import com.abaqustest.mygeotrackingapp.R;
import com.abaqustest.mygeotrackingapp.base.BaseActivity;
import com.abaqustest.mygeotrackingapp.databinding.ActivityMainBinding;
import com.abaqustest.mygeotrackingapp.view.adapter.SectionsPagerAdapter;
import com.abaqustest.mygeotrackingapp.viewmodel.MainViewModel;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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
        mainViewModel.getTasks();
        mBinding.pullToRefresh.setOnRefreshListener(this);
    }

    /**
     * Sets view model.
     */
    private void setupViewModel() {
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    }

    @Override
    public void onRefresh() {
        mainViewModel.getTasks();
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
}
