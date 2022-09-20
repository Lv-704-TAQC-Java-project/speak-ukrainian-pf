package com.ita.edu.speakua.api.models.task;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@JsonInclude
@Builder
public class EditTaskRequest {
    @JsonProperty
    private String name;
    @JsonProperty
    private String headerText;
    @JsonProperty
    private String description;
    @JsonProperty
    private String picture;
    @JsonProperty
    private String startDate;
    @JsonProperty
    private long challengeId;
}