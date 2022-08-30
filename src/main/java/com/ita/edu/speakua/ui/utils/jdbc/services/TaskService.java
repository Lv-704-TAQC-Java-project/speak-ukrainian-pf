package com.ita.edu.speakua.ui.utils.jdbc.services;

import com.ita.edu.speakua.ui.utils.jdbc.dao.TaskDAO;
import com.ita.edu.speakua.ui.utils.jdbc.entity.TaskEntity;

import java.util.List;
import java.util.stream.Collectors;

public class TaskService {

    private final TaskDAO taskDAO;

    public TaskService() {
        taskDAO = new TaskDAO();
    }

    public List<TaskEntity> getAll() {
        return taskDAO.selectAll();
    }

    public List<String> getAllNameWhereNameLike(String name) {
        return taskDAO
                .selectWhereNameLike(name)
                .stream()
                .map(TaskEntity::getName)
                .collect(Collectors.toList());
    }

    public List<String> getAllNameWhere(String name) {
        return taskDAO
                .selectWhereName(name)
                .stream()
                .map(TaskEntity::getName)
                .collect(Collectors.toList());
    }
}