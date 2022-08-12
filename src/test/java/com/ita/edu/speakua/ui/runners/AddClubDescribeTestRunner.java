package com.ita.edu.speakua.ui.runners;

import com.ita.edu.speakua.ui.HomePage;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.addClubComponent.AddClubDescribeComponent;
import org.testng.annotations.BeforeClass;

public class AddClubDescribeTestRunner extends LoginTestRunner {

    protected AddClubDescribeComponent addClubDescribeComponent;

    @BeforeClass
    public void openAddClubDescribeComponent() {
        addClubDescribeComponent = new HomePage(driver)
                .openAdminProfileMenu()
                .openUserProfilePage()
                .openAddClubModal()
                .inputNameOfClub("Спортивні танці")
                .chooseClubCategory("Спортивні секції")
                .inputAgeFrom(4)
                .inputAgeTo(8)
                .openNextStep()
                .inputPhoneNumber("0672131246")
                .openNextStep();
    }
}