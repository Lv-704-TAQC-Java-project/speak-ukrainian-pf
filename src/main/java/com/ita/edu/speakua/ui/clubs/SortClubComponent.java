package com.ita.edu.speakua.ui.clubs;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SortClubComponent extends ClubsPage {
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

    @FindBy(xpath = "//div/label[@class = 'ant-radio-button-wrapper ant-radio-button-wrapper-checked club-view-button']")
    private WebElement blockViewButton;

    public SortClubComponent(WebDriver driver) {
        super(driver);
    }

    @Step("Sort cards by alphabet")
    public SortClubComponent sortByAlphabet() {
        waitVisibility(sortByAlphabetButton);
        clickManagingClubsPageElement(sortByAlphabetButton);
        return this;
    }

    @Step("Sort cards by rating")
    public SortClubComponent sortByRating() {
        waitVisibility(sortByRatingButton);
        clickManagingClubsPageElement(sortByRatingButton);
        return this;
    }

    @Step("Sort cards in ascending order")
    public SortClubComponent orderByAsc() {
        clickManagingClubsPageElement(arrowUpButton);
        return this;
    }

    @Step("Sort cards in descending order")
    public SortClubComponent orderByDesc() {
        clickManagingClubsPageElement(arrowDownButton);
        return this;
    }

    public SortClubComponent listViewButtonClick() {
        listViewButton.click();
        return this;
    }

    public SortClubComponent blockViewButtonClick() {
        blockViewButton.click();
        return this;
    }
}
