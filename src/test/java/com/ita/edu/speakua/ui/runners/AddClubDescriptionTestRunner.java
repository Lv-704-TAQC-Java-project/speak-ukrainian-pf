package com.ita.edu.speakua.ui.runners;

import com.ita.edu.speakua.ui.HomePage;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.addClubComponent.AddClubDescribeComponent;
import org.testng.annotations.BeforeClass;

public class AddClubDescriptionTestRunner extends LoginTestRunner {

    protected AddClubDescribeComponent addClubDescriptionComponent;

    @BeforeClass
    public void openAddClubDescribeComponent() {
        addClubDescriptionComponent = new HomePage(driver)
                .openAdminProfileMenu()
                .openUserProfilePage()
                .openAddClubModal()
                .inputNameOfClub("Спортивні танці")
                .selectCategoryClub("Спортивні секції")
                .inputAgeFrom(4)
                .inputAgeTo(8)
                .openNextStep()
                .inputPhoneNumber("0672131246")
                .openNextStep();
    }
}