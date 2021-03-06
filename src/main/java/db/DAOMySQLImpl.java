package db;



import configuration.CandidateProperties;
import domain.Candidate;
import exceptions.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOMySQLImpl implements CandidateDAO {

    private CandidateProperties properties;

    public DAOMySQLImpl() {

        properties = new CandidateProperties();
    }

    private static final String createCandidate =
            "INSERT INTO candidates(id, name, job, mail, phone, link, info) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String deleteCandidate = "DELETE FROM candidates WHERE id = ?";

    private static final String updateCandidate = "UPDATE candidates SET name = ?, job = ?, mail = ?, " +
            "phone = ?, link = ?, info = ? WHERE id = ?";

    private static final String getAllCandidates = "SELECT * from candidates ORDER BY id";


    public void createCandidate(Candidate candidate) {

        if (candidate.getId() != null)
            throw new IllegalArgumentException("User id already in use");

        Object[] values = {
                candidate.getId(),
                candidate.getName(),
                candidate.getJob(),
                candidate.getMailAddress(),
                candidate.getPhone(),
                candidate.getLink(),
                candidate.getInfo()
        };

        Connection connection = MySqlUtil.openConnection(properties);
        PreparedStatement statement = MySqlUtil.prepareStatement(connection, true, createCandidate, values);
        int affectedRows = MySqlUtil.executeQuery(statement);
        if (affectedRows == 0)
            throw new DAOException("Creating user failed, no rows affected.");

        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                candidate.setId(generatedKeys.getInt(1));
            } else {
                throw new DAOException("Creating user failed, no generated key obtained.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        MySqlUtil.closeConnection(connection);
    }

    public void deleteCandidate(Candidate candidate) {

        Object[] values = {
                candidate.getId()
        };

        Connection connection = MySqlUtil.openConnection(properties);
        PreparedStatement statement = MySqlUtil.prepareStatement(connection, false, deleteCandidate, values);
        int affectedRows = MySqlUtil.executeQuery(statement);
        if (affectedRows == 0)
            throw new DAOException("Deleting user failed, no rows affected");
        else
            candidate.setId(null);

        MySqlUtil.closeConnection(connection);
    }

    public void updateCandidate(Candidate candidate) {
        if (candidate.getId() == null)
            throw new IllegalArgumentException("User is not created yet, the user ID is null.");

        Object[] values = {
                candidate.getId(),
                candidate.getName(),
                candidate.getMailAddress(),
                candidate.getPhone(),
                candidate.getLink(),
                candidate.getJob(),
                candidate.getInfo()
        };

        Connection connection = MySqlUtil.openConnection(properties);
        PreparedStatement statement = MySqlUtil.prepareStatement(connection, true, updateCandidate, values);
        int affectedRows = MySqlUtil.executeQuery(statement);
        if (affectedRows == 0)
            throw new DAOException("Updating user failed, no rows affected.");

        MySqlUtil.closeConnection(connection);

    }

    public List<Candidate> readAllCandidates() {

        List<Candidate> candidates = new ArrayList<>();

        Connection connection = MySqlUtil.openConnection(properties);
        PreparedStatement statement = MySqlUtil.prepareStatement(connection, true, getAllCandidates);
        ResultSet resultSet = MySqlUtil.updateQuery(statement);

        try {
            while (resultSet.next()) {
                candidates.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        MySqlUtil.closeConnection(connection);

        return candidates;

    }

    private static Candidate map(ResultSet resultSet) {
        Candidate candidate = new Candidate();

        try {
            candidate.setId(resultSet.getInt("id"));
            candidate.setName(resultSet.getString("name"));
            candidate.setMailAddress(resultSet.getString("mail"));
            candidate.setPhone(resultSet.getString("phone"));
            candidate.setLink(resultSet.getString("link"));
            candidate.setJob(resultSet.getString("job"));
            candidate.setInfo(resultSet.getString("info"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return candidate;
    }
}
