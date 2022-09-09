package com.ita.edu.speakua.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyProvider {
    private final Properties properties;

    public PropertyProvider() {
        properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/config.properties");
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getJDBCLogin() {
        return properties.getProperty("jdbc_login");
    }

    public String getJDBCPassword() {
        return properties.getProperty("jdbc_password");
    }

    public String getJDBCUrl() {
        return properties.getProperty("jdbc_url");
    }
    public String getAPIBaseUrl() {
        return properties.getProperty("api_base_url");
    }
}
