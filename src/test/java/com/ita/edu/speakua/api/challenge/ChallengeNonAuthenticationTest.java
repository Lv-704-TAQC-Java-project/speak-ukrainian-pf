package com.ita.edu.speakua.api.challenge;


import com.ita.edu.speakua.api.ApiBaseTestRunner;
import com.ita.edu.speakua.api.clients.Authentication;
import com.ita.edu.speakua.api.clients.ChallengeClient;
import com.ita.edu.speakua.api.models.challenge.ChallengeFailResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertEquals;

public class ChallengeNonAuthenticationTest extends ApiBaseTestRunner {
    private Authentication authentication;

    @BeforeClass
    public void beforeClass() {
        authentication = new Authentication("ulpkzrapmhkpzaqcve@sdvgeft.com", "11111111");
    }

    @Issue("TUA-436")
    @Description("Verify that user is not able to create Challenge using invalid values")
    @Test
    public void unSuccessDeleteTest() {
        ChallengeClient challengeClient = new ChallengeClient(authentication.getToken());
        Response getResponse = challengeClient.get(388);
        assertEquals(getResponse.statusCode(), 200);

        Response deleteResponse = challengeClient.delete(388);
        assertEquals(deleteResponse.statusCode(), 401);

        ChallengeFailResponse challengeFailResponse = deleteResponse.as(ChallengeFailResponse.class);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(challengeFailResponse.getStatus(), 401);
        softAssert.assertEquals(challengeFailResponse.getMessage(), "You have no necessary permissions (role)");
        softAssert.assertEquals(getResponse.statusCode(), 200);
        softAssert.assertAll();
    }
}
