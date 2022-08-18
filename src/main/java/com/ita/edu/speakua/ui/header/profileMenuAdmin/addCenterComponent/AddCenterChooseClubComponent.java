package com.ita.edu.speakua.ui.header.profileMenuAdmin.addCenterComponent;

import com.ita.edu.speakua.ui.HomePage;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.addClubComponent.AddClubMainInfoComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddCenterChooseClubComponent extends AbstractAddCenterComponent{

    @FindBy(xpath = "//div[@class='form-fields']")
    private WebElement clubsList;

    @FindBy(xpath = "//span[contains(text(),'Назад')]/ancestor::button")
    private WebElement previousStepButton;

    @FindBy(xpath = "//button[@class='finish-btn']")
    private WebElement finishButton;

    public AddCenterChooseClubComponent(WebDriver driver) {
        super(driver);
    }

    private WebElement getClubsItem(String name){
        return clubsList.findElement(By.xpath(String.format(".//span[contains(text(),'%s')]",name)));
    }

    @Step("Choose club from checkbox list")
    public AddCenterChooseClubComponent chooseClub(String name){
        getClubsItem(name).click();
        return new AddCenterChooseClubComponent(driver);
    }

    @Step("Go back to previous modal 'Describe'")
    public AddCenterDescribeComponent openPreviousStep(){
        previousStepButton.click();
        return new AddCenterDescribeComponent(driver);
    }

    @Step("Add center")
    public HomePage addCenter(){
        finishButton.click();
        return new HomePage(driver);
    }

    @Step("Verify that modal is closed")
    public boolean isModalClosed(){
        return !clubsList.isDisplayed();
    }
}
