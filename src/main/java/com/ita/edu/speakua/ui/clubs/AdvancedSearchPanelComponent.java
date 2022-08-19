package com.ita.edu.speakua.ui.clubs;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AdvancedSearchPanelComponent extends ClubsPage {

    private WebElement districtListSectionChildren;
    private WebElement metroListSectionChildren;
    private List<WebElement> listOfCategoriesCheckList;

    @FindBy(xpath = "//section[contains(@class, 'club-list')]/child::*")
    private List<WebElement> clubListSectionChildren;

    @FindBy(xpath = "//aside")
    private WebElement asideAdvancedSearchMenu;

    @FindBy(xpath = "//div[@class='club-list-label']")
    private WebElement advancedSearchHeader;

    @FindBy(xpath = "//input[@id='basic_cityName']//ancestor::div[contains(@class, 'selector')]")
    private WebElement citySelector;

    @FindBy(xpath = "//input[@id='basic_districtName']//ancestor::div[contains(@class, 'selector')]")
    private WebElement districtSelector;

    @FindBy(xpath = "//input[@id='basic_stationName']//ancestor::div[contains(@class, 'selector')]")
    private WebElement metroSelector;

    @FindBy(xpath = "//input[@id='basic_districtName']/../../..//div")
    private WebElement clearDistrictSelector;

    @FindBy(xpath = "//div[@id='basic_categoriesName']")
    private WebElement categoriesCheckList;

    @FindBy(xpath = "//div[@id='basic_categoriesName']")
    private List<WebElement> categoriesNameList;

    @FindBy(xpath = "//label/span[contains(text(),'Гурток')]")
    private WebElement clubRadioButton;

    @FindBy(xpath = "//label/span[contains(text(),'Центр')]")
    private WebElement centerRadioButton;

    @FindBy(xpath = "//div[@id='basic_isOnline']")
    private WebElement availableOnline;

    @FindBy(xpath = "//label[contains(text(),'Категорії')]/../..")
    private WebElement categoriesBlock;

    @FindBy(xpath = "//label[contains(text(),'Вік дитини')]/../..")
    private WebElement childAgeBlock;

    @FindBy(xpath = "//span[@id='basic_age']//input")
    private WebElement childAgeInput;

    @FindBy(xpath = "//div[@id='basic_districtName_list']/following-sibling::div//div[@class='rc-virtual-list-holder-inner']")
    private WebElement scrollDistrictSelector;

    public AdvancedSearchPanelComponent(WebDriver driver) {
        super(driver);
    }

    @Step("Check is availableOnlineCheckbox displayed")
    public boolean availableOnlineCheckboxIsDisplayed() {
        try {
            return availableOnline.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Step("Check is categoriesBlock displayed")
    public boolean categoriesBlockIsDisplayed() {
        try {
            return categoriesBlock.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Step("Check is childAgeBlock displayed")
    public boolean childAgeBlockIsDisplayed() {
        try {
            return childAgeBlock.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Step("Click on 'club' radioButton")
    public AdvancedSearchPanelComponent clubRadioButtonClick() {
        waitVisibility(clubRadioButton);
        clubRadioButton.click();
        return this;
    }

    @Step("Select filter by 'Center'")
    public AdvancedSearchPanelComponent selectFilterByCenter() {
        waitVisibility(centerRadioButton);
        centerRadioButton.click();
        return this;
    }

    public AdvancedSearchPanelComponent enterChildAge(int age) {
        childAgeInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        childAgeInput.sendKeys(String.valueOf(age));
        return this;
    }

    public int getChildAge() {
        return Integer.parseInt(childAgeInput.getAttribute("value"));
    }

    public boolean isCityListActivated() {
        return citySelector.isDisplayed() && citySelector.isEnabled();
    }

    public boolean isDistrictListActivated() {
        return districtSelector.isDisplayed() && districtSelector.isEnabled();
    }

    public boolean isMetroListActivated() {
        return metroSelector.isDisplayed() && metroSelector.isEnabled();
    }

    public boolean isCheckOnlineActivated() {
        return availableOnline.isDisplayed() && availableOnline.isEnabled();
    }

    public boolean isCategoriesListActivated() {
        for (WebElement categoryElement : categoriesNameList) {
            try {
                categoryElement.isDisplayed();
                categoryElement.isEnabled();
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    public boolean isAgeChildInputActivated() {
        return childAgeBlock.isDisplayed() && childAgeBlock.isEnabled();
    }
}