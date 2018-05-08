package db;


import configuration.CandidateProperties;

import java.sql.*;

public class DerbyUtil extends DbUtil {

    private DerbyUtil() {
    }

    static Connection openConnection(CandidateProperties properties) {

        Connection connection = null;

        try {
            Class.forName(properties.getDerbyDriver());
            try {
                connection = DriverManager.getConnection(properties.getDerbyUrl(),
                        properties.getDerbyUsername(), properties.getDerbyPassword());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return connection;
    }

}

