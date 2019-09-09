package com.abaqustest.mygeotrackingapp.view.adapter;

import android.content.Context;


import com.abaqustest.mygeotrackingapp.R;
import com.abaqustest.mygeotrackingapp.view.fragment.DoneTaskFragment;
import com.abaqustest.mygeotrackingapp.view.fragment.PendingTaskFragment;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 *
 * @author Puneet Ahuja
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_title_pending_task, R.string.tab_title_done_task};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0)
            return PendingTaskFragment.newInstance();
        return DoneTaskFragment.newInstance();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }
}