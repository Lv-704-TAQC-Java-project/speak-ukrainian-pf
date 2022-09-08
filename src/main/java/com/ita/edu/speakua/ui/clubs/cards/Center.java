package com.ita.edu.speakua.ui.clubs.cards;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Center extends Club {

    @FindBy(xpath = ".//div[contains(@class, 'center-name')]")
    private WebElement centerName;

    public Center(WebDriver driver, WebElement cardBody) {
        super(driver, cardBody);
    }

    public WebElement getCenterName() {
        wait.visibility(centerName);
        return centerName;
    }

    public String getTextCenterName() {
//        wait.sleep(2000);
        return getCenterName().getText();
    }
}
