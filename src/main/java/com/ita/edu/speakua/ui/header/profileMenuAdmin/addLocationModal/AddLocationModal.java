package com.ita.edu.speakua.ui.header.profileMenuAdmin.addLocationModal;

import com.ita.edu.speakua.ui.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddLocationModal extends BasePage {
    @FindBy(xpath = "//input[@id='name']")
    private WebElement locationNameField;

    @FindBy(xpath = "//input[@id = 'address']")
    private WebElement addressField;

    @FindBy(xpath = "//input[@id = 'coordinates']")
    private WebElement geographicCoordinatesField;

    @FindBy(xpath = "//input[@id = 'phone']")
    private WebElement phoneNumberField;

    @FindBy(xpath = "//input[@id = 'cityName']")
    private WebElement cityDropDownList;

    @FindBy(xpath = "//input[@id = 'districtName']")
    private WebElement regionDropDownList;

    @FindBy(xpath = "//form[contains(@class, 'ant-form') and not(contains(@id, 'basic'))]//button[contains(@class,'ant-btn ant-btn-default flooded-button add-club-content-next')]")
    private WebElement addButton;


    public AddLocationModal(WebDriver driver) {
        super(driver);
    }

    @Step("Fill In {locationName}")
    public AddLocationModal fillInLocationNameField(String locationName) {
        wait.visibility(locationNameField);
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
    public AddLocationModal selectCityByName(String city) {
        cityDropDownList.click();
        findCityByName(city).click();
        return this;
    }

    public WebElement findRegionByName(String region) {
        return driver.findElement(By.xpath
                (String.format("//div[@class='ant-select-item-option-content'][contains(text(),'%s')]", region)));
    }

    @Step("Select {region}")
    public AddLocationModal selectRegionByName(String region) {
        regionDropDownList.click();
        findRegionByName(region).click();
        return this;
    }

    @Step("Fill In {address}")
    public AddLocationModal fillInAddressField(String address) {
        wait.visibility(addressField);
        addressField.click();
        addressField.clear();
        addressField.sendKeys(address);
        return this;
    }

    @Step("Fill In {coordinates}")
    public AddLocationModal fillInGeographicCoordinatesField(String coordinates) {
        wait.visibility(geographicCoordinatesField);
        geographicCoordinatesField.click();
        geographicCoordinatesField.clear();
        geographicCoordinatesField.sendKeys(coordinates);
        return this;
    }

    @Step("Fill In {phoneNumber}")
    public AddLocationModal fillInPhoneNumberField(String phoneNumber) {
        phoneNumberField.click();
        phoneNumberField.clear();
        phoneNumberField.sendKeys(phoneNumber);
        return this;
    }

    @Step("Is add location button enabled")
    public boolean isAddButtonEnabled() {
        try {
            return addButton.isEnabled();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void addButtonClick() {
        addButton.click();

    }

    public void addLocation(Location location) {
        fillInLocationNameField(location.getName());
        selectCityByName(location.getCity());
        selectRegionByName(location.getRegion());
        fillInAddressField(location.getAddress());
        fillInGeographicCoordinatesField(location.getCoordinates());
        fillInPhoneNumberField(location.getPhone());
        addButtonClick();
    }

    public void addLocationWithMandatoryFields(Location location) {
        fillInLocationNameField(location.getName());
        selectCityByName(location.getCity());
        fillInAddressField(location.getAddress());
        fillInGeographicCoordinatesField(location.getCoordinates());
        fillInPhoneNumberField(location.getPhone());
        addButtonClick();
    }
}