package com.ita.edu.speakua.ui.header.profileMenuAdmin.addClubComponent;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddClubMainInfoComponent extends AbstractAddClubComponent {

    @FindBy(xpath = "//div[@class='modal-title']")
    private WebElement clubHeader;

    @FindBy(xpath = "//input[@placeholder='Назва гуртка']")
    private WebElement inputNameOfClub;

    @FindBy(xpath = "//div[@id='basic_categories']")
    private WebElement categoryList;

    @FindBy(xpath = "//input[@id='basic_ageFrom']")
    private WebElement inputAgeFrom;

    @FindBy(xpath = "//input[@id='basic_ageTo']")
    private WebElement inputAgeTo;

    @FindBy(xpath = "//input[@id='basic_centerId']")
    private WebElement openCenterList;

    @FindBy(xpath = "//div[@class='rc-virtual-list-holder-inner']")
    private WebElement centerList;

    @FindBy(xpath = "//span[contains(text(),'Наступний крок')]/ancestor::button")
    private WebElement nextStepBtn;


    public AddClubMainInfoComponent(WebDriver driver) {
        super(driver);
    }

    public AddClubMainInfoComponent inputNameOfClub(String name){
        waitVisibilityOfWebElement(inputNameOfClub);
        inputNameOfClub.click();
        inputNameOfClub.clear();
        inputNameOfClub.sendKeys(name);
        return new AddClubMainInfoComponent(driver);
    }

    public WebElement getCategoryItem(String name){
        return categoryList.findElement(By.xpath(String.format(".//span[contains(text(),'%s')]",name)));
    }

    public AddClubMainInfoComponent chooseCategoryClub(String name){
        getCategoryItem(name).click();
        return new AddClubMainInfoComponent(driver);
    }

    public AddClubMainInfoComponent inputAgeFrom(int age){
        inputAgeFrom.click();
        inputAgeFrom.clear();
        inputAgeFrom.sendKeys(String.valueOf(age));
        return new AddClubMainInfoComponent(driver);
    }

    public AddClubMainInfoComponent inputAgeTo(int age){
        inputAgeTo.click();
        inputAgeTo.clear();
        inputAgeTo.sendKeys(String.valueOf(age));
        return new AddClubMainInfoComponent(driver);
    }

    public AddClubMainInfoComponent openCenterList(){
        openCenterList.click();
        return new AddClubMainInfoComponent(driver);
    }

    public WebElement getCenterItem(String name){
        return centerList.findElement(By.xpath(String.format(".//div[contains(text(),'%s')]",name)));
    }

    public AddClubMainInfoComponent chooseCenter(String name){
        getCenterItem(name).click();
        return new AddClubMainInfoComponent(driver);
    }

    public AddClubContactComponent clickNextStep(){
        nextStepBtn.click();
        return new AddClubContactComponent(driver);
    }

}
