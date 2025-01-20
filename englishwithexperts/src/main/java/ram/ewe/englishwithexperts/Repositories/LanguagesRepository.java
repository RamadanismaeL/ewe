package ram.ewe.englishwithexperts.Repositories;

import ram.ewe.englishwithexperts.Data.ConnectionDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Ramadan Ismael
 */
public class LanguagesRepository
{
    private static boolean _language;

    //PORTUGUÊS
    public static boolean getPort()
    {
        String sql = "SELECT ESTADO FROM tbidioma WHERE ID = 2";
        try(Connection _connect = ConnectionDAO.connectDAO(); PreparedStatement _prepare = _connect.prepareStatement(sql))
        {
            ResultSet _rs = _prepare.executeQuery();
            if(_rs.next())
            {
                _language = _rs.getBoolean("ESTADO");
                return _language;
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        return false;
    }

    public static void setPort(boolean state)
    {
        String sql = "UPDATE tbidioma SET ESTADO = ? WHERE ID = 2";
        try(Connection _connect = ConnectionDAO.connectDAO(); PreparedStatement _prepare = _connect.prepareStatement(sql))
        {
            _prepare.setBoolean(1, state);
            _prepare.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static boolean getEng()
    {
        String sql = "SELECT ESTADO FROM tbidioma WHERE ID = 1";
        try(Connection _connect = ConnectionDAO.connectDAO(); PreparedStatement _prepare = _connect.prepareStatement(sql))
        {
            ResultSet _rs = _prepare.executeQuery();
            if(_rs.next())
            {
                _language = _rs.getBoolean("ESTADO");
                return _language;
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        return false;
    }

    public static void setEng(boolean state)
    {
        String sql = "UPDATE tbidioma SET ESTADO = ? WHERE ID = 1";
        try(Connection _connect = ConnectionDAO.connectDAO(); PreparedStatement _prepare = _connect.prepareStatement(sql))
        {
            _prepare.setBoolean(1, state);
            _prepare.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
