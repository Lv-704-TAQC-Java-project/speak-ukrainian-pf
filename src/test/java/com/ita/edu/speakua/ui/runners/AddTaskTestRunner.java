package com.ita.edu.speakua.ui.runners;

import com.ita.edu.speakua.ui.HomePage;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.administrationComponent.addTask.AddTaskPage;
import org.testng.annotations.BeforeMethod;

public class AddTaskTestRunner extends LoginTestRunner {
    protected AddTaskPage addTaskPage;

    @BeforeMethod
    public void openEditProfile() {
        addTaskPage = new HomePage(driver)
                .openAdminProfileMenu()
                .openAdministrationModal()
                .clickTaskBtn()
                .clickAddTaskBtn();
    }
}
