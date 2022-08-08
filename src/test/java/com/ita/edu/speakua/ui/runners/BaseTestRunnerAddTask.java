package com.ita.edu.speakua.ui.runners;

import com.ita.edu.speakua.ui.HomePage;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.administrationComponent.addTask.AddTaskPage;
import org.testng.annotations.BeforeClass;

public class BaseTestRunnerAddTask extends BaseTestRunnerWithLogIn{

    protected AddTaskPage addTaskPage;
    protected boolean allFieldsAreEmpty;

    @BeforeClass
    public void openAddTaskPage() {
        addTaskPage = new HomePage(driver)
                .openAdminProfileMenu()
                .openAdministrationModal()
                .clickTaskBtn()
                .clickAddTaskBtn();
        allFieldsAreEmpty = addTaskPage.allFieldsAreEmpty();
    }
}
