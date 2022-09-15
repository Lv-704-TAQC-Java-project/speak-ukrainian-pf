package com.ita.edu.speakua.api.clients;

import com.ita.edu.speakua.api.models.club.request.CreateClubRequest;
import io.restassured.response.Response;

public class ClubClient extends BaseClient{

    private final String path = "/club";
    private final String authentication;


    public ClubClient(String authentication) {
        super();
        this.authentication = authentication;
    }

    public Response post(CreateClubRequest createClubRequest) {
        return prepareRequest()
                .header("Authorization", "Bearer " + this.authentication)
                .body(createClubRequest.json())
                .when()
                .post(String.format("%s%s", baseUrl, path));
    }

    public Response postParseJson(String filePath, String clubName) {
        return prepareRequest()
                .header("Authorization", "Bearer " + this.authentication)
                .body(CreateClubRequest.parseJson(filePath, clubName))
                .when()
                .post(String.format("%s%s", baseUrl, path));
    }
}
