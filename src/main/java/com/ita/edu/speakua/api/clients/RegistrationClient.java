package com.ita.edu.speakua.api.clients;

import com.ita.edu.speakua.api.models.registration.SignUpRequest;
import io.restassured.response.Response;

public class RegistrationClient extends BaseClient {

    public Response signUp(SignUpRequest signUpRequest) {
        return prepareRequest()
                .body(signUpRequest.json())
                .when()
                .post(baseUrl + "/signup");
    }
}