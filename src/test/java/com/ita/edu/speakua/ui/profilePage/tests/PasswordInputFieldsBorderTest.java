package com.ita.edu.speakua.ui.profilePage.tests;

import com.ita.edu.speakua.ui.runners.EditProfileTestRunner;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PasswordInputFieldsBorderTest extends EditProfileTestRunner {

    @Test
    public void checkEditPasswordInputFieldsBorders() {
        String errorBorderColor = "rgb(255, 77, 79)";

        editProfileComponent.changePasswordClick().saveChangesButtonClick().saveChangesButtonIsEnable();

        String oldPasswordBorderColor = editProfileComponent.getOldPasswordFieldBorderColor(errorBorderColor);
        String newPasswordBorderColor = editProfileComponent.getNewPasswordFieldBorderColor(errorBorderColor);
        String repeatNewPasswordBorderColor = editProfileComponent.getRepeatNewPasswordFieldBorderColor(errorBorderColor);


        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(oldPasswordBorderColor.contains(errorBorderColor),
                String.format("Border color of email field border is expected to be %s, but was %s.",
                        errorBorderColor, oldPasswordBorderColor));
        softAssert.assertTrue(newPasswordBorderColor.contains(errorBorderColor),
                String.format("Border color of password field border is expected to be %s, but was %s.",
                        errorBorderColor, newPasswordBorderColor));
        softAssert.assertTrue(repeatNewPasswordBorderColor.contains(errorBorderColor),
                String.format("Border color of password field border is expected to be %s, but was %s.",
                        errorBorderColor, repeatNewPasswordBorderColor));
        softAssert.assertAll();
        editProfileComponent.getCloseEditProfileButton();
    }
}
