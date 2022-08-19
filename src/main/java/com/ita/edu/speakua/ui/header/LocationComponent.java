package com.ita.edu.speakua.ui.header;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;


public class LocationComponent extends Header {
    @FindBy(xpath = "//div[contains(@class, 'city') and contains(@class, 'dropdown')]")
    WebElement locationMenuButton;

    @FindBy(xpath = "//div[contains(@class, 'dropdown')]//ul")
    WebElement locationMenu;

    @FindBy(xpath = "//div[contains(@class, 'dropdown') and not(contains(@class, 'hidden'))]//li[contains(@class, 'menu-item')]")
    List<WebElement> locationList;


    public LocationComponent(WebDriver driver) {
        super(driver);
    }


    public LocationComponent clickLocationMenuButton() {
        locationMenuButton.click();
        return this;
    }
}
