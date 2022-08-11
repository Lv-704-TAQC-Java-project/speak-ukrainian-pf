package com.ita.edu.speakua.ui.profilePage.tests;

import com.ita.edu.speakua.ui.runners.EditProfileTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class EditProfileComponentTest extends EditProfileTestRunner {

    @DataProvider(name = "invalidFirstNameData")
    public static Object[][] invalidFirstNameData() {
        String errorMessage = "Ім'я повинно починатися і закінчуватися літерою";
        return new Object[][]{
                {"", "Будь ласка введіть Ваше ім'я"},
                {"AfBbCcDdEeFfGgHhIiJjKkLlMmNn", "Ім'я не може містити більше, ніж 25 символів"},
                {"AfBbCcDdEeFfGgHhIiJjKkLlMm", "Ім'я не може містити більше, ніж 25 символів"},
                {"!@#$%^&,", "Ім'я не може містити спеціальні символи"},
                {"1234", "Ім'я не може містити цифри"},
                {"-Name", errorMessage},
                {"< Name>", errorMessage},
                {"'Name", errorMessage},
                {"Name-", errorMessage},
                {"<Name >", errorMessage},
                {"Name'", errorMessage}
        };
    }

    @Issue("TUA-177")
    @Severity(SeverityLevel.CRITICAL)
    @Description("My verify EditProfile")
    @Test(dataProvider = "invalidFirstNameData")
    public void verifyEditProfileWithInvalidData(String data, String expectedMessage) {
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
    @DataProvider(name = "invalidLastNameData")
    public static Object[][] invalidLastNameData() {
        return new Object[][]{
                {"", "Введіть прізвище"},
                {"AfBbCcDdEeFfGgHhIiJjKkLlMmNn", "Прізвище не може містити більше, ніж 25 символів"},
                {"AfBbCcDdEeFfGgHhIiJjKkLlMm", "Прізвище не може містити більше, ніж 25 символів"},
                {"!@#$%^&,", "Прізвище не може містити спеціальні символи"},
                {"1234", "Прізвище не може містити цифри"},
                {"-Lastname", "Прізвище повинно починатися та закінчуватися літерою"},
                {"< Lastname>", "Прізвище повинно починатися та закінчуватися літерою"},
                {"'Lastname", "Прізвище повинно починатися та закінчуватися літерою"},
                {"Lastname-", "Прізвище повинно починатися та закінчуватися літерою"},
                {"<Lastname >", "Прізвище повинно починатися та закінчуватися літерою"},
                {"Lastname'", "Прізвище повинно починатися та закінчуватися літерою"}
        };
    }

    @Test(dataProvider = "invalidLastNameData")
    public void verifyEditProfileWithInvalidLastNamedData(String data, String expectedMessage) {
        List<String> errorMessages;
        boolean expectedMessageIsPresent = false;
        boolean saveChangesBtnIsEnabled;
        SoftAssert softAssert = new SoftAssert();

        errorMessages = editProfileComponent.fillInLastName(data).getLastNameErrorText();
        for (String errorMessage: errorMessages) {
            if (errorMessage.equals(expectedMessage)) {
                expectedMessageIsPresent = true;
                break;
            }
        }
        softAssert.assertTrue(expectedMessageIsPresent, "Expected error message is not present.");

        saveChangesBtnIsEnabled = editProfileComponent.saveChangesButtonIsEnable();
        softAssert.assertFalse(saveChangesBtnIsEnabled, "SaveChanges button is enabled.");

        softAssert.assertAll();
    }
    @Test
    public void verifyEditProfileWithEmptyNewRepeatPasswordData(){
        boolean actualMessage;
        boolean expectedMessageIsPresent = false;

        SoftAssert softAssert = new SoftAssert();
        actualMessage = editProfileComponent
                .changePasswordClick()
                .inputOldPassword("admin")
                .inputNewPassword("uyyyyuyu45ytty@")
                .saveChangesButtonClick()
                .saveChangesButtonIsEnable();

        String xPathErrorMessageText = "//div[@class='ant-form-item-explain ant-form-item-explain-connected']";
        WebElement item = driver.findElement(By.xpath(xPathErrorMessageText));

        if(item.isDisplayed()){
            expectedMessageIsPresent = true;
        }
        softAssert.assertEquals(actualMessage, expectedMessageIsPresent);
    }

    @Test
    public void verifyEditProfileWithEmptyNewPasswordData(){
        boolean actualMessage;
        boolean expectedMessageIsPresent = false;

        SoftAssert softAssert = new SoftAssert();
        actualMessage = editProfileComponent
                .changePasswordClick()
                .inputOldPassword("admin")
                .saveChangesButtonClick()
                .saveChangesButtonIsEnable();

        String xPathErrorMessageText = "//div[@class='ant-form-item-explain-error']";
        WebElement item = driver.findElement(By.xpath(xPathErrorMessageText));

        if(item.isDisplayed()){
            expectedMessageIsPresent = true;
        }
        softAssert.assertEquals(actualMessage, expectedMessageIsPresent);
    }
}
