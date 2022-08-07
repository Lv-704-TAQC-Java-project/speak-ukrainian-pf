package com.ita.edu.speakua.ui.header.profileMenuAdmin.addCenterComponent;

import com.ita.edu.speakua.ui.BaseMethods;
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

    public AddLocationComponent fillInLocationNameField(String LocationName) {
        waitVisibilityOfWebElement(locationNameField);
        locationNameField.click();
        locationNameField.clear();
        locationNameField.sendKeys(LocationName);
        return this;
    }

    public WebElement findCityByName(String City) {
        return driver.findElement(By.xpath
                (String.format("//div[@class='ant-select-item-option-content'][contains(text(),'%s')]", City)));
    }

    public AddLocationComponent selectCityByName(String City) {
        cityDropDownList.click();
        findCityByName(City).click();
        return this;
    }

    public WebElement findRegionByName(String Region) {
        return driver.findElement(By.xpath
                (String.format("//div[@class='ant-select-item-option-content'][contains(text(),'%s')]", Region)));
    }

    public AddLocationComponent selectRegionByName(String Region) {
        regionDropDownList.click();
        findRegionByName(Region).click();
        return this;
    }

    public AddLocationComponent fillInAddressField(String Address) {
        waitVisibilityOfWebElement(addressField);
        addressField.click();
        addressField.clear();
        addressField.sendKeys(Address);
        return this;
    }

    public AddLocationComponent fillInPhoneNumberField(String PhoneNumber) {
        phoneNumberField.click();
        phoneNumberField.clear();
        phoneNumberField.sendKeys(PhoneNumber);
        return this;
    }

    public AddLocationComponent addButtonClick() {
        addButton.click();
        return this;
    }
}
