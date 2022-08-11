package com.ita.edu.speakua.ui.header.profileMenuAdmin.addCenterComponent;

import com.ita.edu.speakua.ui.BaseMethods;
import com.ita.edu.speakua.ui.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class AbstractAddCenterComponent extends BaseMethods {

    @FindBy(xpath = "//span[@aria-label='close']")
    private WebElement closeClubComponent;

    public AbstractAddCenterComponent(WebDriver driver) {
        super(driver);
    }
    public HomePage clickCloseComponent(){
        closeClubComponent.click();
        return new HomePage(driver);
    }
}
