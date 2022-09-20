package com.ita.edu.speakua.api.models.task;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@JsonInclude
@Builder
public class CreateTaskRequest {
    @JsonProperty("name")
    private String name;
    @JsonProperty("headerText")
    private String headerText;
    @JsonProperty("description")
    private String description;
    @JsonProperty("picture")
    private String picture;
    @JsonProperty("startDate")
    private String startDate;
}