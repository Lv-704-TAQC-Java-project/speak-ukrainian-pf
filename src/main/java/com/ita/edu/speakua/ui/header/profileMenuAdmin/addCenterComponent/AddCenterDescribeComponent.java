package com.ita.edu.speakua.ui.header.profileMenuAdmin.addCenterComponent;

import com.ita.edu.speakua.ui.HomePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddCenterDescribeComponent extends AbstractAddCenterComponent{



    @FindBy(xpath = "//input[@id='basic_description']")
    WebElement describeArea;

    @FindBy(xpath = "//span[contains(text(),'Назад')]/ancestor::button")
    WebElement previousStep;

    @FindBy(xpath = "//span[contains(text(),'Наступний крок')]/ancestor::button")
    WebElement nextStep;

    public AddCenterDescribeComponent(WebDriver driver) {
        super(driver);
    }

    public AddCenterDescribeComponent inputDescribe(String text){
        describeArea.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        describeArea.sendKeys(Keys.chord(text));
        return new AddCenterDescribeComponent(driver);
    }

    public AddCenterContactComponent clickPreviousStep(){
        previousStep.click();
        return new AddCenterContactComponent(driver);
    }

    public AddCenterChooseClubComponent clickNextStep(){
        nextStep.click();
        return new AddCenterChooseClubComponent(driver);
    }
}
