package com.ita.edu.speakua.ui.header.profileMenuAdmin.addCenterModal;

import com.ita.edu.speakua.ui.BasePage;
import com.ita.edu.speakua.ui.HomePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class AbstractAddCenterStep extends BasePage {

    @FindBy(xpath = "//span[@aria-label='close']")
    private WebElement closeButton;

    public AbstractAddCenterStep(WebDriver driver) {
        super(driver);
    }

    @Step("Close the model with the addition of centers")
    public HomePage close() {
        closeButton.click();
        return new HomePage(driver);
    }
}
