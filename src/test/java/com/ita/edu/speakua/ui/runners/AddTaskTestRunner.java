package com.ita.edu.speakua.ui.runners;

import com.ita.edu.speakua.ui.HomePage;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.administrationComponent.addTask.AddTaskPage;
import com.ita.edu.speakua.utils.jdbc.services.TaskService;
import org.testng.annotations.BeforeMethod;

public class AddTaskTestRunner extends LoginTestRunner {
    protected AddTaskPage addTaskPage;
    protected TaskService taskService = new TaskService();

    @BeforeMethod
    public void openAddTaskPage() {
        addTaskPage = new HomePage(driver)
                .openAdminProfileMenu()
                .openAdministrationModal()
                .openTasksPage()
                .openAddTaskPage();
    }
}