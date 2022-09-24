package com.ita.edu.speakua.api.challenge;

import com.ita.edu.speakua.api.ApiBaseTestRunner;
import com.ita.edu.speakua.api.clients.Authentication;
import com.ita.edu.speakua.api.clients.ChallengeClient;
import com.ita.edu.speakua.api.models.ErrorResponse;
import com.ita.edu.speakua.api.models.challenge.CreateChallengeRequest;
import com.ita.edu.speakua.api.models.challenge.ReadChallengeResponse;
import com.ita.edu.speakua.api.models.challenge.Task;
import com.ita.edu.speakua.utils.jdbc.entity.ChallengeEntity;
import com.ita.edu.speakua.utils.jdbc.entity.TaskEntity;
import com.ita.edu.speakua.utils.jdbc.services.ChallengeService;
import com.ita.edu.speakua.utils.jdbc.services.TaskService;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;

public class ChallengeTest extends ApiBaseTestRunner {

    @DataProvider(name = "credentialsData")
    public Object[][] credentialsData() {
        return new Object[][]{
                {"", ""},
                {properties.getAdminEmail(), properties.getAdminPassword()},
                {properties.getManagerEmail(), properties.getManagerPassword()},
                {properties.getUserEmail(), properties.getUserPassword()}
        };
    }

    @Issue("TUA-437")
    @Description("Verify that user with any role can view info about specific Challenge")
    @Link("https://jira.softserve.academy/browse/TUA-437")
    @Test(dataProvider = "credentialsData")
    public void verifyUserWithAnyRoleCanViewChallenge(String email, String password) {
        // Can be any existing challenge id from database
        long challengeId = new ChallengeService().getAllChallenges().get(0).getId();

        ChallengeClient challengeClient = new ChallengeClient(email.isEmpty() && password.isEmpty()
                ? ""
                : new Authentication(email, password).getToken());

        Response apiResponse = challengeClient.get(challengeId);
        assertEquals(apiResponse.statusCode(), 200,
                "Api response should have status code 200 when requested challenge with id " + challengeId);

        ReadChallengeResponse readChallengeResponse = apiResponse.as(ReadChallengeResponse.class);

        SoftAssert softly = new SoftAssert();
        ChallengeEntity databaseChallenge = new ChallengeService().getChallengeById(challengeId);
        softly.assertEquals(readChallengeResponse.getId(), challengeId,
                "Database and API response should have same id");
        softly.assertEquals(readChallengeResponse.getName(), databaseChallenge.getName(),
                "Database and API response should have same name");
        softly.assertEquals(readChallengeResponse.getTitle(), databaseChallenge.getTitle(),
                "Database and API response should have same title");
        softly.assertEquals(readChallengeResponse.getDescription(), databaseChallenge.getDescription(),
                "Database and API response should have same description");
        softly.assertEquals(readChallengeResponse.getPicture(), databaseChallenge.getPicture(),
                "Database and API response should have same picture url");
        softly.assertEquals(readChallengeResponse.getSortNumber(), databaseChallenge.getSortNumber(),
                "Database and API response should have same sort number");
        softly.assertEquals(readChallengeResponse.getIsActive(), databaseChallenge.getIsActive(),
                "Database and API response should have same activity status");
        softly.assertEquals(readChallengeResponse.getUser() != null ? readChallengeResponse.getUser().getId() : 0,
                databaseChallenge.getUserId(),
                "Database and API response should have same challenge user id");
        softly.assertEquals(readChallengeResponse.getRegistrationLink(), databaseChallenge.getRegistrationLink(),
                "Database and API response should have same registration link");

        ArrayList<Task> apiFirstChallengeTasks = readChallengeResponse.getTasks();
        List<TaskEntity> databaseFirstChallengeTasks = new TaskService().getTasks(challengeId);
        softly.assertEquals(apiFirstChallengeTasks.size(), databaseFirstChallengeTasks.size(),
                "Database and API response should have tasks list of equal size");

        apiFirstChallengeTasks.forEach(apiResponseTask -> {
            AtomicBoolean isApiAndDatabaseResponseContainTaskWithSameId = new AtomicBoolean(false);
            databaseFirstChallengeTasks.forEach(databaseTask -> {
                long taskId = apiResponseTask.getId();
                if (taskId == databaseTask.getId()) {
                    isApiAndDatabaseResponseContainTaskWithSameId.set(true);
                    softly.assertEquals(apiResponseTask.getName(), databaseTask.getName(),
                            "Database and API response should have equal name for Task with id" + taskId);
                    softly.assertEquals(apiResponseTask.getHeaderText(), databaseTask.getHeaderText(),
                            "Database and API response should have equal header text for Task with id" + taskId);
                    softly.assertEquals(apiResponseTask.getPicture(), databaseTask.getPicture(),
                            "Database and API response should have equal picture url for Task with id" + taskId);
                    softly.assertEquals(apiResponseTask.getStartDate(), Arrays
                                    .stream(databaseTask.getStartDate().split("-"))
                                    .map(Integer::parseInt)
                                    .collect(Collectors.toList()),
                            "Database and API response should have equal start date for Task with id" + taskId);
                }
            });
            softly.assertTrue(isApiAndDatabaseResponseContainTaskWithSameId.get(),
                    "Database and API response should have task with same id");
        });
        softly.assertAll();
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
        Authentication authentication = new Authentication(properties.getAdminEmail(), properties.getAdminPassword());

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
        Authentication authentication = new Authentication("ulpkzrapmhkpzaqcve@sdvgeft.com", "11111111");
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
        Authentication authentication = new Authentication(properties.getAdminEmail(), properties.getAdminPassword());

        ChallengeClient challengeClient = new ChallengeClient(authentication.getToken());
        String name = "New Challenge";
        String title = "New title";
        String description = "Lorem ipsum dolor sit amet, consectetuer adipiscin";
        String picturePath = "/upload/test/test.png";
        long maxSortNumber = new ChallengeService().getMaxChallengeSortNumber() + 1;

        CreateChallengeRequest createChallengeRequest = CreateChallengeRequest
                .builder()
                .name(name)
                .title(title)
                .description(description)
                .picture(picturePath)
                .sortNumber((int) maxSortNumber)
                .build();

        Response response = challengeClient.postChallenge(createChallengeRequest);

        assertEquals(response.statusCode(), 200, "Challenge should be created");

        ReadChallengeResponse readChallengeResponse = response.as(ReadChallengeResponse.class);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(readChallengeResponse.getName(), name,
                "Name should be correct");
        softAssert.assertEquals(readChallengeResponse.getTitle(), title,
                "Title should be correct");
        softAssert.assertEquals(readChallengeResponse.getDescription(), description,
                "Description should be correct");
        softAssert.assertEquals(readChallengeResponse.getPicture(), picturePath,
                "Picture should be correct");
        softAssert.assertEquals(readChallengeResponse.getSortNumber(), maxSortNumber,
                "Sort number should be correct");
        softAssert.assertAll();
    }

