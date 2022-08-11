package com.ita.edu.speakua.ui.profilePage.tests.editProfile.tests;

import com.ita.edu.speakua.ui.HomePage;
import com.ita.edu.speakua.ui.header.profileMenuGuest.LoginModalComponent;
import com.ita.edu.speakua.ui.runners.EditProfileTestRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PasswordInputFieldsBorderTest extends EditProfileTestRunner {

    @DataProvider(name = "loginInputFieldsBordersTestData")
    public Object[][] loginInputFieldsBordersTestData() {
        return new Object[][]{
                {"", "","", true, true,true},
                {"some", "a","d", true, true,true},
        };
    }

    @Test(dataProvider = "loginInputFieldsBordersTestData", priority = 1)
    public void checkEditPasswordInputFieldsBorders(String oldPassword, String newPassword,String newRepeatPassword, boolean oldPasswordError,boolean newPasswordError,boolean repeatPasswordError){

         editProfileComponent
                .changePasswordClick()
                 .inputOldPassword(oldPassword)
                 .inputNewPassword(newPassword)
                 .inputNewPasswordRepeat(newRepeatPassword )
                .saveChangesButtonClick()
                .saveChangesButtonIsEnable();


        String expectedOldPasswordBorderColor = oldPasswordError ? "rgb(255, 77, 79)" : "rgb(217, 217, 217)";
        String expectedNewPasswordBorderColor = newPasswordError ? "rgb(255, 77, 79)" : "rgb(217, 217, 217)";
        String expectedRepeatNewPasswordBorderColor = repeatPasswordError ? "rgb(255, 77, 79)" : "rgb(217, 217, 217)";


        String getBorderColorOfOldPasswordWrapper = editProfileComponent.getOldPasswordFieldWrapper(expectedOldPasswordBorderColor).getCssValue("border-color");
        String getBorderColorOfNewPasswordWrapper = editProfileComponent.getNewPasswordFieldWrapper(expectedNewPasswordBorderColor).getCssValue("border-color");
        String getBorderColorOfRepeatNewPasswordWrapper = editProfileComponent.getRepeatNewPasswordFieldWrapper(expectedRepeatNewPasswordBorderColor).getCssValue("border-color");


        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(getBorderColorOfOldPasswordWrapper.contains(expectedOldPasswordBorderColor),
                String.format("Border color of email field border is expected to be %s, but was %s.",
                        expectedOldPasswordBorderColor, getBorderColorOfOldPasswordWrapper));
        softAssert.assertTrue(getBorderColorOfNewPasswordWrapper.contains(expectedNewPasswordBorderColor),
                String.format("Border color of password field border is expected to be %s, but was %s.",
                        expectedNewPasswordBorderColor, getBorderColorOfNewPasswordWrapper));
        softAssert.assertTrue(getBorderColorOfRepeatNewPasswordWrapper.contains(expectedRepeatNewPasswordBorderColor),
                String.format("Border color of password field border is expected to be %s, but was %s.",
                        expectedRepeatNewPasswordBorderColor, getBorderColorOfRepeatNewPasswordWrapper));
        softAssert.assertAll();
        editProfileComponent.getCloseEditProfileButton();
    }
}
