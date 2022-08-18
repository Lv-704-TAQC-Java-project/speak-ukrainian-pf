package com.ita.edu.speakua.ui.addClub.tests;

import com.ita.edu.speakua.ui.HomePage;
import com.ita.edu.speakua.ui.clubs.ClubsPage;
import com.ita.edu.speakua.ui.clubs.card.components.BlockCardComponent;
import com.ita.edu.speakua.ui.runners.BaseTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SortClubTest extends BaseTestRunner {
    @Issue("TUA-513")
    @Description("Verify that the centers in the results of advanced search can be displayed as a block\n")
    @Test
    public void blockCardViewIsDisplayed() {
        new HomePage(driver)
                .openAdvancedSearch()
                .getSortClubComponent()
                .blockViewButtonClick();

        boolean isBlockView = new ClubsPage(driver).isBlockCardContainerDisplayed();
        Assert.assertTrue(isBlockView, "Clubs are not displayed in block type view");
    }

    @Issue("TUA-513")
    @Description("Verify that the centers in the results of advanced search can be displayed as a list\n")
    @Test
    public void listCardViewIsDisplayed() {
        HomePage homePage = new HomePage(driver);
        homePage.openAdvancedSearch()
                .getSortClubComponent()
                .listViewButtonClick();

        boolean isListView = new ClubsPage(driver).isListCardContainerDisplayed();
        Assert.assertTrue(isListView, "Clubs are not displayed in list type view");
    }
}