    @DataProvider(name = "errorMessagesForChallengeFields")
    public Object[][] errorMessagesForChallengeFields() {
        List<String> errors = Arrays.asList("name  must contain a minimum of 5 and a maximum of 30 letters",
                "name must not be blank",
                "title must not be blank",
                "title must contain a minimum of 5 and a maximum of 100 letters",
                "description must contain a maximum of 25000 letters",
                "description must not be blank",
                "picture Incorrect file path. It must be like /upload/*/*.png",
                "picture must not be blank",
                "sortNumber must not be null");
        return new Object[][]{
                {null, Arrays.asList("name must not be blank",
                        "title must not be blank",
                        "description must not be blank",
                        "picture must not be blank",
                        "sortNumber must not be null")},
                {"", errors},
                {" ", errors}
        };
    }

    @Issue("TUA-431")
    @Link("https://jira.softserve.academy/browse/TUA-431")
    @Description("Verify that user can  not create Challenge using invalid data")
    @Test(dataProvider = "errorMessagesForChallengeFields")
    public void unsuccessfulPostChallengeTest(String incorrectValue, List<String> expectedErrorMessages) {
        Authentication authentication = new Authentication(properties.getAdminEmail(), properties.getAdminPassword());

        ChallengeClient challengeClient = new ChallengeClient(authentication.getToken());

        CreateChallengeRequest createChallengeRequestWithNullValues = CreateChallengeRequest
                .builder()
                .name(incorrectValue)
                .title(incorrectValue)
                .description(incorrectValue)
                .picture(incorrectValue)
                .sortNumber(null)
                .build();

        Response response = challengeClient.postChallenge(createChallengeRequestWithNullValues);

        assertEquals(response.statusCode(), 400,
                "Challenge should not be created");

        ErrorResponse errorResponse = response.as(ErrorResponse.class);

        SoftAssert softAssert = new SoftAssert();

        expectedErrorMessages.forEach(errorMessage ->
                softAssert.assertTrue(errorResponse.getMessage().contains(errorMessage),
                        "Error message should appear: " + errorMessage));
        softAssert.assertAll();
    }
}
