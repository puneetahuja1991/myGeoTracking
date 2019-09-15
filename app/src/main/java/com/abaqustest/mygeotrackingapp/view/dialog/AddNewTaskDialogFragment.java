package com.abaqustest.mygeotrackingapp.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.abaqustest.mygeotrackingapp.R;
import com.abaqustest.mygeotrackingapp.databinding.LayoutDialogAddNewTaskBinding;
import com.abaqustest.mygeotrackingapp.model.AddNewTaskFields;
import com.abaqustest.mygeotrackingapp.viewmodel.AddNewTaskViewModel;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

/**
 * The type Add new task dialog fragment.
 *
 * @author Puneet Ahuja
 */
public class AddNewTaskDialogFragment extends DialogFragment implements View.OnClickListener{
    private Context mContext;
    private Dialog dialog;
    private LayoutDialogAddNewTaskBinding mLayoutDialogNotifyPriceChangeBinding;
    private AddNewTaskViewModel addNewTaskViewModel;

    /**
     * New instance add new task dialog fragment.
     *
     * @param context the context
     * @return the add new task dialog fragment
     */
    public static AddNewTaskDialogFragment newInstance(Context context) {
        AddNewTaskDialogFragment fragment = new AddNewTaskDialogFragment();
        fragment.setContext(context);
        return fragment;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel_btn:
                dialog.dismiss();
                break;
            case R.id.add_btn:
                break;
            default:
                break;
        }
    }

    /**
     * Sets context.
     *
     * @param mContext the m context
     */
    public void setContext(Context mContext) {
        this.mContext = mContext;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mContext);
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View dialogView = inflater.inflate(R.layout.layout_dialog_add_new_task, null, false);
        mLayoutDialogNotifyPriceChangeBinding = LayoutDialogAddNewTaskBinding.bind(dialogView);
        mLayoutDialogNotifyPriceChangeBinding.setLifecycleOwner(this);
        mLayoutDialogNotifyPriceChangeBinding.cancelBtn.setOnClickListener(this);
        mLayoutDialogNotifyPriceChangeBinding.addBtn.setOnClickListener(this);
        dialogBuilder.setView(dialogView);
        dialog = dialogBuilder.create();
        dialog.setCanceledOnTouchOutside(true);
        setViewModel();
        setupObservers();
        return dialog;
    }


    /**
     * Sets observers.
     */
    private void setupObservers() {
        addNewTaskViewModel.getAddNewTaskForm().getNewTaskFieldsMutableLiveData().observe(this, addNewTaskFields -> {
            addNewTaskViewModel.addTask(addNewTaskFields);
            Toast.makeText(getContext(),"Task has been created",Toast.LENGTH_SHORT).show();
            dismiss();
        });
    }


    /**
     * Sets view model.
     */
    private void setViewModel() {
        addNewTaskViewModel = ViewModelProviders.of(this).get(AddNewTaskViewModel.class);
        mLayoutDialogNotifyPriceChangeBinding.setAddNewTaskViewModel(addNewTaskViewModel);
    }

}
