package com.ita.edu.speakua.ui.addCenter.tests;

import com.ita.edu.speakua.ui.HomePage;
import com.ita.edu.speakua.ui.runners.LoginTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddCenterTest extends LoginTestRunner {

    @Issue("TUA-252")
    @Description("Verify if an error message appears under field when empty data is entered")
    @Test
    public void verifyErrorMessageIsDisplayTest(){
       boolean message = new HomePage(driver)
                .openAdminProfileMenu()
                .openAddCenterModal()
                .failOpenNextStep()
                .isErrorMessageDisplayed();
        Assert.assertTrue(message, "Error message should be displayed");
    }
}
