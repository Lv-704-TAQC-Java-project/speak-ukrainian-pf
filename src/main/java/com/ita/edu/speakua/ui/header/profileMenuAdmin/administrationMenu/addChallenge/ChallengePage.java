package com.ita.edu.speakua.ui.header.profileMenuAdmin.administrationMenu.addChallenge;

import com.ita.edu.speakua.ui.header.Header;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ChallengePage extends Header {
    @FindBy(xpath = "//a[contains(text(),'Додати челендж')]/ancestor::button")
    private WebElement addChallengeButton;

    @FindBy(xpath = "//tbody//tr[1]//td[2]/a")
    private WebElement lastChallengeNumber;

    public ChallengePage(WebDriver driver) {
        super(driver);
    }

    @Step("Open a model for adding challenges")
    public AddChallengePage openChallengeAddPage() {
        addChallengeButton.click();
        return new AddChallengePage(driver);
    }

    public int getLastChallengeNumber(){
        return Integer.parseInt(lastChallengeNumber.getText());
    }
}
