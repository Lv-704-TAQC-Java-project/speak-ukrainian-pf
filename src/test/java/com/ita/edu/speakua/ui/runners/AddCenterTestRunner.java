package com.ita.edu.speakua.ui.runners;

import com.ita.edu.speakua.ui.HomePage;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.addCenterComponent.AddCenterDescribeComponent;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.addCenterComponent.AddCenterMainInfoComponent;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.addClubComponent.AddClubDescribeComponent;
import org.testng.annotations.BeforeClass;

public class AddCenterTestRunner extends LoginTestRunner {

    protected AddCenterMainInfoComponent addCenterMainInfoComponent;

    @BeforeClass
    public void openAddClubDescribeComponent() {
        addCenterMainInfoComponent = new HomePage(driver)
                .openAdminProfileMenu()
                .openAddCenterModal();
    }
}