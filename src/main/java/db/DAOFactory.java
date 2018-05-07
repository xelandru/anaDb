package db;


public abstract class DAOFactory {

    public static CandidateDAO getDAOInstance(DBs dBs) {

        switch (dBs) {
            case DERBY:
                return new DAODerbyImpl();
            case MYSQL:
                return new DAOMySQLImpl();
            default:
                return null;
        }
    }
}
