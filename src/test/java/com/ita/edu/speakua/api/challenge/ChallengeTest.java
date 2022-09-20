package com.ita.edu.speakua.api.challenge;

import com.ita.edu.speakua.api.ApiBaseTestRunner;
import com.ita.edu.speakua.api.clients.Authentication;
import com.ita.edu.speakua.api.clients.ChallengeClient;
import com.ita.edu.speakua.api.models.ErrorResponse;
import com.ita.edu.speakua.api.models.challenge.CreateChallengeRequest;
import com.ita.edu.speakua.api.models.challenge.ReadChallengeResponse;
import com.ita.edu.speakua.utils.jdbc.entity.ChallengeEntity;
import com.ita.edu.speakua.utils.jdbc.entity.TaskEntity;
import com.ita.edu.speakua.utils.jdbc.services.ChallengeService;
import com.ita.edu.speakua.utils.jdbc.services.TaskService;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.restassured.response.Response;
import org.checkerframework.checker.units.qual.C;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

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

        ReadChallengeResponse readChallengeResponse = response.as(ReadChallengeResponse.class);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(readChallengeResponse.getId(), 378);
        softAssert.assertEquals(readChallengeResponse.getName(), "MzFGwuRN");
        softAssert.assertEquals(readChallengeResponse.getSortNumber(), 1897237667);
        softAssert.assertEquals(readChallengeResponse.getTasks().size(), 0);
        softAssert.assertEquals(readChallengeResponse.getUser().getFirstName(), "Admin");
        softAssert.assertEquals(readChallengeResponse.getUser().getId(), 1);
        softAssert.assertAll();
    }

    @Issue("TUA-437")
    @Description("Verify API returns same challenge as database")
    @Link("https://jira.softserve.academy/browse/TUA-437")
    @Test
    public void verifyApiGetChallengeEqualsDatabaseChallenge() {
        long databaseFirstChallengeId = new ChallengeService().getAllChallenges().get(0).getId();
        ChallengeEntity databaseChallenge = new ChallengeService().getChallengeById(databaseFirstChallengeId);
        List<TaskEntity> firstChallengeTasks = new TaskService().getTasks(databaseFirstChallengeId);
        ChallengeClient client = new ChallengeClient(authentication.getToken());

        Response response = client.get(databaseFirstChallengeId);
        assertEquals(response.statusCode(), 200);

        ReadChallengeResponse readChallengeResponse = response.as(ReadChallengeResponse.class);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(readChallengeResponse.getId(), databaseFirstChallengeId);
        softAssert.assertEquals(readChallengeResponse.getName(), databaseChallenge.getName());
        softAssert.assertEquals(readChallengeResponse.getTitle(), databaseChallenge.getTitle());
        softAssert.assertEquals(readChallengeResponse.getDescription(), databaseChallenge.getDescription());
        softAssert.assertEquals(readChallengeResponse.getPicture(), databaseChallenge.getPicture());
        softAssert.assertEquals(readChallengeResponse.getSortNumber(), databaseChallenge.getSortNumber());
        softAssert.assertEquals(readChallengeResponse.getIsActive(), databaseChallenge.getIsActive());
        softAssert.assertEquals(readChallengeResponse.getTasks().size(), firstChallengeTasks.size());
        softAssert.assertEquals(readChallengeResponse.getUser() != null ? readChallengeResponse.getUser().getId() : 0, databaseChallenge.getUserId());
        softAssert.assertEquals(readChallengeResponse.getRegistrationLink(), databaseChallenge.getRegistrationLink());

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
    @Link("https://jira.softserve.academy/browse/TUA-430")
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

    @Issue("TUA-436")
    @Link("https://jira.softserve.academy/browse/TUA-436")
    @Description("Verify that user is not able to create Challenge using invalid values")
    @Test
    public void unSuccessDeleteTest() {
        authentication = new Authentication("ulpkzrapmhkpzaqcve@sdvgeft.com", "11111111");
        ChallengeClient challengeClient = new ChallengeClient(authentication.getToken());
        Response getResponse = challengeClient.get(388);
        assertEquals(getResponse.statusCode(), 200);

        Response deleteResponse = challengeClient.delete(388);
        assertEquals(deleteResponse.statusCode(), 401);

        ErrorResponse challengeFailResponse = deleteResponse.as(ErrorResponse.class);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(challengeFailResponse.getStatus(), 401);
        softAssert.assertEquals(challengeFailResponse.getMessage(), "You have no necessary permissions (role)");
        softAssert.assertEquals(getResponse.statusCode(), 200);
        softAssert.assertAll();
    }

    @Issue("TUA-429")
    @Link("https://jira.softserve.academy/browse/TUA-429")
    @Description("Verify that user can create Challenge using valid values")
    @Test
    public void successPostChallengeTest() {
        ChallengeClient challengeClient = new ChallengeClient(authentication.getToken());
        String name = "New Challenge";
        String title = "New title";
        String description = "Lorem ipsum dolor sit amet, consectetuer adipiscin";
        String picturePath = "/upload/test/test.png";
        long maxId = new ChallengeService().getMaxChallengeId() + 1;

        CreateChallengeRequest createChallengeRequest = CreateChallengeRequest
                .builder()
                .name(name)
                .title(title)
                .description(description)
                .picture(picturePath)
                .sortNumber((int) maxId)
                .build();

        Response response = challengeClient.postChallenge(createChallengeRequest);
        System.out.println(response.asPrettyString());
        assertEquals(response.statusCode(), 200, "Challenge should be created");

        ReadChallengeResponse readChallengeResponse = response.as(ReadChallengeResponse.class);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(readChallengeResponse.getName(), name);
        softAssert.assertEquals(readChallengeResponse.getTitle(), title);
        softAssert.assertEquals(readChallengeResponse.getDescription(), description);
        softAssert.assertEquals(readChallengeResponse.getPicture(), picturePath);
        softAssert.assertEquals(readChallengeResponse.getSortNumber(), maxId);
        softAssert.assertAll();
    }
}
