package com.ita.edu.speakua.ui.header.profileMenuAdmin.addClubComponent;

import com.ita.edu.speakua.ui.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddClubDescribeComponent extends AbstractAddClubComponent {

    @FindBy(xpath = "//textarea[@id='basic_description']")
    WebElement describeArea;

    @FindBy(xpath = "//span[contains(text(),'Назад')]/ancestor::button")
    WebElement previousStep;

    @FindBy(xpath = "//span[contains(text(),'Завершити')]/ancestor::button")
    WebElement finishBtn;

    @FindBy(xpath = "//div[contains(@class, 'explain')]//div[1]")
    private WebElement errorMessageDescriptionFieldPart1;

    @FindBy(xpath = "//div[contains(@class, 'explain')]//div[2]")
    private WebElement errorMessageDescriptionFieldPart2;

    public AddClubDescribeComponent(WebDriver driver) {
        super(driver);
    }

    public AddClubDescribeComponent inputDescribe(String text) {
        describeArea.click();
        describeArea.clear();
        describeArea.sendKeys(text);
        return new AddClubDescribeComponent(driver);
    }

    public AddClubContactComponent clickPreviousStep() {
        previousStep.click();
        return new AddClubContactComponent(driver);
    }

    public HomePage clickFinishStep() {
        finishBtn.click();
        return new HomePage(driver);
    }

    public String getErrorMessageDescriptionField() {
        waitVisibilityOfWebElement(errorMessageDescriptionFieldPart1);
        waitVisibilityOfWebElement(errorMessageDescriptionFieldPart2);
        return errorMessageDescriptionFieldPart1.getText() + errorMessageDescriptionFieldPart2.getText();
    }

    public boolean finishBtnIsEnable() {
        return finishBtn.isEnabled();
    }

    public AddClubDescribeComponent clearDescribeField() {
        describeArea.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        return this;
    }


}
