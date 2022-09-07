package com.ita.edu.speakua.api.clients;

import com.ita.edu.speakua.api.models.login.SingInRequest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class LoginClient extends BaseClient {
    private String accessToken;

    public LoginClient() {
        super(ContentType.JSON, "signin", property.getAPIBaseUrl());
        accessToken = "";
    }
    public Response signin(SingInRequest credentioals) {
        return prepareRequest()
                .body(credentioals)
                .when()
                .post(baseUrl+"/signin");
    }

}
