package com.ita.edu.speakua.ui.addClub.tests;

import com.ita.edu.speakua.ui.HomePage;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.profilePage.ProfilePage;
import com.ita.edu.speakua.ui.runners.LoginTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddClubWithValidDataTest extends LoginTestRunner {

    @Issue("TUA-506")
    @Description("Verify that user can create club with valid data")
    @Test
    public void verifyClubIsAdded() throws InterruptedException {
        ProfilePage profilePage = new HomePage(driver)
                .openAdminProfileMenu()
                .openAddClubModal()
                .inputNameOfClub("Спортивні танці")
                .selectCategoryClub("Спортивні секції")
                .inputAgeFrom(4)
                .inputAgeTo(8)
                .openNextStep()
                .inputPhoneNumber("0672131246")
                .openNextStep()
                .inputDescription("Lorem Ipsum is simply dummy text of the ")
                .addClub();

        boolean isClubAdded = new HomePage(driver).openAdminProfileMenu().openUserProfilePage().isClubAvailableOnCurrentPage("Спортивні танці");
        Assert.assertTrue(isClubAdded);

    }

}
