package com.ita.edu.speakua.ui.header.profileMenuAdmin.administrationComponent.addTask;

import com.ita.edu.speakua.ui.header.HeaderComponent;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AddTaskPage extends HeaderComponent {

    @FindBy(xpath = "//input[@id='startDate']")
    WebElement startDate;

    @FindBy(xpath = "//input[@type='file']")
    WebElement uploadImage;

    @FindBy(xpath = "//input[@id='name']")
    WebElement inputName;

    @FindBy(xpath = "//label[contains(text(),'Заголовок')]/../following-sibling::div//p")
    WebElement inputHeading;

    @FindBy(xpath = "//label[contains(text(),'Опис')]/../following-sibling::div//p")
    WebElement inputDescribing;

    @FindBy(xpath = "//input[@id='challengeId']")
    WebElement openChallenge;

    @FindBy(xpath = "//div[@class='rc-virtual-list-holder-inner']")
    WebElement challengeList;

    @FindBy(xpath = "//span[contains(text(),'Зберегти')]/ancestor::button")
    WebElement saveButton;

    @FindBy(xpath = "//div[contains(@class, 'warning')]//span[text()]")
    WebElement errorMessage;

    @FindBy(xpath = "//span[contains(text(),'Please')]")
    WebElement errorMessageIsEmpty;

    @FindBy(xpath = "//span[contains(text(),'Помилка')]")
    WebElement errorInvalidCharacters;

    @FindBy(xpath = "//span[contains(text(),'minimum of 40')]")
    WebElement errorLessThenFortyCharacters;

    @FindBy(xpath = "//span[@class='ant-upload-list-item-actions']")
    List<WebElement> imageElements;

    @FindBy(xpath = "//span[@aria-live='polite']")
    WebElement challengeInput;

    public AddTaskPage(WebDriver driver) {
        super(driver);
    }

    public AddTaskPage inputStartDate(String name){
        startDate.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        startDate.sendKeys(Keys.chord(name));
        return new AddTaskPage(driver);
    }

    public AddTaskPage inputImage(){
        uploadImage.sendKeys("D:\\smile.png");
        return this;
    }
    public AddTaskPage inputImageSecondPhoto(){
        uploadImage.sendKeys("C:\\Users\\User\\IdeaProjects\\speak-ukrainian-pf\\smile-png.png");
        return this;
    }

    public AddTaskPage inputName(String name){
        inputName.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        inputName.sendKeys(Keys.chord(name));
        return new AddTaskPage(driver);
    }

    public AddTaskPage inputHeading(String name){
        inputHeading.sendKeys(Keys.chord(Keys.CONTROL, "a"), "");
        inputHeading.sendKeys(name);
        return new AddTaskPage(driver);
    }

    public AddTaskPage inputDescribing(String name){
        inputDescribing.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        inputDescribing.sendKeys(Keys.chord(name));
        return new AddTaskPage(driver);
    }

    public AddTaskPage openChallengeList(){
        openChallenge.click();
        return new AddTaskPage(driver);
    }

    public WebElement getChallengeItem(String name){
        return challengeList.findElement(By.xpath(String.format(".//div[contains(text(),'%s')]",name)));
    }

    public AddTaskPage chooseChallenge(String name){
        openChallengeList()
                .getChallengeItem(name).click();
        return new AddTaskPage(driver);
    }

    public boolean allFieldsAreEmpty(){
        waitVisibilityOfWebElement(startDate);
        new AddTaskPage(driver)
                .openChallengeList()
                .openChallengeList();
        return  startDateIsEmpty() &&
                imageIsEmpty() &&
                nameIsEmpty() &&
                headingIsEmpty() &&
                describeIsEmpty() &&
                challengeIsEmpty();
    }

    public boolean startDateIsEmpty(){
        return startDate.getAttribute("value").isEmpty();
    }

    public boolean nameIsEmpty(){
        return inputName.getAttribute("value").isEmpty();
    }

    public boolean imageIsEmpty(){
        return imageElements.size() == 0;
    }

    public boolean headingIsEmpty(){
        return inputHeading.getText().isEmpty();
    }

    public boolean describeIsEmpty(){
        return inputDescribing.getText().isEmpty();
    }

    public boolean challengeIsEmpty(){
        return challengeInput.getText().isEmpty();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

    public boolean errorMessageIsEmptyIsVisible(){
        waitVisibilityOfElement(By.xpath("//span[contains(text(),'Please')]"));
        return errorMessageIsEmpty.isDisplayed();
    }

    public boolean errorMessageInvalidCharactersIsVisible(){
        waitVisibilityOfElement(By.xpath("//span[contains(text(),'Помилка')]"));
        return errorInvalidCharacters.isDisplayed();
    }

    public boolean errorMessageLessThenFortyCharactersIsVisible(){
        waitVisibilityOfElement(By.xpath("//span[contains(text(),'minimum of 40')]"));
        return errorLessThenFortyCharacters.isDisplayed();
    }

    public boolean errorMessageLessThenFiveCharactersIsVisible(){
        waitVisibilityOfElement(By.xpath("//span[contains(text(),'minimum of 5')]"));
        return errorLessThenFortyCharacters.isDisplayed();
    }
    public boolean errorMessageMoreThenThreeThousandCharactersIsVisible(){
        waitVisibilityOfElement(By.xpath("//span[contains(text(),'minimum of 40')]"));
        return errorLessThenFortyCharacters.isDisplayed();
    }

    public boolean errorMessageMoreThenOneHundredCharactersIsVisible(){
        waitVisibilityOfElement(By.xpath("//span[contains(text(),'minimum of 100')]"));
        return errorLessThenFortyCharacters.isDisplayed();
    }
    public boolean errorMessageIsDisplayed(){
        try {
            return errorMessage.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public TaskPage clickSaveButton(){
        saveButton.click();
        return new TaskPage(driver);
    }

    public AddTaskPage tryClickSaveButton(){
        if (errorMessageIsDisplayed())
            waitStalenessOfElement(errorMessage);
        saveButton.click();
        return new AddTaskPage(driver);
    }
}
