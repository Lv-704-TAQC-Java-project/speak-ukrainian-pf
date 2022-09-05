package com.ita.edu.speakua.ui.clubs;

import com.ita.edu.speakua.ui.clubs.card.components.CardComponent;
import com.ita.edu.speakua.ui.clubs.card.components.CenterComponent;
import com.ita.edu.speakua.ui.header.Header;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClubsPage extends Header {

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

    @FindBy(xpath = "//div[@class='search']//span[contains(@class, 'anticon-search')]")
    private WebElement submitSearchButton;

    public ClubsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Get access to advanced search panel")
    public AdvancedSearchPanelComponent getAdvancedSearchPanelComponent() {
        return new AdvancedSearchPanelComponent(driver);
    }

    public ExpandedCardComponent getExpandedCardComponent() {
        return new ExpandedCardComponent(driver);
    }

    @Step("Get list of cards")
    public List<CardComponent> getCards() {
        wait.sleep(1000);
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

    @Step("Get access to sort cards component")
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
        wait.sleep(2000);
    }

    @Step("Is closed advanced search panel")
    public boolean isDisappearsAdvancedSearchPanelComponent() {
        return advancedSearchButton == null;
    }

    @Step("Clubs page: Search by {phrase}")
    public ClubsPage fillInSearch(String phrase) {
        action.clearInput(searchInput);
        searchInput.click();
        char[] chars = phrase.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            searchInput.sendKeys(String.valueOf(chars[i]));
            waitCardsRefresh(i == 0 ? 1000 : 500, 100);
        }
        submitSearchButton.click();
        waitCardsRefresh(1000, 100);
        return this;
    }

    private void waitCardsRefresh(long timeoutMillis, int polling) {
        try {
            getCards().get(0).waitNameRefresh(timeoutMillis, polling);
        } catch (TimeoutException | StaleElementReferenceException ignore) {
        }
    }

    public int getSearchInputLength() {
        return searchInput.getAttribute("value").length();
    }

    public String[] getCenterNames() {
        return this
                .getCenters()
                .stream()
                .map(CenterComponent::getTextCenterName)
                .toArray(String[]::new);
    }

    public List<String> getClubNames() {
        return this
                .getCards()
                .stream()
                .map(CardComponent::getCardName)
                .collect(Collectors.toList());
    }
}