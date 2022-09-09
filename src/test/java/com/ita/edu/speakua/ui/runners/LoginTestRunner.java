package com.ita.edu.speakua.ui.runners;

import com.ita.edu.speakua.ui.HomePage;
import org.testng.annotations.BeforeClass;

public class LoginTestRunner extends SameWindowTestRunner {
    protected HomePage homePage;

    @BeforeClass
    public void login() {
        homePage = getHomePage();
        homePage
                .openGuestProfileMenu()
                .openLoginModal()
                .enterEmail(configProps.getUserEmail())
                .enterPassword(configProps.getUserPassword())
                .submitLoginForm()
                .reloadHomePage();
    }
}