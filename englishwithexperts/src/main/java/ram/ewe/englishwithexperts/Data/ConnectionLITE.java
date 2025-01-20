package ram.ewe.englishwithexperts.Data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ramadan Ismael
 */
public class ConnectionLITE
{
    private static final List<Connection> _connectionList = new ArrayList<>();
    private static Connection _connection;
    private static String _driver = "org.sqlite.JDBC";
    private static final String _url = "jdbc:sqlite:eweLogs.db";

    public static Connection connectLite()
    {
        try
        {
            Class.forName(_driver);
            _connection = DriverManager.getConnection(_url);
            _connectionList.add(_connection);
            if(_connection != null)
            {
                System.out.println("DBLite Conectado!");
            }
            return _connection;
        }
        catch (ClassNotFoundException | SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void closeAllConnections() {
        for(Connection conn : _connectionList) {
            try{
                if(conn != null && !conn.isClosed()) {
                    conn.close();
                }
            }catch(SQLException e) {
                e.printStackTrace();
            }
        }
        _connectionList.clear();
        System.out.println("Conexão LITE fechada...");
    }

    public void restartAllConnections() {
        _connectionList.clear();
        System.out.println("Conexão LITE reiniciada...");
    }
}
