package com.ita.edu.speakua.ui.profilePage.tests;

import com.ita.edu.speakua.ui.header.profileMenuAdmin.profilePage.EditProfileModal;
import com.ita.edu.speakua.ui.runners.SameWindowTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static java.lang.String.format;

public class EditProfileTest extends SameWindowTestRunner {
    private EditProfileModal editProfileModal;
    private String initialPhone;
    private String initialFirstName;
    private String initialLastName;

    @BeforeClass
    public void openEditProfileModal() {
        signInAsAdmin();

        editProfileModal = getHomePage()
                .openAdminProfileMenu()
                .openUserProfilePage()
                .openEditProfileModal();
        
        initialPhone = editProfileModal.getPhone();
        initialFirstName = editProfileModal.getFirstName();
        initialLastName = editProfileModal.getLastName();
    }

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
    public void verifyThatErrorMessagesAppearWhenEnteringFirstNameInvalidData(String invalidData, String expectedErrorMessage) {
        SoftAssert softAssert = new SoftAssert();

        String actualErrorMessage = editProfileModal
                .enterFirstName(invalidData)
                .getFirstnameErrorText();
        softAssert.assertEquals(actualErrorMessage, expectedErrorMessage, "Expected error message should appear");

        boolean isSaveChangesButtonDisabled = editProfileModal.isSaveChangesButtonEnabled();
        softAssert.assertFalse(isSaveChangesButtonDisabled, "'Зберегти зміни' button should be disabled");

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
    @Description("Verify error messages are shown and 'Save' button is disabled when entering invalid phone number")
    @Test(dataProvider = "invalidPhoneData")
    public void verifyPhoneErrorMessageWhenEditProfile(String phone, String expectedError) {
        List<String> phoneErrors = editProfileModal
                .enterPhone(phone)
                .getPhoneErrors();

        SoftAssert softly = new SoftAssert();
        softly.assertTrue(phoneErrors.contains(expectedError),
                format("List of errors %s should contain '%s' error", phoneErrors, expectedError));

        softly.assertFalse(editProfileModal.isSaveChangesButtonEnabled(),
                "SaveChanges button is enabled.");
        softly.assertAll();
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
        List<String> errorMessages = editProfileModal.enterLastName(lastName).getLastNameErrorText();
        boolean expectedMessageIsPresent = errorMessages.contains(expectedMessage);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(expectedMessageIsPresent, "Expected error message:\n"
                + expectedMessage
                + "\n\tActual error messages:\n"
                + errorMessages);

        boolean saveChangesBtnIsEnabled = editProfileModal.isSaveChangesButtonEnabled();
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
        actualMessage = editProfileModal
                .togglePasswordChange()
                .enterOldPassword("admin")
                .enterNewPassword("uyyyyuyu45ytty@")
                .save()
                .isSaveChangesButtonEnabled();

        String xPathErrorMessageText = "//div[@class='ant-form-item-explain ant-form-item-explain-connected']";
        WebElement item = driver.findElement(By.xpath(xPathErrorMessageText));

        if (item.isDisplayed()) {
            expectedMessageIsPresent = true;
        }
        softAssert.assertEquals(actualMessage, expectedMessageIsPresent);

        editProfileModal.togglePasswordChange();
    }

    @Issue("TUA-359")
    @Description("Verify that error messages are shown while leaving empty any field in the 'Змінити пароль' pop-up")
    @Test
    public void verifyEditProfileWithEmptyNewPasswordData() {
        boolean actualMessage;
        boolean expectedMessageIsPresent = false;

        SoftAssert softAssert = new SoftAssert();
        actualMessage = editProfileModal
                .togglePasswordChange()
                .enterOldPassword("admin")
                .save()
                .isSaveChangesButtonEnabled();

        String xPathErrorMessageText = "//div[@class='ant-form-item-explain-error']";
        WebElement item = driver.findElement(By.xpath(xPathErrorMessageText));

        if (item.isDisplayed()) {
            expectedMessageIsPresent = true;
        }
        softAssert.assertEquals(actualMessage, expectedMessageIsPresent);

        editProfileModal.togglePasswordChange();
    }

    @AfterMethod
    public void setInitialInfo() {
        if (!editProfileModal.getPhone().equals(initialPhone)) {
            editProfileModal.enterPhone(initialPhone);
        }
        if (!editProfileModal.getFirstName().equals(initialFirstName)) {
            editProfileModal.enterFirstName(initialFirstName);
        }
        if (!editProfileModal.getLastName().equals(initialLastName)) {
            editProfileModal.enterLastName(initialLastName);
        }
    }
}
