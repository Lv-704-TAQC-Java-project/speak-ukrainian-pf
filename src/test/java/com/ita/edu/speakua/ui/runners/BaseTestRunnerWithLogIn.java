package com.ita.edu.speakua.ui.runners;

import com.ita.edu.speakua.ui.header.HeaderComponent;
import org.testng.annotations.BeforeClass;

public class BaseTestRunnerWithLogIn extends BaseTestRunnerOneWindow {

    @BeforeClass
    public void login() {
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
