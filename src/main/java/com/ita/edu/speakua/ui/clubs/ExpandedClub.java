package com.ita.edu.speakua.ui.clubs;

import com.ita.edu.speakua.ui.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ExpandedClub extends BasePage {

    @FindBy(xpath = "//button[contains(@class,'close')]")
    private WebElement exitButton;

    @FindBy(xpath = "//div[@class='club-name']")
    private WebElement clubName;

    @FindBy(xpath = "//div[@class='container']//span[@class='name']")
    private List<WebElement> listOfCategories;

    @FindBy(xpath = "//div[@class='ant-modal-body']//div[@class='age']//span[@class='years']")
    private WebElement ageDescription;

    @FindBy(xpath = "//span[@class='feedback']")
    private WebElement feedbackCount;

    @FindBy(xpath = "//span[@class='years']")
    private WebElement audienceAge;

    public ExpandedClub(WebDriver driver) {
        super(driver);
    }

    public Integer[] getAgeRestriction() {
        String line = ageDescription.getText();
        String regex = "[^\\d]+";
        String[] splitedLine = line.split(regex);
        Integer[] intLine = new Integer[2];

        for (int i = 1; i <= splitedLine.length - 1; i++) {
            intLine[i - 1] = Integer.parseInt(splitedLine[i]);
        }
        return intLine;
    }

    public List<String> getListOfNamesOfCategories() {
        List<String> listOfNameOfCategories = new ArrayList<>();
        for (WebElement category : listOfCategories) {
            listOfNameOfCategories.add(category.getText());
        }
        return listOfNameOfCategories;
    }

    @Step("Clubs page: close")
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