package com.ita.edu.speakua.ui;

import com.ita.edu.speakua.ui.clubs.ClubsPage;
import com.ita.edu.speakua.ui.clubs.card.components.CardComponent;
import com.ita.edu.speakua.ui.clubs.card.components.CenterComponent;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {

    protected final Duration TIMEOUT = Duration.ofSeconds(12);
    protected final Duration SHORT_TIMEOUT = Duration.ofSeconds(3);
    protected WebDriver driver;


    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public void openUrl(String url) {
        driver.get(url);
    }

    public String readCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void waitForPageToLoad() {
        try {
            WebDriverWait wait = new WebDriverWait(this.driver, TIMEOUT);
            wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState;").equals("complete")); //"interactive"
        } catch (Throwable error) {
            error.printStackTrace();
        }
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

    public void waitVisibilityOfElement(By locator, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitVisibilityOfElements(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public void waitVisibilityOfElements(List<WebElement> elements) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public void waitInvisibilityOfElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
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

    public void clickManagingClubsPageElement(WebElement element) {
        List<CardComponent> cards = new ClubsPage(driver).getCards();
        element.click();
        driver.manage().timeouts().implicitlyWait(SHORT_TIMEOUT);
        for (CardComponent card : cards) {
            try {
                waitStalenessOfElement(card.getCardBody());
            } catch (TimeoutException ignored) {
            }
        }
    }

    public void clickManagingCenterPageElement(WebElement element) {
        List<CenterComponent> cards = new ClubsPage(driver).getCenters();
        element.click();
        driver.manage().timeouts().implicitlyWait(SHORT_TIMEOUT);
        sleep(2000);
    }


}
