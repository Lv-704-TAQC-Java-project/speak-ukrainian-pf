package com.ita.edu.speakua.api.challenge;

import com.ita.edu.speakua.api.ApiBaseTestRunner;
import com.ita.edu.speakua.api.clients.Authentication;
import com.ita.edu.speakua.api.clients.ChallengeClient;
import com.ita.edu.speakua.api.models.ErrorResponse;
import com.ita.edu.speakua.api.models.challenge.ReadChallengeResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.asynchttpclient.Request;
import org.testng.annotations.BeforeClass;
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

    @Test
    public void unSuccessPostTest() {
        ChallengeClient client = new ChallengeClient(authentication.getToken());
        RequestSpecification request = client.input("nam","tit","des");
        assertEquals(request., 400);

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

}
