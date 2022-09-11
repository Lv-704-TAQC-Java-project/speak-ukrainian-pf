package com.ita.edu.speakua.ui.runners;

import com.ita.edu.speakua.ui.header.profileMenuAdmin.addClubModal.AddClubDescriptionStep;
import org.testng.annotations.BeforeClass;

public class AddClubDescriptionTestRunner extends SignInTestRunner {
    protected AddClubDescriptionStep addClubDescriptionStep;

    @BeforeClass
    public void openAddClubDescribeComponent() {
        addClubDescriptionStep = getHomePage()
                .openAdminProfileMenu()
                .openUserProfilePage()
                .openAddClubModal()
                .enterClubName("Спортивні танці")
                .selectCategoryClub("Спортивні секції")
                .enterMinimumAge(4)
                .enterMaximumAge(8)
                .openNextStep()
                .enterPhone("0672131246")
                .openNextStep();
    }
}