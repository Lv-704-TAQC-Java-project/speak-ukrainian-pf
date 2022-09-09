package com.ita.edu.speakua.utils.jdbc.dto;

import com.ita.edu.speakua.utils.jdbc.entity.ChallengeEntity;
import com.ita.edu.speakua.utils.jdbc.entity.TaskEntity;
import lombok.Data;

@Data
public class TaskJoinChallengeDTO {
    private long id;
    private String description;
    private String name;
    private String picture;
    private String startDate;
    private ChallengeEntity challenge;
    private String headerText;

    public TaskJoinChallengeDTO(TaskEntity entity) {
        this.id = entity.getId();
        this.description = entity.getDescription();
        this.name = entity.getName();
        this.picture = entity.getPicture();
        this.startDate = entity.getStartDate();
        this.headerText = entity.getHeaderText();
    }
}