package com.ita.edu.speakua.ui.header.profileMenuAdmin.administrationComponent;

import com.ita.edu.speakua.ui.BaseMethods;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.administrationComponent.addChallenge.AddChallengePage;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.administrationComponent.addChallenge.ChallengePage;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.administrationComponent.addTask.AddTaskPage;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.administrationComponent.addTask.TaskPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdministrationComponent extends BaseMethods {

    @FindBy(xpath = "//a[contains(text(),'Завдання')]")
    WebElement taskBtn;
    @FindBy(xpath = "//a[contains(text(),'Челенджі')]")
    WebElement challengeBtn;

    public AdministrationComponent(WebDriver driver) {
        super(driver);
    }

    public TaskPage clickTaskBtn(){
        waitVisibilityOfWebElement(taskBtn);
        taskBtn.click();
        return new TaskPage(driver);
    }

    public ChallengePage clickChallengeBtn(){
        challengeBtn.click();
        return new ChallengePage(driver);
    }

}
