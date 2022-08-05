package com.ita.edu.speakua.ui;

import com.ita.edu.speakua.ui.header.HeaderComponent;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AboutPage extends HeaderComponent {

    // private HeaderComponent header;

    @FindBy(xpath = "//section[@class='ant-layout aboutProject global-padding']")
    private WebElement pageIdentifier;

    public AboutPage(WebDriver driver) {
        super(driver);
    }

//    public HeaderComponent getHeader() {
//        if (header == null) {
//            header = new HeaderComponent(driver);
//        }
//        return header;
//    }

    public boolean getPageIdentity() {
        try {
            if (pageIdentifier.isDisplayed()) {
                return true;
            }
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

}
