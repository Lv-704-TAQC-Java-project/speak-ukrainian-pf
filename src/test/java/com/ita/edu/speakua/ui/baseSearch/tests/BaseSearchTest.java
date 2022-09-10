package com.ita.edu.speakua.ui.baseSearch.tests;

import com.ita.edu.speakua.ui.HomePage;
import com.ita.edu.speakua.ui.clubs.ClubsPage;
import com.ita.edu.speakua.ui.clubs.ExpandedClub;
import com.ita.edu.speakua.ui.clubs.cards.ClubCard;
import com.ita.edu.speakua.ui.runners.BaseTestRunner;
import com.ita.edu.speakua.utils.jdbc.entity.ClubEntity;
import com.ita.edu.speakua.utils.jdbc.services.ClubService;
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
    public void verifyThatUserCanPerformSearchByClubName() {
        HomePage homePage = getHomePage();
        ClubService clubService = new ClubService();

        String city = homePage.getLocationFromHeader();

        List<String> clubNames = clubService.getAllNameByCity(city);

        String clubToSearch = clubNames.get(new Random().nextInt(clubNames.size()));

        ClubsPage clubsPage = new HomePage(driver)
                .openAdvancedSearch()
                .fillInSearch(clubToSearch);

        SoftAssert softAssert = new SoftAssert();

        List<ClubEntity> clubEntities = clubService.getAllNameWhereNameLike(clubToSearch);
        List<ClubCard> actualClubs = clubsPage.getCards();

        assertTrue(actualClubs.size() >= 1,
                "There should be at least one club on the page");
        softAssert.assertEquals(clubEntities.size(), actualClubs.size(),
                "The clubs quantity on the page should be the same as in the DB");

        for (ClubCard card : actualClubs) {
            for (ClubEntity clubEntity : clubEntities) {
                ExpandedClub expandedCardComponent = card.expandCard();

                String pageClubName = expandedCardComponent.getClubName();
                String dbClubName = clubEntity.getName();
                softAssert.assertEquals(pageClubName, dbClubName,
                        "Club name on the page should be the same as in the DB");

                int pageClubFeedbackCount = expandedCardComponent.getFeedbackCount();
                int dbClubFeedbackCount = clubEntity.getFeedbackCount();
                softAssert.assertEquals(pageClubFeedbackCount, dbClubFeedbackCount,
                        "Club feedback count on the page should be the same as in the DB");

                List<Integer> pageClubAudienceAge = expandedCardComponent.getAudienceAge();
                List<Integer> dbClubAudienceAge = List.of(clubEntity.getAgeFrom(), clubEntity.getAgeTo());
                softAssert.assertEquals(pageClubAudienceAge, dbClubAudienceAge,
                        "Club audience age on the page should be the same as in the DB");

                expandedCardComponent.close();
            }
        }

        softAssert.assertAll();
    }
}