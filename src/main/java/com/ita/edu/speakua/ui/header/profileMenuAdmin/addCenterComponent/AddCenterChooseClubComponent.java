package com.ita.edu.speakua.ui.header.profileMenuAdmin.addCenterComponent;

import com.ita.edu.speakua.ui.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddCenterChooseClubComponent extends AbstractAddCenterComponent{

    @FindBy(xpath = "//div[@class='form-fields']")
    WebElement clubsList;

    @FindBy(xpath = "//span[contains(text(),'Назад')]/ancestor::button")
    WebElement previousStep;

    @FindBy(xpath = "//span[contains(text(),'Завершити')]/ancestor::button")
    WebElement finishBtn;

    public AddCenterChooseClubComponent(WebDriver driver) {
        super(driver);
    }

    public WebElement getClubsItem(String name){
        return clubsList.findElement(By.xpath(String.format(".//span[contains(text(),'%s')]",name)));
    }

    public AddCenterChooseClubComponent chooseLocation(String name){
        getClubsItem(name).click();
        return new AddCenterChooseClubComponent(driver);
    }

    public AddCenterDescribeComponent clickPreviousStep(){
        previousStep.click();
        return new AddCenterDescribeComponent(driver);
    }

    public HomePage clickFinishStep(){
        finishBtn.click();
        return new HomePage(driver);
    }

}
