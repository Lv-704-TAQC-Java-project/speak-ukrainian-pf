package com.ita.edu.speakua.ui.advancedSearch.tests;

import com.ita.edu.speakua.ui.HomePage;
import com.ita.edu.speakua.ui.clubs.ClubsPage;
import com.ita.edu.speakua.ui.clubs.card.components.CardComponent;
import com.ita.edu.speakua.ui.runners.SameWindowTestRunner;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;


public class AdvancedSearchByPhraseTest extends SameWindowTestRunner {
    private ClubsPage clubsPage;

    @BeforeClass
    public void openClubsPageAdvancedSearch() {
        clubsPage = new HomePage(driver)
                .clickAdvancedSearchButton();
    }

    @DataProvider(name = "searchData")
    public static Object[][] searchData() {
        return new Object[][]{
                {"я"},
                {"bVFGgqFhQnbPWUxedbdQTMZFgHQACFuyDPcdAinKpWlKXffivF"},
                {"Журналістика, дитяче телебачення, монтаж відео, влогів"},
        };
    }

    @Test(dataProvider = "searchData")
    public void verifySearchFunctionality(String query) {
        String[] cardsTextBeforeSearch = clubsPage.getCards().stream().map(CardComponent::getCardText).toArray(String[]::new);
        clubsPage.fillInSearch(query);
        assertSearchResults(cardsTextBeforeSearch, query);
    }

    @Test(dataProvider = "searchData")
    public void verifyPasteSearchFunctionality(String query) {
        String[] cardsTextBeforeSearch = clubsPage.getCards().stream().map(CardComponent::getCardText).toArray(String[]::new);
        clubsPage.pasteInSearch(query);
        assertSearchResults(cardsTextBeforeSearch, query);
    }

    private void assertSearchResults (String[] cardsTextBeforeSearch, String query) {
        final int maxInputValueLength = 50;
        String[] cardsTextAfterSearch = clubsPage.getCards().stream().map(CardComponent::getCardText).toArray(String[]::new);

        boolean areCardsDifferentAfterSearch = false;
        SoftAssert softAssert = new SoftAssert();

        for (int i = 0; i < Math.min(cardsTextAfterSearch.length, cardsTextBeforeSearch.length); i++) {
            softAssert.assertTrue(cardsTextAfterSearch[i].toLowerCase().contains(query.toLowerCase()),
                    String.format("%s\nShould contain:\n%s", cardsTextAfterSearch[i], query));
            softAssert.assertTrue(query.length() > maxInputValueLength
                            ? clubsPage.getSearchInputLength() == maxInputValueLength
                            : clubsPage.getSearchInputLength() == query.length(),
                    "Input value should be equal query length or do not exceed " + maxInputValueLength);
            if (!cardsTextBeforeSearch[i].equals(cardsTextAfterSearch[i])) {
                areCardsDifferentAfterSearch = true;
                break;
            }
        }

        softAssert.assertTrue(areCardsDifferentAfterSearch, "Cards must be different after search");
        softAssert.assertAll();
    }
}