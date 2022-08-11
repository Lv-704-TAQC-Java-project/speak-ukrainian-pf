package com.ita.edu.speakua.ui;

import com.ita.edu.speakua.ui.header.HeaderComponent;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ServicesPage extends HeaderComponent {

    @FindBy(xpath = "//section[@class='ant-layout serviceInUkr global-padding']")
    WebElement pageIdentifier;


    public ServicesPage(WebDriver driver) {
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

