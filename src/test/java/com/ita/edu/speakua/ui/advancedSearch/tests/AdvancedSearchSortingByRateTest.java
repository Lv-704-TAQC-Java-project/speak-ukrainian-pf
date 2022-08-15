package com.ita.edu.speakua.ui.advancedSearch.tests;

import com.ita.edu.speakua.ui.clubs.ClubsPage;
import com.ita.edu.speakua.ui.clubs.SortClubComponent;
import com.ita.edu.speakua.ui.header.HeaderComponent;
import com.ita.edu.speakua.ui.runners.BaseTestRunner;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

public class AdvancedSearchSortingByRateTest extends BaseTestRunner {
    @Test
    public void verifySortingClubsByRate() {
        HeaderComponent header = new HeaderComponent(driver);
        SoftAssert softAssert = new SoftAssert();
        SortClubComponent sort = header.getNavigationComponent()
                .clickClubsBtn()
                .advancedSearchButtonClick()
                .getSortClubComponent()
                .sortByRatingButtonClick();
        ClubsPage clubsPage = new ClubsPage(driver);

        List<Integer> cardCountOfStars = new ArrayList<>();
        for (int i = 0; i < clubsPage.getCards().size(); i++) {
            cardCountOfStars.add(clubsPage.getCards().get(i).getStarRatingZeroList().size());
            System.out.println(clubsPage.getCards().get(i).getStarRatingZeroList().size());
            boolean result = cardCountOfStars.get(i).equals(5);
            softAssert.assertTrue(result, "Club cards are not sorted by Rate when advancedSearch is opened");
        }

        sort.arrowUpButtonClick();

        cardCountOfStars = new ArrayList<>();
        for (int i = 0; i < clubsPage.getCards().size(); i++) {
            cardCountOfStars.add(i, clubsPage.getCards().get(i).getStarRatingFullList().size());
            System.out.println(clubsPage.getCards().get(i).getStarRatingFullList().size());
            boolean result = cardCountOfStars.get(i).equals(5);
            softAssert.assertTrue(result, "Club cards are not sorted by Rate when advancedSearch is opened");
        }
        softAssert.assertAll();
    }
}
