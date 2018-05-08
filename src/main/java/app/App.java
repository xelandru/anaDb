package app;


import db.CandidateDAO;
import db.DAOFactory;
import db.DAOMySQLImpl;
import db.DBs;
import domain.Candidate;

public class App {


    public static void main(String[] args) {

        CandidateDAO mySQL = DAOFactory.getDAOInstance(DBs.MYSQL);
        Candidate candidate = new Candidate();
        candidate.setName("alex");
        candidate.setJob("qa");
        mySQL.createCandidate(candidate);


    }
}
