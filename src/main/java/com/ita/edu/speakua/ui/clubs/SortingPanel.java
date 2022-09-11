package com.ita.edu.speakua.ui.clubs;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SortingPanel extends ClubsPage {
    @FindBy(xpath = "//div/span[contains(text(), 'за алфавітом')]")
    private WebElement sortByAlphabetButton;

    @FindBy(xpath = "//div/span[contains(text(), 'за рейтингом')]")
    private WebElement sortByRatingButton;

    @FindBy(xpath = "//div/span[@aria-label = 'arrow-up']")
    private WebElement arrowUpButton;

    @FindBy(xpath = "//div/span[@aria-label = 'arrow-down']")
    private WebElement arrowDownButton;

    @FindBy(xpath = "//div/label[@class = 'ant-radio-button-wrapper club-view-button']")
    private WebElement listViewButton;

    @FindBy(xpath = "//span[@class='ant-radio-button ant-radio-button-checked']/parent::label")
    private WebElement blockViewButton;

    public SortingPanel(WebDriver driver) {
        super(driver);
    }

    @Step("Sorting panel: sort cards by alphabet")
    public SortingPanel sortByAlphabet() {
        wait.visibility(sortByAlphabetButton);
        clickManagingClubsPageElement(sortByAlphabetButton);
        return this;
    }

    @Step("Sorting panel: sort cards by rating")
    public SortingPanel sortByRating() {
        wait.visibility(sortByRatingButton);
        clickManagingClubsPageElement(sortByRatingButton);
        return this;
    }

    @Step("Sorting panel: sort cards in ascending order")
    public SortingPanel orderByAsc() {
        clickManagingClubsPageElement(arrowDownButton);
        return this;
    }

    @Step("Sorting panel: sort cards in descending order")
    public SortingPanel orderByDesc() {
        clickManagingClubsPageElement(arrowUpButton);
        return this;
    }

    public SortingPanel listViewButtonClick() {
        wait.visibility(listViewButton);
        listViewButton.click();
        return this;
    }

    public SortingPanel blockViewButtonClick() {
        wait.visibility(blockViewButton);
        blockViewButton.click();
        return this;
    }
}
