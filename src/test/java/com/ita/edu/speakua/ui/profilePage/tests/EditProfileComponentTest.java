package com.ita.edu.speakua.ui.profilePage.tests;

import com.ita.edu.speakua.ui.runners.EditProfileTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class EditProfileComponentTest extends EditProfileTestRunner {

    @DataProvider(name = "invalidFirstNameData")
    public static Object[][] invalidFirstNameData() {
        String errorMessageStartEndLetter = "Ім'я повинно починатися та закінчуватися літерою";
        String errorMessageSpecialCharacters = "Ім'я не може містити спеціальні символи";
        return new Object[][]{
                {"", "Введіть ім'я"},
                {"AfBbCcDdEeFfGgHhIiJjKkLlMmNn", "Ім'я не може містити більше, ніж 25 символів"},
                {"AfBbCcDdEeFfGgHhIiJjKkLlMm", "Ім'я не може містити більше, ніж 25 символів"},
                {"!@#$%^&,", errorMessageSpecialCharacters},
                {"1234", "Ім'я не може містити цифри"},
                {"-Name", errorMessageStartEndLetter},
                {"< Name>", errorMessageSpecialCharacters},
                {"'Name", errorMessageStartEndLetter},
                {"Name-", errorMessageStartEndLetter},
                {"<Name >", errorMessageSpecialCharacters},
                {"Name'", errorMessageStartEndLetter}
        };
    }

    @Issue("TUA-328")
    @Description("Verify impossibility of editing profile with first name invalid data")
    @Test(dataProvider = "invalidFirstNameData")
    public void verifyErrorMessageWithFirstNameInvalidData(String data, String expectedMessage) {
        String actualMessage;
        boolean saveChangesBtnIsEnabled;
        SoftAssert softAssert = new SoftAssert();

        actualMessage = editProfileComponent.fillInFirstName(data).getFirstnameErrorText();
        softAssert.assertEquals(actualMessage, expectedMessage, "Expected error message did not appear");

        saveChangesBtnIsEnabled = editProfileComponent.saveChangesButtonIsEnable();
        softAssert.assertFalse(saveChangesBtnIsEnabled, "SaveChanges button is enabled");
        softAssert.assertAll();
    }

    @DataProvider(name = "invalidPhoneData")
    public static Object[][] invalidPhoneData() {
        return new Object[][]{
                {"06895", "Телефон не відповідає вказаному формату"},
                {"065987458", "Телефон не відповідає вказаному формату"},
                {"06593859632586", "Телефон не відповідає вказаному формату"},
                {"06598521475", "Телефон не відповідає вказаному формату"},
                {"jngeoлщшогнеп", "Телефон не може містити літери"},
                {"", "Будь ласка введіть Ваш номер телефону"},
                {"!@#$%^&*(_+.:", "Телефон не може містити спеціальні символи"},
        };
    }

    @Test(dataProvider = "invalidPhoneData")
    public void verifyPhoneErrorMessageWhenEditProfileTest(String phone, String expectedMessage) {
        List<String> errorMessages = editProfileComponent.fillInPhone(phone).getPhoneErrorText();
        boolean expectedMessageIsPresent = errorMessages.contains(expectedMessage);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(expectedMessageIsPresent, "Expected error message is present.");

        boolean saveChangesBtnIsEnabled = editProfileComponent.saveChangesButtonIsEnable();
        softAssert.assertFalse(saveChangesBtnIsEnabled, "SaveChanges button is not enabled.");

        softAssert.assertAll();
    }
}
