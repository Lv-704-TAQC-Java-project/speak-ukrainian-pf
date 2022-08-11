package com.ita.edu.speakua.ui;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BaseMethods {

    protected final Duration TIMEOUT = Duration.ofSeconds(12);
    protected final Duration SHORT_TIMEOUT = Duration.ofSeconds(3);
    protected WebDriver driver;

    public BaseMethods(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openUrl(String url) {
        driver.get(url);
    }

    public String readCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void waitForPageToReload() {
        try {
            WebDriverWait wait = new WebDriverWait(this.driver, TIMEOUT);
            wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState;").equals("loading"));
            wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState;").equals("complete"));
        } catch (Throwable error) {
            error.printStackTrace();
        }
    }

    public void waitForAttributeValueWithJS(WebElement element, String attribute, String value) {
        try {
            WebDriverWait wait = new WebDriverWait(this.driver, TIMEOUT);
            wait.until(driver -> ((JavascriptExecutor) driver)
                    .executeScript(String.format("return window.getComputedStyle(arguments[0], ':after').getPropertyValue('%s');", attribute), element)
                    .toString().equals(value));
        } catch (Throwable error) {
            error.printStackTrace();
        }
    }

    public void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitElementIsClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitVisibilityOfElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitAttributeOfElementContains(By locator, String attribute, String value) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        wait.until(ExpectedConditions.attributeContains(locator, attribute, value));
    }

    public void waitAttributeOfElementContains(WebElement element, String attribute, String value) {
        WebDriverWait wait = new WebDriverWait(driver, SHORT_TIMEOUT);
        wait.until(ExpectedConditions.attributeContains(element, attribute, value));
    }

    public void waitVisibilityOfElement(By locator, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitVisibilityOfWebElements(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public void waitVisibilityOfWebElements(List<WebElement> elements) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public void waitInvisibilityOfElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitInvisibilityOfElement(WebElement element, long seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitStalenessOfElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        wait.until(ExpectedConditions.stalenessOf(element));
    }

    public void waitVisibilityOfWebElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForTextPresentInElement(WebElement element, String text) {
        WebDriverWait wait = new WebDriverWait(driver, SHORT_TIMEOUT);
        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public WebElement clearInput(WebElement element) {
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"),
                Keys.BACK_SPACE);
        return element;
    }

    public void setNewValueForInput(WebElement element, String value) {
        clearInput(element).sendKeys(value);
    }

    public void actionsClickOnElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
    }

    public void waitStalenessOfPreviousErrors(List<WebElement> errors) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        if (errors.size() != 0) {
            for (WebElement error : errors) {
                try {
                    wait.until(ExpectedConditions.stalenessOf(error));
                } catch (TimeoutException ignore) {
                }
            }
        }
    }
}
