package com.ita.edu.speakua.ui.clubs.card.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CenterComponent extends CardComponent{

    @FindBy(xpath = ".//div[contains(@class, 'center-name')]")
    private WebElement centerName;

    public CenterComponent(WebDriver driver, WebElement cardBody) {
        super(driver, cardBody);
    }

    public WebElement getCenterName() {
        return centerName;
    }
}
