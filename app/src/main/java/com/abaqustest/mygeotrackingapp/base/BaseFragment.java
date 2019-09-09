package com.abaqustest.mygeotrackingapp.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

/**
 * The type Base fragment.
 *
 * @param <T> the type parameter
 *
 * @author Puneet Ahuja
 */
public abstract class BaseFragment<T extends ViewDataBinding> extends Fragment {

    /**
     * The M binding.
     */
    protected T mBinding;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), getResourceLayout(), null, false);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onBindingDone();
    }


    /**
     * On binding done.
     */
    protected abstract void onBindingDone();

    /**
     * Gets resource layout.
     *
     * @return the resource layout
     */
    protected abstract int getResourceLayout();


}