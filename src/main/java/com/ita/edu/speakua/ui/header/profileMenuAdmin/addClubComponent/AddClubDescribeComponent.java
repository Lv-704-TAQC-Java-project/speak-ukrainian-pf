package com.ita.edu.speakua.ui.header.profileMenuAdmin.addClubComponent;

import com.ita.edu.speakua.ui.HomePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddClubDescribeComponent extends AbstractAddClubComponent{

    @FindBy(xpath = "//input[@id='basic_description']")
    WebElement describeArea;

    @FindBy(xpath = "//span[contains(text(),'Назад')]/ancestor::button")
    WebElement previousStep;

    @FindBy(xpath = "//span[contains(text(),'Завершити')]/ancestor::button")
    WebElement finishBtn;

    public AddClubDescribeComponent(WebDriver driver) {
        super(driver);
    }

    public AddClubMainInfoComponent inputDescribe(String text){
        describeArea.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        describeArea.sendKeys(Keys.chord(text));
        return new AddClubMainInfoComponent(driver);
    }

    public AddClubContactComponent clickPreviousStep(){
        previousStep.click();
        return new AddClubContactComponent(driver);
    }

    public HomePage clickFinishStep(){
        finishBtn.click();
        return new HomePage(driver);
    }

}
