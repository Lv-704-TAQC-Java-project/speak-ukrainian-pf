package com.ita.edu.speakua.ui.baseSearch.tests;

import com.ita.edu.speakua.ui.HomePage;
import com.ita.edu.speakua.ui.clubs.ClubsPage;
import com.ita.edu.speakua.ui.clubs.ExpandedCardComponent;
import com.ita.edu.speakua.ui.clubs.card.components.CardComponent;
import com.ita.edu.speakua.ui.runners.BaseTestRunner;
import com.ita.edu.speakua.ui.utils.jdbc.entity.ClubEntity;
import com.ita.edu.speakua.ui.utils.jdbc.services.ClubService;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Random;

import static org.testng.Assert.assertTrue;

public class BaseSearchTest extends BaseTestRunner {

    @Issue("TUA-226")
    @Description("Verify that user can perform basic search by name of a club")
    @Test
    public void verifyPossibilityOfSearchByClubName() {
        HomePage homePage = new HomePage(driver);
        ClubService clubService = new ClubService();

        String city = homePage.getLocationFromHeader();

        List<String> clubNames = clubService.getAllNameByCity(city);

        String clubToSearch = clubNames.get(new Random().nextInt(clubNames.size()));

        ClubsPage clubsPage = new HomePage(driver).openAdvancedSearch().fillInSearch(clubToSearch);

        SoftAssert softAssert = new SoftAssert();

        List<ClubEntity> clubEntities = clubService.getAllNameWhereNameLike(clubToSearch);
        List<CardComponent> actualClubs = clubsPage.getCards();

        assertTrue(actualClubs.size() >= 1);
        softAssert.assertEquals(clubEntities.size(), actualClubs.size());

        for (CardComponent card : actualClubs) {
            for (ClubEntity clubEntity : clubEntities) {
                ExpandedCardComponent expandedCardComponent = card.expandCard();

                String cardName = expandedCardComponent.getClubName();
                String entityName = clubEntity.getName();
                softAssert.assertEquals(cardName, entityName);

                int cardFeedbackCount = expandedCardComponent.getFeedbackCount();
                int entityFeedbackCount = clubEntity.getFeedbackCount();
                softAssert.assertEquals(cardFeedbackCount, entityFeedbackCount);

                List<Integer> audienceAge = expandedCardComponent.getAudienceAge();
                List<Integer> entityAudienceAge = List.of(clubEntity.getAgeFrom(), clubEntity.getAgeTo());
                softAssert.assertEquals(audienceAge, entityAudienceAge);

                expandedCardComponent.close();
            }
        }

        softAssert.assertAll();
    }
}