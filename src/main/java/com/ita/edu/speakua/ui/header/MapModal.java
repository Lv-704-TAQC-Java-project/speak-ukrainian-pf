package com.ita.edu.speakua.ui.header;

import com.ita.edu.speakua.ui.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;


public class MapModal extends BasePage {
    @FindBy(xpath = "//button[contains(@class,'show-map-button')]")
    private WebElement showOnMapButton;

    @FindBy(xpath = "//input[@id='mapCitiesList']/ancestor::div[@class='ant-select-selector']")
    private WebElement locationMenuButton;

    public MapModal(WebDriver driver) {
        super(driver);
    }
}
