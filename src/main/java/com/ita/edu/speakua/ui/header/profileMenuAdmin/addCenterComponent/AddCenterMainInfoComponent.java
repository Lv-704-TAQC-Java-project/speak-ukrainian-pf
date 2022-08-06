package com.ita.edu.speakua.ui.header.profileMenuAdmin.addCenterComponent;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddCenterMainInfoComponent extends AbstractAddCenterComponent{

    @FindBy(xpath = "//div[@class='modal-title']")
    private WebElement centerHeader;

    @FindBy(xpath = "//input[@id='basic_name']")
    private WebElement inputName;

    @FindBy(xpath = "//div[@id='basic_locations']")
    private WebElement locationsList;

    @FindBy(xpath = "//div[@class='btn']")
    private WebElement nextStepBtn;

    @FindBy(xpath = "//div[contains(text(),'Некоректна назва центру')]")
    private WebElement errorMessage;

    @FindBy(xpath = "//button[contains(@class, 'add-location-btn')]")
    private WebElement addLocationButton;

    private AddLocationComponent addLocationComponent;

    public AddCenterMainInfoComponent(WebDriver driver) {
        super(driver);
    }

    public AddCenterMainInfoComponent inputName(String name){
        inputName.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        inputName.sendKeys(Keys.chord(name));
        return new AddCenterMainInfoComponent(driver);
    }

    public WebElement getLocationItem(String name){
        return locationsList.findElement(By.xpath(String.format(".//span[contains(text(),'%s')]",name)));
    }

    public AddCenterMainInfoComponent chooseLocation(String name){
        getLocationItem(name).click();
        return new AddCenterMainInfoComponent(driver);
    }

    public AddCenterContactComponent clickNextStep(){
        nextStepBtn.click();
        return new AddCenterContactComponent(driver);
    }

    public AddCenterMainInfoComponent tryClickNextStep(){
        nextStepBtn.click();
        return new AddCenterMainInfoComponent(driver);
    }

    public boolean errorMessageCenterName(){
        waitVisibilityOfElement(By.xpath("//div[contains(text(),'Некоректна назва центру')]"));
        return errorMessage.isDisplayed();
    }

    public AddLocationComponent getAddLocationComponent() {
        return new AddLocationComponent(driver);
    }

    public AddLocationComponent clickAddLocationButton() {
        addLocationButton.click();
        return new AddLocationComponent(driver);
    }

}
