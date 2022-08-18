package com.ita.edu.speakua.ui;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BaseMethods {

    protected final Duration TIMEOUT = Duration.ofSeconds(12);
    protected final Duration SHORT_TIMEOUT = Duration.ofSeconds(3);
    protected WebDriver driver;
    private Actions actions;

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

    public void waitPageReload() {
        try {
            WebDriverWait wait = new WebDriverWait(this.driver, TIMEOUT);
            wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState;").equals("loading"));
            wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState;").equals("complete"));
        } catch (Throwable error) {
            error.printStackTrace();
        }
    }

    public void waitAttributeValue(WebElement element, String attribute, String value) {
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

    public void waitClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitVisibility(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitVisibility(WebElement element) {
        waitVisibility(element, TIMEOUT.getSeconds());
    }

    public void waitVisibility(WebElement element, long seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitVisibility(List<WebElement> elements) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public void waitVisibility(List<WebElement> elements, long seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public void waitInvisibility(WebElement element) {
        waitInvisibility(element, TIMEOUT.getSeconds());
    }

    public void waitInvisibility(WebElement element, long seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitInvisibility(List<WebElement> elements, long seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.invisibilityOfAllElements(elements));
    }

    public void waitStaleness(WebElement element) {
        waitStaleness(element, TIMEOUT.getSeconds());
    }

    public void waitStaleness(WebElement element, long seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.stalenessOf(element));
    }

    public void fluentWaitStaleness(WebElement element, long timeoutMillis, int polling) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofMillis(timeoutMillis))
                .pollingEvery(Duration.ofMillis(polling))
                .ignoring(NoSuchElementException.class);
        try {
            wait.until(ExpectedConditions.stalenessOf(element));
        } catch (TimeoutException ignore) {
        }
    }

    public void fluentWaitVisibility(WebElement element, long timeoutMillis, int polling) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofMillis(timeoutMillis))
                .pollingEvery(Duration.ofMillis(polling))
                .ignoring(NoSuchElementException.class)
                .ignoring(TimeoutException.class);
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException ignore) {
        }
    }

    public void waitValue(WebElement element, String value, long seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.attributeContains(element, "value", value));
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

    public void waitVisibilityOfWebElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void waitAttributeOfElementContains(WebElement element, String attribute, String value) {
        WebDriverWait wait = new WebDriverWait(driver, SHORT_TIMEOUT);
        wait.until(ExpectedConditions.attributeContains(element, attribute, value));
    }

    public void setNewValueForInput(WebElement element, String value) {
        clearInput(element).sendKeys(value);
    }

    public void actionsClickOnElement(WebElement element) {
        actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
    }

    public void scrollTo(WebElement element) {
        actions = new Actions(driver);
        actions.scrollToElement(element).perform();
    }

    public void waitStalenessOfPreviousErrors(List<WebElement> errors, long seconds) {
        if (errors.size() > 0) {
            for (WebElement error : errors) {
                try {
                    waitStaleness(error, seconds);
                } catch (TimeoutException ignore) {
                }
            }
        }
    }

    public WebElement safeFind(String xpath) {
        WebElement element = null;
        try {
            element = driver.findElement(By.xpath( xpath));
        } catch (NoSuchElementException ignored) {
        }
        return element;
    }
}
