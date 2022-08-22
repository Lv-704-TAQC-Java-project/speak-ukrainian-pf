package com.ita.edu.speakua.ui.header.profileMenuAdmin.addCenterComponent;

import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddCenterDescribeComponent extends AbstractAddCenterComponent {

    @FindBy(xpath = "//textarea[@id='basic_description']")
    private WebElement describeArea;

    @FindBy(xpath = "//span[contains(text(),'Наступний крок')]/ancestor::button")
    private WebElement nextStepButton;

    public AddCenterDescribeComponent(WebDriver driver) {
        super(driver);
    }

    @Step("Add a description of center")
    public AddCenterDescribeComponent addDescribe(String text) {
        wait.visibility(describeArea);
        describeArea.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        describeArea.sendKeys(Keys.chord(text));
        return new AddCenterDescribeComponent(driver);
    }

    @Step("Open next modal 'Describe'")
    public AddCenterChooseClubComponent openNextStep() {
        nextStepButton.click();
        return new AddCenterChooseClubComponent(driver);
    }
}
