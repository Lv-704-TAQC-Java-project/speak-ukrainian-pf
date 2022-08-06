package com.ita.edu.speakua.ui.runners;

import com.ita.edu.speakua.ui.HomePage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class BaseTestRunnerWithLogIn extends BaseTestRunner{

    @BeforeMethod
    public void startTestFromAuthorizedPage(){
        HomePage homePage = new HomePage(driver);
        homePage.openGuestProfileMenu()
                .openLoginModal()
                .fillInEmail(configProps.getUserEmail())
                .fillInPassword(configProps.getUserPassword())
                .logIn()
                .waitForPageToReload();

    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
