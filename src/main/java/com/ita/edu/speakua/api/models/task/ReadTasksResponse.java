package com.ita.edu.speakua.api.models.task;

import lombok.Data;

import java.util.Collection;

@Data
public class ReadTasksResponse {
    public Collection<Task> tasks;
}