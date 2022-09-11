package com.ita.edu.speakua.ui.advancedSearch.tests;

import com.ita.edu.speakua.ui.clubs.ClubsPage;
import com.ita.edu.speakua.ui.runners.BaseTestRunner;
import com.ita.edu.speakua.utils.jdbc.services.ClubService;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static java.lang.String.format;
import static org.testng.Assert.assertEquals;


public class AdvancedSearchByPhraseTest extends BaseTestRunner {
    @DataProvider(name = "searchByPhraseData")
    public static Object[][] searchByPhraseData() {
        return new Object[][]{
                {"вивчаємо все, що можна уявити в ІТ і навіть більше", false},
                {"С", false},
                {"ціль створити мережу найкращих центрів раннього розвитку в Україні", false},
                {"хореографія", true},
                {"вивчаємо все, що можна уявити в ІТ і навіть більше", true},
                {"Ми поставили перед собою ціль створити мережу найкращих центрів раннього розвитку в Україні", true}
        };
    }

    @Issue("TUA-428")
    @Description("User should be able to search clubs by phrase")
    @Test(dataProvider = "searchByPhraseData")
    public void verifySearchByPhraseFunctionality(String phrase, boolean isJavaScriptPermitted) {
        ClubsPage clubsPage = getHomePage().openAdvancedSearch();

        List<String> databaseClubNames = new ClubService().getAllClubNamesFromCityBySearchPhrase("Київ", phrase, 6);
        final int inputFieldLengthLimit = 50;

        if (isJavaScriptPermitted) {
            clubsPage.searchBySettingInputWithJavaScript(phrase, inputFieldLengthLimit);
        } else {
            clubsPage.searchBy(phrase);
        }

        List<String> actualClubNames = clubsPage.getClubNames();

        assertEquals(actualClubNames.size(), databaseClubNames.size(),
                "Clubs quantity on page should be equal to clubs quantity in DB");

        SoftAssert softly = new SoftAssert();
        for (int i = 0; i < actualClubNames.size(); i++) {
            softly.assertEquals(actualClubNames.get(i).trim(), databaseClubNames.get(i).trim(),
                    format("Club name %s on page should be equal to club name in DB\n", i + 1));
        }
        softly.assertTrue(clubsPage.getSearchInputLength() <= inputFieldLengthLimit,
                "Input value length should not exceed " + inputFieldLengthLimit);
        softly.assertAll();
    }
}