package com.ita.edu.speakua.ui.runners;

import com.ita.edu.speakua.ui.utils.ConfigProperties;
import com.ita.edu.speakua.ui.utils.TestNgListener;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.time.Duration;

@Listeners(TestNgListener.class)
public class SameWindowTestRunner {
    protected static ConfigProperties configProps;
    protected WebDriver driver;

    @BeforeSuite
    public void beforeSuite() {
        WebDriverManager.chromedriver().setup();
        configProps = new ConfigProperties();
    }

    @BeforeClass
    public void setUp(ITestContext context) {
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");
//        options.addArguments("--window-size=1920,1080", "--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        context.setAttribute("myDriver", driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(configProps.getBaseUrl());
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}

