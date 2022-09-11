package com.ita.edu.speakua.ui.profilePage.tests;

import com.ita.edu.speakua.ui.header.profileMenuAdmin.profilePage.EditProfileModal;
import com.ita.edu.speakua.ui.runners.SameWindowTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PasswordInputFieldsBorderTest extends SameWindowTestRunner {
    private EditProfileModal editProfileModal;

    @BeforeClass
    public void openEditProfileModal() {
        signInAsAdmin();

        editProfileModal = getHomePage()
                .openAdminProfileMenu()
                .openUserProfilePage()
                .openEditProfilePage();
    }

    @Issue("TUA-359")
    @Description("Verify that fields is bordered in red color with invalid data")
    @Test
    public void checkEditPasswordInputFieldsBorders() {
        String errorBorderColor = "rgb(255, 77, 79)";

        editProfileModal.togglePasswordChange().save().isSaveChangesButtonEnabled();

        String oldPasswordBorderColor = editProfileModal.getOldPasswordFieldBorderColor(errorBorderColor);
        String newPasswordBorderColor = editProfileModal.getNewPasswordFieldBorderColor(errorBorderColor);
        String repeatNewPasswordBorderColor = editProfileModal.getRepeatNewPasswordFieldBorderColor(errorBorderColor);


        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(oldPasswordBorderColor.contains(errorBorderColor),
                String.format("Border color of old password field border is expected to be %s, but was %s.",
                        errorBorderColor, oldPasswordBorderColor));
        softAssert.assertTrue(newPasswordBorderColor.contains(errorBorderColor),
                String.format("Border color of new password field border is expected to be %s, but was %s.",
                        errorBorderColor, newPasswordBorderColor));
        softAssert.assertTrue(repeatNewPasswordBorderColor.contains(errorBorderColor),
                String.format("Border color of repeat new password field border is expected to be %s, but was %s.",
                        errorBorderColor, repeatNewPasswordBorderColor));
        softAssert.assertAll();
        editProfileModal.getCloseEditProfileButton();
    }
}
