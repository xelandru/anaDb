package configuration;


import exceptions.ConfigurationException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CandidateProperties  extends Properties{

    private static final String PROPERTIES_FILE = "configuration.properties";
    private static final Properties PROPERTIES = new Properties();

    private static final String DB_USERNAME = "db.username";
    private static final String DB_PASSWORD = "db.password";
    private static final String DB_URL = "db.url";
    private static final String DB_DRIVER = "db.driver";

    private static final String MAIL_USERNAME = "mail.username";
    private static final String MAIL_PASSWORD = "mail.password";
    private static final String MAIL_SENDER_ADDRESS = "mail.sender.address";

    static {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream propertiesFile = classLoader.getResourceAsStream(PROPERTIES_FILE);

        if (propertiesFile == null) {
            throw new ConfigurationException(
                    "Properties file '" + PROPERTIES_FILE + "' is missing"
            );
        }

        try {
            PROPERTIES.load(propertiesFile);
        } catch (IOException e) {
            throw new ConfigurationException(
                    "Can not load properties file '" + PROPERTIES_FILE + "'.", e);
        }

    }

    public String getDBUserName() {

        return PROPERTIES.getProperty(DB_USERNAME);
    }

    public String getDBPassword() {

        return PROPERTIES.getProperty(DB_PASSWORD);
    }

    public String getDBUrl() {

        return PROPERTIES.getProperty(DB_URL);
    }

    public String getDBDriver() {

        return PROPERTIES.getProperty(DB_DRIVER);
    }



    public String getMailUserName() {
        return PROPERTIES.getProperty(MAIL_USERNAME);
    }

    public String getMailPassword() {
        return PROPERTIES.getProperty(MAIL_PASSWORD);
    }

    public String getMailSenderAddress() {
        return PROPERTIES.getProperty(MAIL_SENDER_ADDRESS);
    }
}

