package com.ita.edu.speakua.api.models.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@JsonInclude
@Builder
public class EditUserRequest {
    @JsonProperty
    private String email;
    @JsonProperty
    private String firstName;
    @JsonProperty
    private String lastName;
    @JsonProperty
    private String phone;
    @JsonProperty
    private String urlLogo;
    @JsonProperty
    private boolean status;
    @JsonProperty
    private String roleName;
}