package com.ita.edu.speakua.ui.clubs;

import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class AdvancedSearchPanel extends ClubsPage {

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

    @FindBy(xpath = "//label[@for='basic_cityName']/../following-sibling::div//span[@class='ant-select-arrow']")
    private WebElement citySelectArrow;

    @FindBy(xpath = "//label[@for='basic_cityName']/../following-sibling::div//span[@class='ant-select-clear']")
    private WebElement citySelectClear;

    public AdvancedSearchPanel(WebDriver driver) {
        super(driver);
    }


    @Step("Check is citySelector displayed")
    public boolean isCitySelectorVisible() {
        return action.isVisible(citySelector);
    }

    @Step("Check is districtSelector displayed")
    public boolean isDistrictSelectorVisible() {
        return action.isVisible(districtSelector);
    }

    @Step("Check is metroSelector displayed")
    public boolean isMetroSelectorVisible() {
        return action.isVisible(metroSelector);
    }

    @Step("Check is availableOnlineCheckbox displayed")
    public boolean isAvailableOnlineCheckboxVisible() {
        return action.isVisible(availableOnline);
    }

    @Step("Check is categoriesBlock displayed")
    public boolean isCategoriesBlockVisible() {
        return action.isVisible(categoriesBlock);
    }

    @Step("Check is childAgeBlock displayed")
    public boolean isChildAgeBlockVisible() {
        return action.isVisible(childAgeBlock);
    }

    @Step("Advanced search panel: filter by clubs")
    public AdvancedSearchPanel selectClubFilter() {
        wait.visibility(clubRadioButton);
        clubRadioButton.click();
        return this;
    }

    @Step("Advanced search panel: filter by centers")
    public AdvancedSearchPanel selectCenterFilter() {
        wait.visibility(centerRadioButton);
        centerRadioButton.click();
        return this;
    }

    @Step("Advanced search panel: enter child's age")
    public AdvancedSearchPanel enterChildAge(int age) {
        childAgeInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        childAgeInput.sendKeys(String.valueOf(age));
        return this;
    }

    public int getChildAge() {
        return Integer.parseInt(childAgeInput.getAttribute("value"));
    }

    @Step("Advanced search panel: remove city filter")
    public AdvancedSearchPanel removeCityFilter() {
        new Actions(driver)
                .moveToElement(citySelectArrow)
                .perform();
        if (action.isVisible(citySelectClear)) {
            citySelectClear.click();
        }
        return this;
    }
}