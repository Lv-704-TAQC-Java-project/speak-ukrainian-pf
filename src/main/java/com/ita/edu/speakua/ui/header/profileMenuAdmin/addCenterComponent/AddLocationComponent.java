package com.ita.edu.speakua.ui.header.profileMenuAdmin.addCenterComponent;

import com.ita.edu.speakua.ui.BaseMethods;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddLocationComponent extends BaseMethods {

    @FindBy(xpath = "//input[@id='name']")
    private WebElement locationNameField;

    @FindBy(xpath = "//input[@id = 'address']")
    private WebElement addressField;

    @FindBy(xpath = "//input[@id = 'coordinates']")
    private WebElement geographicСoordinatesField;

    @FindBy(xpath = "//input[@id = 'phone']")
    private WebElement phoneNumberField;

    @FindBy(xpath = "//input[@id = 'cityName']")
    private WebElement cityDropDownList;

    @FindBy(xpath = "//input[@id = 'districtName']")
    private WebElement regionDropDownList;

    @FindBy(xpath = "//input[@id = 'stationName']")
    private WebElement metroDropDownList;

    @FindBy(xpath = "//div[contains(@class, 'add-club-content')]/button")
    private WebElement addButton;

    @FindBy(xpath = "//div[contains(@class, 'modal-add-club')]//button[@class = 'ant-modal-close']")
    private WebElement closeAddLocationButton;

    public AddLocationComponent(WebDriver driver) {
        super(driver);
    }

    @Step("Fill In {locationName}")
    public AddLocationComponent fillInLocationNameField(String locationName) {
        waitVisibilityOfWebElement(locationNameField);
        locationNameField.click();
        locationNameField.clear();
        locationNameField.sendKeys(locationName);
        return this;
    }

    public WebElement findCityByName(String city) {
        return driver.findElement(By.xpath
                (String.format("//div[@class='ant-select-item-option-content'][contains(text(),'%s')]", city)));
    }

    @Step("Select {city}")
    public AddLocationComponent selectCityByName(String city) {
        cityDropDownList.click();
        findCityByName(city).click();
        return this;
    }

    public WebElement findRegionByName(String region) {
        return driver.findElement(By.xpath
                (String.format("//div[@class='ant-select-item-option-content'][contains(text(),'%s')]", region)));
    }

    @Step("Select {region}")
    public AddLocationComponent selectRegionByName(String region) {
        regionDropDownList.click();
        findRegionByName(region).click();
        return this;
    }

    @Step("Fill In {address}")
    public AddLocationComponent fillInAddressField(String address) {
        waitVisibilityOfWebElement(addressField);
        addressField.click();
        addressField.clear();
        addressField.sendKeys(address);
        return this;
    }

    @Step("Fill In {coordinates}")
    public AddLocationComponent fillInGeographicСoordinatesField(String coordinates) {
        waitVisibilityOfWebElement(geographicСoordinatesField);
        geographicСoordinatesField.click();
        geographicСoordinatesField.clear();
        geographicСoordinatesField.sendKeys(coordinates);
        return this;
    }

    @Step("Fill In {phoneNumber}")
    public AddLocationComponent fillInPhoneNumberField(String phoneNumber) {
        phoneNumberField.click();
        phoneNumberField.clear();
        phoneNumberField.sendKeys(phoneNumber);
        return this;
    }

    @Step("Is add location button enabled")
    public boolean isAddButtonEnabled() {
        return addButton.isEnabled();
    }

    public void clickCloseButton(){
        closeAddLocationButton.click();
    }

    @Step("Click addButton")
    public AddCenterMainInfoComponent addButtonClick() {
        addButton.click();
        return new AddCenterMainInfoComponent(driver);
    }
}

