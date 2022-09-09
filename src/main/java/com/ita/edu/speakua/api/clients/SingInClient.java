package com.ita.edu.speakua.api.clients;

import com.ita.edu.speakua.api.models.login.SingInRequest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class SingInClient extends BaseClient {
    private String accessToken;

    public SingInClient() {
        super();
        accessToken = "";
    }
    public Response successSignInRequest(SingInRequest credential) {
        return prepareRequest()
                .body(credential)
                .when()
                .post(baseUrl+"/signin");
    }

}
