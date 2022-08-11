package com.ita.edu.speakua.ui.addCenter.tests;

import com.ita.edu.speakua.ui.HomePage;
import com.ita.edu.speakua.ui.runners.LoginTestRunner;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddCenterTest extends LoginTestRunner {
    @Test
    public void verifyErrorMessageIsDisplayTest(){
       boolean message = new HomePage(driver)
                .openAdminProfileMenu()
                .openAddCenterModal()
                .tryClickNextStep()
                .errorMessageCenterName();

        Assert.assertTrue(message, "Error message should be displayed");
    }
}
