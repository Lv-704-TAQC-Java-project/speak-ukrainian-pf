package com.ita.edu.speakua.ui.runners;

import com.ita.edu.speakua.ui.HomePage;
import com.ita.edu.speakua.utils.ConfigProperties;
import com.ita.edu.speakua.utils.TestNgListener;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

@Listeners(TestNgListener.class)
public class BaseSetupTestRunner {
    protected static ConfigProperties configProps;
    protected WebDriver driver;

    @BeforeSuite
    public void beforeSuite() {
        WebDriverManager.chromedriver().setup();
        configProps = new ConfigProperties();
    }


    public void setDriver(List<String> arguments, ITestContext context) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080", "--no-sandbox", "--disable-dev-shm-usage");
        options.addArguments(arguments);
        driver = new ChromeDriver(options);
        context.setAttribute("myDriver" + Thread.currentThread().getId(), driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(configProps.getBaseUrl());
    }

    public void setDriver(ITestContext context) {
        driver = new ChromeDriver();
        context.setAttribute("myDriver" + Thread.currentThread().getId(), driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(configProps.getBaseUrl());
    }

    public void signInAsAdmin() {
        getHomePage()
                .openGuestProfileMenu()
                .openSignInModal()
                .enterEmail(configProps.getAdminEmail())
                .enterPassword(configProps.getAdminPassword())
                .submit();
    }

    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public HomePage getHomePage() {
        return new HomePage(driver);
    }
}
