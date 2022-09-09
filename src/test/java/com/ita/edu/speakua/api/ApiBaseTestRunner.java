package com.ita.edu.speakua.api;

import com.ita.edu.speakua.utils.ConfigProperties;
import org.testng.annotations.BeforeSuite;

public class ApiBaseTestRunner {
    protected static ConfigProperties properties;

    @BeforeSuite
    public void beforeSuite() {
        if(properties == null) {
            properties = new ConfigProperties();
        }
    }
}
