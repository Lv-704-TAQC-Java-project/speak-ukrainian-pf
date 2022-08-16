package com.ita.edu.speakua.ui.runners;

import com.ita.edu.speakua.ui.utils.ConfigProperties;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;


public class BaseTestRunner extends BaseSetupTestRunner{

    @BeforeMethod
    public void setUp(ITestContext context) {
       setDriver(context);
    }
}

