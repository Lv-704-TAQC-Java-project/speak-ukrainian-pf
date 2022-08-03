package utils;

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

    public String getUserEmail() {
        return properties.getProperty("VALID_USER_EMAIL");
    }

    public String getUserPassword() {
        return properties.getProperty("VALID_USER_PASSWORD");
    }

    public String getBaseUrl() {
        return properties.getProperty("BASE_URL");
    }

}
