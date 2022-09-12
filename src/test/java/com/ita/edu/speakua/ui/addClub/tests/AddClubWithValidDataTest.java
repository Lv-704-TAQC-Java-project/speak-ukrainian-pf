package com.ita.edu.speakua.ui.addClub.tests;

import com.ita.edu.speakua.ui.HomePage;
import com.ita.edu.speakua.ui.runners.SignInTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AddClubWithValidDataTest extends SignInTestRunner {

    @DataProvider(name = "addClubValidData")
    public Object[][] addClubValidData() {
        return new Object[][]{
                {"Спортивні танці", "Спортивні секції", 4, 8, "0672131246"}
        };
    }

    @Issue("TUA-506")
    @Description("Verify that user can create club with valid data")
    @Test(dataProvider = "addClubValidData")
    public void verifyClubIsAdded(String name, String category, int ageFrom, int ageTo, String phoneNumber) {
        String descriptionInput = new String(new char[50]).replace("\0", "Lorem Ipsu");

        new HomePage(driver)
                .openAdminProfileMenu()
                .openAddClubModal()
                .enterClubName(name)
                .selectCategoryClub(category)
                .enterMinimumAge(ageFrom)
                .enterMaximumAge(ageTo)
                .goToNextStep()
                .enterPhone(phoneNumber)
                .goToNextStep()
                .enterDescription(descriptionInput)
                .addClub();

        boolean isClubAdded = new HomePage(driver)
                .openAdminProfileMenu()
                .openUserProfilePage()
                .isClubAvailableOnCurrentPage(name);

        Assert.assertTrue(isClubAdded);

    }
}