package db;


import configuration.CandidateProperties;

import java.sql.*;

public class MySqlUtil extends DbUtil{

    private MySqlUtil() {
    }

    static Connection openConnection(CandidateProperties properties) {

        Connection connection = null;

        try {
            Class.forName(properties.getMysqlDriver());
            try {
                connection = DriverManager.getConnection(properties.getMysqlUrl(),
                        properties.getMysqlUsername(), properties.getMysqlPassword());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return connection;
    }

}

