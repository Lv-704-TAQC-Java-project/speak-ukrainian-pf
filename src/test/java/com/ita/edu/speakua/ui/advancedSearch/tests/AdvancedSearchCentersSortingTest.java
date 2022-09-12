package com.ita.edu.speakua.ui.advancedSearch.tests;

import com.ita.edu.speakua.ui.clubs.ClubsPage;
import com.ita.edu.speakua.ui.runners.SameWindowTestRunner;
import com.ita.edu.speakua.utils.jdbc.services.CenterService;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AdvancedSearchCentersSortingTest extends SameWindowTestRunner {

    @Issue("TUA-440")
    @Description("Verify user can sort Centers alphabetically")
    @Test
    public void verifyCentersAlphabeticSortFunctionality() {
        ClubsPage clubsPage = getHomePage().openAdvancedSearch();

        clubsPage
                .getAdvancedSearchPanelComponent()
                .selectCenterFilter()
                .getSortClubComponent()
                .sortByAlphabet();

        long cardsQuantity = 6;
        CenterService centerService = new CenterService();
        String[] databaseCenterNamesAscOrder = centerService.getCenterNames("Київ", "name", false, cardsQuantity);
        String[] actualCenterNamesAscOrder = clubsPage.getCenterNames();

        clubsPage
                .getSortClubComponent()
                .orderByDesc();

        String[] databaseCenterNamesDescOrder = centerService.getCenterNames("Київ", "name",true, cardsQuantity);
        String[] actualCenterNamesDescOrder = clubsPage.getCenterNames();

        SoftAssert softAssert = new SoftAssert();
        for (int i = 0; i < cardsQuantity; i++) {
            softAssert.assertEquals(actualCenterNamesAscOrder[i],
                    databaseCenterNamesAscOrder[i],
                    "Incorrect cards sequence after sorting by alphabet in ascending order.");
            softAssert.assertEquals(actualCenterNamesDescOrder[i],
                    databaseCenterNamesDescOrder[i],
                    "Incorrect cards sequence after sorting by alphabet in descending order.");
        }
        softAssert.assertAll();
    }
}