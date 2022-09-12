package com.ita.edu.speakua.ui.addClub.tests;

import com.ita.edu.speakua.ui.header.profileMenuAdmin.addClubModal.AddClubDescriptionStep;
import com.ita.edu.speakua.ui.runners.SameWindowTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AddClubDescriptionTest extends SameWindowTestRunner {
    private AddClubDescriptionStep addClubDescriptionStep;

    @BeforeClass
    public void openAddClubDescriptionStep() {
        signInAsAdmin();

        addClubDescriptionStep = getHomePage()
                .openAdminProfileMenu()
                .openUserProfilePage()
                .openAddClubModal()
                .enterClubName("Спортивні танці")
                .selectCategoryClub("Спортивні секції")
                .enterMinimumAge(4)
                .enterMaximumAge(8)
                .goToNextStep()
                .enterPhone("0672131246")
                .goToNextStep();
    }

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
        boolean isButtonEnable = addClubDescriptionStep
                .enterDescription(descriptionInput.substring(0, charAmount))
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
        addClubDescriptionStep.enterDescription(description);

        String firstActualErrorMessage = addClubDescriptionStep.getErrorMessageDescriptionField().get(0);
        String secondActualErrorMessage = addClubDescriptionStep.getErrorMessageDescriptionField().get(1);
        int amountOfErrorMessages = addClubDescriptionStep.getErrorMessageDescriptionField().size();

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
        String fifteenHundredCharactersDescription = new String(new char[60]).replace("\0", "Lorem ipsum dolor am sit.");
        String lengthErrorMessage = "Опис гуртка може містити від 40 до 1500 символів.";
        return new Object[][]{
                {fifteenHundredCharactersDescription, ""},
                {fifteenHundredCharactersDescription + "m", lengthErrorMessage},
                {fifteenHundredCharactersDescription.substring(0, 1498), ""},
                {fifteenHundredCharactersDescription + "Hello world", lengthErrorMessage},
        };
    }

    @Issue("TUA-177")
    @Description("Verify that description length error message is shown, when entering more than 1500 symbols into description field")
    @Test(dataProvider = "descriptionLengthErrorData")
    public void verifyDescriptionLengthErrorMessage(String description, String expectedError) {
        List<String> descriptionErrors = addClubDescriptionStep
                .enterDescription(description)
                .getDescriptionErrors();

        boolean areErrorsExpected = !expectedError.isEmpty();

        assertEquals(!descriptionErrors.isEmpty(), areErrorsExpected,
                format("Description errors should %sbe shown", areErrorsExpected ? "" : "not "));

        if (areErrorsExpected) {
            assertTrue(descriptionErrors.contains(expectedError),
                    format("List of errors %s should contain '%s' error", descriptionErrors, expectedError));
        }
    }

    @DataProvider(name = "validDescriptionData")
    public static Object[][] validDescriptionData() {
        return new Object[][]{
                {"Education, students, Школа балету, Teachers"},
                {"1234567890123456789012345678901234567890"},
                {"!\"#$%&'()*+,-./:;<=>?@[]^_`{}~!#$%&'()*+,-./:;<=>?@[]^_`{}~"}
        };
    }

    @Issue("TUA-173")
    @Description("Verify the description field is filled in with valid data and the 'Завершити' button is enable")
    @Test(dataProvider = "validDescriptionData")
    public void verifyCreatingClubWithDescribeValidData(String testData) {
        boolean isFinishButtonEnable;

        SoftAssert softAssert = new SoftAssert();

        addClubDescriptionStep.enterDescription(testData);
        isFinishButtonEnable = addClubDescriptionStep.isButtonEnable();
        softAssert.assertTrue(isFinishButtonEnable, "'Завершити' button is disabled");

        softAssert.assertAll();
    }

    @DataProvider(name = "descriptionNotEnoughLengthErrorData")
    public Object[][] descriptionNotEnoughLengthError() {
        String twentyCharactersDescription = ("Lorem ipsum dolor am");
        return new Object[][]{
                {twentyCharactersDescription, true},
                {"m", true},
                {twentyCharactersDescription + "Hello world! Lorem ", true},
        };
    }

    @Issue("TUA-176")
    @Description("Verify that description length error message appears when the user enters less than 40 symbols into the field")
    @Test(dataProvider = "descriptionNotEnoughLengthErrorData")
    public void verifyDescriptionNotEnoughLengthErrorMessage(String description, boolean isErrorShown) {
        String lengthErrorMessage = "Опис гуртка закороткий";

        boolean descriptionErrorsContainLengthError = addClubDescriptionStep
                .enterDescription(description)
                .descriptionErrorsContainMessage(lengthErrorMessage);

        if (isErrorShown) {
            Assert.assertTrue(addClubDescriptionStep.areDescriptionErrorsShown(), "Description errors are shown.");
            Assert.assertTrue(descriptionErrorsContainLengthError, "Description length error is shown.");
        } else {
            Assert.assertFalse(addClubDescriptionStep.areDescriptionErrorsShown(), "Description errors aren't shown.");
            Assert.assertFalse(descriptionErrorsContainLengthError, "Description length error isn't shown.");
        }
    }
}