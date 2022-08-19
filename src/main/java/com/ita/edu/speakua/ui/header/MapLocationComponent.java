package com.ita.edu.speakua.ui.header;

import com.ita.edu.speakua.ui.utils.BaseMethods;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;


public class MapLocationComponent extends BaseMethods {
    @FindBy(xpath = "//button[contains(@class,'show-map-button')]")
    private WebElement showOnMapButton;

    @FindBy(xpath = "//input[@id='mapCitiesList']/ancestor::div[@class='ant-select-selector']")
    private WebElement locationMenuButton;

    public MapLocationComponent(WebDriver driver) {
        super(driver);
    }
}
