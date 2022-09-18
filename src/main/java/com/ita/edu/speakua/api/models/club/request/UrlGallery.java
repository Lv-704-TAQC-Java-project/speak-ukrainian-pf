package com.ita.edu.speakua.api.models.club.request;

import lombok.Data;

@Data
public class UrlGallery {
    private String urlGallery;

    @Override
    public String toString() {
        return "\"urlGallery\": "
                + "\"" + urlGallery + "\"";
    }
}
