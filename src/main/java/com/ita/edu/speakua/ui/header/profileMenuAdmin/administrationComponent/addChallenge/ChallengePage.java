package com.ita.edu.speakua.ui.header.profileMenuAdmin.administrationComponent.addChallenge;

import com.ita.edu.speakua.ui.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ChallengePage extends BasePage {
    @FindBy(xpath = "//a[contains(text(),'Додати челендж')]/ancestor::button")
    WebElement addChallengeBtn;

    public ChallengePage(WebDriver driver) {
        super(driver);
    }

    public AddChallengePage clickAddChallengeBtn(){
        addChallengeBtn.click();
        return new AddChallengePage(driver);
    }
}
