package com.ita.edu.speakua.api.models.registration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@JsonInclude
@Builder
public class SignUpRequest {
    @JsonProperty
    public String firstName;
    @JsonProperty
    public String lastName;
    @JsonProperty
    public String email;
    @JsonProperty
    public String password;
    @JsonProperty
    public String phone;
    @JsonProperty
    public String roleName;
}