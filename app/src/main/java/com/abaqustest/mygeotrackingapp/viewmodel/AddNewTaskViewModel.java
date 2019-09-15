package com.abaqustest.mygeotrackingapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.abaqustest.mygeotrackingapp.base.BaseViewModel;
import com.abaqustest.mygeotrackingapp.model.AddNewTaskFields;
import com.abaqustest.mygeotrackingapp.model.AddNewTaskForm;
import com.abaqustest.mygeotrackingapp.repository.TasksRepository;

/**
 * The type Add new task view model.
 *
 * @author Puneet Ahuja
 */
public class AddNewTaskViewModel extends BaseViewModel {

    private AddNewTaskForm addNewTaskForm;
    private TasksRepository tasksRepository;

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
        tasksRepository = new TasksRepository();
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
     * Add task.
     *
     * @param addNewTaskFields the add new task fields
     */
    public void addTask(AddNewTaskFields addNewTaskFields) {
        tasksRepository.insertTaskToDb(addNewTaskFields.getTaskName());
    }
}
