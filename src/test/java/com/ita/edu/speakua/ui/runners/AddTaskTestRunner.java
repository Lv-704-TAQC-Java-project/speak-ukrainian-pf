package com.ita.edu.speakua.ui.runners;

import com.ita.edu.speakua.ui.header.profileMenuAdmin.administrationMenu.addTask.AddTaskPage;
import com.ita.edu.speakua.utils.jdbc.services.TaskService;
import org.testng.annotations.BeforeMethod;

public class AddTaskTestRunner extends SignInTestRunner {
    protected AddTaskPage addTaskPage;
    protected TaskService taskService = new TaskService();

    @BeforeMethod
    public void openAddTaskPage() {
        addTaskPage = homePage
                .openAdminProfileMenu()
                .openAdministrationMenu()
                .openTasksPage()
                .openAddTaskPage();
    }
}