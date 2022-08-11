package com.ita.edu.speakua.ui.advancedSearch.tests;

import com.ita.edu.speakua.ui.clubs.ClubsPage;
import com.ita.edu.speakua.ui.clubs.SortClubComponent;
import com.ita.edu.speakua.ui.runners.AdvancedSearchTestRunner;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

public class AdvancedSearchSortingTest extends AdvancedSearchTestRunner {

    @Test
    public void advancedSearchABCSortingClubTest() {
        ClubsPage clubsPage = new ClubsPage(driver);
        clubsPage
                .getAdvancedSearchPanelComponent()
                .clubRadioButtonClick();
        SortClubComponent sortClubComponent = clubsPage
                .getSortClubComponent()
                .sortByABCButtonClick()
                .arrowUpButtonClick();

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
            cardNamesTextDecActual.add(i, clubsPage.getCards().get(i).getTextCardName().toLowerCase());
        }

        softAssert.assertEquals(cardNamesTextDecActual.toString(), cardNamesDECExpected.toString(),
                "Club cards are not sorted by ABC DEC when arrowUp is clicked");

        sortClubComponent.arrowDownButtonClick();
        softAssert.assertTrue(clubsPage.getCards().get(0).getTextCardName().toLowerCase().startsWith("a"),
                "Club cards are not sorted by ABC ASC when arrowDown is clicked");

        softAssert.assertAll();
    }

    @Test
    public void advancedSearchABCSortingCenterTest() {
        ClubsPage clubsPage = new ClubsPage(driver);
        clubsPage
                .getAdvancedSearchPanelComponent()
                .centerRadioButtonClick();

        SortClubComponent sortClubComponent = clubsPage.getSortClubComponent()
                .sortByABCButtonClick()
                .arrowUpButtonClick();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(clubsPage.getCenters().get(0).getTextCenterName().toLowerCase().startsWith("ш"),
                "Center cards are not sorted by ABC DEC order when arrowUp is clicked");

        sortClubComponent.arrowDownButtonClick();

        softAssert.assertTrue(clubsPage.getCenters().get(0).getTextCenterName().toLowerCase().startsWith("a"),
                "Center cards are not sorted by ABC ASC order when arrowDown is clicked");
        softAssert.assertAll();
    }

    @Test
    public void advancedSearchRatingSortingClubTst() {
        ClubsPage clubsPage = new ClubsPage(driver);
        clubsPage
                .getAdvancedSearchPanelComponent()
                .clubRadioButtonClick();
        SortClubComponent sortClubComponent = clubsPage
                .getSortClubComponent()
                .sortByRatingButtonClick()
                .arrowUpButtonClick();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(clubsPage.getCards().get(0).getStarRatingFullList().size(), 5);

        sortClubComponent.arrowDownButtonClick();
        softAssert.assertEquals(clubsPage.getCards().get(0).getStarRatingZeroList().size(), 5);
        softAssert.assertAll();
    }

    @Test
    public void advancedSearchRatingSortingCenterTest() {
        ClubsPage clubsPage = new ClubsPage(driver);
        clubsPage
                .getAdvancedSearchPanelComponent()
                .centerRadioButtonClick();

        SortClubComponent sortClubComponent = clubsPage.getSortClubComponent();
        sortClubComponent
                .sortByRatingButtonClick()
                .arrowUpButtonClick();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(clubsPage.getCards().get(0).getStarRatingFullList().size(), 5);

        sortClubComponent.arrowDownButtonClick();
        softAssert.assertEquals(clubsPage.getCards().get(0).getStarRatingZeroList().size(), 5);
        softAssert.assertAll();
    }
}
