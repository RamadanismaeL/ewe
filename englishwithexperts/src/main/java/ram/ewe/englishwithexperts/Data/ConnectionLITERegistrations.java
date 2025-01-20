package ram.ewe.englishwithexperts.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ramadan Ismael
 */
public class ConnectionLITERegistrations
{
    //private static final List<Connection> _connectionList = new ArrayList<>();
    private static final String _url = "jdbc:sqlite:eweRegistrations.db";

    public static Connection connectLite()
    {
        try
        {
            String _driver = "org.sqlite.JDBC";
            Class.forName(_driver);
            Connection _connection = DriverManager.getConnection(_url);
            //_connectionList.add(_connection);
            if(_connection != null)
            {
                System.out.println("DBLite Registrations - Conectado!");
            }
            return _connection;
        }
        catch (ClassNotFoundException | SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}
