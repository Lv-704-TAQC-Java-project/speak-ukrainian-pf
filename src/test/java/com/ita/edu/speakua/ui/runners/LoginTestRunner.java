package com.ita.edu.speakua.ui.runners;

import com.ita.edu.speakua.ui.header.Header;
import org.testng.annotations.BeforeClass;

public class LoginTestRunner extends SameWindowTestRunner {

    @BeforeClass
    public void login() {
        Header header = new Header(driver);
        header.openGuestProfileMenu()
                .openLoginModal()
                .fillInEmail(configProps.getUserEmail())
                .fillInPassword(configProps.getUserPassword())
                .clickLoginButton();
        header.getHomePageReload();
    }
}
