package com.ita.edu.speakua.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigProperties {
    private final Properties properties;

    public ConfigProperties() {
        properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("src/test/resources/config.properties");
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getAdminEmail() {
        return properties.getProperty("ADMIN_EMAIL");
    }

    public String getAdminPassword() {
        return properties.getProperty("ADMIN_PASSWORD");
    }

    public String getManagerEmail() {
        return properties.getProperty("MANAGER_EMAIL");
    }

    public String getManagerPassword() {
        return properties.getProperty("MANAGER_PASSWORD");
    }

    public String getUserEmail() {
        return properties.getProperty("USER_EMAIL");
    }

    public String getUserPassword() {
        return properties.getProperty("USER_PASSWORD");
    }

    public String getBaseUrl() {
        return properties.getProperty("BASE_URL");
    }
}