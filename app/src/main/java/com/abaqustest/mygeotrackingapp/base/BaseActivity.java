package com.abaqustest.mygeotrackingapp.base;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

/**
 * Base activity.
 *
 * @param <T> the type parameter
 * @author Puneet Ahuja
 */
public abstract class BaseActivity<T extends ViewDataBinding> extends AppCompatActivity {

    protected T mBinding;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, getResourceLayout());
        mBinding.setLifecycleOwner(this);
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


    /**
     * Show dialog.
     *
     * @param message the message
     */
    public void showDialog(String message) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(message);
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
        }
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    /**
     * Hide dialog.
     */
    public void hideDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }


}