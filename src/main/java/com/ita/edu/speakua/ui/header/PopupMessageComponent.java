package com.ita.edu.speakua.ui.header;

import com.ita.edu.speakua.ui.BaseMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class PopupMessageComponent extends BaseMethods {

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
