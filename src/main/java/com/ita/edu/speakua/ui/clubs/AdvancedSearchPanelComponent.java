package com.ita.edu.speakua.ui.clubs;

import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdvancedSearchPanelComponent extends ClubsPage {

    @FindBy(xpath = "//input[@id='basic_cityName']//ancestor::div[contains(@class, 'selector')]")
    private WebElement citySelector;

    @FindBy(xpath = "//input[@id='basic_districtName']//ancestor::div[contains(@class, 'selector')]")
    private WebElement districtSelector;

    @FindBy(xpath = "//input[@id='basic_stationName']//ancestor::div[contains(@class, 'selector')]")
    private WebElement metroSelector;

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

    public AdvancedSearchPanelComponent(WebDriver driver) {
        super(driver);
    }


    @Step("Check is citySelector displayed")
    public boolean isCitySelectorVisible() {
        return isVisible(citySelector);
    }

    @Step("Check is districtSelector displayed")
    public boolean isDistrictSelectorVisible() {
        return isVisible(districtSelector);
    }

    @Step("Check is metroSelector displayed")
    public boolean isMetroSelectorVisible() {
        return isVisible(metroSelector);
    }

    @Step("Check is availableOnlineCheckbox displayed")
    public boolean isAvailableOnlineCheckboxVisible() {
        return isVisible(availableOnline);
    }

    @Step("Check is categoriesBlock displayed")
    public boolean isCategoriesBlockVisible() {
        return isVisible(categoriesBlock);
    }

    @Step("Check is childAgeBlock displayed")
    public boolean isChildAgeBlockVisible() {
        return isVisible(childAgeBlock);
    }

    @Step("Click on 'club' radioButton")
    public AdvancedSearchPanelComponent selectClubFilter() {
        waitVisibility(clubRadioButton);
        clubRadioButton.click();
        return this;
    }

    @Step("Click on 'center' radioButton")
    public AdvancedSearchPanelComponent selectCenterFilter() {
        waitVisibility(centerRadioButton);
        centerRadioButton.click();
        return this;
    }

    public AdvancedSearchPanelComponent setChildAge(int age) {
        childAgeInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        childAgeInput.sendKeys(String.valueOf(age));
        return this;
    }

    public int getChildAge() {
        return Integer.parseInt(childAgeInput.getAttribute("value"));
    }
}