package com.ita.edu.speakua.ui.utils.jdbc.dto;

import com.ita.edu.speakua.ui.utils.jdbc.entity.ClubEntity;

public class ClubJoinCenterDTO {
    private long id;
    private int age_from;
    private int age_to;
    private long center_external_id;
    private long club_external_id;
    private String contacts;
    private String description;
    private boolean is_approved;
    private boolean is_online;
    private String name;
    private double rating;
    private String url_background;
    private String url_logo;
    private String urlWeb;
    private String work_time;
    private CenterDTO center;
    private long user_id;
    private int feedback_count;

    public void setClub(ClubEntity club) {
        //        ToDo
    }
}
