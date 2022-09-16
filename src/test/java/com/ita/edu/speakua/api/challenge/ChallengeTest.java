package com.ita.edu.speakua.api.challenge;

import com.ita.edu.speakua.api.ApiBaseTestRunner;
import com.ita.edu.speakua.api.clients.Authentication;
import com.ita.edu.speakua.api.clients.ChallengeClient;
import com.ita.edu.speakua.api.models.ErrorResponse;
import com.ita.edu.speakua.api.models.challenge.CreateChallengeRequest;
import com.ita.edu.speakua.api.models.challenge.ReadChallengeResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertEquals;

public class ChallengeTest extends ApiBaseTestRunner {
    private Authentication authentication;

    @BeforeClass
    public void beforeClass() {
        authentication = new Authentication(properties.getUserEmail(), properties.getUserPassword());
    }

    @Test
    public void successGetTest() {
        ChallengeClient client = new ChallengeClient(authentication.getToken());
        Response response = client.get(378);
        assertEquals(response.statusCode(), 200);

        ReadChallengeResponse createChallengeResponse = response.as(ReadChallengeResponse.class);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(createChallengeResponse.getId(), 378);
        softAssert.assertEquals(createChallengeResponse.getName(), "MzFGwuRN");
        softAssert.assertEquals(createChallengeResponse.getSortNumber(), 1897237667);
        softAssert.assertEquals(createChallengeResponse.getTasks().size(), 0);
        softAssert.assertEquals(createChallengeResponse.getUser().getFirstName(), "Admin");
        softAssert.assertEquals(createChallengeResponse.getUser().getId(), 1);
        softAssert.assertAll();
    }

    @Test
    public void failGetTest() {
        ChallengeClient client = new ChallengeClient(authentication.getToken());
        Response response = client.get(3780);
        assertEquals(response.statusCode(), 404);

        ErrorResponse errorResponse = response.as(ErrorResponse.class);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(errorResponse.getStatus(), 404);
        softAssert.assertEquals(errorResponse.getMessage(), "Challenge not found by id: 3780");
        softAssert.assertAll();

    }

    @DataProvider(name = "addChallengeData")
    public Object[][] addChallengeData() {
        return new Object[][]{
                {"эЭъЪыЫёЁ", "эЭъЪыЫёЁ", "эЭъЪыЫёЁэЭъЪыЫёЁэЭъЪыЫёЁэЭъЪыЫёЁэЭъЪыЫёЁ",
                        new String[]{"name Помилка. Текст містить недопустимі символи",
                                "description Помилка. Текст містить недопустимі символи",
                                "title Помилка. Текст містить недопустимі символи"}},
                {"nam", "tit", "des", new String[]{"description must contain a maximum of 25000 letters",
                        "title must contain a minimum of 5 and a maximum of 100 letters",
                        "name  must contain a minimum of 5 and a maximum of 30 letters"}},
                {"Lorem ipsum dolor sit amet, consect", "Lorem ipsum dolor sit amet, consect",
                        new String(new char[350]).replace("\0", "Lorem ipsum "),
                        new String[]{"name  must contain a minimum of 5 and a maximum of 30 letters"}}
        };
    }

    @Issue("TUA-430")
    @Description("Verify that user is not able to create Challenge using invalid values")
    @Test(dataProvider = "addChallengeData")
    public void unSuccessPostTest(String name, String title, String description, String[] expectedMessages) {
        ChallengeClient challengeClient = new ChallengeClient(authentication.getToken());
        CreateChallengeRequest createChallengeRequest = CreateChallengeRequest
                .builder()
                .name(name)
                .title(title)
                .description(description)
                .picture("/upload/test/test.png")
                .sortNumber(1)
                .build();

        Response postResponse = challengeClient.post(createChallengeRequest);
        assertEquals(postResponse.statusCode(), 400,
                "Incorrect response status code");

        ErrorResponse errorResponse = postResponse.as(ErrorResponse.class);
        SoftAssert softly = new SoftAssert();

        softly.assertEquals(errorResponse.getStatus(), 400,
                "Unexpected response status code");
        for (String expectedMessage : expectedMessages) {
            softly.assertTrue(errorResponse.getMessage().contains(expectedMessage), "Message should be correct "
                    + errorResponse.getMessage() + " / " + expectedMessage);
        }
        softly.assertAll();
    }

}
