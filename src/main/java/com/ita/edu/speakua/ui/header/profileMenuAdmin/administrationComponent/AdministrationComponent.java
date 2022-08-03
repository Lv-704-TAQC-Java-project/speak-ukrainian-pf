package com.ita.edu.speakua.ui.header.profileMenuAdmin.administrationComponent;

import com.ita.edu.speakua.ui.BasePage;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.administrationComponent.addChallenge.AddChallengePage;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.administrationComponent.taskPage.AddTaskPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdministrationComponent extends BasePage {

    @FindBy(xpath = "//a[contains(text(),'Завдання')]")
    WebElement taskBtn;
    @FindBy(xpath = "//a[contains(text(),'Челенджі')]")
    WebElement challengeBtn;

    public AdministrationComponent(WebDriver driver) {
        super(driver);
    }

    public AddTaskPage clickTaskBtn(){
        taskBtn.click();
        return new AddTaskPage(driver);
    }

    public AddChallengePage clickChallengeBtn(){
        challengeBtn.click();
        return new AddChallengePage(driver);
    }

}
