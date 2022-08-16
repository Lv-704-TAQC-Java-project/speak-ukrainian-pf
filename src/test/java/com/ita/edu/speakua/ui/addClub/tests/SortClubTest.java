package com.ita.edu.speakua.ui.addClub.tests;

import com.ita.edu.speakua.ui.HomePage;
import com.ita.edu.speakua.ui.clubs.ClubsPage;
import com.ita.edu.speakua.ui.clubs.card.components.BlockCardComponent;
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

        boolean isBlockView = new ClubsPage(driver).isBlockCardContainerDisplayed();
        Assert.assertTrue(isBlockView, "Clubs are not displayed in block type view");
    }

    @Test
    public void listCardViewIsDisplayed() {
        HomePage homePage = new HomePage(driver);
        homePage.clickAdvancedSearchButton()
                .getSortClubComponent()
                .listViewButtonClick();

        boolean isListView = new ClubsPage(driver).isListCardContainerDisplayed();
        Assert.assertTrue(isListView, "Clubs are not displayed in list type view");
    }
}
