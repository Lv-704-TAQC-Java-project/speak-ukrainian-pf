package com.ita.edu.speakua.ui.addTask.tests;

import com.ita.edu.speakua.ui.header.profileMenuAdmin.administrationComponent.addTask.TaskPage;
import com.ita.edu.speakua.ui.runners.AddTaskTestRunner;
import com.ita.edu.speakua.ui.utils.jdbc.dao.TaskDAO;
import com.ita.edu.speakua.ui.utils.jdbc.entity.TaskEntity;
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
import java.util.ArrayList;
import java.util.List;

public class AddTaskTest extends AddTaskTestRunner {
    private final String tomorrow = LocalDate.now().plusDays(1).toString();
    private final String pathToImage = Paths.get(Paths.get(System.getProperty("user.dir")).toString(),
            "src", "test", "resources", "image.png").toString();
    private final String title = "Lorem ipsum dolor sit amet, sed do eiusmod et dolore magna aliqua.";
    private final String description = "Facilisis sed odio morbi quis. Mauris rhoncus aenean vel elit scelerisque.";
    private final String challenge = "The European languages";

    @DataProvider(name = "invalidDescriptionData")
    public static Object[][] invalidDescriptionData() {
        return new Object[][]{
                {"", "description не може бути пустим"},
                {"ъэы, ผม, Ÿ, ðъэы, ผม, Ÿ, ðъэы, ผม, Ÿ, ðъэы, ผม, Ÿ, ð",
                        "description Помилка. Текст містить недопустимі символи"},
                {"Lorem Ipsum 5% ",
                        "description must contain a minimum of 40 and a maximum of 3000 letters"},
                {new String(new char[350]).replace("\0", "Lorem ipsum "),
                        "description must contain a minimum of 40 and a maximum of 3000 letters"}
        };
    }

