package com.ita.edu.speakua.utils.jdbc.services;

import com.ita.edu.speakua.utils.jdbc.dao.ChallengeDAO;
import com.ita.edu.speakua.utils.jdbc.entity.ChallengeEntity;

import java.util.List;

public class ChallengeService {
    private final ChallengeDAO centerDAO;

    public ChallengeService() {
        centerDAO = new ChallengeDAO();
    }

    public List<ChallengeEntity> getAllChallenges() {
        return centerDAO.selectAllChallenges();
    }

    public ChallengeEntity getChallengeById(long id) {
        return centerDAO.selectChallengeById(id);
    }
}