package com.ita.edu.speakua.ui;

import com.ita.edu.speakua.ui.utils.jdbc.dao.CenterDAO;
import com.ita.edu.speakua.ui.utils.jdbc.dao.CityDAO;
import com.ita.edu.speakua.ui.utils.jdbc.dao.ClubDAO;
import com.ita.edu.speakua.ui.utils.jdbc.dao.TaskDAO;
import com.ita.edu.speakua.ui.utils.jdbc.entity.*;
import com.ita.edu.speakua.ui.utils.jdbc.services.ClubService;
import org.testng.annotations.Test;

import java.util.List;

public class temp {
    ClubService clubService = new ClubService();
    @Test
    public void jdbs() {
        CityDAO cityDAO = new CityDAO();
        List<CityEntity> cities = cityDAO.selectAll();
        System.out.println(cities);
        CityEntity city = cityDAO.selectById(3);
        System.out.println(city);

        System.out.println("===============================================");

        CenterDAO centerDAO = new CenterDAO();
        List<CenterEntity> centers = centerDAO.selectAll();
        CenterEntity center = centerDAO.selectById(1);
//        List<CenterNameEntity> firstSixCentersByNameAsc = centerDAO.firstSixNamesAsc();
//        List<CenterNameEntity> firstSixCentersByNameDesc = centerDAO.firstSixNamesDesc();
        System.out.println(center);
//        System.out.println(firstSixCentersByNameAsc);
//        System.out.println(firstSixCentersByNameDesc);
//        System.out.println(centers);

//        TaskDAO taskDAO = new TaskDAO();
//        List<TaskEntity> tasks = taskDAO.selectLikeName("Maksym test");
//        System.out.println(tasks);
    }

    @Test
    public void jdbcClub() {

        ClubEntity club = clubService.selectById(1);
        System.out.println(club);
        System.out.println("===============================================");
//        List<ClubEntity> sixNamesAsc = clubDAO.parseSixNamesAsc();
//        System.out.println(sixNamesAsc);

    }
}