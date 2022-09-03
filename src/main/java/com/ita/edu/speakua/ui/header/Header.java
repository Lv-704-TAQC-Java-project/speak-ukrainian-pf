package com.ita.edu.speakua.ui.header;

import com.ita.edu.speakua.ui.BasePage;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.AdminProfileMenuComponent;
import com.ita.edu.speakua.ui.header.profileMenuGuest.GuestProfileMenuComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class Header extends BasePage {
    @FindBy(xpath = "//div[contains(@class, 'user-profile')]")
    private WebElement profileMenuButton;

    @FindBy(xpath = "//span[@aria-label='user']")
    private WebElement imageUserComponent;

    @FindBy(xpath = "//span[contains(text(), 'Показати на мапі')]/parent::button")
    private WebElement showOnMapButton;

    @FindBy(xpath = "//div[contains(@class, 'user-profile')]")
    private WebElement profileMenuBtn;

    @FindBy(xpath = "//header//div[contains(@class, 'city')]")
    private WebElement city;

    @FindBy(xpath = "//div[@class='search']//input")
    private WebElement searchInputField;

    @FindBy(xpath = "//div[@class='search']//span[contains(@class, 'anticon-search')]")
    private WebElement submitSearchButton;


    public Header(WebDriver driver) {
        super(driver);
    }

    public NavigationComponent getNavigationComponent() {
        return new NavigationComponent(driver);
    }

    public LocationComponent getLocationComponent() {

        return new LocationComponent(driver);
    }

    public GuestProfileMenuComponent getGuestProfileMenuComponent() {
        return new GuestProfileMenuComponent(driver);
    }

    public AdminProfileMenuComponent getAdminProfileMenuComponent() {
        return new AdminProfileMenuComponent(driver);
    }

    @Step("Open guest profile menu")
    public GuestProfileMenuComponent openGuestProfileMenu() {
        profileMenuButton.click();
        return getGuestProfileMenuComponent();
    }

    @Step("Open admin profile menu")
    public AdminProfileMenuComponent openAdminProfileMenu() {
        profileMenuButton.click();
        return getAdminProfileMenuComponent();
    }

    public Header getHomePageReload() {
        wait.pageReload();
        return new Header(driver);
    }

    public String getLocationFromHeader() {
        return city.getText();
    }

    @Step("Header: Search by {phrase}")
    public Header searchBy(String phrase) {
        action.setNewValueForInput(searchInputField, phrase);
        wait.sleep((phrase.length() / 10 + 1) * 4000);
        submitSearchButton.click();
        wait.sleep(1000);
        return this;
    }

    @Step("Header: Search {phrase} by setting value of search field in {inputMaxLength} range and typing rest.")
    public Header fastSearchBy(String phrase, int inputMaxLength) {
        wait.sleep(1000);
        searchInputField.click();
        wait.sleep(500);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        String phraseSubstringInMaxLengthRange = phrase.length() < inputMaxLength
                ? phrase
                : phrase.substring(0, inputMaxLength - 1);
        String phraseSubstringOverMaxLengthRange = phrase.length() < inputMaxLength
                ? phrase.substring(phrase.length() - 1)
                : phrase.substring(inputMaxLength - 2);
        executor.executeScript(String.format("arguments[0].value='%s'", phraseSubstringInMaxLengthRange), searchInputField);

        searchInputField.sendKeys(Keys.BACK_SPACE);
        wait.sleep(500);
        searchInputField.sendKeys(phraseSubstringOverMaxLengthRange);
        wait.sleep(500);
        submitSearchButton.click();
        wait.sleep(1000);
        return this;
    }
}