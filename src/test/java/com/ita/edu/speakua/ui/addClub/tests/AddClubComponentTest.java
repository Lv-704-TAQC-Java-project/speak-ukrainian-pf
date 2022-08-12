package com.ita.edu.speakua.ui.addClub.tests;

import com.ita.edu.speakua.ui.HomePage;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.addClubComponent.AddClubDescribeComponent;
import com.ita.edu.speakua.ui.runners.AddClubDescribeTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertTrue;

public class AddClubComponentTest extends AddClubDescribeTestRunner {

    @DataProvider(name = "descriptionValidData")
    public Object[][] validDescriptionField() {
        return new Object[][]{
                {1000},
                {40},
                {1500}
        };
    }

    @Issue("TUA-172")
    @Description("Verify that the button is enable when the correct data is entered")
    @Test(dataProvider = "descriptionValidData")
    public void verifyAddClubDescribeFieldValidDataTest(int charAmount) {
        String descriptionInput = new String(new char[150]).replace("\0", "Lorem Ipsu");
        boolean isButtonEnable = addClubDescribeComponent
                .inputDescribe(descriptionInput.substring(0,charAmount))
                .isButtonEnable();
        assertTrue(isButtonEnable, "Button should be enabled");
    }

    @DataProvider(name = "descriptionErrorWithForbiddenCharacters")
    public Object[][] errorDescriptionField() {
        return new Object[][]{
                {"‘э’, ‘ъ’, ‚ü‘,‘ö‘,‘ä‘\\n 'Ы, ‘э’, ‘ъ’, ‚ü‘,‘ö‘,‘ä‘", "Некоректний опис гуртка",
                        "Опис гуртка не може містити російські літери"}
        };
    }

    @Test(dataProvider = "descriptionErrorWithForbiddenCharacters")
    public void verifyErrorMessageAddClubDescriptionField(String description, String firstError, String secondError) {
        addClubDescribeComponent.inputDescription(description);

        String firstActualErrorMessage = addClubDescribeComponent.getErrorMessageDescriptionField().get(0);
        String secondActualErrorMessage = addClubDescribeComponent.getErrorMessageDescriptionField().get(1);
        int amountOfErrorMessages = addClubDescribeComponent.getErrorMessageDescriptionField().size();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(firstActualErrorMessage, firstError,
                "first error message does not correspond expected");
        softAssert.assertEquals(secondActualErrorMessage, secondError,
                "second error message does not correspond expected");
        softAssert.assertEquals(amountOfErrorMessages, 2,
                "There are more than 2 error messages");
        softAssert.assertAll();
    }

    @Test
    public void verifyLengthErrorMessageForDescriptionFieldWhenAddClub() {
        String descriptionInput = new String(new char[150]).replace("\0", "Lorem Ipsu");

        AddClubDescribeComponent addClubDescribeComponent = new HomePage(driver)
                .openAdminProfileMenu()
                .openUserProfilePage()
                .openAddClubModal()
                .inputNameOfClub("Спортивні танці")
                .selectCategoryClub("Спортивні секції")
                .inputAgeFrom(4)
                .inputAgeTo(8)
                .clickNextStep()
                .inputPhoneNumber("0672131246")
                .clickNextStep()
                .inputDescription(descriptionInput);

        SoftAssert softAssert = new SoftAssert();
        String lengthErrorText = "Опис гуртка може містити від 40 до 1500 символів.";
        softAssert.assertFalse(addClubDescribeComponent.errorMessageForDescriptionFieldContainsText(lengthErrorText));

        addClubDescribeComponent.inputDescription(descriptionInput.substring(0, 1498));
        softAssert.assertFalse(addClubDescribeComponent.errorMessageForDescriptionFieldContainsText(lengthErrorText));

        addClubDescribeComponent.inputDescription(descriptionInput + "m");
        softAssert.assertTrue(addClubDescribeComponent.errorMessageForDescriptionFieldContainsText(lengthErrorText));

        addClubDescribeComponent.inputDescription(descriptionInput + "Hello world");
        softAssert.assertTrue(addClubDescribeComponent.errorMessageForDescriptionFieldContainsText(lengthErrorText));
        softAssert.assertAll();
    }

    @DataProvider(name = "validDescriptionData")
    public static Object[][] validDescriptionData() {
        return new Object[][]{
                {"Education, students, Школа балету, Teachers"},
                {"1234567890123456789012345678901234567890"},
                {"!\"#$%&'()*+,-./:;<=>?@[]^_`{}~"}
        };
    }

    @Issue("TUA-173")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify the description field is filled in with valid data and The 'Завершити' button is enable")
    @Test(dataProvider = "validDescriptionData")
    public void verifyCreatingClubWithDescribeValidData(String testData) {
        boolean btnFinishIsEnable;

        SoftAssert softAssert = new SoftAssert();

        addClubDescribeComponent.inputDescription(testData);
        btnFinishIsEnable = addClubDescribeComponent.isFinishButtonEnable();
        softAssert.assertTrue(btnFinishIsEnable);

        softAssert.assertAll();
    }
}
