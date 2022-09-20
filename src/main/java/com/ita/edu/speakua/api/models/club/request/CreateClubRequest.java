package com.ita.edu.speakua.api.models.club.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;

import java.util.ArrayList;

@Builder
public class CreateClubRequest {
    @JsonProperty("categoriesName")
    private ArrayList<String> categoriesName;
    @JsonProperty("locations")
    private ArrayList<Location> locations;
    @JsonProperty("description")
    private String description;
    @JsonProperty("name")
    private String name;
    @JsonProperty("ageFrom")
    private int ageFrom;
    @JsonProperty("ageTo")
    private int ageTo;
    @JsonProperty("urlBackground")
    private String urlBackground;
    @JsonProperty("urlLogo")
    private String urlLogo;
    @JsonProperty("urlGallery")
    private ArrayList<UrlGallery> urlGallery;
    @JsonProperty("isOnline")
    private Boolean isOnline;
    @JsonProperty("contacts")
    private String contacts;
    @JsonProperty("isApproved")
    private Boolean isApproved;
    @JsonProperty("userId")
    private int userId;
    @JsonProperty("centerId")
    private int centerId;
    @JsonProperty("clubExternalId")
    private int clubExternalId;
    @JsonProperty("centerExternalId")
    private int centerExternalId;

    @Override
    public String toString() {
        ObjectMapper objectMapper = new ObjectMapper();
        String object = "";
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return object;
    }
}