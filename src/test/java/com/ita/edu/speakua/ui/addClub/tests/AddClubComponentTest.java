package com.ita.edu.speakua.ui.addClub.tests;

import com.ita.edu.speakua.ui.runners.AddClubDescribeTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
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
    @Severity(SeverityLevel.CRITICAL)
    @Test(dataProvider = "descriptionValidData")
    public void verifyAddClubDescribeFieldValidDataTest(int charAmount) {
        String descriptionInput = new String(new char[150]).replace("\0", "Lorem Ipsu");
        boolean isButtonEnable = addClubDescribeComponent
                .inputDescription(descriptionInput.substring(0, charAmount))
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

    @Issue("TUA-178")
    @Description("Verify error messages when description field is filled in with russian language")
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


    @DataProvider(name = "descriptionLengthErrorData")
    public Object[][] descriptionLengthError() {
        String fifteenHundredCharacterDescription = new String(new char[60]).replace("\0", "Lorem ipsum dolor am sit.");
        return new Object[][]{
                {fifteenHundredCharacterDescription, false},
                {fifteenHundredCharacterDescription + "m", true},
                {fifteenHundredCharacterDescription.substring(0, 1498), false},
                {fifteenHundredCharacterDescription + "Hello world", true},
        };
    }

    @Issue("TUA-177")
    @Description("Verify that description length error message appears when the user enters more than 1500 symbols into the field")
    @Test(dataProvider = "descriptionLengthErrorData")
    public void verifyDescriptionLengthErrorMessage(String description, boolean isErrorShown) {
        String lengthErrorText = "Опис гуртка може містити від 40 до 1500 символів.";

        boolean descriptionErrorsContainLengthError = addClubDescribeComponent
                .inputDescription(description)
                .errorMessageForDescriptionFieldContainsText(lengthErrorText);

        if (isErrorShown) {
            Assert.assertTrue(addClubDescribeComponent.areDescriptionErrorsShown());
            Assert.assertTrue(descriptionErrorsContainLengthError);
        } else {
            Assert.assertFalse(addClubDescribeComponent.areDescriptionErrorsShown());
            Assert.assertFalse(descriptionErrorsContainLengthError);
        }
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
        btnFinishIsEnable = addClubDescribeComponent.isButtonEnable();
        softAssert.assertTrue(btnFinishIsEnable);

        softAssert.assertAll();
    }
}
