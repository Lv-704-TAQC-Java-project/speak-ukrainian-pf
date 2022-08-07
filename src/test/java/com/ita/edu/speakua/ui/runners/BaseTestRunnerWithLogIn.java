package com.ita.edu.speakua.ui.runners;

import com.ita.edu.speakua.ui.header.HeaderComponent;
import org.testng.annotations.BeforeMethod;

public class BaseTestRunnerWithLogIn extends BaseTestRunner {

    @BeforeMethod
    public void setUp() {
        super.setUp();
        HeaderComponent header = new HeaderComponent(driver);
        header.openGuestProfileMenu()
                .openLoginModal()
                .clickLoginButton()
                .fillInEmail(configProps.getUserEmail())
                .fillInPassword(configProps.getUserPassword())
                .clickLoginButton();
        header.getHomePageReload();
    }
}
