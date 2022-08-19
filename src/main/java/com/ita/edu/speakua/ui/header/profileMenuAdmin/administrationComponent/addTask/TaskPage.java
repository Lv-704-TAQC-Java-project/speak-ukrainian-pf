package com.ita.edu.speakua.ui.header.profileMenuAdmin.administrationComponent.addTask;

import com.ita.edu.speakua.ui.header.Header;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class TaskPage extends Header {
    @FindBy(xpath = "//div[@class='task-header']")
    private WebElement name;

    @FindBy(xpath = "//div[@class='header-content']//div[@class='task-text']")
    private WebElement title;

    @FindBy(xpath = "//div[@class='task-content']//p")
    private WebElement description;

    public TaskPage(WebDriver driver) {
        super(driver);
    }

    public String getNameText() {
        waitVisibility(name);
        return name.getText();
    }

    public String getTitleText() {
        waitVisibility(title);
        return title.getText();
    }

    public String getDescriptionText() {
        waitVisibility(description);
        return description.getText();
    }
}