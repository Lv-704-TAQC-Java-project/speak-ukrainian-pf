package com.ita.edu.speakua.api.models.task;

import lombok.Data;

import java.util.ArrayList;

@Data
public class ReadTasksResponse {
    private ArrayList<Task> tasks;
}