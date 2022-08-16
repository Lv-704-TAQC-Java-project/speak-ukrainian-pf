package com.ita.edu.speakua.ui.baseSearch.tests;

import com.ita.edu.speakua.ui.HomePage;
import com.ita.edu.speakua.ui.clubs.ClubsPage;
import com.ita.edu.speakua.ui.runners.BaseTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

public class BaseSearchTest extends BaseTestRunner {

    @Issue("TUA-226")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that user can perform basic search by name of a club")
    @Test
    public void verifyPossibilityOfSearchByClubName() {
        ClubsPage clubsPage = new HomePage(driver)
                .fillInSearch("smth");
    }
}
