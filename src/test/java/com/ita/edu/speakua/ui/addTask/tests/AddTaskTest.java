package com.ita.edu.speakua.ui.addTask.tests;

import com.ita.edu.speakua.ui.header.profileMenuAdmin.administrationComponent.addTask.TaskPage;
import com.ita.edu.speakua.ui.runners.AddTaskTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.nio.file.Paths;
import java.time.LocalDate;

public class AddTaskTest extends AddTaskTestRunner {
    private final String pathToImage = Paths.get(Paths.get(System.getProperty("user.dir")).toString(),
            "src", "test", "resources", "image.png").toString();

    @Issue("TUA-525")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that admin can't create a task with invalid data in description field and title field")
    @Test
    public void verifyCreateTaskInvalidDescriptionAndTitle() {
        String descriptionInput = new String(new char[350]).replace("\0", "Lorem Ipsu");
        boolean allFieldsAreEmpty = addTaskPage.areFieldsEmpty();
        addTaskPage = addTaskPage
                .enterStartDate("2022-08-23")
                .uploadImage(pathToImage)
                .enterName("Yaroslav test")
                .enterTitle(descriptionInput.substring(0, 50))
                .selectChallenge("Example name");
//                .failSave();

//        boolean errorMessageDescribeIsEmpty = addTaskPage.errorMessageIsEmptyIsVisible();

        addTaskPage = addTaskPage
                .enterDescription("ъэы, ผม, Ÿ, ðъэы, ผม, Ÿ, ðъэы, ผม, Ÿ, ðъэы, ผม, Ÿ, ð");
        addTaskPage.save();

        boolean errorMessageDescribeInvalidCharacters = addTaskPage.errorMessageInvalidCharactersIsVisible();

        addTaskPage.enterTitle(descriptionInput.substring(0, 39));
        addTaskPage.save();

        boolean errorMessageHeadingNotEnoughChars = addTaskPage.errorMessageIsDisplayed();

        addTaskPage.enterTitle(descriptionInput.substring(0, 3001));
        addTaskPage.save();

        boolean errorMessageHeadingTooManyChars = addTaskPage.errorMessageIsDisplayed();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(allFieldsAreEmpty, "All field should be empty");
//        softAssert.assertTrue(errorMessageDescribeIsEmpty, "A message should appear that the field is empty");
        softAssert.assertTrue(errorMessageDescribeInvalidCharacters, "A message should appear stating that invalid " +
                "characters have been entered");
        softAssert.assertTrue(errorMessageHeadingNotEnoughChars, "A message should appear stating that not enough " +
                "characters have been entered");
        softAssert.assertTrue(errorMessageHeadingTooManyChars, "A message should appear stating that too many " +
                "characters have been entered");
        softAssert.assertAll();
    }

    @DataProvider(name = "invalidHeaderData")
    public static Object[][] invalidHeaderData() {
        return new Object[][]{
                {"", "headerText не може бути пустим"},
                {"ъэы, ผม, Ÿ, ðъэы, ผม, Ÿ, ðъэы, ผม, Ÿ, ðъэы, ผม, Ÿ, ð",
                        "headerText Помилка. Текст містить недопустимі символи"},
                {"Lorem Ipsum 5% ",
                        "headerText must contain a minimum of 40 and a maximum of 3000 letters"},
                {new String(new char[400]).replace("\0", "Lorem 56№*"),
                        "headerText must contain a minimum of 40 and a maximum of 3000 letters"}
        };
    }

    @Issue("TUA-524")
    @Description("Verify impossibility of creating task with heading invalid data")
    @Test(dataProvider = "invalidHeaderData")
    public void verifyCreatingTaskWithHeadingInvalidData(String invalidData, String expectedMessage) {
        String descriptionInput = new String(new char[10]).replace("\0", "Lorem 56№*");
        String actualErrorMessage;

        boolean isAllFieldsAreEmptyByDefault = addTaskPage.areFieldsEmpty();

        addTaskPage.enterStartDate(LocalDate.now().plusDays(1).toString())
                .uploadImage(pathToImage)
                .enterName("Test task # 5/")
                .enterTitle(invalidData)
                .enterDescription(descriptionInput)
                .selectChallenge("Example name")
                .save();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(isAllFieldsAreEmptyByDefault);
        actualErrorMessage = addTaskPage.getErrorMessageText();
        softAssert.assertEquals(actualErrorMessage, expectedMessage);

        softAssert.assertAll();
    }


