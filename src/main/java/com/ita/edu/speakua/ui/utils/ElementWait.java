package com.ita.edu.speakua.ui.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ElementWait {
    private final Duration TIMEOUT = Duration.ofSeconds(12);
    private final Duration SHORT_TIMEOUT = Duration.ofSeconds(3);
    private final WebDriver driver;
    private final WebDriverWait wait;


    public ElementWait(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, TIMEOUT);
    }

    public void pageReload() {
        try {
            wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState;").equals("loading"));
            wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState;").equals("complete"));
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

    public void clickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void visibility(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void visibility(WebElement element) {
        visibility(element, TIMEOUT.getSeconds());
    }

    public void visibility(WebElement element, long seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void visibility(List<WebElement> elements) {
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public void visibility(List<WebElement> elements, long seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public void invisibility(WebElement element) {
        invisibility(element, TIMEOUT.getSeconds());
    }

    public void invisibility(WebElement element, long seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void invisibility(List<WebElement> elements, long seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.invisibilityOfAllElements(elements));
    }

    public void staleness(WebElement element) {
        staleness(element, TIMEOUT.getSeconds());
    }

    public void staleness(WebElement element, long seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.stalenessOf(element));
    }

    public void fluentStaleness(WebElement element, long timeoutMillis, int polling) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofMillis(timeoutMillis))
                .pollingEvery(Duration.ofMillis(polling))
                .ignoring(NoSuchElementException.class);
        try {
            wait.until(ExpectedConditions.stalenessOf(element));
        } catch (TimeoutException ignore) {
        }
    }

    public void fluentVisibility(WebElement element, long timeoutMillis, int polling) {
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

    public void value(WebElement element, String value, long seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.attributeContains(element, "value", value));
    }

    public void attributeOfElementContains(WebElement element, String attribute, String value) {
        WebDriverWait wait = new WebDriverWait(driver, SHORT_TIMEOUT);
        wait.until(ExpectedConditions.attributeContains(element, attribute, value));
    }

    public void stalenessOfPreviousErrors(List<WebElement> errors, long seconds) {
        if (errors.size() > 0) {
            for (WebElement error : errors) {
                try {
                    staleness(error, seconds);
                } catch (TimeoutException ignore) {
                }
            }
        }
    }
}
