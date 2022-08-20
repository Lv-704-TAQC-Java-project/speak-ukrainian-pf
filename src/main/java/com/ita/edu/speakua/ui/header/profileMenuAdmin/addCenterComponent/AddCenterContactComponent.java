package com.ita.edu.speakua.ui.header.profileMenuAdmin.addCenterComponent;

import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddCenterContactComponent extends AbstractAddCenterComponent {

    @FindBy(xpath = "//input[@id='contacts_contactТелефон']")
    private WebElement enterPhoneNumber;

    @FindBy(xpath = "//span[contains(text(),'Наступний крок')]/ancestor::button")
    private WebElement nextStepButton;

    public AddCenterContactComponent(WebDriver driver) {
        super(driver);
    }

    @Step("Enter phone number")
    public AddCenterContactComponent enterPhoneNumber(String data) {
        enterPhoneNumber.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        enterPhoneNumber.sendKeys(Keys.chord(data));
        return new AddCenterContactComponent(driver);
    }

    @Step("Open next modal 'Describe'")
    public AddCenterDescribeComponent openNextStep() {
        nextStepButton.click();
        return new AddCenterDescribeComponent(driver);
    }
}
