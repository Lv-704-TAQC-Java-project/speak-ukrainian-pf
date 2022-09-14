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
                {"nam", "tit", "des"},
                {"Lorem ipsum dolor sit amet, consect", "Lorem ipsum dolor sit amet, consect", "Lorem ipsum dolor " +
                        "sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet " +
                        "dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation " +
                        "ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum " +
                        "iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu " +
                        "feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent " +
                        "luptatum zzril delenit augue duis dolore te feugait nulla facilisi.Lorem ipsum dolor sit amet, " +
                        "consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna " +
                        "aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper " +
                        "suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in " +
                        "hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla " +
                        "facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril " +
                        "delenit augue duis dolore te feugait nulla facilisi.Lorem ipsum dolor sit amet, consectetuer " +
                        "adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat " +
                        "volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit " +
                        "lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit " +
                        "in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at " +
                        "vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit " +
                        "augue duis dolore te feugait nulla facilisi.Lorem ipsum dolor sit amet, consectetuer " +
                        "adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat " +
                        "volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit " +
                        "lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit " +
                        "in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero " +
                        "eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis d" +
                        "olore te feugait nulla facilisi.Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed " +
                        "diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim " +
                        "ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea " +
                        "commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie " +
                        "consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio " +
                        "dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi." +
                        "Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod " +
                        "tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis " +
                        "nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. " +
                        "Duis autem vel eu"},
                {"эЭъЪыЫёЁ", "эЭъЪыЫёЁ", "эЭъЪыЫёЁэЭъЪыЫёЁэЭъЪыЫёЁэЭъЪыЫёЁэЭъЪыЫёЁ"},
        };
    }

    @Issue("TUA-430")
    @Description("Verify that user is not able to create Challenge using invalid values")
    @Test(dataProvider = "addChallengeData")
    public void unSuccessPostTest(String name, String title, String description) {
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
        softly.assertEquals(errorResponse.getMessage(), "email is not valid",
                "Incorrect error message");
        softly.assertAll();
    }
}
