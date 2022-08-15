package com.ita.edu.speakua.ui.clubs;

import com.ita.edu.speakua.ui.BaseMethods;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SortClubComponent extends ClubsPage {
    @FindBy(xpath = "//div/span[contains(text(), 'за алфавітом')]")
    private WebElement sortByABCButton;

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

    @Step("Click on 'sort by ABC' button")
    public SortClubComponent sortByABCButtonClick() {
        waitVisibilityOfWebElement(sortByABCButton);
        clickManagingClubsPageElement(sortByABCButton);
        return this;
    }

    @Step("Click on 'sort by rating' button")
    public SortClubComponent sortByRatingButtonClick() {
        waitVisibilityOfWebElement(sortByRatingButton);
        clickManagingClubsPageElement(sortByRatingButton);
        return this;
    }

    @Step("Click on 'arrowUp' button")
    public SortClubComponent arrowUpButtonClick() {
        clickManagingClubsPageElement(arrowUpButton);
        return this;
    }

    @Step("Click on 'arrowDown' button")
    public SortClubComponent arrowDownButtonClick() {
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
