package com.ita.edu.speakua.ui.addTask.tests;

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

public class AddTaskTest extends AddTaskTestRunner {
    private final String pathToImage = Paths.get(Paths.get(System.getProperty("user.dir")).toString(),
            "src", "test", "resources", "image.png").toString();

    @Test
    public void verifyCreateTaskInvalidData() {

        boolean allFieldsAreEmpty = addTaskPage.areFieldsEmpty();

        addTaskPage = addTaskPage
                .enterStartDate("2022-08-23")
                .uploadImage(pathToImage)
                .enterName("Yaroslav test")
                .enterTitle("Lorem Ipsum is simply dummy text of the printing and typesetting industry. " +
                        "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s," +
                        " when an unknown printer took a galley of type and scrambled " +
                        "it to make a type specimen book.")
                .selectChallenge("Example name");
//                .tryClickSaveButton();

//        boolean errorMessageDescribeIsEmpty = addTaskPage.errorMessageIsEmptyIsVisible();

        addTaskPage = addTaskPage
                .enterDescription("ъэы, ผม, Ÿ, ðъэы, ผม, Ÿ, ðъэы, ผม, Ÿ, ðъэы, ผม, Ÿ, ð")
                .failSave();

        boolean errorMessageDescribeInvalidCharacters = addTaskPage.errorMessageInvalidCharactersIsVisible();

        addTaskPage = addTaskPage
                .enterTitle("Lorem Ipsum is simply ")
                .failSave();

        boolean errorMessageHeadingNotEnoughChars = addTaskPage.errorMessageInvalidCharactersCount();

        addTaskPage = addTaskPage
                .enterTitle("What is Lorem Ipsum?\n" +
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has " +
                        "been the industry's standard dummy text ever since the 1500s, when an unknown printer took a " +
                        "galley of type and scrambled it to make a type specimen book. It has survived not only five " +
                        "centuries, but also the leap into electronic typesetting, remaining essentially unchanged. " +
                        "It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum " +
                        "passages, and more recently with desktop publishing software like Aldus PageMaker including " +
                        "versions of Lorem Ipsum.\n" +
                        "It is a long established fact that a reader will be distracted by the readable content of " +
                        "a page when looking at its layout. The point of using Lorem Ipsum is that it has a " +
                        "more-or-less normal distribution of letters, as opposed to using 'Content here, content here'," +
                        " making it look like readable English. Many desktop publishing packages and web page editors " +
                        "now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover " +
                        "many web sites still in their infancy. Various versions have evolved over the years, sometimes" +
                        " by accident, sometimes on purpose (injected humour and the like). Contrary to popular belief, " +
                        "Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature " +
                        "from 45 BC, making it over 2000 years old. Richard McClintock,a Latin professor at" +
                        "Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, " +
                        "from a Lorem Ipsum passage, and going through the cites of the word in classical literature, " +
                        "discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de " +
                        "Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. " +
                        "This book is a treatise on the theory of ethics, very popular during the Renaissance. The " +
                        "first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section " +
                        "1.10.32. There are many variations of passages of Lorem Ipsum available, but the majority " +
                        "have suffered alteration in some form,by injected humour, or randomised words which don't " +
                        "look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to " +
                        "be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum " +
                        "generators on the Internet tend to repeat predefined chunks as necessary, making this the " +
                        "first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined " +
                        "with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. " +
                        "The generated Lorem Ipsum is therefore always free from repetition, injected humour, or " +
                        "non-characteristic words etc. The standard chunk of Lorem Ipsum used since the 1500s is " +
                        "reproduced below for those interested. Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum " +
                        "et Malorum\" by Cicero are also reproduced in their exact original form, accompanied by " +
                        "English versions from the 1914 translation")
                .failSave();

        boolean errorMessageHeadingTooManyChars = addTaskPage.errorMessageInvalidCharactersCount();

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
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify impossibility of creating task with heading invalid data")
    @Test(dataProvider = "invalidHeaderData")
    public void verifyCreatingTaskWithHeadingInvalidData(String invalidData, String expectedMessage) {
        String descriptionInput = new String(new char[10]).replace("\0", "Lorem 56№*");
        String actualErrorMessage;

        boolean isAllFieldsAreEmptyByDefault = addTaskPage.areFieldsEmpty();

        addTaskPage.enterStartDate("2022-10-19")
                .uploadImage(pathToImage)
                .enterName("Test task # 5/")
                .enterTitle(invalidData)
                .enterDescription(descriptionInput)
                .selectChallenge("Example name")
                .failSave();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(isAllFieldsAreEmptyByDefault);
        actualErrorMessage = addTaskPage.getErrorMessageText();
        softAssert.assertEquals(actualErrorMessage, expectedMessage);

        softAssert.assertAll();
    }


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
                        "been the industry's standard dummy text ever since the 1500s, when an unknown printer took a ")
                .failSave();


        Assert.assertTrue(addTaskPage.errorMessageIsEmptyIsVisible(), "Error message didn't find");
    }
}
