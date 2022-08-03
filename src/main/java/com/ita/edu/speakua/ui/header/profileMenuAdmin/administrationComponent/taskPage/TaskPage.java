package com.ita.edu.speakua.ui.header.profileMenuAdmin.administrationComponent.taskPage;

import com.ita.edu.speakua.ui.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TaskPage extends BasePage {

    @FindBy(xpath = "//a[contains(text(),'Додати завдання')]/ancestor::button")
    WebElement addTaskBtn;

    public TaskPage(WebDriver driver) {
        super(driver);
    }

    public AddTaskPage clickAddTaskBtn(){
        addTaskBtn.click();
        return new AddTaskPage(driver);
    }
}
