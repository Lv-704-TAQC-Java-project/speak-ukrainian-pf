package com.ita.edu.speakua.api.clients;

import com.ita.edu.speakua.api.models.user.EditUserRequest;
import io.restassured.response.Response;

public class UserClient extends BaseClient {
    private final String path = "/user";
    private final String token;

    public UserClient(String token) {
        super();
        this.token = token;
    }

    public Response put(long id, EditUserRequest editUserRequest) {
        return prepareRequest()
                .header("Authorization", "Bearer " + this.token)
                .body(editUserRequest)
                .when()
                .put(String.format("%s%s/%s", baseUrl, path, id));
    }

    public Response get(long id) {
        return prepareRequest()
                .header("Authorization", "Bearer " + this.token)
                .when()
                .get(String.format("%s%s/%s", baseUrl, path, id));
    }
}