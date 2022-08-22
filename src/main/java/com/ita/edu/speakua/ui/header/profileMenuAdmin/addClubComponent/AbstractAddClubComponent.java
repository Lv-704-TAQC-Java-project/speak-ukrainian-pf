package com.ita.edu.speakua.ui.header.profileMenuAdmin.addClubComponent;

import com.ita.edu.speakua.ui.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class AbstractAddClubComponent extends BasePage {

    @FindBy(xpath = "//span[@aria-label='close']")
    private WebElement closeButton;

    public AbstractAddClubComponent(WebDriver driver) {
        super(driver);
    }

}
