package com.ita.edu.speakua.ui.baseSearch.tests;

import com.ita.edu.speakua.ui.HomePage;
import com.ita.edu.speakua.ui.clubs.ClubsPage;
import com.ita.edu.speakua.ui.runners.BaseTestRunner;
import org.testng.annotations.Test;

public class BaseSearchTest extends BaseTestRunner {

    @Test
    public void verifyPossibilityOfSearchByClubName(){
        ClubsPage clubsPage = new HomePage(driver)
                .fillInSearch("smth");
    }
}
