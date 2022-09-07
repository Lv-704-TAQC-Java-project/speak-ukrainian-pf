package com.ita.edu.speakua.api.models.login;

import lombok.Data;

@Data
public class SingInResponse {
    private long id;
    private String email;
    private String accessToken;
}
