package com.ita.edu.speakua.api.clients;

import com.ita.edu.speakua.api.models.challenge.CreateChallengeRequest;
import io.restassured.response.Response;

public class ChallengeClient extends BaseClient {
    private final String path = "/challenge";
    private final String token;


    public ChallengeClient(String token) {
        super();
        this.token = token;
    }

    public Response get(long id) {
        return prepareRequest()
                .header("Authorization", "Bearer " + this.token)
                .when()
                .get(String.format("%s%s/%s", baseUrl, path, id));
    }

    public Response post(CreateChallengeRequest createChallengeRequest) {
        return prepareRequest()
                .body(createChallengeRequest)
                .when()
                .post(baseUrl + path);
    }

    public Response postChallenge(CreateChallengeRequest createChallengeRequest) {
        return prepareRequest()
                .header("Authorization", "Bearer " + this.token)
                .body(createChallengeRequest)
                .when()
                .post(baseUrl + path);
    }

    public Response delete(int id) {
        return prepareRequest()
                .header("Authorization", "Bearer " + this.token)
                .when()
                .delete(String.format("%s%s/%s", baseUrl, path, id));
    }
}
