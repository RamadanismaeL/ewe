package ram.ewe.englishwithexperts.Repositories;

import ram.ewe.englishwithexperts.Data.ConnectionDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Ramadan Ismael
 */
public class ThemeRepository {
    private static boolean _theme;

    public static boolean getClaro()
    {
        String sql = "SELECT ESTADO FROM tbTema WHERE ID = 1";
        try(Connection _connect = ConnectionDAO.connectDAO(); PreparedStatement _prepare = _connect.prepareStatement(sql))
        {
            ResultSet _rs = _prepare.executeQuery();
            if(_rs.next())
            {
                _theme = _rs.getBoolean("ESTADO");
                return _theme;
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        return false;
    }

    public static void setClaro(boolean state)
    {
        String sql = "UPDATE tbTema SET ESTADO = ? WHERE ID = 1";
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

    public static boolean getEscuro()
    {
        String sql = "SELECT ESTADO FROM tbTema WHERE ID = 2";
        try(Connection _connect = ConnectionDAO.connectDAO(); PreparedStatement _prepare = _connect.prepareStatement(sql))
        {
            ResultSet _rs = _prepare.executeQuery();
            if(_rs.next())
            {
                _theme = _rs.getBoolean("ESTADO");
                return _theme;
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        return false;
    }

    public static void setEscuro(boolean state)
    {
        String sql = "UPDATE tbTema SET ESTADO = ? WHERE ID = 2";
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
