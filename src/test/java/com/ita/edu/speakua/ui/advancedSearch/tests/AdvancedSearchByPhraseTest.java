package com.ita.edu.speakua.ui.advancedSearch.tests;

import com.ita.edu.speakua.ui.HomePage;
import com.ita.edu.speakua.ui.clubs.ClubsPage;
import com.ita.edu.speakua.ui.runners.SameWindowTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;


public class AdvancedSearchByPhraseTest extends SameWindowTestRunner {
    private ClubsPage clubsPage;

    @BeforeClass
    public void openClubsPageAdvancedSearch() {
        clubsPage = new HomePage(driver)
                .openAdvancedSearch();
    }

    @DataProvider(name = "searchData")
    public static Object[][] searchData() {
        return new Object[][]{
                {"С", false},
                {"bVFGgqFhQnbPWUxedbdQTMZFgHQACFuyDPcdAinKpWlKXffivF", false},
                {"Журналістика, дитяче телебачення, монтаж відео, влогів", false},
                {"bVFGgqFhQnbPWUxedbdQTMZFgHQACFuyDPcdAinKpWlKXffivF", true},
                {"Журналістика, дитяче телебачення, монтаж відео, влогів", true},
        };
    }

    /**
     *
     * @param query phrase that we are looking for in cards
     * @param isTypeOfInputPaste perform input by separate keystrokes if false, paste phrase to input field if true
     */
    @Issue("TUA-428")
    @Description("Verify search field behavior for strings of different length, that we enter as separate keystrokes or paste as a phrase.")
    @Test(dataProvider = "searchData")
    public void verifySearchByPhraseFunctionality(String query, boolean isTypeOfInputPaste) {
        final int maxInputValueLength = 50;

        String[] cardsTextBeforeSearch = clubsPage.getClubsNames();

        if (isTypeOfInputPaste) {
            clubsPage.pasteInSearch(query, maxInputValueLength);
        } else {
            clubsPage.fillInSearch(query);
        }

        String[] cardsTextAfterSearch = clubsPage.getClubsNames();

        boolean areCardsDifferentAfterSearch = false;
        SoftAssert softly = new SoftAssert();

        for (int i = 0; i < Math.min(cardsTextAfterSearch.length, cardsTextBeforeSearch.length); i++) {
            softly.assertTrue(cardsTextAfterSearch[i].toLowerCase().contains(query.toLowerCase()),
                    String.format("%s\nShould contain:\n%s", cardsTextAfterSearch[i], query));
            softly.assertTrue(clubsPage.getSearchInputLength() <= maxInputValueLength,
                    "Input value length should not exceed " + maxInputValueLength);
            if (!cardsTextBeforeSearch[i].equals(cardsTextAfterSearch[i])) {
                areCardsDifferentAfterSearch = true;
                break;
            }
        }

        softly.assertTrue(areCardsDifferentAfterSearch, "Cards must be different after search.");
        softly.assertAll();
    }
}