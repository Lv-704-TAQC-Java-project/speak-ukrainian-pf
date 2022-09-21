package com.ita.edu.speakua.api.clients;

import com.ita.edu.speakua.api.models.user.EditUserRequest;
import io.restassured.response.Response;

public class UserClient extends BaseClient {

    private final String path = "/user";

    private final String authentication;


    public UserClient(String authentication) {
        super();
        this.authentication = authentication;
    }

    public Response put(int id, EditUserRequest editUserRequest) {
        return prepareRequest()
                .header("Authorization", "Bearer " + this.authentication)
                .body(editUserRequest)
                .when()
                .put(String.format("%s%s/%s", baseUrl, path, id));
    }

    public Response get(int id) {
        return prepareRequest()
                .header("Authorization", "Bearer " + this.authentication)
                .when()
                .get(String.format("%s%s/%s", baseUrl, path, id));
    }
}