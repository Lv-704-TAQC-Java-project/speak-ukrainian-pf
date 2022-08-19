package com.ita.edu.speakua.ui;

import com.ita.edu.speakua.ui.header.Header;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewsPage extends Header {

    @FindBy(xpath = "//div[@class='global-padding news-content']")
    WebElement pageIdentifier;


    public NewsPage(WebDriver driver) {
        super(driver);
    }


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
