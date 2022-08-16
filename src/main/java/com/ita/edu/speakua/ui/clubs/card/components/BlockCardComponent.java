package com.ita.edu.speakua.ui.clubs.card.components;

import com.ita.edu.speakua.ui.clubs.SortClubComponent;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BlockCardComponent extends CardComponent {

    public BlockCardComponent(WebDriver driver, WebElement cardBody) {
        super(driver, cardBody);
    }
}
