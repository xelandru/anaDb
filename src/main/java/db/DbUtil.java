package db;


import configuration.CandidateProperties;

import java.sql.*;

final class DbUtil {

    private DbUtil() {
    }

    static Connection openConnection(CandidateProperties properties) {

        Connection connection = null;

        try {
            Class.forName(properties.getDBDriver());
            try {
                connection = DriverManager.getConnection(properties.getDBUrl(),
                        properties.getDBUserName(), properties.getDBPassword());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return connection;
    }

    static void closeConnection(Connection connection) {
        if (connection != null)
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    static PreparedStatement prepareStatement(Connection connection, boolean returnGeneratedKeys,
                                              String sql, Object[] values) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql,
                    returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        setValues(statement, values);
        return statement;


    }

    static PreparedStatement prepareStatement(Connection connection, boolean returnGeneratedKeys,
                                              String sql) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql,
                    returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;


    }

    private static void setValues(PreparedStatement statement, Object... values) {
        for (int i = 0; i < values.length; i++) {
            try {
                statement.setObject(i + 1, values[i]);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    static int executeQuery(PreparedStatement statement) {

        int num = 0;
        try {
            num = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return num;
    }

    static ResultSet updateQuery(PreparedStatement statement) {

        ResultSet resultSet = null;

        try {
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;
    }
}

