package com.ita.edu.speakua.api.clients;

import com.ita.edu.speakua.api.models.ErrorResponse;
import com.ita.edu.speakua.api.models.challenge.CreateChallengeRequest;
import com.ita.edu.speakua.api.models.challenge.CreateChallengeResponse;
import com.ita.edu.speakua.api.models.registration.SignUpRequest;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.asynchttpclient.Request;

public class ChallengeClient extends BaseClient {
    private final String path = "/challenge";
    private final String authentication;


    public ChallengeClient(String authentication) {
        super();
        this.authentication = authentication;
    }

    public Response get(int id) {
        return prepareRequest()
                .header("Authorization", "Bearer " + this.authentication)
                .when()
                .get(String.format("%s%s/%s", baseUrl, path, id));
    }

    public Response post(CreateChallengeRequest createChallengeRequest) {
        return prepareRequest()
                .body(createChallengeRequest.json())
                .when()
                .post(baseUrl + "/challenge");
    }
}
