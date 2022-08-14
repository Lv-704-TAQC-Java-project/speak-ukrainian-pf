package com.ita.edu.speakua.ui.clubs;

import com.ita.edu.speakua.ui.BaseMethods;
import org.openqa.selenium.By;
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

    //@FindBy(xpath = "//div/label[@class = 'ant-radio-button-wrapper club-view-button']")
    private WebElement listViewButton;

    //@FindBy(xpath = "//div/label[@class = 'ant-radio-button-wrapper ant-radio-button-wrapper-checked club-view-button']")
    private WebElement blockViewButton;

    public SortClubComponent(WebDriver driver) {
        super(driver);
    }

    public SortClubComponent sortByABCButtonClick() {
        waitVisibilityOfWebElement(sortByABCButton);
        clickManagingClubsPageElement(sortByABCButton);
        return this;
    }

    public SortClubComponent sortByRatingButtonClick() {
        waitVisibilityOfWebElement(sortByRatingButton);
        clickManagingClubsPageElement(sortByRatingButton);
        return this;
    }

    public SortClubComponent arrowUpButtonClick() {
        clickManagingClubsPageElement(arrowUpButton);
        return this;

    }

    public SortClubComponent arrowDownButtonClick() {
        clickManagingClubsPageElement(arrowDownButton);
        return this;
    }

    public WebElement getListViewButton() {
        if (listViewButton == null) {
            listViewButton = driver.findElement(By.xpath("//div/label[@class = 'ant-radio-button-wrapper club-view-button']"));
        }
        return listViewButton;
    }

    public WebElement getBlockViewButton() {
        if (blockViewButton == null) {
            blockViewButton = driver.findElement(By.xpath("//div/label[@class = 'ant-radio-button-wrapper ant-radio-button-wrapper-checked club-view-button']"));

        }
        return blockViewButton;
    }

    public SortClubComponent listViewButtonClick() {
        getListViewButton().click();
        return this;
    }

    public SortClubComponent blockViewButtonClick() {
        getBlockViewButton().click();
        return this;
    }
}