    @Issue("TUA-526")
    @Test
    public void verifyCreateTaskWithoutChallenge() {
        addTaskPage = addTaskPage
                .enterStartDate("2023-01-01")
                .enterName("Lorem Ipsum")
                .enterTitle("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has " +
                        "been the industry's standard dummy text ever since the 1500s, when an unknown printer took a " +
                        "galley of type and scrambled it to make a type specimen book. It has survived not only five " +
                        "centuries, but also the leap into electronic typesetting, remaining essentially unchanged. " +
                        "It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum " +
                        "passages, and more recently with desktop publishing software like Aldus PageMaker including " +
                        "versions of Lorem Ipsum")
                .enterDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has " +
                        "been the industry's standard dummy text ever since the 1500s, when an unknown printer took a ");
        addTaskPage.save();


        Assert.assertTrue(addTaskPage.errorMessageIsEmptyIsVisible(), "Error message didn't find");
    }

    @DataProvider(name = "invalidDateData")
    public static Object[][] invalidDateData() {
        return new Object[][]{
                {"", "startDate не може бути відсутнім, має бути задано"},
                {"2021-01-01", "startDate дата має бути в майбутньому"}
        };
    }

    @Issue("TUA-521")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that admin can't create a task with invalid data in date field")
    @Test(dataProvider = "invalidDateData")
    public void verifyCreateTaskInvalidData(String actualDate, String expectedErrorMessage) {
        String descriptionInput = new String(new char[350]).replace("\0", "Lorem Ipsu");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(addTaskPage.areFieldsEmpty());
        addTaskPage = addTaskPage
                .uploadImage(pathToImage)
                .enterName("Yaroslav test")
                .enterTitle(descriptionInput.substring(0, 50))
                .enterDescription(descriptionInput.substring(0, 50))
                .selectChallenge("Example name")
                .enterStartDate(actualDate);
        addTaskPage.save();
        String actualErrorMessage = addTaskPage.getErrorMessageText();
        softAssert.assertEquals(actualErrorMessage, expectedErrorMessage);
        softAssert.assertAll();
    }

    @Issue("TUA-522")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that admin can't create a task without image on 'Add task' page")
    @Test
    public void verifyCreateTaskWithoutImageError() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(addTaskPage.areFieldsEmpty());
        addTaskPage = addTaskPage
                .enterStartDate(LocalDate.now().plusDays(1).toString())
                .enterName("Maksym test")
                .enterTitle("Lorem ipsum dolor sit amet, sed do eiusmod et dolore magna aliqua.")
                .enterDescription("Facilisis sed odio morbi quis. Mauris rhoncus aenean vel elit scelerisque.")
                .selectChallenge("The European languages");
        addTaskPage.save();
        String actualErrorMessage = addTaskPage.getErrorMessageText();
        softAssert.assertEquals(actualErrorMessage, "Фото не може бути пустим");
        softAssert.assertAll();
    }

    @Issue("TUA-520")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that admin can create a task on 'Add task' page")
    @Test
    public void verifyCreateTask() {
        String name = "Maksym test";
        String title = "Lorem ipsum dolor sit amet, sed do eiusmod et dolore magna aliqua.";
        String description = "Facilisis sed odio morbi quis. Mauris rhoncus aenean vel elit scelerisque.";
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(addTaskPage.areFieldsEmpty());
        addTaskPage = addTaskPage
                .enterStartDate(LocalDate.now().plusDays(1).toString())
                .uploadImage(pathToImage)
                .enterName(name)
                .enterTitle(title)
                .enterDescription(description)
                .selectChallenge("The European languages");

        TaskPage taskPage = addTaskPage.save();

        softAssert.assertEquals(addTaskPage.getSuccessMessage(), String.format("Завдання%s' успішно додане!", name));
        softAssert.assertEquals(taskPage.getNameText(), name);
        softAssert.assertEquals(taskPage.getTitleText(), title);
        softAssert.assertEquals(taskPage.getDescriptionText(), description);
        softAssert.assertAll();
    }
}
