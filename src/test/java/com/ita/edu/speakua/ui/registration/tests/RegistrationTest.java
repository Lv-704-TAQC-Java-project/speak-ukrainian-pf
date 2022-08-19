package com.ita.edu.speakua.ui.registration.tests;

import com.ita.edu.speakua.ui.header.Header;
import com.ita.edu.speakua.ui.runners.BaseTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class RegistrationTest extends BaseTestRunner {

    @DataProvider(name = "registrationFormInputData")
    public Object[][] registrationFormInputData() {
        return new Object[][]{
                {"Прізвище", "Ім'я", "0631133456", "adminNewRegistration@gmail.com", "12345Aa!"}
        };
    }
    @Issue("TUA-454")
    @Description("Verify that registration data are remembered")
    @Test(dataProvider = "registrationFormInputData")
    public void verifyThatRegistrationDataAreRememberedTest(String lastName, String firstName, String phone, String email, String password) {
        Header header = new Header(driver);
        header
                .openGuestProfileMenu()
                .openRegistrationModal()
                .fillInLastName(lastName)
                .fillInName(firstName)
                .fillInPhone(phone)
                .fillInEmail(email)
                .fillInPassword(password)
                .fillInConfirmPassword(password)
                .closeRegistrationModal()
                .openGuestProfileMenu()
                .openRegistrationModal();

        String lastNameValue = header
                .getGuestProfileMenuComponent()
                .getRegistrationModal()
                .getLastNameValue();

        String firstNameValue = header
                .getGuestProfileMenuComponent()
                .getRegistrationModal()
                .getFistNameValue();

        String phoneValue = header
                .getGuestProfileMenuComponent()
                .getRegistrationModal()
                .getPhoneValue();

        String emailValue = header
                .getGuestProfileMenuComponent()
                .getRegistrationModal()
                .getEmailValue();

        String passwordValue = header
                .getGuestProfileMenuComponent()
                .getRegistrationModal()
                .getPasswordValue();

        String confirmPasswordValue = header
                .getGuestProfileMenuComponent()
                .getRegistrationModal()
                .getConfirmPasswordValue();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(lastNameValue, lastName,
                "Last name is not remembered");
        softAssert.assertEquals(firstNameValue, firstName,
                "First name is not remembered");
        softAssert.assertEquals(phoneValue, phone,
                "Phone number is not remembered");
        softAssert.assertEquals(emailValue, email,
                "Email is not remembered");
        softAssert.assertEquals(passwordValue, password,
                "Password is not remembered");
        softAssert.assertEquals(confirmPasswordValue, password,
                "Confirm password is not remembered");
        softAssert.assertAll();
    }
}
