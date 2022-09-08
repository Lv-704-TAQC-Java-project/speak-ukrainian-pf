package com.ita.edu.speakua.ui.runners;

import com.ita.edu.speakua.ui.header.profileMenuAdmin.addClubComponent.AddClubDescriptionStep;
import org.testng.annotations.BeforeClass;

public class AddClubDescriptionTestRunner extends LoginTestRunner {
    protected AddClubDescriptionStep addClubDescriptionStep;

    @BeforeClass
    public void openAddClubDescribeComponent() {
        addClubDescriptionStep = getHomePage()
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