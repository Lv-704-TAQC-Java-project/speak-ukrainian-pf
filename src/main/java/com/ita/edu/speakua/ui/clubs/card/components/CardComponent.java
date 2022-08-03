package com.ita.edu.speakua.ui.clubs.card.components;

import com.ita.edu.speakua.ui.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public class CardComponent extends BasePage {

    protected DefaultElementLocatorFactory cardParentContext;

    @FindBy(xpath =  ".//div[@class='title']")
    protected WebElement cardTitle;

    public CardComponent(WebDriver driver, WebElement cardBody) {
        super(driver);
        cardParentContext = new DefaultElementLocatorFactory(cardBody);
        PageFactory.initElements(cardParentContext, this);
    }



}
