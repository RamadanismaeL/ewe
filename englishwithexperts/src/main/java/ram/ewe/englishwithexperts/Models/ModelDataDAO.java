package ram.ewe.englishwithexperts.Models;

import ram.ewe.englishwithexperts.Data.ConnectionDAO;
import ram.ewe.englishwithexperts.Repositories.UserDataRepository;

import java.sql.SQLException;

public class ModelDataDAO {
    private static ModelDataDAO _instance;
    private final ConnectionDAO _connect;
    private final UserDataRepository _userDataRepository;

    private ModelDataDAO()
    {
        this._connect = new ConnectionDAO();
        try {
            this._userDataRepository = new UserDataRepository();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static synchronized ModelDataDAO getInstance()
    {
        if(_instance == null) return _instance = new ModelDataDAO();
        return _instance;
    }

    public ConnectionDAO getConnectionDAO() { return this._connect; }
    public UserDataRepository getUserDataModel() { return this._userDataRepository; }
}
