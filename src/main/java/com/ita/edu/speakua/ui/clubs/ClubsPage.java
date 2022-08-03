package com.ita.edu.speakua.ui.clubs;

import com.ita.edu.speakua.ui.BasePage;
import com.ita.edu.speakua.ui.clubs.card.components.CardComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class ClubsPage extends BasePage {

    private List<CardComponent> cards;

    @FindBy(xpath = "//div[@class = 'content-clubs-list false']")
    private WebElement blockCardContainer;

    @FindBy(xpath = "//div[@class = 'content-clubs-list content-clubs-block']")
    private WebElement listCardContainer;

    @FindBy(xpath = ".//div[contains(@class, 'card-body')]")
    private List<WebElement> cardsBody;

    public ClubsPage(WebDriver driver) {
        super(driver);
    }

    public List<CardComponent> getCards() {
        this.cards = new ArrayList<>();
        for (WebElement card : cardsBody) {
            this.cards.add(new CardComponent(driver, card));
        }
        return this.cards;
    }


}
