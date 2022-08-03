package com.ita.edu.speakua.ui;

import com.ita.edu.speakua.ui.header.AbstractProfileMenu;
import com.ita.edu.speakua.ui.header.HeaderComponent;
import org.openqa.selenium.WebDriver;

public class Ma extends BasePage{
    WebDriver webDriver = new WebDriver;
    public Ma(WebDriver driver) {
        super(driver);
    }

    public static void main(String[] args) {
        AbstractProfileMenu profileMenu = new HeaderComponent(driver).openProfileMenu()

    }
}
