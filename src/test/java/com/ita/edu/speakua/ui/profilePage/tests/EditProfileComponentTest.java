package com.ita.edu.speakua.ui.profilePage.tests;

import com.ita.edu.speakua.ui.runners.EditProfileTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
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

        actualMessage = editProfileComponent.setFirstName(data).getFirstnameErrorText();
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

    @Issue("TUA-356")
    @Description("Verify error messages are shown and 'Save' button is disabled while entering invalid data in phone field")
    @Test(dataProvider = "invalidPhoneData")
    public void verifyPhoneErrorMessageWhenEditProfileTest(String phone, String expectedMessage) {
        List<String> errorMessages = editProfileComponent.setPhone(phone).getPhoneErrorText();
        boolean expectedMessageIsPresent = errorMessages.contains(expectedMessage);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(expectedMessageIsPresent, "Expected error message:\n"
                + expectedMessage
                + "\n\tActual error messages:\n"
                + errorMessages);

        boolean saveChangesBtnIsEnabled = editProfileComponent.saveChangesButtonIsEnable();
        softAssert.assertFalse(saveChangesBtnIsEnabled, "SaveChanges button is not enabled.");

        softAssert.assertAll();
    }

    @DataProvider(name = "invalidLastNameData")
    public static Object[][] invalidLastNameData() {
        return new Object[][]{
                {"", "Будь ласка введіть Ваше прізвище"},
                {"AfBbCcDdEeFfGgHhIiJjKkLlMmNn", "Прізвище не може містити більше, ніж 25 символів"},
                {"AfBbCcDdEeFfGgHhIiJjKkLlMm", "Прізвище не може містити більше, ніж 25 символів"},
                {"!@#$%^&,", "Прізвище не може містити спеціальні символи"},
                {"1234", "Прізвище не може містити цифри"},
                {"-Lastname", "Прізвище повинно починатися і закінчуватися літерою"},
                {"< Lastname>", "Прізвище не може містити спеціальні символи"},
                {"'Lastname", "Прізвище повинно починатися і закінчуватися літерою"},
                {"Lastname-", "Прізвище повинно починатися і закінчуватися літерою"},
                {"<Lastname >", "Прізвище не може містити спеціальні символи"},
                {"Lastname'", "Прізвище повинно починатися і закінчуватися літерою"}
        };
    }
    @Issue("TUA-343")
    @Description("Verify impossibility of editing profile with last name invalid data")
    @Test(dataProvider = "invalidLastNameData")
    public void verifyEditProfileWithInvalidLastNamedData(String lastName, String expectedMessage) {
        List<String> errorMessages = editProfileComponent.setLastName(lastName).getLastNameErrorText();
        boolean expectedMessageIsPresent = errorMessages.contains(expectedMessage);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(expectedMessageIsPresent, "Expected error message:\n"
                + expectedMessage
                + "\n\tActual error messages:\n"
                + errorMessages);

        boolean saveChangesBtnIsEnabled = editProfileComponent.saveChangesButtonIsEnable();
        softAssert.assertFalse(saveChangesBtnIsEnabled, "SaveChanges button is not enabled.");

        softAssert.assertAll();
    }

    @Issue("TUA-359")
    @Description("Verify that error messages are shown while leaving empty any field in the 'Змінити пароль' pop-up")
    @Test(priority = 1)
    public void verifyEditProfileWithEmptyNewRepeatPasswordData() {
        boolean actualMessage;
        boolean expectedMessageIsPresent = false;

        SoftAssert softAssert = new SoftAssert();
        actualMessage = editProfileComponent
                .togglePasswordChange()
                .enterOldPassword("admin")
                .enterNewPassword("uyyyyuyu45ytty@")
                .save()
                .saveChangesButtonIsEnable();

        String xPathErrorMessageText = "//div[@class='ant-form-item-explain ant-form-item-explain-connected']";
        WebElement item = driver.findElement(By.xpath(xPathErrorMessageText));

        if (item.isDisplayed()) {
            expectedMessageIsPresent = true;
        }
        softAssert.assertEquals(actualMessage, expectedMessageIsPresent);

        editProfileComponent.togglePasswordChange();
    }

    @Issue("TUA-359")
    @Description("Verify that error messages are shown while leaving empty any field in the 'Змінити пароль' pop-up")
    @Test
    public void verifyEditProfileWithEmptyNewPasswordData() {
        boolean actualMessage;
        boolean expectedMessageIsPresent = false;

        SoftAssert softAssert = new SoftAssert();
        actualMessage = editProfileComponent
                .togglePasswordChange()
                .enterOldPassword("admin")
                .save()
                .saveChangesButtonIsEnable();

        String xPathErrorMessageText = "//div[@class='ant-form-item-explain-error']";
        WebElement item = driver.findElement(By.xpath(xPathErrorMessageText));

        if (item.isDisplayed()) {
            expectedMessageIsPresent = true;
        }
        softAssert.assertEquals(actualMessage, expectedMessageIsPresent);

        editProfileComponent.togglePasswordChange();
    }

    @AfterMethod
    public void setInitialInfo() {
        if (!editProfileComponent.getPhone().equals(initialPhone)) {
            editProfileComponent.setPhone(initialPhone);
        }
        if (!editProfileComponent.getFirstName().equals(initialFirstName)) {
            editProfileComponent.setFirstName(initialFirstName);
        }
        if (!editProfileComponent.getLastName().equals(initialLastName)) {
            editProfileComponent.setLastName(initialLastName);
        }
    }
}
