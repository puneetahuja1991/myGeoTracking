package com.abaqustest.mygeotrackingapp.view.activity;

import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.abaqustest.mygeotrackingapp.R;
import com.abaqustest.mygeotrackingapp.base.BaseActivity;
import com.abaqustest.mygeotrackingapp.database.Notification;
import com.abaqustest.mygeotrackingapp.databinding.ActivityNotificationsBinding;
import com.abaqustest.mygeotrackingapp.view.adapter.NotificationsAdapter;
import com.abaqustest.mygeotrackingapp.viewmodel.NotificationsViewModel;

import java.util.List;

/**
 * The type Notifications activity.
 *
 * @author Puneet Ahuja
 */
public class NotificationsActivity extends BaseActivity<ActivityNotificationsBinding> {

    private NotificationsViewModel notificationsViewModel;
    private NotificationsAdapter notificationsAdapter;

    @Override
    protected void onBindingDone() {
        setupViewModel();
        setupToolBar();
        initObservers();
    }

    @Override
    protected int getResourceLayout() {
        return R.layout.activity_notifications;
    }

    /**
     * Sets view model.
     */
    private void setupViewModel() {
        notificationsViewModel = ViewModelProviders.of(this).get(NotificationsViewModel.class);
    }

    /**
     * Method to set tool bar
     */
    private void setupToolBar() {
        Toolbar mToolbar = mBinding.layoutAppBar.toolbar;
        mToolbar.setTitle(R.string.txt_title_my_notifications);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
    }

    /**
     * Init observers.
     */
    private void initObservers() {
        notificationsViewModel.getNotificationLiveData().observe(this, notifications -> populateMessages(notifications));
    }

    private void populateMessages(List<Notification> notifications) {
        if (notifications != null && notifications.size() > 0) {
            mBinding.rvNotifications.setVisibility(View.VISIBLE);
            mBinding.tvNoNotifications.setVisibility(View.GONE);
            if (notificationsAdapter == null) {
                notificationsAdapter = new NotificationsAdapter(notifications);
                mBinding.rvNotifications.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
                LinearLayoutManager layoutManager = new LinearLayoutManager(this);
                mBinding.rvNotifications.setAdapter(notificationsAdapter);
                mBinding.rvNotifications.setLayoutManager(layoutManager);
            } else {
                notificationsAdapter.notifyNotifications(notifications);
            }
        } else {
            mBinding.rvNotifications.setVisibility(View.GONE);
            mBinding.tvNoNotifications.setVisibility(View.VISIBLE);
        }
    }
}
