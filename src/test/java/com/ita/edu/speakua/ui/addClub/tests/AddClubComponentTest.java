package com.ita.edu.speakua.ui.addClub.tests;

import com.ita.edu.speakua.ui.HomePage;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.addClubComponent.AddClubDescribeComponent;
import com.ita.edu.speakua.ui.runners.BaseTestRunnerWithLogIn;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AddClubComponentTest extends BaseTestRunnerWithLogIn {

    @Test
    public void verifyAddClubDescribeFieldValidDataTest() {
        AddClubDescribeComponent addClubDescribeComponent = new HomePage(driver)
                .openAdminProfileMenu()
                .openAddGroupModal()
                .inputNameOfClub("Спортивні танці")
                .chooseCategoryClub("Спортивні секції")
                .inputAgeFrom(4)
                .inputAgeTo(8)
                .clickNextStep()
                .inputPhoneNumber("0672131246")
                .clickNextStep()
                .inputDescribe("Lorem Ipsum is simply dummy text of the ");
        boolean verifyBtnFinishIsEnableFortyChars = addClubDescribeComponent.finishBtnIsEnable();

        addClubDescribeComponent
                .inputDescribe("Lorem Ipsum is simply dummy text of the printing and typesetting industry. " +
                        "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, " +
                        "when an unknown printer took a galley of type and scrambled it to make a type specimen book. " +
                        "It has survived not only five centuries, but also the leap into electronic typesetting, " +
                        "remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset " +
                        "sheets containing Lorem Ipsum passages, and more recently with desktop publishing software " +
                        "like Aldus PageMaker including versions of Lorem Ipsum. It is a long established fact that " +
                        "a reader will be distracted by the readable content of a page when looking at its layout. " +
                        "The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, " +
                        "as opposed to using 'Content here, content here', making it look like readable English. " +
                        "Many desktop publishing packages and web page editors now use Lorem Ipsum as their default " +
                        "model text, and a search for");
        boolean verifyBtnFinishIsEnableThousandChars = addClubDescribeComponent.finishBtnIsEnable();

        addClubDescribeComponent
                .inputDescribe("Lorem Ipsum is simply dummy text of the printing and typesetting industry. " +
                        "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, " +
                        "when an unknown printer took a galley of type and scrambled it to make a type specimen book. " +
                        "It has survived not only five centuries, but also the leap into electronic typesetting, " +
                        "remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset " +
                        "sheets containing Lorem Ipsum passages, and more recently with desktop publishing software " +
                        "like Aldus PageMaker including versions of Lorem Ipsum. It is a long established fact that " +
                        "a reader will be distracted by the readable content of a page when looking at its layout. " +
                        "The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, " +
                        "as opposed to using 'Content here, content here', making it look like readable English. " +
                        "Many desktop publishing packages and web page editors now use Lorem Ipsum as their default " +
                        "model text, and a search for Many desktop publishing packages and web page editors now " +
                        "use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover " +
                        "many web sites still in their infancy. Various versions have evolved over the years, " +
                        "sometimes by accident, sometimes on purpose (injected humour and the like) Contrary to " +
                        "popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical " +
                        "Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin 1");
        boolean verifyBtnFinishIsEnableOneAndHalfThousandChars = addClubDescribeComponent.finishBtnIsEnable();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(verifyBtnFinishIsEnableFortyChars, "Button should be enabled");
        softAssert.assertTrue(verifyBtnFinishIsEnableThousandChars, "Button should be enabled");
        softAssert.assertTrue(verifyBtnFinishIsEnableOneAndHalfThousandChars, "Button should be enabled");
        softAssert.assertAll();
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
        AddClubDescribeComponent addClubDescribeComponent = new HomePage(driver)

                .openAdminProfileMenu()
                .openUserProfilePage()
                .openAddClubModal()
                .inputNameOfClub("Спортивні танці")
                .chooseCategoryClub("Спортивні секції")
                .inputAgeFrom(4)
                .inputAgeTo(8)
                .clickNextStep()
                .inputPhoneNumber("0672131246")
                .clickNextStep()
                .inputDescribe(description);

        String firstActualErrorMessage = addClubDescribeComponent.getErrorMessageDescriptionField().get(0);
        String secondActualErrorMessage = addClubDescribeComponent.getErrorMessageDescriptionField().get(1);
        int amountOfErrorMessages = addClubDescribeComponent.getErrorMessageDescriptionField().size();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(firstActualErrorMessage, firstError);
        softAssert.assertEquals(secondActualErrorMessage, secondError);
        softAssert.assertEquals(amountOfErrorMessages, 2);
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
                .chooseCategoryClub("Спортивні секції")
                .inputAgeFrom(4)
                .inputAgeTo(8)
                .clickNextStep()
                .inputPhoneNumber("0672131246")
                .clickNextStep()
                .inputDescribe(descriptionInput);

        SoftAssert softAssert = new SoftAssert();
        String lengthErrorText = "Опис гуртка може містити від 40 до 1500 символів.";
        softAssert.assertFalse(addClubDescribeComponent.errorMessageForDescriptionFieldContainsText(lengthErrorText));

        addClubDescribeComponent.inputDescribe(descriptionInput.substring(0, 1498));
        softAssert.assertFalse(addClubDescribeComponent.errorMessageForDescriptionFieldContainsText(lengthErrorText));

        addClubDescribeComponent.inputDescribe(descriptionInput + "m");
        softAssert.assertTrue(addClubDescribeComponent.errorMessageForDescriptionFieldContainsText(lengthErrorText));

        addClubDescribeComponent.inputDescribe(descriptionInput + "Hello world");
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

    @Test(dataProvider = "validDescriptionData")
    public void verifyDescribeComponentWithValidData(String testData) {
        boolean btnFinishIsEnable;
        AddClubDescribeComponent addClubDescribeComponent = new HomePage(driver)
                .openAdminProfileMenu()
                .openUserProfilePage()
                .openAddClubModal()
                .inputNameOfClub("Club3")
                .chooseCategoryClub("Основи")
                .inputAgeFrom(4)
                .inputAgeTo(9)
                .clickNextStep()
                .inputPhoneNumber("0973756135")
                .clickNextStep();

        SoftAssert softAssert = new SoftAssert();

        addClubDescribeComponent.inputDescribe(testData);
        btnFinishIsEnable = addClubDescribeComponent.finishBtnIsEnable();
        softAssert.assertTrue(btnFinishIsEnable);

        softAssert.assertAll();
    }
}
