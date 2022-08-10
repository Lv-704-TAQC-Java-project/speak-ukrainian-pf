package com.ita.edu.speakua.ui.advancedSearch.tests;

import com.ita.edu.speakua.ui.HomePage;
import com.ita.edu.speakua.ui.clubs.ClubsPage;
import com.ita.edu.speakua.ui.clubs.card.components.CardComponent;
import com.ita.edu.speakua.ui.runners.BaseTestRunner;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class SortCentersAlphabeticalOrderTest extends BaseTestRunner {
    @Test
    public void verifySortAcsDescWorksForCenterCards() {
        new HomePage(driver)
                .clickAdvancedSearchButton()
                .getAdvancedSearchPanelComponent()
                .centerRadioButtonClick()
                .getSortClubComponent()
                .sortByABCButtonClick();

        ClubsPage clubsPage = new ClubsPage(driver);

        String[] actualCenterNamesAscOrder = clubsPage.getCards().stream().map(CardComponent::getTextCardName).toArray(String[]::new);
        String[] expectedCenterNamesAscOrder = new String[]{
                "API testing2",
                "BabyClub",
                "Ccccc1",
                "center1",
                "Center#1",
                "Center Baby Club"
        };

        SoftAssert softAssert = new SoftAssert();
        for (int i = 0; i < expectedCenterNamesAscOrder.length; i++) {
            softAssert.assertEquals(actualCenterNamesAscOrder[i], expectedCenterNamesAscOrder[i]);
        }

        clubsPage.getSortClubComponent().arrowUpButtonClick();

        String[] actualCenterNamesDescOrder = clubsPage.getCards().stream().map(CardComponent::getTextCardName).toArray(String[]::new);
        String[] expectedCenterNamesDescOrder = new String[]{
                "Школа мистецтв імені Миколи Дмитровича Леонтовича",
                "Центр творчості дітей та юнацтва",
                "Центр розвитку",
                "Центр позашкільної роботи Святошинського району",
                "Центр дитячого та сімейного розвитку Одуванчик",
                "Фольк-студія \"Правиця\""
        };

        for (int i = 0; i < expectedCenterNamesDescOrder.length; i++) {
            softAssert.assertEquals(actualCenterNamesDescOrder[i], expectedCenterNamesDescOrder[i]);
        }

        softAssert.assertAll();
    }
}