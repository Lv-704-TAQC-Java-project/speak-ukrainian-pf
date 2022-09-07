package com.ita.edu.speakua.ui.advancedSearch.tests;

import com.ita.edu.speakua.ui.HomePage;
import com.ita.edu.speakua.ui.clubs.ClubsPage;
import com.ita.edu.speakua.ui.runners.SameWindowTestRunner;
import com.ita.edu.speakua.utils.jdbc.services.ClubService;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static java.lang.String.format;
import static org.testng.Assert.assertEquals;


public class AdvancedSearchByPhraseTest extends SameWindowTestRunner {
    private ClubsPage clubsPage;

    @BeforeClass
    public void openClubsPageAdvancedSearch() {
        clubsPage = new HomePage(driver)
                .openAdvancedSearch();
    }

    @Issue("TUA-428")
    @Description("User should be able to search clubs by phrase")
    @Test(dataProvider = "searchByPhraseData")
    public void verifySearchByPhraseFunctionality(String phrase) {
        searchByPhrase(phrase, false);
    }

    @DataProvider(name = "searchByPhraseData")
    public static Object[][] searchByPhraseData() {
        return new Object[][]{
                {"вивчаємо все, що можна уявити в ІТ і навіть більше"},
                {"С"},
                {"ціль створити мережу найкращих центрів раннього розвитку в Україні"}
        };
    }

    @Issue("TUA-428")
    @Description("User should be able to search clubs by pasting search phrase into search field")
    @Test(dataProvider = "pastingSearchPhraseData")
    public void verifyFastSearchByPhraseFunctionality(String phrase) {
        searchByPhrase(phrase, true);
    }

    @DataProvider(name = "pastingSearchPhraseData")
    public static Object[][] pastingSearchPhraseData() {
        return new Object[][]{
                {"хореографія"},
                {"вивчаємо все, що можна уявити в ІТ і навіть більше"},
                {"Ми поставили перед собою ціль створити мережу найкращих центрів раннього розвитку в Україні"}
        };
    }

    private void searchByPhrase(String phrase, boolean isSearchByPhraseUsingPaste) {
        List<String> clubNamesInDataBase = new ClubService().getAllClubNamesFromCityBySearchPhrase("Київ", phrase, 6);
        final int maxInputFieldLength = 50;

        if (isSearchByPhraseUsingPaste) {
            clubsPage.searchByPhraseUsingPaste(phrase, maxInputFieldLength);
        } else {
            clubsPage.searchBy(phrase);
        }

        List<String> clubNamesOnPage = clubsPage.getClubNames();

        assertEquals(clubNamesOnPage.size(), clubNamesInDataBase.size(),
                "Clubs quantity on page should be equal to clubs quantity in DB");

        SoftAssert softly = new SoftAssert();
        for (int i = 0; i < clubNamesOnPage.size(); i++) {
            softly.assertEquals(clubNamesOnPage.get(i).trim(), clubNamesInDataBase.get(i).trim(),
                    format("Club name %s on page should be equal to club name in DB\n", i + 1));
        }
        softly.assertTrue(clubsPage.getSearchInputLength() <= maxInputFieldLength,
                "Input value length should not exceed " + maxInputFieldLength);
        softly.assertAll();
    }
}