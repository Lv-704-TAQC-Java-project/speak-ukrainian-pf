package com.ita.edu.speakua.ui.clubs;

import com.ita.edu.speakua.ui.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ExpandedClub extends BasePage {

    @FindBy(xpath = "//button[contains(@class,'close')]")
    private WebElement exitButton;

    @FindBy(xpath = "//div[@class='club-name']")
    private WebElement clubName;

    @FindBy(xpath = "//span[@class='feedback']")
    private WebElement feedbackCount;

    @FindBy(xpath = "//span[@class='years']")
    private WebElement audienceAge;

    public ExpandedClub(WebDriver driver) {
        super(driver);
    }


    @Step("Expanded card: close")
    public ClubsPage close() {
        exitButton.click();
        return new ClubsPage(driver);
    }

    public int getFeedbackCount() {
        return Integer.parseInt(feedbackCount
                .getText()
                .replaceAll("[^0-9]", ""));
    }

    public List<Integer> getAudienceAge() {
        return Arrays.stream(audienceAge
                        .getText()
                        .split("до"))
                .map(s -> Integer.parseInt(s.replaceAll("[^0-9]", "")))
                .collect(Collectors.toList());
    }

    public String getClubName() {
        wait.visibility(clubName);
        return clubName.getText();
    }
}