package com.abaqustest.mygeotrackingapp.model;

import android.text.TextUtils;
import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.MutableLiveData;

import com.abaqustest.mygeotrackingapp.R;
import com.abaqustest.mygeotrackingapp.utils.Utils;
import com.google.android.material.textfield.TextInputEditText;

/**
 * The type Add new task form.
 *
 * @author Puneet Ahuja
 */
public class AddNewTaskForm extends BaseObservable {

    private AddNewTaskFields newTaskFields;
    private AddNewTaskErrorFields newTaskErrorFields;
    private View.OnFocusChangeListener onFocusTaskName;
    private MutableLiveData<AddNewTaskFields> newTaskFieldsMutableLiveData;

    /**
     * Instantiates a new Add new task form.
     */
    public AddNewTaskForm() {
        newTaskFields = new AddNewTaskFields();
        newTaskErrorFields = new AddNewTaskErrorFields();
        newTaskFieldsMutableLiveData = new MutableLiveData<>();
        onFocusTaskName = (view, focused) -> {
            TextInputEditText et = (TextInputEditText) view;
            if (et.getText().length() > 0 && !focused) {
                isValidTaskName(false);
            } else if (!focused) {
                isValidTaskName(true);
            }
        };
    }

    /**
     * On click.
     */
    public void onClick() {
        if (isValid()) {
            newTaskFieldsMutableLiveData.setValue(newTaskFields);
        }
    }

    /**
     * Gets new task fields mutable live data.
     *
     * @return the new task fields mutable live data
     */
    public MutableLiveData<AddNewTaskFields> getNewTaskFieldsMutableLiveData() {
        return newTaskFieldsMutableLiveData;
    }

    /**
     * Is valid boolean.
     *
     * @return the boolean
     */
    public boolean isValid() {
        boolean valid = isValidTaskName(true);
        notifyPropertyChanged(BR.taskNameError);
        return valid;
    }

    /**
     * Is valid task name boolean.
     *
     * @param setMessage the set message
     * @return the boolean
     */
    public boolean isValidTaskName(boolean setMessage) {
        if (!TextUtils.isEmpty(newTaskFields.getTaskName())) {
            if (Utils.isStringContainsSpecialCharacter(newTaskFields.getTaskName())) {
                newTaskErrorFields.setTaskNameError(R.string.error_task_name_limit);
                notifyPropertyChanged(BR.taskNameError);
            } else if (Utils.isStringContainsDigit(newTaskFields.getTaskName())) {
                newTaskErrorFields.setTaskNameError(R.string.error_field_digit_character);
                notifyPropertyChanged(BR.taskNameError);
            } else {
                newTaskErrorFields.setTaskNameError(null);
                notifyPropertyChanged(BR.taskNameError);
                return true;
            }
            return false;
        } else {
            if (setMessage) {
                newTaskErrorFields.setTaskNameError(R.string.error_empty_task_name);
                notifyPropertyChanged(BR.taskNameError);
            }
            return false;
        }
    }

    /**
     * Gets new task fields.
     *
     * @return the new task fields
     */
    public AddNewTaskFields getNewTaskFields() {
        return newTaskFields;
    }

    /**
     * Gets new task error fields.
     *
     * @return the new task error fields
     */
    public AddNewTaskErrorFields getNewTaskErrorFields() {
        return newTaskErrorFields;
    }

    /**
     * Gets task name error.
     *
     * @return the task name error
     */
    @Bindable
    public Integer getTaskNameError() {
        return newTaskErrorFields.getTaskNameError();
    }

    /**
     * Gets on focus task name.
     *
     * @return the on focus task name
     */
    public View.OnFocusChangeListener getOnFocusTaskName() {
        return onFocusTaskName;
    }
}
