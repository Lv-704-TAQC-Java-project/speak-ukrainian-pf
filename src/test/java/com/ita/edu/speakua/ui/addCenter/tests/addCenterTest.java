package com.ita.edu.speakua.ui.addCenter.tests;

import com.ita.edu.speakua.ui.HomePage;
import com.ita.edu.speakua.ui.runners.BaseTestRunnerWithLogIn;
import org.testng.Assert;
import org.testng.annotations.Test;

public class addCenterTest extends BaseTestRunnerWithLogIn {
    @Test
    public void verifyErrorMessageIsDisplay(){
       boolean message = new HomePage(driver)
                .openAdminProfileMenu()
                .openAddCenterModal()
                .tryClickNextStep()
                .errorMessageCenterName();
        Assert.assertTrue(message);
    }
}
