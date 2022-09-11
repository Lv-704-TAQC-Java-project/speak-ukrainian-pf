package com.ita.edu.speakua.ui.addCenter.tests;

import com.ita.edu.speakua.ui.HomePage;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.addCenterModal.AddCenterMainInfoStep;
import com.ita.edu.speakua.ui.runners.SignInTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddCenterTest extends SignInTestRunner {

    @Issue("TUA-252")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify if an error message appears under field when empty data is entered")
    @Test
    public void verifyErrorMessageIsDisplayTest() {
        AddCenterMainInfoStep addCenter = new HomePage(driver)
                .openAdminProfileMenu()
                .openAddCenterModal();
        addCenter.openNextStep();
        boolean isMessage = addCenter.isErrorMessageDisplayed();
        Assert.assertTrue(isMessage, "Error message should be displayed");
    }
}
