package com.ita.edu.speakua.api.models.challenge;

import lombok.Data;

@Data
public class Task{
    public int id;
    public String name;
    public String headerText;
    public String picture;
    public String startDate;
}