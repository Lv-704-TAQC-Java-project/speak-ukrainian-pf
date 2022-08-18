package com.ita.edu.speakua.ui.addChallenge.tests;

import com.ita.edu.speakua.ui.runners.AddChallengeTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.nio.file.Paths;

public class AddChallengeTest extends AddChallengeTestRunner {

    private final String pathToImage = Paths.get(Paths.get(System.getProperty("user.dir")).toString(),
            "src", "test", "resources", "image.png").toString();

    @Issue("TUA-527")
    @Description("Verify that admin can create a challenge with valid data")
    @Test
    public void addChallengePositiveTest(){

        String descriptionInput = new String(new char[50]).replace("\0", "Lorem Ipsu");
        boolean areFieldsEmpty = addChallengePage.areFieldsEmpty();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(areFieldsEmpty,"Fields are not empty!");
        addChallengePage
                .enterSortNumber(lastChallengeNumber + 1)
                .uploadImage(pathToImage)
                .enterName(RandomStringUtils.randomAlphabetic(8))
                .enterTitle(RandomStringUtils.randomAlphabetic(8))
                .enterDescription(descriptionInput)
                .save();
        System.out.println(addChallengePage.getMessageText());
        softAssert.assertTrue(addChallengePage.getMessageText().contains("успішно"), "Your challenge is not added!");
        softAssert.assertAll();
    }
}