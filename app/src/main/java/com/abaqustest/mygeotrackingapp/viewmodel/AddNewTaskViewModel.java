package com.abaqustest.mygeotrackingapp.viewmodel;

import android.app.Application;
import android.view.View;

import com.abaqustest.mygeotrackingapp.base.BaseViewModel;
import com.abaqustest.mygeotrackingapp.model.AddNewTaskForm;
import com.google.android.material.textfield.TextInputEditText;

import androidx.annotation.NonNull;

/**
 * The type Add new task view model.
 *
 * @author Puneet Ahuja
 */
public class AddNewTaskViewModel extends BaseViewModel {

    private View.OnFocusChangeListener onFocusTaskName;
    private AddNewTaskForm addNewTaskForm;

    /**
     * Instantiates a new Add new task view model.
     *
     * @param application the application
     */
    public AddNewTaskViewModel(@NonNull Application application) {
        super(application);
        init();
    }

    /**
     * Init.
     */
    private void init() {
        addNewTaskForm = new AddNewTaskForm();
        onFocusTaskName = (view, focused) -> {
            TextInputEditText et = (TextInputEditText) view;
            if (et.getText().length() > 0 && !focused) {
                addNewTaskForm.isValidTaskName(false);
            } else if (!focused) {
                addNewTaskForm.isValidTaskName(true);
            }
        };
    }

    /**
     * On add new task clicked.
     */
    public void onAddNewTaskClicked() {
        addNewTaskForm.onClick();
    }

    /**
     * Gets add new task form.
     *
     * @return the add new task form
     */
    public AddNewTaskForm getAddNewTaskForm() {
        return addNewTaskForm;
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
