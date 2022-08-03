package com.ita.edu.speakua.ui.header.profileMenuAdmin.addCenterComponent;

import com.ita.edu.speakua.ui.BasePage;
import com.ita.edu.speakua.ui.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class AbstractAddCenterComponent extends BasePage {

    @FindBy(xpath = "//span[@aria-label='close']")
    private WebElement closeClubComponent;

    public AbstractAddCenterComponent(WebDriver driver) {
        super(driver);
    }
    public HomePage clickCloseComponent(String name){
        closeClubComponent.click();
        return new HomePage(driver);
    }
}
