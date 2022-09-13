package com.ita.edu.speakua.api.models.club.response;

import lombok.Data;

import java.util.Date;

@Data
public class Feedback{
    private int id;
    private int rate;
    private Date date;
    private String text;
    private User user;
    private String club;
}
