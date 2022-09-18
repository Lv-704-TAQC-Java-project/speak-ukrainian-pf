package com.ita.edu.speakua.api.clients;

import com.ita.edu.speakua.api.models.challenge.CreateChallengeRequest;
import io.restassured.response.Response;

public class ChallengeClient extends BaseClient {
    private final String path = "/challenge";
    private final String authentication;


    public ChallengeClient(String authentication) {
        super();
        this.authentication = authentication;
    }

    public Response get(long id) {
        return prepareRequest()
                .header("Authorization", "Bearer " + this.authentication)
                .when()
                .get(String.format("%s%s/%s", baseUrl, path, id));
    }

    public Response post(CreateChallengeRequest createChallengeRequest) {
        return prepareRequest()
                .body(createChallengeRequest.json())
                .when()
                .post(baseUrl + path);
    }

    public Response delete(int id) {
        return prepareRequest()
                .header("Authorization", "Bearer " + this.authentication)
                .when()
                .delete(String.format("%s%s/%s", baseUrl, path, id));
    }
}
