package com.ita.edu.speakua.ui.utils.jdbc.services;

import com.ita.edu.speakua.ui.utils.jdbc.dao.ChallengeDAO;
import com.ita.edu.speakua.ui.utils.jdbc.entity.ChallengeEntity;

import java.util.List;

public class ChallengeService {
    private final ChallengeDAO centerDAO;

    public ChallengeService() {
        centerDAO = new ChallengeDAO();
    }

    public List<ChallengeEntity> getAll() {
        return centerDAO.selectAll();
    }

    public ChallengeEntity getById(long id) {
        return centerDAO.selectById(id);
    }
}