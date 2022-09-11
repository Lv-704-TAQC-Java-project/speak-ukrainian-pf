package com.ita.edu.speakua.ui.runners;

import com.ita.edu.speakua.ui.HomePage;
import org.testng.annotations.BeforeClass;

public class SignInTestRunner extends SameWindowTestRunner {
    protected HomePage homePage;

    @BeforeClass
    public void signIn() {
        homePage = getHomePage();
        homePage
                .openGuestProfileMenu()
                .openSignInModal()
                .enterEmail(configProps.getUserEmail())
                .enterPassword(configProps.getUserPassword())
                .submit();
    }
}