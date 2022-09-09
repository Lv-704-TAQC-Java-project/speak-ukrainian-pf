package com.ita.edu.speakua.ui;

import com.ita.edu.speakua.ui.utils.ElementAction;
import com.ita.edu.speakua.ui.utils.ElementWait;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
    protected WebDriver driver;
    protected ElementWait wait;
    protected ElementAction action;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new ElementWait(driver);
        this.action = new ElementAction(driver);
        PageFactory.initElements(driver, this);
    }
}
