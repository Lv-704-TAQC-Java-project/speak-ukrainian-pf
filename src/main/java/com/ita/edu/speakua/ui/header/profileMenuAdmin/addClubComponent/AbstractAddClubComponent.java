package com.ita.edu.speakua.ui.header.profileMenuAdmin.addClubComponent;

import com.ita.edu.speakua.ui.BaseMethods;
import com.ita.edu.speakua.ui.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class AbstractAddClubComponent extends BaseMethods {

    @FindBy(xpath = "//span[@aria-label='close']")
    private WebElement closeClubComponent;

    public AbstractAddClubComponent(WebDriver driver) {
        super(driver);
    }

    public HomePage clickCloseComponent(String name){
        closeClubComponent.click();
        return new HomePage(driver);
    }
}
