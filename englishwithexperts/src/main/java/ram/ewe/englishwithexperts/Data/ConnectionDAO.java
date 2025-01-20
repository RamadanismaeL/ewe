package ram.ewe.englishwithexperts.Data;

import ram.ewe.englishwithexperts.Models.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ramadan ismaeL
 */
public class ConnectionDAO {
    private static final List<Connection> _connectionList = new ArrayList<>();
    private final static String _driver = "com.mysql.cj.jdbc.Driver";
    private final static String _server = System.getenv("DB_SERVER");
    private final static String _port = System.getenv("DB_PORT");
    private final static String _url = "jdbc:mysql://"+_server+":"+_port+"/db_englishwithexperts";
    private final static String _username = System.getenv("DB_USERNAME");
    private final static String _password = System.getenv("DB_PASSWORD");
    private static Connection _connection = null;

    public static Connection connectDAO() throws SQLException
    {
        try
        {
            Class.forName(_driver);
            _connection = DriverManager.getConnection(_url, _username, _password);
            _connectionList.add(_connection);
            if(_connection != null)
            {
                System.out.println("Nova Conexão..");
            }
            return _connection;
        }
        catch(ClassNotFoundException error)
        {
            System.out.println("DataBase Driver not found..");
            return null;
        }
        catch(SQLException error)
        {
            System.out.println("Ocorreu um erro ao acessar o banco de dados : "+error.getMessage());
            return null;
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
        System.out.println("Conexão fechada...");
    }

    public void restartAllConnections() {
        _connectionList.clear();
        System.out.println("Conexão reiniciada...");
    }
}
