package com.ita.edu.speakua.api.models.club.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public class UrlGallery {
    @JsonProperty("urlGallery")
    private String urlGallery;
}
