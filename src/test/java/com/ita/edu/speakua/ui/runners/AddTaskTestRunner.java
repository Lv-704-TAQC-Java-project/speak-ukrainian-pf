package com.ita.edu.speakua.ui.runners;

import com.ita.edu.speakua.ui.HomePage;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.administrationComponent.addTask.AddTaskPage;
import org.testng.annotations.BeforeClass;

public class AddTaskTestRunner extends BaseTestRunnerWithLogIn {
    protected AddTaskPage addTaskPage;

    @BeforeClass
    public void openEditProfile() {
        addTaskPage = new HomePage(driver)
                .openAdminProfileMenu()
                .openAdministrationModal()
                .clickTaskBtn()
                .clickAddTaskBtn();
    }
}
