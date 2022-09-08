package com.ita.edu.speakua.ui.header.profileMenuAdmin.addCenterModal;

import com.ita.edu.speakua.ui.HomePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddCenterChooseClubStep extends AbstractAddCenterStep {

    @FindBy(xpath = "//div[@class='form-fields']")
    private WebElement clubsList;

    @FindBy(xpath = "//button[@class='finish-btn']")
    private WebElement finishButton;

    public AddCenterChooseClubStep(WebDriver driver) {
        super(driver);
    }

    private WebElement getClubsItem(String name) {
        return clubsList.findElement(By.xpath(String.format(".//span[contains(text(),'%s')]", name)));
    }

    @Step("Choose club from checkbox list")
    public AddCenterChooseClubStep chooseClub(String name) {
        getClubsItem(name).click();
        return new AddCenterChooseClubStep(driver);
    }

    @Step("Add center")
    public HomePage addCenter() {
        finishButton.click();
        return new HomePage(driver);
    }
}
