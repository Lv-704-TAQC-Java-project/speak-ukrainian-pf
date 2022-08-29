package com.ita.edu.speakua.ui.advancedSearch.tests;

import com.ita.edu.speakua.ui.HomePage;
import com.ita.edu.speakua.ui.clubs.ClubsPage;
import com.ita.edu.speakua.ui.clubs.card.components.CardComponent;
import com.ita.edu.speakua.ui.runners.SameWindowTestRunner;
import com.ita.edu.speakua.ui.utils.jdbc.dao.CenterDAO;
import com.ita.edu.speakua.ui.utils.jdbc.entity.CenterEntity;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;

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

        CenterDAO centerDAO = new CenterDAO();
        String[] expectedCenterNamesAscOrder = centerDAO
                .firstSixNamesAsc()
                .stream()
                .map(CenterEntity::getName)
                .toArray(String[]::new);
        System.out.println(Arrays.toString(expectedCenterNamesAscOrder));

        ClubsPage clubsPage = new ClubsPage(driver);
        String[] actualCenterNamesAscOrder = clubsPage
                .getCards()
                .stream()
                .map(CardComponent::getCardName)
                .toArray(String[]::new);
        System.out.println(Arrays.toString(actualCenterNamesAscOrder));

        clubsPage
                .getSortClubComponent()
                .orderByDesc();

        String[] expectedCenterNamesDescOrder = centerDAO
                .firstSixNamesDesc()
                .stream()
                .map(CenterEntity::getName)
                .toArray(String[]::new);
        System.out.println(Arrays.toString(expectedCenterNamesDescOrder));

        String[] actualCenterNamesDescOrder = clubsPage
                .getCards()
                .stream()
                .map(CardComponent::getCardName)
                .toArray(String[]::new);
        System.out.println(Arrays.toString(actualCenterNamesDescOrder));

        SoftAssert softAssert = new SoftAssert();
        for (int i = 0; i < expectedCenterNamesDescOrder.length; i++) {
            softAssert.assertEquals(actualCenterNamesAscOrder[i], expectedCenterNamesAscOrder[i],
                    "Incorrect cards sequence after sorting by alphabet in ascending order.");
            softAssert.assertEquals(actualCenterNamesDescOrder[i], expectedCenterNamesDescOrder[i],
                    "Incorrect cards sequence after sorting by alphabet in descending order.");
        }
        softAssert.assertAll();
    }
}
