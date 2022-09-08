package com.ita.edu.speakua.ui.header.profileMenuAdmin.addCenterModal;

import com.ita.edu.speakua.ui.header.profileMenuAdmin.addLocationModal.AddLocationModal;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.addLocationModal.Location;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class AddCenterMainInfoStep extends AbstractAddCenterStep {

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

    public AddCenterMainInfoStep(WebDriver driver) {
        super(driver);
    }

    @Step("Input name of center")
    public AddCenterMainInfoStep inputName(String name) {
        inputName.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        inputName.sendKeys(Keys.chord(name));
        return new AddCenterMainInfoStep(driver);
    }

    private WebElement getLocationItem(String name) {
        return locationsList.findElement(By.xpath(String.format(".//span[contains(text(),'%s')]", name)));
    }

    @Step("Verify that location is displayed")
    public boolean isLocationAdded(String name) {
        try {
            wait.sleep(1000);
            action.scrollTo(getLocationItem(name));
            return getLocationItem(name).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Step("Choose location from checkbox list")
    public AddCenterMainInfoStep chooseLocation(String name) {
        wait.sleep(1000);
        action.scrollTo(getLocationItem(name));
        getLocationItem(name).click();
        return new AddCenterMainInfoStep(driver);
    }

    @Step("Open next modal 'Contacts'")
    public AddCenterContactsStep openNextStep() {
        wait.visibility(nextStepButton);
        nextStepButton.click();
        return new AddCenterContactsStep(driver);
    }

    @Step("Verify error message is displayed")
    public boolean isErrorMessageDisplayed() {
        wait.visibility(errorMessage);
        return errorMessage.isDisplayed();
    }

    @Step("Add location on center component")
    public AddCenterMainInfoStep addLocation(Location location) {
        wait.sleep(1000);
        addLocationButton.click();
        new AddLocationModal(driver).addLocation(location);
        return this;
    }

    @Step("Add location on center component filling only mandatory fields")
    public AddCenterMainInfoStep addLocationWithMandatoryFields(Location location) {
        wait.sleep(1000);
        addLocationButton.click();
        new AddLocationModal(driver).addLocationWithMandatoryFields(location);
        return this;
    }
}
