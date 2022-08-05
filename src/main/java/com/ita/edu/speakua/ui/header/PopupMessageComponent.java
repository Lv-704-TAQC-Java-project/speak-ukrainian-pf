package com.ita.edu.speakua.ui.header;

import com.ita.edu.speakua.ui.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class PopupMessageComponent extends BasePage {

    @FindBy(xpath = "//div[@class='ant-message']//span[contains(text(), 'успішно')]")
    WebElement loginSuccessPopupMessage;

    @FindBy(xpath = "//div[@class='ant-message']//span[contains(text(), 'невірний')]")
    WebElement loginErrorPopupMessage;

    public PopupMessageComponent(WebDriver driver) {
        super(driver);
    }

    public String getLoginSuccessPopupMessageText() {
        return loginSuccessPopupMessage.getText();
    }

    public String getLoginErrorPopupMessageText() {
        return loginErrorPopupMessage.getText();
    }


}