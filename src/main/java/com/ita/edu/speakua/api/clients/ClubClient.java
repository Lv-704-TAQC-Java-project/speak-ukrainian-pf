package com.ita.edu.speakua.api.clients;

import io.restassured.response.Response;

public class ClubClient extends BaseClient{

    private final String path = "/club";
    private final String authentication;


    public ClubClient(String authentication) {
        super();
        this.authentication = authentication;
    }

    public Response post() {
        return prepareRequest()
                .header("Authorization", "Bearer " + this.authentication)
                .when()
                .post(String.format("%s%s", baseUrl, path));
    }
}
