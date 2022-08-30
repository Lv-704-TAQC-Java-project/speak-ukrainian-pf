package com.ita.edu.speakua.ui.advancedSearch.tests;

import com.ita.edu.speakua.ui.HomePage;
import com.ita.edu.speakua.ui.clubs.ClubsPage;
import com.ita.edu.speakua.ui.clubs.card.components.CenterComponent;
import com.ita.edu.speakua.ui.runners.SameWindowTestRunner;
import com.ita.edu.speakua.ui.utils.jdbc.services.CenterService;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class CentersOrderByAlphabetTest extends SameWindowTestRunner {

    @Issue("TUA-440")
    @Description("Verify that the user can sort Centers in alphabetical order")
    @Test
    public void verifyCentersOrderByAlphabetSortFunctionality() {
        new HomePage(driver)
                .openAdvancedSearch()
                .getAdvancedSearchPanelComponent()
                .selectCenterFilter()
                .getSortClubComponent()
                .sortByAlphabet();

        long cardsQuantity = 6;
        CenterService centerService = new CenterService();
        List<String> expectedCenterNamesAscOrder = centerService.getNamesInKyivOrderByNameAsc(cardsQuantity);

        ClubsPage clubsPage = new ClubsPage(driver);
        String[] actualCenterNamesAscOrder = clubsPage
                .getCenters()
                .stream()
                .map(CenterComponent::getTextCenterName)
                .toArray(String[]::new);

        clubsPage
                .getSortClubComponent()
                .orderByDesc();

        List<String> expectedCenterNamesDescOrder = centerService.getNamesInKyivOrderByNameDesc(cardsQuantity);

        String[] actualCenterNamesDescOrder = clubsPage
                .getCenters()
                .stream()
                .map(CenterComponent::getTextCenterName)
                .toArray(String[]::new);

        SoftAssert softAssert = new SoftAssert();
        for (int i = 0; i < cardsQuantity; i++) {
            softAssert.assertEquals(actualCenterNamesAscOrder[i],
                    expectedCenterNamesAscOrder.get(i).trim().replaceAll("\\s+", " "),
                    "Incorrect cards sequence after sorting by alphabet in ascending order.");
            softAssert.assertEquals(actualCenterNamesDescOrder[i],
                    expectedCenterNamesDescOrder.get(i).trim().replaceAll("\\s+", " "),
                    "Incorrect cards sequence after sorting by alphabet in descending order.");
        }
        softAssert.assertAll();
    }
}
