package com.ita.edu.speakua.ui.runners;

import org.testng.annotations.BeforeClass;

public class LoginTestRunner extends SameWindowTestRunner {

    @BeforeClass
    public void login() {
        getHomePage()
                .openGuestProfileMenu()
                .openLoginModal()
                .fillInEmail(configProps.getUserEmail())
                .fillInPassword(configProps.getUserPassword())
                .submitLoginForm()
                .getHomePageReload();
    }
}