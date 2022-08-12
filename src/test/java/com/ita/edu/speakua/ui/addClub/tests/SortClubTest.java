package com.ita.edu.speakua.ui.addClub.tests;

import com.ita.edu.speakua.ui.HomePage;
import com.ita.edu.speakua.ui.clubs.ClubsPage;
import com.ita.edu.speakua.ui.runners.BaseTestRunner;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SortClubTest extends BaseTestRunner {
    @Test
    public void blockCardViewIsDisplayed() {
        new HomePage(driver)
                .clickAdvancedSearchButton()
                .getSortClubComponent()
                .blockViewButtonClick();

        boolean isDisplayed = new ClubsPage(driver).getBlockCardContainer().isDisplayed();
        Assert.assertTrue(isDisplayed, "Clubs are not displayed in block type view");
    }

    @Test
    @Description("Verify that cards are displayed in a list view")
    public void listCardViewIsDisplayed() {
        new HomePage(driver)
                .clickAdvancedSearchButton()
                .getSortClubComponent()
                .listViewButtonClick();

        boolean isDisplayed = new ClubsPage(driver).getWideCardContainer().isDisplayed();
        Assert.assertTrue(isDisplayed, "Clubs are not displayed in list type view");
    }

}
