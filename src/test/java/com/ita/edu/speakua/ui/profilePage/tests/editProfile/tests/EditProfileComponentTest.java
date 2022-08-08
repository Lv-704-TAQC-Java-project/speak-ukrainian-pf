package com.ita.edu.speakua.ui.profilePage.tests.editProfile.tests;

import com.ita.edu.speakua.ui.HomePage;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.profilePage.EditProfileComponent;
import com.ita.edu.speakua.ui.runners.BaseTestRunnerOpenEditProfile;
import com.ita.edu.speakua.ui.runners.BaseTestRunnerWithLogIn;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class EditProfileComponentTest extends BaseTestRunnerWithLogIn {

    @DataProvider(name = "test-data")
    public static Object[][] testData() {
        return new Object[][]{
                {"", "Введіть ім'я"},
                {"AfBbCcDdEeFfGgHhIiJjKkLlMmNn", "Ім'я не може містити більше, ніж 25 символів"},
                {"AfBbCcDdEeFfGgHhIiJjKkLlMm", "Ім'я не може містити більше, ніж 25 символів"},
                {"!@#$%^&,", "Ім'я не може містити спеціальні символи"},
                {"1234", "Ім'я не може містити цифри"},
                {"-Name", "Ім'я повинно починатися та закінчуватися літерою"},
                {"< Name>", "Ім'я повинно починатися та закінчуватися літерою"},
                {"'Name", "Ім'я повинно починатися та закінчуватися літерою"},
                {"Name-", "Ім'я повинно починатися та закінчуватися літерою"},
                {"<Name >", "Ім'я повинно починатися та закінчуватися літерою"},
                {"Name'", "Ім'я повинно починатися та закінчуватися літерою"}

        };
    }

    @Test(dataProvider = "test-data")
    public void verifyEditProfileWithInvalidData(String data, String expectedMessage) {
        String actualMessage;
        boolean saveChangesBtnIsEnabled;

        EditProfileComponent editProfileComponent = new HomePage(driver)
                .openAdminProfileMenu()
                .openUserProfilePage()
                .clickEditProfileButton();

        SoftAssert softAssert = new SoftAssert();

        actualMessage = editProfileComponent.fillInFirstName(data).getFirstnameErrorText();
        softAssert.assertEquals(actualMessage, expectedMessage);

        saveChangesBtnIsEnabled = editProfileComponent.saveChangesButtonIsEnable();
        softAssert.assertTrue(saveChangesBtnIsEnabled);

        softAssert.assertAll();
    }
}
