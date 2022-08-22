package com.ita.edu.speakua.ui.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class ElementAction {
    private final WebDriver driver;
    private final Actions actions;

    public ElementAction(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
    }

    public WebElement clearInput(WebElement element) {
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"),
                Keys.BACK_SPACE);
        return element;
    }

    public void setNewValueForInput(WebElement element, String value) {
        clearInput(element).sendKeys(value);
    }

    public void click(WebElement element) {
        actions.moveToElement(element).click().perform();
    }

    public void scrollTo(WebElement element) {
        actions.scrollToElement(element).perform();
    }

    public WebElement safeFind(String xpath) {
        WebElement element = null;
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
            element = driver.findElement(By.xpath(xpath));
        } catch (NoSuchElementException ignored) {
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        return element;
    }

    public boolean isVisible(WebElement element) {
        try {
            return element.isDisplayed() && element.isEnabled();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
