package db;

import domain.Candidate;

import java.io.InputStream;
import java.util.List;

public interface CandidateDAO {

    void createCandidate(Candidate candidate);
    void deleteCandidate(Candidate candidate);
    void updateCandidate(Candidate candidate);
    List<Candidate> readAllCandidates();
    InputStream getCV(Candidate candidate);

}
