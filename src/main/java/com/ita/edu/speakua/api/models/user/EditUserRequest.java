package com.ita.edu.speakua.api.models.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ita.edu.speakua.api.data.Role;
import lombok.Builder;

@JsonInclude
@Builder
public class EditUserRequest {
    @JsonProperty("id")
    private int id;
    @JsonProperty("email")
    private String email;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("urlLogo")
    private String urlLogo;
    @JsonProperty("status")
    private String status;
    @JsonProperty("roleName")
    private Role roleName;
}