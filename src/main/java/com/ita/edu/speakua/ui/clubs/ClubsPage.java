package com.ita.edu.speakua.ui.clubs;

import com.ita.edu.speakua.ui.clubs.card.components.CardComponent;
import com.ita.edu.speakua.ui.clubs.card.components.CenterComponent;
import com.ita.edu.speakua.ui.header.HeaderComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class ClubsPage extends HeaderComponent {

    private List<CardComponent> cards;
    private List<CenterComponent> centers;
    private PaginationComponent paginationComponent;
    private SortClubComponent sortClubComponent;

    @FindBy(xpath = "//span[@aria-label='search']")
    private WebElement searchIcon;

    @FindBy(xpath = "//div[@class = 'content-clubs-list content-clubs-block']")
    private WebElement blockCardContainer;

    @FindBy(xpath = "//div[@class = 'content-clubs-list false']")
    private WebElement listCardContainer;

    @FindBy(xpath = "//span[contains(@class, 'anticon-control')]")
    private WebElement advancedSearchButton;

    @FindBy(xpath = "//div[contains(@class, 'card-body')]")
    private List<WebElement> cardsBody;

    @FindBy(xpath = "//input[contains(@id, 'rc_select')]")
    private WebElement searchInput;

    public ClubsPage(WebDriver driver) {
        super(driver);
        waitInvisibility(cardsBody.get(0));
        waitVisibility(cardsBody.get(0));
    }

    @Step("Open advanced search panel")
    public AdvancedSearchPanelComponent getAdvancedSearchPanelComponent() {
        return new AdvancedSearchPanelComponent(driver);
    }

    public ExpandedCardComponent getExpandedCardComponent() {
        return new ExpandedCardComponent(driver);
    }

    public List<CardComponent> getCards() {
        this.cards = new ArrayList<>();
        for (WebElement card : cardsBody) {
            this.cards.add(new CardComponent(driver, card));
        }
        return this.cards;
    }

    public List<CenterComponent> getCenters() {
        this.centers = new ArrayList<>();
        for (WebElement center : cardsBody) {
            this.centers.add(new CenterComponent(driver, center));
        }
        return this.centers;
    }

    @Step("Open advanced search panel")
    public ClubsPage advancedSearchButtonClick() {
        clickManagingClubsPageElement(advancedSearchButton);
        return this;
    }

    public PaginationComponent getPaginationComponent() {
        paginationComponent = new PaginationComponent(driver);
        return paginationComponent;
    }

    public PaginationComponent openPaginationComponent() {
        return getPaginationComponent().waitForPaginationComponentToOpen();
    }

    public SortClubComponent getSortClubComponent() {
        if (sortClubComponent == null) {
            sortClubComponent = new SortClubComponent(driver);
        }
        return sortClubComponent;
    }

    public boolean isBlockCardContainerDisplayed() {
        try {
            blockCardContainer.isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isListCardContainerDisplayed() {
        try {
            listCardContainer.isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void clickManagingClubsPageElement(WebElement element) {
        List<CardComponent> cards = new ClubsPage(driver).getCards();
        element.click();
        driver.manage().timeouts().implicitlyWait(SHORT_TIMEOUT);
        for (CardComponent card : cards) {
            try {
                waitStaleness(card.getCardBody());
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

    @Step("Is closed advanced search panel")
    public boolean isDisappearsAdvancedSearchPanelComponent() {
        return advancedSearchButton == null;
    }

    public ClubsPage fillInSearch(String query) {
        clearInput(searchInput);
        searchInput.click();
        for (int i = 0; i < query.length(); i++) {
            if (i == 1) {
                waitForCardsRefresh(1000, 100);
            }
            if (i < query.length() - 1) {
                searchInput.sendKeys(query.substring(i, i + 1));
            }
            waitForCardsRefresh(200, 50);
        }
        searchInput.sendKeys(query.substring(query.length() - 1));
        waitForCardsRefresh(1000, 100);
        waitForCardsRefresh(1000, 100);
        return this;
    }

    public ClubsPage pasteInSearch(String query) {
        clearInput(searchInput);
        searchInput.click();
        JavascriptExecutor executor = (JavascriptExecutor) driver;

        if (query.length() == 1) {
            searchInput.sendKeys(query);
        } else if (query.length() <= 50) {
            executor.executeScript(String.format("arguments[0].value='%s'", query.substring(0, query.length() - 1)), searchInput);
            searchInput.sendKeys(query.substring(query.length() - 1));
        } else {
            executor.executeScript(String.format("arguments[0].value='%s'", query.substring(0, 49)), searchInput);
            searchInput.sendKeys(query.substring(49));
        }
        waitForCardsRefresh(1000, 100);
        waitForCardsRefresh(1000, 100);
        searchIcon.click();
        waitForCardsRefresh(1000, 100);
        return this;
    }

    private void waitForCardsRefresh(long timeoutMillis, int polling) {
        try {
            getCards().get(0).waitNameRefresh(timeoutMillis, polling);
        } catch (TimeoutException | StaleElementReferenceException ignore) {
        }
    }

    public int getSearchInputLength() {
        return searchInput.getAttribute("value").length();
    }
}
