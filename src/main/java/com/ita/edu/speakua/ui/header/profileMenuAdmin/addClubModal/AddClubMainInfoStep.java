package com.ita.edu.speakua.ui.header.profileMenuAdmin.addClubModal;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddClubMainInfoStep extends AbstractAddClubStep {
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
    private WebElement nextStepButton;

    public AddClubMainInfoStep(WebDriver driver) {
        super(driver);
    }

    @Step("Add club 'main info' step: Set nameOfClub {name}")
    public AddClubMainInfoStep inputNameOfClub(String name) {
        wait.visibility(inputNameOfClub);
        inputNameOfClub.click();
        inputNameOfClub.clear();
        inputNameOfClub.sendKeys(name);
        return new AddClubMainInfoStep(driver);
    }

    private WebElement getCategoryItem(String name) {
        return categoryList.findElement(By.xpath(String.format(".//span[contains(text(),'%s')]", name)));
    }

    @Step("Add club 'main info' step: select category club")
    public AddClubMainInfoStep selectCategoryClub(String name) {
        getCategoryItem(name).click();
        return new AddClubMainInfoStep(driver);
    }

    @Step("Add club 'main info' step: input the child's minimum age {age}")
    public AddClubMainInfoStep inputAgeFrom(int age) {
        inputAgeFrom.click();
        inputAgeFrom.clear();
        inputAgeFrom.sendKeys(String.valueOf(age));
        return new AddClubMainInfoStep(driver);
    }

    @Step("Add club 'main info' step: input the child's maximum age {age}")
    public AddClubMainInfoStep inputAgeTo(int age) {
        inputAgeTo.click();
        inputAgeTo.clear();
        inputAgeTo.sendKeys(String.valueOf(age));
        return new AddClubMainInfoStep(driver);
    }


    private WebElement getCenterItem(String name) {
        return centerList.findElement(By.xpath(String.format(".//div[contains(text(),'%s')]", name)));
    }


    @Step("Add club 'main info' step: open next tab 'Contacts'")
    public AddClubContactsStep openNextStep() {
        nextStepButton.click();
        return new AddClubContactsStep(driver);
    }
}