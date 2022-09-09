package com.ita.edu.speakua.api.models.login;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SingInRequest {
    private String email;
    private String password;
}
