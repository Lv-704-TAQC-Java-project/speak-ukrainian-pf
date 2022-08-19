package com.ita.edu.speakua.ui.runners;

import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;


public class BaseTestRunner extends BaseSetupTestRunner{

    @BeforeMethod
    public void setUp(ITestContext context) {
       setDriver(context);
    }
}

