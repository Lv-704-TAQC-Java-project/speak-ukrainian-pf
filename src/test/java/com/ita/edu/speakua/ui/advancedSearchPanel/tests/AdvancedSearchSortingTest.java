package com.ita.edu.speakua.ui.advancedSearchPanel.tests;

import com.ita.edu.speakua.ui.HomePage;
import com.ita.edu.speakua.ui.clubs.ClubsPage;
import com.ita.edu.speakua.ui.clubs.SortClubComponent;
import com.ita.edu.speakua.ui.header.HeaderComponent;
import com.ita.edu.speakua.ui.runners.BaseTestRunner;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

public class AdvancedSearchSortingTest extends BaseTestRunner {

    @Test
    public void advancedSearchABCSortingClubTest() {
        HeaderComponent header = new HeaderComponent(driver);
        SoftAssert softAssert = new SoftAssert();
        header
                .getNavigationComponent()
                .clickClubsBtn()
                .advancedSearchButtonClick();

        ClubsPage clubsPage = new ClubsPage(driver);

        List<String> cardNamesText = new ArrayList<>();
        for (int i = 0; i < clubsPage.getCards().size(); i++) {
            cardNamesText.add(i, clubsPage.getCards().get(i).getTextCardName().toUpperCase());
            softAssert.assertTrue(cardNamesText.get(i).startsWith("A"),
                    "Club cards are not sorted by ABC ASC when advancedSearch is opened");
        }

        SortClubComponent sortClubComponent = clubsPage.getSortClubComponent()
                .arrowUpButtonClick();

        List<String> cardNamesDECExpected = new ArrayList<>();
        cardNamesDECExpected.add("ЯЧСЯЧЯЧС");
        cardNamesDECExpected.add("ШКОЛА ТАНЦІВ DREAM TEAM");
        cardNamesDECExpected.add("ШКОЛА РОБОТОТЕХНІКИ ТА ПРОГРАМУВАННЯ ДЛЯ ДІТЕЙ ROBOCODE");
        cardNamesDECExpected.add("ШКОЛА ЛІДЕРСТВА І БІЗНЕСУ KIDBI");
        cardNamesDECExpected.add("ШКОЛА ДЖАЗОВОГО ТА ЕСТРАДНОГО МИСТЕЦТВ");
        cardNamesDECExpected.add("ШКОЛА БОЙОВОГО ГОПАКА «ШАБЛЕЗУБ»");

        List<String> cardNamesTextDecActual = new ArrayList<>();
        for (int i = 0; i < clubsPage.getCards().size(); i++) {
            cardNamesTextDecActual.add(i, clubsPage.getCards().get(i).getTextCardName().toUpperCase());
        }

        softAssert.assertEquals(cardNamesTextDecActual.toString(), cardNamesDECExpected.toString(),
                "Club cards are not sorted by ABC DEC when arrowUp is clicked");

        sortClubComponent.arrowDownButtonClick();

        softAssert.assertTrue(cardNamesText.get(0).startsWith("A"),
                "Club cards are not sorted by ABC ASC when arrowDown is clicked");
        softAssert.assertAll();
    }

    @Test
    public void advancedSearchABCSortingCenterTest() {
        HeaderComponent header = new HeaderComponent(driver);
        SoftAssert softAssert = new SoftAssert();
        header
                .getNavigationComponent()
                .clickClubsBtn()
                .advancedSearchButtonClick();

        ClubsPage clubsPage = new ClubsPage(driver);
        clubsPage
                .getAdvancedSearchPanelComponent()
                .centerRadioButtonClick();

        softAssert.assertTrue(clubsPage.getCenters().get(0).getTextCenterName().toLowerCase().startsWith("a"),
                "Center cards are not sorted by ABC ASC order when user switch to centers");

        SortClubComponent sortClubComponent = clubsPage.getSortClubComponent()
                .arrowUpButtonClick();

        softAssert.assertTrue(clubsPage.getCenters().get(0).getTextCenterName().toLowerCase().startsWith("t"),
                "Center cards are not sorted by ABC DEC order when arrowUp is clicked");

        sortClubComponent.arrowDownButtonClick();

        softAssert.assertTrue(clubsPage.getCenters().get(0).getTextCenterName().toLowerCase().startsWith("a"),
                "Center cards are not sorted by ABC ASC order when arrowDown is clicked");
        softAssert.assertAll();
    }

    @Test
    public void advancedSearchRatingSortingCenterTest() {
        HomePage homePage = new HomePage(driver);
        SoftAssert softAssert = new SoftAssert();
        homePage
                .clickAdvancedSearchButton();

        ClubsPage clubsPage = new ClubsPage(driver);
        clubsPage
                .getAdvancedSearchPanelComponent()
                .centerRadioButtonClick();

        softAssert.assertTrue(clubsPage.getCenters().get(0).getTextCenterName().toLowerCase().startsWith("a"),
                "Center cards are not sorted by ABC ASC order when user switch to centers");

        SortClubComponent sortClubComponent = clubsPage.getSortClubComponent();
        sortClubComponent
                .sortByRatingButtonClick();

        softAssert.assertEquals(clubsPage.getCards().get(0).getStarRatingZeroList().size(), 5);
        softAssert.assertAll();
    }
}
