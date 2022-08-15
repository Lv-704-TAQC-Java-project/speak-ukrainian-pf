package com.ita.edu.speakua.ui.header.profileMenuAdmin.addCenterComponent;

import com.ita.edu.speakua.ui.header.profileMenuAdmin.addLocation.AddLocationComponent;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.addLocation.Location;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class AddCenterMainInfoComponent extends AbstractAddCenterComponent {

    @FindBy(xpath = "//div[@class='modal-title']")
    private WebElement centerHeader;

    @FindBy(xpath = "//input[@id='basic_name']")
    private WebElement inputName;

    @FindBy(xpath = "//div[@id='basic_locations']")
    private WebElement locationsList;

    @FindBy(xpath = "//div[@class='btn']/button")
    private WebElement nextStepButton;

    @FindBy(xpath = "//div[contains(text(),'Некоректна назва центру')]")
    private WebElement errorMessage;

    @FindBy(xpath = "//button[contains(@class, 'add-location-btn')]")
    private WebElement addLocationButton;

    public AddCenterMainInfoComponent(WebDriver driver) {
        super(driver);
    }

    @Step("Input name of center")
    public AddCenterMainInfoComponent inputName(String name) {
        inputName.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        inputName.sendKeys(Keys.chord(name));
        return new AddCenterMainInfoComponent(driver);
    }

    private WebElement getLocationItem(String name) {
        return locationsList.findElement(By.xpath(String.format(".//span[contains(text(),'%s')]", name)));
    }

    @Step("Verify that location is displayed")
    public boolean isLocationAdded(String name) {
        try {
            waitVisibility(getLocationItem(name));
            actionsMoveTo(getLocationItem(name));
            return getLocationItem(name).isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }

    @Step("Choose location from checkbox list")
    public AddCenterMainInfoComponent chooseLocation(String name) {
        getLocationItem(name).click();
        return new AddCenterMainInfoComponent(driver);
    }

    @Step("Open next modal 'Contacts'")
    public AddCenterContactComponent openNextStep() {
        nextStepButton.click();
        return new AddCenterContactComponent(driver);
    }

    @Step("Click next step button")
    public AddCenterMainInfoComponent failOpenNextStep() {
        nextStepButton.click();
        return new AddCenterMainInfoComponent(driver);
    }

    @Step("Verify error message is displayed")
    public boolean isErrorMessageDisplayed() {
        waitVisibility(errorMessage);
        return errorMessage.isDisplayed();
    }

    @Step("Add location on center component")
    public AddCenterMainInfoComponent addLocation(Location location) {
        sleep(1000);
        addLocationButton.click();
        new AddLocationComponent(driver).addLocation(location);
        return this;
    }
}
