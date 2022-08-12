package com.ita.edu.speakua.ui.clubs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PaginationComponent extends ClubsPage {
    @FindBy(xpath = "//div[contains(@class, 'ant-card-body')]")
    private List<WebElement> listOfClubsOnCurrentPage;

    @FindBy(xpath = "//li[contains(@class, 'ant-pagination-item')]")
    private List<WebElement> listOfPagesInPagination;


    @FindBy(xpath = "//ul[contains(@class, 'pagination')]")
    private WebElement paginationContainer;

    @FindBy(xpath = "")
    private WebElement currentPageButton;//Check

    @FindBy(xpath = "//li[contains(@class, 'pagination-item') and @title=1]")
    private WebElement firstPageButton;

    private WebElement lastPaginationPage;


    public PaginationComponent(WebDriver driver) {
        super(driver);
    }

    public WebElement getLastPaginationPage() {
//        sleep(3000);
        lastPaginationPage = listOfPagesInPagination.get(listOfPagesInPagination.size() - 1);
//        lastPaginationPage = driver
//                .findElements(By.xpath("//li[contains(@class, 'ant-pagination-item')]"))
//                .get(listOfPagesInPagination.size() - 1);
        return lastPaginationPage;
    }

    public int getLastPaginationPageNumber() {
//        if (getLastPaginationPage().isDisplayed()) {
//            waitStalenessOfElement(getLastPaginationPage());
//        } else {
//            waitVisibilityOfWebElement(getLastPaginationPage());
//        }
        return Integer.parseInt(getLastPaginationPage().getText());
    }

    public void clickFirstPageButton() {
        clickManagingClubsPageElement(firstPageButton);
    }

    public int getQuantityOfClubsOnCurrentPage() {
        return listOfClubsOnCurrentPage.size();
    }

    //Check
    public int getNumberOfPagesInPagination() {
        return Integer.parseInt(getLastPaginationPage().findElement(By.xpath("./child::a")).getText());
    }

    public PaginationComponent openLastPageInPagination() {
        getLastPaginationPage().click();
        return this.waitForPageToRefresh();
    }

    public PaginationComponent waitForPaginationComponentToOpen() {
        waitForPageToReload();
        return this;
    }

    public PaginationComponent waitForPageToRefresh() {
        WebElement firstClubPOnPage = listOfClubsOnCurrentPage.get(0);
        waitInvisibilityOfElement(firstClubPOnPage);
        return this;
    }
}
