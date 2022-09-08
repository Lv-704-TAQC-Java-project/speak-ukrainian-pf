package com.ita.edu.speakua.api.clients;

import com.ita.edu.speakua.api.models.login.SingInRequest;
import com.ita.edu.speakua.api.models.login.SingInResponse;
import io.restassured.response.Response;

public class Authentication {
    private SingInResponse response;

    public Authentication(String email, String password) {
        SingInClient client = new SingInClient();
        SingInRequest cred = new SingInRequest(email, password);
        this.response = client.successSignInRequest(cred).as(SingInResponse.class);
    }
    public String getToken() {
        return this.response.getAccessToken();
    }
}
