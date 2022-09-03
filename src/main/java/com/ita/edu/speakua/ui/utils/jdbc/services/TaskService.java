package com.ita.edu.speakua.ui.utils.jdbc.services;

import com.ita.edu.speakua.ui.utils.jdbc.dao.ChallengeDAO;
import com.ita.edu.speakua.ui.utils.jdbc.dao.TaskDAO;
import com.ita.edu.speakua.ui.utils.jdbc.dto.TaskJoinChallengeDTO;
import com.ita.edu.speakua.ui.utils.jdbc.entity.TaskEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskService {

    private final TaskDAO taskDAO;
    private final ChallengeDAO challengeDAO;

    public TaskService() {
        taskDAO = new TaskDAO();
        challengeDAO = new ChallengeDAO();
    }

    public List<TaskEntity> getAll() {
        return taskDAO.selectAllTasks();
    }

    public List<String> getAllNameWhere(String name) {
        return taskDAO
                .selectTasks(name)
                .stream()
                .map(TaskEntity::getName)
                .collect(Collectors.toList());
    }

    public List<TaskEntity> getTasks(String name, String orderBy, boolean desc) {
        return taskDAO.selectTasks(name, orderBy, desc);
    }

    public long getTasksCount(String name) {
        return taskDAO.getTasksCount(name);
    }

    public List<TaskJoinChallengeDTO> getTasksJoinChallengeDTO(String name, String orderBy, boolean desc) {
        List<TaskJoinChallengeDTO> taskWithChallenge = new ArrayList<>();
        for (TaskEntity task: taskDAO.selectTasks(name, orderBy, desc)) {
            TaskJoinChallengeDTO taskWithChallengeDTO = new TaskJoinChallengeDTO(task);
            taskWithChallengeDTO.setChallenge(challengeDAO.selectChallengeById(task.getChallengeId()));
            taskWithChallenge.add(taskWithChallengeDTO);
        }
        return taskWithChallenge;
    }
}