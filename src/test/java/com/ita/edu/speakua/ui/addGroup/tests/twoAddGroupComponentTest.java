package com.ita.edu.speakua.ui.addGroup.tests;

import com.ita.edu.speakua.ui.HomePage;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.addClubComponent.AddClubDescribeComponent;
import com.ita.edu.speakua.ui.runners.BaseTestRunnerWithLogIn;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class twoAddGroupComponentTest extends BaseTestRunnerWithLogIn {

    @Test
    public void verifyDescribeComponentWithValidData(){
        boolean btnFinishIsEnable;
        String[] testData = new String[]{"Education, students, Школа балету, Teachers",
                "1234567890123456789012345678901234567890",
                "!\"#$%&'()*+,-./:;<=>?@[]^_`{}~"};

        AddClubDescribeComponent addClubDescribeComponent = new HomePage(driver)
                .openAdminProfileMenu()
                .openUserProfilePage()
                .openAddClubModal()
                .inputNameOfClub("Club3")
                .chooseCategoryClub("Основи")
                .inputAgeFrom(4)
                .inputAgeTo(9)
                .clickNextStep()
                .inputPhoneNumber("0973756135")
                .clickNextStep();

        SoftAssert softAssert = new SoftAssert();

        for (String data : testData){
            addClubDescribeComponent
                    .inputDescribe(data);

            btnFinishIsEnable = addClubDescribeComponent.finishBtnIsEnable();
            softAssert.assertTrue(btnFinishIsEnable);
        }

        softAssert.assertAll();
    }
}
