package com.abaqustest.mygeotrackingapp.view.activity;

import android.view.View;

import com.abaqustest.mygeotrackingapp.R;
import com.abaqustest.mygeotrackingapp.base.BaseActivity;
import com.abaqustest.mygeotrackingapp.databinding.ActivityMainBinding;
import com.abaqustest.mygeotrackingapp.view.adapter.SectionsPagerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.widget.Toolbar;

/**
 * The type Main activity.
 *
 * @author Puneet Ahuja
 */
public class MainActivity  extends BaseActivity<ActivityMainBinding> {

    @Override
    protected void onBindingDone() {
        setupToolBar();
        setupTabBar();
        //setupFloatingButton();
    }
  /*  *//**
     *  Method to set floating button
     *//*
    private void setupFloatingButton() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }*/
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
