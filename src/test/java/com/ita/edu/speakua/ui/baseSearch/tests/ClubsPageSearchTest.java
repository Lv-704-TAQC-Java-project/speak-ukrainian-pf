package com.ita.edu.speakua.ui.baseSearch.tests;

import com.ita.edu.speakua.ui.HomePage;
import com.ita.edu.speakua.ui.clubs.ClubsPage;
import com.ita.edu.speakua.ui.clubs.card.components.CardComponent;
import com.ita.edu.speakua.ui.runners.SameWindowTestRunner;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class ClubsPageSearchTest extends SameWindowTestRunner {
    private ClubsPage clubsPage;
    private int lastPageNumberBeforeSearch;

    @BeforeClass
    public void openClubsPageAdvancedSearch() {
        clubsPage = new HomePage(driver)
                .clickAdvancedSearchButton();

        lastPageNumberBeforeSearch = clubsPage.getPaginationComponent()
                .getLastPaginationPageNumber();
    }

    @DataProvider(name = "searchData")
    public static Object[][] searchData() {
        String errorMessage = "Ім'я повинно починатися і закінчуватися літерою";
        return new Object[][]{
                {"ю", 1},
                {"Ми поставили перед собою ціль створити мережу найк", 50},
                {"Ми поставили перед собою ціль створити мережу найкращих центрів раннього розвитку в Україні", 50},
        };
    }

    @Test(dataProvider = "searchData")
    public void verifySearchFunctionality(String query, int inputLength) {
        List<CardComponent> cardsBeforeSearch = clubsPage.getCards();
        clubsPage.fillInSearch(query);
        List<CardComponent> cardsAfterSearch = clubsPage.getCards();

        boolean areCardsDifferentAfterSearch = false;
        for (int i = 0; i < cardsAfterSearch.size(); i++) {
            if(cardsBeforeSearch.get(i).equals(cardsAfterSearch.get(i))) {
                areCardsDifferentAfterSearch = true;
            } else {
                areCardsDifferentAfterSearch = false;
                break;
            }
        }

        int lastPageNumberAfterSearch = clubsPage.getPaginationComponent()
                .getLastPaginationPageNumber();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(areCardsDifferentAfterSearch);
        softAssert.assertTrue(lastPageNumberBeforeSearch != lastPageNumberAfterSearch, "Number of pages decreased after search.");
        softAssert.assertEquals(clubsPage.getSearchInputLength(), inputLength);
        softAssert.assertAll();
    }
}