package com.ita.edu.speakua.ui.advancedSearch.tests;

import com.ita.edu.speakua.ui.clubs.ClubsPage;
import com.ita.edu.speakua.ui.clubs.SortingPanel;
import com.ita.edu.speakua.ui.runners.AdvancedSearchTestRunner;
import com.ita.edu.speakua.utils.jdbc.services.ClubService;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class AdvancedSearchSortingTest extends AdvancedSearchTestRunner {

    @Issue("TUA-103")
    @Description("Verify that clubs are sorted by ABC")
    @Test
    public void advancedSearchABCSortClubTest() {
        advancedSearchPanel.clearCitySelector();
        advancedSearchPanel.selectClubFilter();

        SortingPanel sortClubComponent = clubsPage
                .getSortClubComponent()
                .sortByAlphabet()
                .orderByDesc();

        int cardsLimit = 6;

        ClubService clubService = new ClubService();
        SoftAssert softAssert = new SoftAssert();

        List<String> cardNamesDescExpected = clubService.getNamesOrderByDescending(cardsLimit);
        for (int i = 0; i < clubsPage.getCards().size(); i++) {
            softAssert.assertEquals(clubsPage.getCards().get(i).getCardName().replaceAll("[\n\r]", ""),
                    cardNamesDescExpected.get(i).replaceAll("[\n\r]", ""),
                    "Club cards are not sorted by ABC DEC when arrowUp is clicked");
        }

        sortClubComponent.orderByAsc();

        List<String> cardNamesAscExpected = clubService.getNamesOrderByAscending(cardsLimit);
        for (int i = 0; i < clubsPage.getCards().size(); i++) {
            softAssert.assertEquals(clubsPage.getCards().get(i).getCardName(),
                    cardNamesAscExpected.get(i),
                    "Club cards are not sorted by ABC ASC when arrowDown is clicked");
        }
        softAssert.assertAll();
    }

    @Issue("TUA-103")
    @Description("Verify that centers are sorted by ABC")
    @Test
    public void advancedSearchABCSortCenterTest() {
        advancedSearchPanel.selectCenterFilter();

        SortingPanel sortClubComponent = clubsPage.getSortClubComponent()
                .sortByAlphabet()
                .orderByDesc();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(clubsPage.getCenters().get(0).getTextCenterName().toLowerCase().startsWith("ш"),
                "Center cards are not sorted by ABC in descending order when arrowUp is clicked");

        sortClubComponent.orderByAsc();

        softAssert.assertTrue(clubsPage.getCenters().get(0).getTextCenterName().toLowerCase().startsWith("a"),
                "Center cards are not sorted by ABC in ascending order when arrowDown is clicked");
        softAssert.assertAll();
    }

    @Issue("TUA-103")
    @Description("Verify that clubs are sorted by rating")
    @Test
    public void advancedSearchRatingSortClubTest() {
        advancedSearchPanel.selectClubFilter();

        SortingPanel sortClubComponent = clubsPage
                .getSortClubComponent()
                .sortByRating()
                .orderByDesc();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(clubsPage.getCards().get(0).getStarRatingFullList().size(), 5,
                "Clubs are not sorted by rating in descending order when arrowUpButton is clicked");

        sortClubComponent.orderByAsc();
        softAssert.assertEquals(clubsPage.getCards().get(0).getStarRatingZeroList().size(), 5,
                "Clubs are not sorted by rating in ascending order when arrowDownButton is clicked");
        softAssert.assertAll();
    }

    @Issue("TUA-103")
    @Issue("TUA-449")
    @Description("Verify that centers are sorted by rating")
    @Test
    public void advancedSearchRatingSortCenterTest() {
        advancedSearchPanel.selectCenterFilter();

        SortingPanel sortClubComponent = clubsPage.getSortClubComponent();
        sortClubComponent
                .sortByRating()
                .orderByDesc();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(clubsPage.getCards().get(0).getStarRatingFullList().size(), 5,
                "Centers are not sorted by rating in descending order when arrowUpButton is clicked");

        sortClubComponent.orderByAsc();
        softAssert.assertEquals(clubsPage.getCards().get(0).getStarRatingZeroList().size(), 5,
                "Centers are not sorted by rating in ascending order when arrowDownButton is clicked");
        softAssert.assertAll();
    }

    @Issue("TUA-516")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that the clubs can be sorted by rating")
    @Test
    public void verifySortingClubsByRating() {
        advancedSearchPanel
                .selectClubFilter()
                .clearCitySelector();
        ClubsPage clubsPage = new ClubsPage(driver);

        SortingPanel sort = clubsPage
                .getSortClubComponent()
                .sortByRating();

        SoftAssert softAssert = new SoftAssert();
        ClubService clubService = new ClubService();
        long countOfCardsOnPage = clubsPage.getCards().size();
        List<String> cardNames = clubService.getAllNamesOrderByRatingIdAscLimit(countOfCardsOnPage);
        List<Double> cardRatings = clubService.getAllRatingsOrderByRatingIdAscLimit(countOfCardsOnPage);
        for (int i = 0; i < countOfCardsOnPage; i++) {
            int cardCountOfStarsActual = clubsPage.getCards().get(i).getStarRatingZeroList().size();
            int cardCountOfStarsExpected = (int) Math.floor(cardRatings.get(i));

            softAssert.assertEquals(cardCountOfStarsActual, 5 - cardCountOfStarsExpected);

            String cardNameActual = clubsPage.getCards().get(i).getCardName();
            String cardNameExpected = cardNames.get(i);

            softAssert.assertEquals(cardNameActual, cardNameExpected);
        }

        sort.orderByDesc();

        cardNames = clubService.getAllNamesOrderByRatingIdDescLimit(countOfCardsOnPage);
        cardRatings = clubService.getAllRatingsOrderByRatingIdDescLimit(countOfCardsOnPage);
        for (int i = 0; i < countOfCardsOnPage; i++) {
            int cardCountOfStarsActual = clubsPage.getCards().get(i).getStarRatingFullList().size();
            int cardCountOfStarsExpected = cardRatings.get(i) <= 5 ? (int) Math.floor(cardRatings.get(i)) : 5;

            softAssert.assertEquals(cardCountOfStarsActual, cardCountOfStarsExpected);

            String cardNameActual = clubsPage.getCards().get(i).getCardName();
            String cardNameExpected = cardNames.get(i);

            softAssert.assertEquals(cardNameActual, cardNameExpected);
        }
        softAssert.assertAll();
    }
}
