package com.ita.edu.speakua.ui;

import com.ita.edu.speakua.ui.utils.jdbc.dao.CenterDAO;
import com.ita.edu.speakua.ui.utils.jdbc.dao.CityDAO;
import com.ita.edu.speakua.ui.utils.jdbc.entity.CenterEntity;
import com.ita.edu.speakua.ui.utils.jdbc.entity.CityEntity;
import org.testng.annotations.Test;

import java.util.List;

public class temp {
    @Test
    public void jdbs() {
        CityDAO cityDAO = new CityDAO();
        List<CityEntity> cities = cityDAO.selectAll();
        System.out.println(cities);
        CityEntity city = cityDAO.selectById(3);
        System.out.println(city);

        System.out.println("=====================================================================");

        CenterDAO centerDAO = new CenterDAO();
        List<CenterEntity> centers = centerDAO.selectAll();
        CenterEntity center = centerDAO.selectById(1);
        System.out.println(center);
        System.out.println(centers);
    }
}