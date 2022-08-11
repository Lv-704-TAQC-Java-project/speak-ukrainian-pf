package com.ita.edu.speakua.ui.header.profileMenuAdmin.administrationComponent.addTask;

import com.ita.edu.speakua.ui.BaseMethods;
import com.ita.edu.speakua.ui.header.HeaderComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TaskPage extends HeaderComponent {

    @FindBy(xpath = "//a[contains(text(),'Додати завдання')]/ancestor::button")
    WebElement addTaskBtn;

    public TaskPage(WebDriver driver) {
        super(driver);
    }

    public AddTaskPage clickAddTaskBtn(){
        waitVisibilityOfWebElement(addTaskBtn);
        addTaskBtn.click();
        return new AddTaskPage(driver);
    }
}
