package com.ita.edu.speakua.ui.header;

import com.ita.edu.speakua.ui.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class PopupMessage extends BasePage {

    @FindBy(xpath = "//div[@class='message']//span[contains(text(), 'успішно')]")
    WebElement loginSuccessPopupMessage;

    @FindBy(xpath = "//div[@class='message']//span[contains(text(), 'невірний')]")
    WebElement loginErrorPopupMessage;

    public PopupMessage(WebDriver driver) {
        super(driver);
    }

    public String getSuccessPopupMessageText() {
        return loginSuccessPopupMessage.getText();
    }

    public String getErrorPopupMessageText() {
        return loginErrorPopupMessage.getText();
    }
}
