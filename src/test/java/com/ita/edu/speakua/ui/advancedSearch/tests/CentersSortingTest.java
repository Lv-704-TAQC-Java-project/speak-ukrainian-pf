package com.ita.edu.speakua.ui.advancedSearch.tests;

import com.ita.edu.speakua.ui.clubs.ClubsPage;
import com.ita.edu.speakua.ui.runners.SameWindowTestRunner;
import com.ita.edu.speakua.ui.utils.jdbc.services.CenterService;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CentersSortingTest extends SameWindowTestRunner {

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
        String[] actualCenterNamesAscOrder = clubsPage.getCenterNames();
        String[] expectedCenterNamesAscOrder = centerService.getCenterNames("Київ", "name", false, cardsQuantity);

        clubsPage
                .getSortClubComponent()
                .orderByDesc();

        String[] actualCenterNamesDescOrder = clubsPage.getCenterNames();
        String[] expectedCenterNamesDescOrder = centerService.getCenterNames("Київ", "name",true, cardsQuantity);

        SoftAssert softAssert = new SoftAssert();
        for (int i = 0; i < cardsQuantity; i++) {
            softAssert.assertEquals(actualCenterNamesAscOrder[i],
                    expectedCenterNamesAscOrder[i],
                    "Incorrect cards sequence after sorting by alphabet in ascending order.");
            softAssert.assertEquals(actualCenterNamesDescOrder[i],
                    expectedCenterNamesDescOrder[i],
                    "Incorrect cards sequence after sorting by alphabet in descending order.");
        }
        softAssert.assertAll();
    }
}