package com.ita.edu.speakua.ui.runners;

import com.ita.edu.speakua.ui.utils.TestNgListener;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

@Listeners(TestNgListener.class)
public class SameWindowTestRunner extends BaseSetupTestRunner {

    @BeforeClass
    public void setUp(ITestContext context) {
        List<String> arguments = new ArrayList<>();
        arguments.add("--lang=uk-UA");
//        arguments.add("--headless");
//        arguments.add("--window-size=1920,1080");
//        arguments.add( "--no-sandbox" );
//        arguments.add("--disable-dev-shm-usage");
        setDriver(arguments, context);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}

