package com.ita.edu.speakua.ui.advancedSearch.tests;

import com.ita.edu.speakua.ui.clubs.ClubsPage;
import com.ita.edu.speakua.ui.clubs.SortClubComponent;
import com.ita.edu.speakua.ui.clubs.card.components.CardComponent;
import com.ita.edu.speakua.ui.runners.AdvancedSearchTestRunner;
import com.ita.edu.speakua.ui.utils.jdbc.services.ClubService;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

public class AdvancedSearchSortingTest extends AdvancedSearchTestRunner {

    @Issue("TUA-103")
    @Description("Verify that clubs are sorted by ABC")
    @Test
    public void advancedSearchABCSortClubTest() {
        advancedSearchPanel.selectClubFilter();
        ClubsPage clubsPage = new ClubsPage(driver);

        SortClubComponent sortClubComponent = clubsPage
                .getSortClubComponent()
                .sortByAlphabet()
                .orderByDesc();

        SoftAssert softAssert = new SoftAssert();

        List<String> cardNamesDECExpected = new ArrayList<>();
        cardNamesDECExpected.add("ячсячячс");
        cardNamesDECExpected.add("школа танців dream team");
        cardNamesDECExpected.add("школа робототехніки та програмування для дітей robocode");
        cardNamesDECExpected.add("школа лідерства і бізнесу kidbi");
        cardNamesDECExpected.add("школа джазового та естрадного мистецтв");
        cardNamesDECExpected.add("школа бойового гопака «шаблезуб»");

        List<String> cardNamesTextDecActual = new ArrayList<>();
        for (int i = 0; i < clubsPage.getCards().size(); i++) {
            cardNamesTextDecActual.add(i, clubsPage.getCards().get(i).getCardName().toLowerCase());
        }

        softAssert.assertEquals(cardNamesTextDecActual.toString(), cardNamesDECExpected.toString(),
                "Club cards are not sorted by ABC DEC when arrowUp is clicked");

        sortClubComponent.orderByAsc();
        softAssert.assertTrue(clubsPage.getCards().get(0).getCardName().toLowerCase().startsWith("a"),
                "Club cards are not sorted by ABC ASC when arrowDown is clicked");

        softAssert.assertAll();
    }

    @Issue("TUA-103")
    @Description("Verify that centers are sorted by ABC")
    @Test
    public void advancedSearchABCSortCenterTest() {
        advancedSearchPanel.selectCenterFilter();
        ClubsPage clubsPage = new ClubsPage(driver);

        SortClubComponent sortClubComponent = clubsPage.getSortClubComponent()
                .sortByAlphabet()
                .orderByDesc();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(clubsPage.getCenters().get(0).getTextCenterName().toLowerCase().startsWith("ш"),
                "Center cards are not sorted by ABC DEC order when arrowUp is clicked");

        sortClubComponent.orderByAsc();

        softAssert.assertTrue(clubsPage.getCenters().get(0).getTextCenterName().toLowerCase().startsWith("a"),
                "Center cards are not sorted by ABC ASC order when arrowDown is clicked");
        softAssert.assertAll();
    }

    @Issue("TUA-103")
    @Description("Verify that clubs are sorted by rating")
    @Test
    public void advancedSearchRatingSortClubTest() {
        advancedSearchPanel.selectClubFilter();
        ClubsPage clubsPage = new ClubsPage(driver);

        SortClubComponent sortClubComponent = clubsPage
                .getSortClubComponent()
                .sortByRating()
                .orderByDesc();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(clubsPage.getCards().get(0).getStarRatingFullList().size(), 5,
                "Clubs are not sorted by rating ASC when arrowUpButton is clicked");

        sortClubComponent.orderByAsc();
        softAssert.assertEquals(clubsPage.getCards().get(0).getStarRatingZeroList().size(), 5,
                "Clubs are not sorted by rating DEC when arrowDownButton is clicked");
        softAssert.assertAll();
    }

    @Issue("TUA-103")
    @Issue("TUA-449")
    @Description("Verify that centers are sorted by rating")
    @Test
    public void advancedSearchRatingSortCenterTest() {
        advancedSearchPanel.selectCenterFilter();
        ClubsPage clubsPage = new ClubsPage(driver);

        SortClubComponent sortClubComponent = clubsPage.getSortClubComponent();
        sortClubComponent
                .sortByRating()
                .orderByDesc();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(clubsPage.getCards().get(0).getStarRatingFullList().size(), 5,
                "Centers are not sorted by rating ASC when arrowUpButton is clicked");

        sortClubComponent.orderByAsc();
        softAssert.assertEquals(clubsPage.getCards().get(0).getStarRatingZeroList().size(), 5,
                "Centers are not sorted by rating DEC when arrowDownButton is clicked");
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

        SortClubComponent sort = clubsPage
                .getSortClubComponent()
                .sortByRating();

        SoftAssert softAssert = new SoftAssert();
        ClubService clubService = new ClubService();
        long countOfCardsOnPage = clubsPage.getCards().size();
        List<String> cardNames = clubService.getAllNamesOrderByRatingIdAscLimit(countOfCardsOnPage);
        List<Double> cardRatings = clubService.getAllRatingsOrderByRatingIdAscLimit(countOfCardsOnPage);
        for (int i = 0; i < countOfCardsOnPage; i++) {
            int cardCountOfStarsActual = clubsPage.getCards().get(i).getStarRatingFullList().size();
            double cardCountOfStarsExpected = cardRatings.get(i);

            softAssert.assertEquals(cardCountOfStarsActual, (int) cardCountOfStarsExpected);

            String cardNameActual = clubsPage.getCards().get(i).getCardName();
            String cardNameExpected = cardNames.get(i);

            softAssert.assertEquals(cardNameActual, cardNameExpected);
        }

        sort.orderByDesc();

        cardNames = clubService.getAllNamesOrderByRatingIdDescLimit(countOfCardsOnPage);
        cardRatings = clubService.getAllRatingsOrderByRatingIdDescLimit(countOfCardsOnPage);
        for (int i = 0; i < countOfCardsOnPage; i++) {
            int cardCountOfStarsActual = clubsPage.getCards().get(i).getStarRatingFullList().size();
            double cardCountOfStarsExpected = cardRatings.get(i) <= 5 ? cardRatings.get(i) : 5;

            softAssert.assertEquals(cardCountOfStarsActual, (int) cardCountOfStarsExpected);

            String cardNameActual = clubsPage.getCards().get(i).getCardName();
            String cardNameExpected = cardNames.get(i);

            softAssert.assertEquals(cardNameActual, cardNameExpected);
        }
        softAssert.assertAll();
    }
}
