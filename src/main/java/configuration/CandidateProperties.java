package configuration;


import exceptions.ConfigurationException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CandidateProperties  extends Properties{

    private static final String PROPERTIES_FILE = "configuration.properties";

    private static final String DERBY_USERNAME = "db.username";
    private static final String DERBY_PASSWORD = "db.password";
    private static final String DERBY_URL = "db.url";
    private static final String DERBY_DRIVER = "db.driver";

    private static final String MYSQL_USERNAME = "db.username";
    private static final String MYSQL_PASSWORD = "db.password";
    private static final String MYSQL_URL = "db.url";
    private static final String MYSQL_DRIVER = "db.driver";

    private static final String MAIL_USERNAME = "mail.username";
    private static final String MAIL_PASSWORD = "mail.password";
    private static final String MAIL_SENDER_ADDRESS = "mail.sender.address";


    public CandidateProperties() {
        super();

        InputStream propertiesFile = getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE);

        if (propertiesFile == null) {
            throw new ConfigurationException(
                    "Properties file '" + PROPERTIES_FILE + "' is missing"
            );
        }

        try {
            load(propertiesFile);
        } catch (IOException e) {
            throw new ConfigurationException(
                    "Can not load properties file '" + PROPERTIES_FILE + "'.", e);
        }
    }

    public String getDerbyUsername() {

        return getProperty(DERBY_USERNAME);
    }

    public String getDerbyPassword() {

        return getProperty(DERBY_PASSWORD);
    }

    public String getDerbyUrl() {

        return getProperty(DERBY_URL);
    }

    public String getDerbyDriver() {

        return getProperty(DERBY_DRIVER);
    }

    public String getMysqlUsername() {

        return getProperty(MYSQL_USERNAME);
    }

    public String getMysqlPassword() {

        return getProperty(MYSQL_PASSWORD);
    }

    public String getMysqlUrl() {

        return getProperty(MYSQL_URL);
    }

    public String getMysqlDriver() {

        return getProperty(MYSQL_DRIVER);
    }


    public String getMailUserName() {
        return getProperty(MAIL_USERNAME);
    }

    public String getMailPassword() {
        return getProperty(MAIL_PASSWORD);
    }

    public String getMailSenderAddress() {
        return getProperty(MAIL_SENDER_ADDRESS);
    }

    public static void main(String[] args) {
        new CandidateProperties();
    }
}

