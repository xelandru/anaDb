package db;



import configuration.CandidateProperties;
import domain.Candidate;
import exceptions.DAOException;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOImpl implements CandidateDAO {

    private CandidateProperties properties;

    public DAOImpl() {

        properties = new CandidateProperties();
    }

    private static final String createCandidate =
            "INSERT INTO candidates(id, firstName, lastName, eMail, phoneNumber, hireDate, linkedIn, cv) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String deleteCandidate = "DELETE FROM candidates WHERE id = ?";

    private static final String updateCandidate = "UPDATE candidates SET firstName = ?, lastName = ?, eMail = ?, " +
            "phoneNumber = ?, hireDate = ?, linkedIn = ?, cv = ? WHERE id = ?";

    private static final String getAllCandidates = "SELECT * from candidates ORDER BY id";

    private static final String getCV = "SELECT cv from candidates WHERE id = ?";


    public void createCandidate(Candidate candidate) {

        if (candidate.getId() != null)
            throw new IllegalArgumentException("User id already in use");

        Object[] values = {
                candidate.getId(),
                candidate.getFirstName(),
                candidate.getLastName(),
                candidate.getEmail(),
                candidate.getPhoneNumber(),
                candidate.getHireDate(),
                candidate.getLinkedIn(),
                candidate.getCv()
        };

        Connection connection = DbUtil.openConnection(properties);
        PreparedStatement statement = DbUtil.prepareStatement(connection, true, createCandidate, values);
        int affectedRows = DbUtil.executeQuery(statement);
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
        DbUtil.closeConnection(connection);
    }

    public void deleteCandidate(Candidate candidate) {

        Object[] values = {
                candidate.getId()
        };

        Connection connection = DbUtil.openConnection(properties);
        PreparedStatement statement = DbUtil.prepareStatement(connection, false, deleteCandidate, values);
        int affectedRows = DbUtil.executeQuery(statement);
        if (affectedRows == 0)
            throw new DAOException("Deleting user failed, no rows affected");
        else
            candidate.setId(null);

        DbUtil.closeConnection(connection);
    }

    public void updateCandidate(Candidate candidate) {
        if (candidate.getId() == null)
            throw new IllegalArgumentException("User is not created yet, the user ID is null.");

        Object[] values = {
                candidate.getFirstName(),
                candidate.getLastName(),
                candidate.getEmail(),
                candidate.getPhoneNumber(),
                candidate.getHireDate(),
                candidate.getLinkedIn(),
                candidate.getCv(),
                candidate.getId()
        };

        Connection connection = DbUtil.openConnection(properties);
        PreparedStatement statement = DbUtil.prepareStatement(connection, true, updateCandidate, values);
        int affectedRows = DbUtil.executeQuery(statement);
        if (affectedRows == 0)
            throw new DAOException("Updating user failed, no rows affected.");

        DbUtil.closeConnection(connection);


    }

    public List<Candidate> readAllCandidates() {

        List<Candidate> candidates = new ArrayList<>();

        Connection connection = DbUtil.openConnection(properties);
        PreparedStatement statement = DbUtil.prepareStatement(connection, true, getAllCandidates);
        ResultSet resultSet = DbUtil.updateQuery(statement);

        try {
            while (resultSet.next()) {
                candidates.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        DbUtil.closeConnection(connection);

        return candidates;

    }

    public InputStream getCV(Candidate candidate) {

        if (candidate.getId() == null)
            throw new IllegalArgumentException("User does not have an ID");

        Object[] values = {
                candidate.getId()
        };

        Connection connection = DbUtil.openConnection(properties);
        PreparedStatement statement = DbUtil.prepareStatement(connection, true, getCV, values);
        ResultSet resultSet = DbUtil.updateQuery(statement);
        InputStream binaryStream = null;
        Blob blob= null;

        try {
            if(resultSet.next())
                 blob = resultSet.getBlob("cv");
            if (blob != null) {
                binaryStream = blob.getBinaryStream();
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        DbUtil.closeConnection(connection);
        return binaryStream;
    }

    private static Candidate map(ResultSet resultSet) {
        Candidate candidate = new Candidate();

        try {
            candidate.setId(resultSet.getInt("id"));
            candidate.setFirstName(resultSet.getString("firstname"));
            candidate.setLastName(resultSet.getString("lastname"));
            candidate.setEmail(resultSet.getString("email"));
            candidate.setPhoneNumber(resultSet.getString("phoneNumber"));
            candidate.setHireDate(resultSet.getDate("hireDate"));
            candidate.setLinkedIn(resultSet.getString("linkedIn"));
//        candidate.setCv(new FileInputStream(resultSet.get("cv")));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return candidate;
    }
}
