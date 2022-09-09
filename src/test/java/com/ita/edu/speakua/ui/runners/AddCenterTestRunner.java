package com.ita.edu.speakua.ui.runners;

import com.ita.edu.speakua.ui.HomePage;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.addCenterModal.AddCenterMainInfoStep;
import org.testng.annotations.BeforeClass;

public class AddCenterTestRunner extends LoginTestRunner {

    protected AddCenterMainInfoStep addCenterMainInfoComponent;

    @BeforeClass
    public void openAddClubDescribeComponent() {
        addCenterMainInfoComponent = new HomePage(driver)
                .openAdminProfileMenu()
                .openAddCenterModal();
    }
}