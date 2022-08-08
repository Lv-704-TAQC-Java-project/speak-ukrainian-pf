package com.ita.edu.speakua.ui.registration.tests;

import com.ita.edu.speakua.ui.header.HeaderComponent;
import com.ita.edu.speakua.ui.runners.BaseTestRunner;
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

    @Test(dataProvider = "registrationFormInputData")
    public void verifyThatRegistrationDataAreRememberedTest(String LastName, String FirstName, String Phone, String Email, String Password) {
        HeaderComponent header = new HeaderComponent(driver);
        header
                .openGuestProfileMenu()
                .openRegistrationModal()
                .fillInLastName(LastName)
                .fillInName(FirstName)
                .fillInPhone(Phone)
                .fillInEmail(Email)
                .fillInPassword(Password)
                .fillInConfirmPassword(Password);

        header
                .getGuestProfileMenuComponent()
                .getRegistrationModal().closeRegistrationModal()
                .openGuestProfileMenu()
                .openRegistrationModal();

        String LastNameValue = header.getGuestProfileMenuComponent().getRegistrationModal().getLastNameValue();
        String FirstNameValue = header.getGuestProfileMenuComponent().getRegistrationModal().getFistNameValue();
        String PhoneValue = header.getGuestProfileMenuComponent().getRegistrationModal().getPhoneValue();
        String EmailValue = header.getGuestProfileMenuComponent().getRegistrationModal().getEmailValue();
        String PasswordValue = header.getGuestProfileMenuComponent().getRegistrationModal().getPasswordValue();
        String ConfirmPasswordValue = header.getGuestProfileMenuComponent().getRegistrationModal().getConfirmPasswordValue();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(LastNameValue, LastName);
        softAssert.assertEquals(FirstNameValue, FirstName);
        softAssert.assertEquals(PhoneValue, Phone);
        softAssert.assertEquals(EmailValue, Email);
        softAssert.assertEquals(PasswordValue, Password);
        softAssert.assertEquals(ConfirmPasswordValue, Password);
        softAssert.assertAll();

    }
}
