package com.ita.edu.speakua.ui.utils.jdbc.dto;

import com.ita.edu.speakua.ui.utils.jdbc.entity.CenterEntity;
import com.ita.edu.speakua.ui.utils.jdbc.entity.ClubEntity;

public class CenterDTO {
    private long id;
    private long centerExternalId;
    private String contacts;
    private String description;
    private String name;
    private String urlBackgroundPicture;
    private String urlLogo;
    private String urlWeb;
    private long userId;
    private long clubCount;
    private double rating;

    public void setCenter(CenterEntity center) {
//        ToDo
    }
}
