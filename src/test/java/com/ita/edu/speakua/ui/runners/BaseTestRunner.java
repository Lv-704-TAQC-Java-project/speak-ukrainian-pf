package com.ita.edu.speakua.ui.runners;

import com.ita.edu.speakua.utils.TestNgListener;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.util.ArrayList;
import java.util.List;

@Listeners(TestNgListener.class)
public class BaseTestRunner extends BaseSetupTestRunner {

    @BeforeMethod
    public void setUp(ITestContext context) {
        List<String> arguments = new ArrayList<>();
        arguments.add("--lang=uk-UA");
        arguments.add("--window-size=1920,1080");
        setDriver(arguments, context);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}