    @Issue("TUA-525")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that admin can't create a task with invalid data in description field")
    @Test(dataProvider = "invalidDescriptionData")
    public void verifyCreateTaskInvalidDescription(String textDescription, String expectedMessage) {
        boolean allFieldsAreEmpty = addTaskPage.areFieldsEmpty();
        addTaskPage = addTaskPage
                .enterStartDate(LocalDate.now().plusDays(2).toString())
                .uploadImage(pathToImage)
                .enterName("Yaroslav test")
                .enterTitle("Some forty character text for the test!!")
                .enterDescription(textDescription)
                .selectChallenge(challenge);
        addTaskPage.save();
        String errorMessage = addTaskPage.getErrorMessageText();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(allFieldsAreEmpty);
        softAssert.assertEquals(errorMessage, expectedMessage);
        softAssert.assertAll();
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
    public void verifyCreateTaskInvalidDateData(String actualDate, String expectedErrorMessage) {
        String descriptionInput = new String(new char[350]).replace("\0", "Lorem Ipsu");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(addTaskPage.areFieldsEmpty());
        addTaskPage = addTaskPage
                .enterStartDate(actualDate)
                .uploadImage(pathToImage)
                .enterName("Yaroslav test")
                .enterTitle(descriptionInput.substring(0, 50))
                .enterDescription(descriptionInput.substring(0, 500))
                .selectChallenge("Example name");
        addTaskPage.save();
        String actualErrorMessage = addTaskPage.getErrorMessageText();
        softAssert.assertEquals(actualErrorMessage, expectedErrorMessage);
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
        String actualErrorMessage;
        String clubName = "Test task # 5/";
        boolean areAllFieldsEmptyByDefault = addTaskPage.areFieldsEmpty();

        addTaskPage.enterStartDate(LocalDate.now().plusDays(1).toString())
                .uploadImage(pathToImage)
                .enterName(clubName)
                .enterTitle(invalidData)
                .enterDescription(description)
                .selectChallenge(challenge)
                .save();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(areAllFieldsEmptyByDefault, "Not all fields are empty by default");
        actualErrorMessage = addTaskPage.getErrorMessageText();
        softAssert.assertEquals(actualErrorMessage, expectedMessage, "Expected error message did not appear");

        List<String> clubs = new ArrayList<>();
        new TaskDAO()
                .selectLikeName(clubName)
                .forEach(club -> clubs.add(club.toString()));

        softAssert.assertTrue(clubs.isEmpty());

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

    @DataProvider(name = "invalidNameData")
    public static Object[][] invalidNameData() {
        return new Object[][]{
                {"", "name не може бути пустим"},
                {"Lor",
                        "name must contain a minimum of 5 and a maximum of 100 letters"},
                {new String(new char[400]).replace("\0", "Lorem 56№*"),
                        "name must contain a minimum of 5 and a maximum of 100 letters"}
        };
    }

    @Issue("TUA-523")
    @Description("Verify impossibility of creating task with name invalid data")
    @Test(dataProvider = "invalidNameData")
    public void verifyCreateTaskWithInvalidNameData(String invalidData, String expectedMessage) {
        String actualErrorMessage;

        boolean isAllFieldsAreEmptyByDefault = addTaskPage.areFieldsEmpty();

        addTaskPage.enterStartDate(LocalDate.now().plusDays(1).toString())
                .uploadImage(pathToImage)
                .enterName(invalidData)
                .enterTitle("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has " +
                        "been the industry's standard dummy text ever since the 1500s, when an unknown printer took a " +
                        "galley of type and scrambled it to make a type specimen book. It has survived not only five " +
                        "centuries, but also the leap into electronic typesetting, remaining essentially unchanged. " +
                        "It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum " +
                        "passages, and more recently with desktop publishing software like Aldus PageMaker including " +
                        "versions of Lorem Ipsum")
                .enterDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has " +
                        "been the industry's standard dummy text ever since the 1500s, when an unknown printer took a ")
                .selectChallenge("Example name");
        addTaskPage.save();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(isAllFieldsAreEmptyByDefault);
        actualErrorMessage = addTaskPage.getErrorMessageText();
        softAssert.assertEquals(actualErrorMessage, expectedMessage);

        softAssert.assertAll();
    }

    @Issue("TUA-522")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that admin can't create a task without image on 'Add task' page")
    @Test
    public void verifyCreateTaskWithoutImageError() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(addTaskPage.areFieldsEmpty(),
                "Create task form fields should be empty by default.");
        addTaskPage = addTaskPage
                .enterStartDate(tomorrow)
                .enterName("Maksym test")
                .enterTitle(title)
                .enterDescription(description)
                .selectChallenge(challenge);
        addTaskPage.save();
        String actualErrorMessage = addTaskPage.getErrorMessageText();
        softAssert.assertEquals(actualErrorMessage, "Фото не може бути пустим",
                "Incorrect error message when creating task without image.");
        softAssert.assertAll();
    }

    @Issue("TUA-520")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that admin can create a task on 'Add task' page")
    @Test
    public void verifyCreateTask() {
        String name = "Maksym test " + System.currentTimeMillis();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(addTaskPage.areFieldsEmpty(),
                "Create task form fields should be empty by default.");
        addTaskPage = addTaskPage
                .enterStartDate(tomorrow)
                .uploadImage(pathToImage)
                .enterName(name)
                .enterTitle(title)
                .enterDescription(description)
                .selectChallenge(challenge);

        TaskPage taskPage = addTaskPage.save();
        TaskDAO taskDAO = new TaskDAO();
        List<TaskEntity> tasks = taskDAO.selectLikeName(name);

        softAssert.assertEquals(tasks.get(0).getName(), name, "Name");
        softAssert.assertEquals(tasks.get(0).getStartDate(), tomorrow, "Date");
        softAssert.assertEquals(tasks.get(0).getHeaderText(), "<p>" + title + "</p>", "Description");
        softAssert.assertEquals(tasks.get(0).getDescription(), "<p>" + description + "</p>", "Header title");

        softAssert.assertEquals(addTaskPage.getSuccessMessage(),
                String.format("Завдання '%s' успішно додане!", name),
                "Incorrect popup success message.");
        softAssert.assertEquals(taskPage.getNameText(), name,
                "Incorrect name of created task.");
        softAssert.assertEquals(taskPage.getTitleText(), title,
                "Incorrect title of created task.");
        softAssert.assertEquals(taskPage.getDescriptionText(), description,
                "Incorrect description of created task.");

        softAssert.assertAll();
    }
}
