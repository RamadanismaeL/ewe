package ram.ewe.englishwithexperts.Repositories;

import ram.ewe.englishwithexperts.Data.ConnectionDAO;
import ram.ewe.englishwithexperts.Data.ConnectionLITE;
import ram.ewe.englishwithexperts.Repositories.Interface.IUserLogs;
import ram.ewe.englishwithexperts.Models.Model;

import java.sql.*;

/**
 * @author Ramadan Ismael
 */
public class UserDataRepository implements IUserLogs {
    private Connection _connect = null;
    private Connection _connectLITE = null;
    private PreparedStatement _prepare = null;
    private ResultSet _rs = null;

    public UserDataRepository() throws SQLException
    {
        _connect = ConnectionDAO.connectDAO();
        _connectLITE = ConnectionLITE.connectLite();
    }

    public void setUserDataModel(String fullName, String username, String profile)
    {
        if(_connect == null)
        {
            Model.getInstance().getAlertsFactory().getOnErroDataBase();
        }
        else
        {
            String sql = "UPDATE tbUser_controller set NOME = ?, USUÁRIO = ?, PERFIL = ? WHERE ID = 1";
            try
            {
                _prepare = _connect.prepareStatement(sql);
                _prepare.setString(1, fullName);
                _prepare.setString(2, username);
                _prepare.setString(3, profile);
                _prepare.executeUpdate();
            }
            catch(SQLException erro)
            {
                erro.printStackTrace();
            }
        }
    }

    public String getFullName()
    {
        if(_connect == null)
        {
            Model.getInstance().getAlertsFactory().getOnErroDataBase();
        }
        else
        {
            String sql = "SELECT NOME FROM tbUser_controller WHERE ID = 1";

            try
            {
                _prepare = _connect.prepareStatement(sql);
                _rs = _prepare.executeQuery();
                if (_rs.next()) {
                    return _rs.getString("NOME");
                }
            }
            catch (SQLException erro)
            {
                erro.printStackTrace();
            }
        }
        return null;
    }

    public String getUserName()
    {
        if(_connect == null)
        {
            Model.getInstance().getAlertsFactory().getOnErroDataBase();
        }
        else
        {
            String sql = "SELECT USUÁRIO FROM tbUser_controller WHERE ID = 1";

            try
            {
                _prepare = _connect.prepareStatement(sql);
                _rs = _prepare.executeQuery();
                if(_rs.next())
                {
                    return _rs.getString("USUÁRIO");
                }
            }
            catch (SQLException error)
            {
                error.printStackTrace();
            }
        }
        return null;
    }

    public String getProfile()
    {
        if(_connect == null)
        {
            Model.getInstance().getAlertsFactory().getOnErroDataBase();
        }
        else {
            String sql = "SELECT PERFIL FROM tbUser_controller WHERE ID = 1";
            try {
                _prepare = _connect.prepareStatement(sql);
                _rs = _prepare.executeQuery();
                if (_rs.next()) {
                    return _rs.getString("PERFIL");
                }
            } catch (SQLException erro) {
                erro.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void setUserLogs(String username, String profile, String description) {
        String sql = "INSERT INTO tbUserLogs(USUARIO, PERFIL, DESCRICAO) VALUES(?, ?, ?)";

        try
        {
            if(_connectLITE == null)
            {
                Model.getInstance().getAlertsFactory().getOnErroDataBase();
            }
            else
            {
                _prepare = _connectLITE.prepareStatement(sql);
                _prepare.setString(1, username);
                _prepare.setString(2, profile);
                _prepare.setString(3, description);
                _prepare.executeUpdate();
                System.out.println("log registrado com sucesso!");
            }
        }
        catch (SQLException erro)
        {
            erro.printStackTrace();
        }
    }
}
