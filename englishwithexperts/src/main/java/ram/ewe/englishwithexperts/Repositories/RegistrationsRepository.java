package ram.ewe.englishwithexperts.Repositories;

import javafx.fxml.FXMLLoader;
import ram.ewe.englishwithexperts.Controllers.Alerts.Erro.AlertErroCriticoController;
import ram.ewe.englishwithexperts.Data.ConnectionDAO;
import ram.ewe.englishwithexperts.Data.ConnectionLITERegistrations;
import ram.ewe.englishwithexperts.Models.Model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Ramadan Ismael
 */
public class RegistrationsRepository
{
    ///  M E T H O D
    public static void set_metodo_Add(String metodo)
    {
        String sql = "INSERT INTO method(metodo) VALUES(?)";
        try(Connection connect = ConnectionLITERegistrations.connectLite(); PreparedStatement prepare = connect.prepareStatement(sql))
        {
            prepare.setString(1, metodo);
            prepare.executeUpdate();
        }
        catch (SQLException e)
        {
            AlertErroCriticoController.setErroCritico(e.getMessage());
            try {
                Model.getInstance().getAlertsFactory().getERROCRITICO();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public static void set_metodo_Update(int id, String metodo)
    {
        String sql = "UPDATE method SET metodo = ? WHERE id = ?";
        try(Connection connect = ConnectionLITERegistrations.connectLite(); PreparedStatement prepare = connect.prepareStatement(sql))
        {
            prepare.setString(1, metodo);
            prepare.setInt(2, id);
            prepare.executeUpdate();
        }
        catch (SQLException e)
        {
            AlertErroCriticoController.setErroCritico(e.getMessage());
            try {
                Model.getInstance().getAlertsFactory().getERROCRITICO();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public static void set_metodo_Delete(int id)
    {
        String sql = "DELETE FROM method WHERE id = ?";
        try(Connection _connect = ConnectionLITERegistrations.connectLite(); PreparedStatement _prepare = _connect.prepareStatement(sql))
        {
            _prepare.setInt(1, id);
            _prepare.executeUpdate();
        }
        catch (SQLException e)
        {
            AlertErroCriticoController.setErroCritico(e.getMessage());
            try {
                Model.getInstance().getAlertsFactory().getERROCRITICO();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
        }
    }



    ///  R E L A Ç Ã O
    public static void set_relacao_Add(String relacao)
    {
        String sql = "INSERT INTO relacaoEmerg(relacao) VALUES(?)";
        try(Connection connect = ConnectionLITERegistrations.connectLite(); PreparedStatement prepare = connect.prepareStatement(sql))
        {
            prepare.setString(1, relacao);
            prepare.executeUpdate();
        }
        catch (SQLException e)
        {
            AlertErroCriticoController.setErroCritico(e.getMessage());
            try {
                Model.getInstance().getAlertsFactory().getERROCRITICO();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public static void set_relacao_Update(int id, String relacao)
    {
        String sql = "UPDATE relacaoEmerg SET relacao = ? WHERE id = ?";
        try(Connection connect = ConnectionLITERegistrations.connectLite(); PreparedStatement prepare = connect.prepareStatement(sql))
        {
            prepare.setString(1, relacao);
            prepare.setInt(2, id);
            prepare.executeUpdate();
        }
        catch (SQLException e)
        {
            AlertErroCriticoController.setErroCritico(e.getMessage());
            try {
                Model.getInstance().getAlertsFactory().getERROCRITICO();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public static void set_relacao_Delete(int id)
    {
        String sql = "DELETE FROM relacaoEmerg WHERE id = ?";
        try(Connection _connect = ConnectionLITERegistrations.connectLite(); PreparedStatement _prepare = _connect.prepareStatement(sql))
        {
            _prepare.setInt(1, id);
            _prepare.executeUpdate();
        }
        catch (SQLException e)
        {
            AlertErroCriticoController.setErroCritico(e.getMessage());
            try {
                Model.getInstance().getAlertsFactory().getERROCRITICO();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
        }
    }



    ///  C I V I L
    public static void set_civil_Add(String civil)
    {
        String sql = "INSERT INTO ieCivil(civil) VALUES(?)";
        try(Connection connect = ConnectionLITERegistrations.connectLite(); PreparedStatement prepare = connect.prepareStatement(sql))
        {
            prepare.setString(1, civil);
            prepare.executeUpdate();
        }
        catch (SQLException e)
        {
            AlertErroCriticoController.setErroCritico(e.getMessage());
            try {
                Model.getInstance().getAlertsFactory().getERROCRITICO();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public static void set_civil_Update(int id, String civil)
    {
        String sql = "UPDATE ieCivil SET civil = ? WHERE id = ?";
        try(Connection connect = ConnectionLITERegistrations.connectLite(); PreparedStatement prepare = connect.prepareStatement(sql))
        {
            prepare.setString(1, civil);
            prepare.setInt(2, id);
            prepare.executeUpdate();
        }
        catch (SQLException e)
        {
            AlertErroCriticoController.setErroCritico(e.getMessage());
            try {
                Model.getInstance().getAlertsFactory().getERROCRITICO();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public static void set_civil_Delete(int id)
    {
        String sql = "DELETE FROM ieCivil WHERE id = ?";
        try(Connection _connect = ConnectionLITERegistrations.connectLite(); PreparedStatement _prepare = _connect.prepareStatement(sql))
        {
            _prepare.setInt(1, id);
            _prepare.executeUpdate();
        }
        catch (SQLException e)
        {
            AlertErroCriticoController.setErroCritico(e.getMessage());
            try {
                Model.getInstance().getAlertsFactory().getERROCRITICO();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
        }
    }



    ///  G Ê N E R O
    public static void set_genero_Add(String genero)
    {
        String sql = "INSERT INTO ieSexo(genero) VALUES(?)";
        try(Connection connect = ConnectionLITERegistrations.connectLite(); PreparedStatement prepare = connect.prepareStatement(sql))
        {
            prepare.setString(1, genero);
            prepare.executeUpdate();
        }
        catch (SQLException e)
        {
            AlertErroCriticoController.setErroCritico(e.getMessage());
            try {
                Model.getInstance().getAlertsFactory().getERROCRITICO();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public static void set_genero_Update(int id, String genero)
    {
        String sql = "UPDATE ieSexo SET genero = ? WHERE id = ?";
        try(Connection connect = ConnectionLITERegistrations.connectLite(); PreparedStatement prepare = connect.prepareStatement(sql))
        {
            prepare.setString(1, genero);
            prepare.setInt(2, id);
            prepare.executeUpdate();
        }
        catch (SQLException e)
        {
            AlertErroCriticoController.setErroCritico(e.getMessage());
            try {
                Model.getInstance().getAlertsFactory().getERROCRITICO();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public static void set_genero_Delete(int id)
    {
        String sql = "DELETE FROM ieSexo WHERE id = ?";
        try(Connection _connect = ConnectionLITERegistrations.connectLite(); PreparedStatement _prepare = _connect.prepareStatement(sql))
        {
            _prepare.setInt(1, id);
            _prepare.executeUpdate();
        }
        catch (SQLException e)
        {
            AlertErroCriticoController.setErroCritico(e.getMessage());
            try {
                Model.getInstance().getAlertsFactory().getERROCRITICO();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
        }
    }



    ///  D P
    public static void set_dp_Add(String tipo)
    {
        String sql = "INSERT INTO dp(tipo) values(?)";
        try(Connection connect = ConnectionLITERegistrations.connectLite(); PreparedStatement prepare = connect.prepareStatement(sql))
        {
            prepare.setString(1, tipo);
            prepare.executeUpdate();
        }
        catch (SQLException e)
        {
            AlertErroCriticoController.setErroCritico(e.getMessage());
            try {
                Model.getInstance().getAlertsFactory().getERROCRITICO();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public static void set_dp_Update(int id, String tipo)
    {
        String sql = "UPDATE dp SET tipo = ? WHERE id = ?";
        try(Connection connect = ConnectionLITERegistrations.connectLite(); PreparedStatement prepare = connect.prepareStatement(sql))
        {
            prepare.setString(1, tipo);
            prepare.setInt(2, id);
            prepare.executeUpdate();
        }
        catch (SQLException e)
        {
            AlertErroCriticoController.setErroCritico(e.getMessage());
            try {
                Model.getInstance().getAlertsFactory().getERROCRITICO();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public static void set_dp_Delete(int id)
    {
        String sql = "DELETE FROM dp WHERE id = ?";
        try(Connection _connect = ConnectionLITERegistrations.connectLite(); PreparedStatement _prepare = _connect.prepareStatement(sql))
        {
            _prepare.setInt(1, id);
            _prepare.executeUpdate();
        }
        catch (SQLException e)
        {
            AlertErroCriticoController.setErroCritico(e.getMessage());
            try {
                Model.getInstance().getAlertsFactory().getERROCRITICO();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
        }
    }



    ///  H M
    public static void set_hm_Add(String horario, String modalidade)
    {
        String sql = "INSERT INTO hm(horario, modalidade) values(?, ?)";
        try(Connection connect = ConnectionLITERegistrations.connectLite(); PreparedStatement prepare = connect.prepareStatement(sql))
        {
            prepare.setString(1, horario);
            prepare.setString(2, modalidade);
            prepare.executeUpdate();
        }
        catch (SQLException e)
        {
            AlertErroCriticoController.setErroCritico(e.getMessage());
            try {
                Model.getInstance().getAlertsFactory().getERROCRITICO();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public static void set_hm_Update(int id, String horario, String modalidade)
    {
        String sql = "UPDATE hm SET Horario = ?, Modalidade = ? WHERE id = ?";
        try(Connection connect = ConnectionLITERegistrations.connectLite(); PreparedStatement prepare = connect.prepareStatement(sql))
        {
            prepare.setString(1, horario);
            prepare.setString(2, modalidade);
            prepare.setInt(3, id);
            prepare.executeUpdate();
        }
        catch (SQLException e)
        {
            AlertErroCriticoController.setErroCritico(e.getMessage());
            try {
                Model.getInstance().getAlertsFactory().getERROCRITICO();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public static void set_hm_Delete(int id)
    {
        String sql = "DELETE FROM hm WHERE id = ?";
        try(Connection _connect = ConnectionLITERegistrations.connectLite(); PreparedStatement _prepare = _connect.prepareStatement(sql))
        {
            _prepare.setInt(1, id);
            _prepare.executeUpdate();
        }
        catch (SQLException e)
        {
            AlertErroCriticoController.setErroCritico(e.getMessage());
            try {
                Model.getInstance().getAlertsFactory().getERROCRITICO();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
        }
    }

    /// N D M
    public static void set_ndm_Add(String nivel, String duracao, double mensalidade)
    {
        String sql = "INSERT INTO ndm(nivel, duracao, mensalidade) values(?, ?, ?)";
        try(Connection connect = ConnectionLITERegistrations.connectLite(); PreparedStatement prepare = connect.prepareStatement(sql))
        {
            prepare.setString(1, nivel);
            prepare.setString(2, duracao);
            prepare.setDouble(3, mensalidade);
            prepare.executeUpdate();
        }
        catch (SQLException e)
        {
            AlertErroCriticoController.setErroCritico(e.getMessage());
            try {
                Model.getInstance().getAlertsFactory().getERROCRITICO();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public static void set_ndm_Update(int id, String nivel, String duracao, double mensalidade)
    {
        String sql = "UPDATE ndm SET nivel = ?, duracao = ?, mensalidade = ? WHERE id = ?";
        try(Connection connect = ConnectionLITERegistrations.connectLite(); PreparedStatement prepare = connect.prepareStatement(sql))
        {
            prepare.setString(1, nivel);
            prepare.setString(2, duracao);
            prepare.setDouble(3, mensalidade);
            prepare.setInt(4, id);
            prepare.executeUpdate();
        }
        catch (SQLException e)
        {
            AlertErroCriticoController.setErroCritico(e.getMessage());
            try {
                Model.getInstance().getAlertsFactory().getERROCRITICO();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public static void set_ndm_Delete(int id)
    {
        String sql = "DELETE FROM ndm WHERE id = ?";
        try(Connection _connect = ConnectionLITERegistrations.connectLite(); PreparedStatement _prepare = _connect.prepareStatement(sql))
        {
            _prepare.setInt(1, id);
            _prepare.executeUpdate();
        }
        catch (SQLException e)
        {
            AlertErroCriticoController.setErroCritico(e.getMessage());
            try {
                Model.getInstance().getAlertsFactory().getERROCRITICO();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
        }
    }

    /// I N S C R I Ç Ã O
    public static void setUpdateTaxaDeInscricao(double valor)
    {
        String sql = "UPDATE taxaDeInscricao SET Valor = ? WHERE ID = 1";
        try(Connection _connect = ConnectionLITERegistrations.connectLite(); PreparedStatement _prepare = _connect.prepareStatement(sql))
        {
            _prepare.setDouble(1, valor);
            _prepare.executeUpdate();
        }
        catch (SQLException e)
        {
            AlertErroCriticoController.setErroCritico(e.getMessage());
            try {
                Model.getInstance().getAlertsFactory().getERROCRITICO();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public static int getTaxaDeInscricao()
    {
        String sql = "SELECT Valor FROM taxaDeInscricao WHERE ID = 1";
        try(Connection _connect = ConnectionLITERegistrations.connectLite(); PreparedStatement _prepare = _connect.prepareStatement(sql))
        {
            ResultSet _rs = _prepare.executeQuery();
            if(_rs.next())
            {
                return _rs.getInt("Valor");
            }
        }
        catch(SQLException e)
        {
            AlertErroCriticoController.setErroCritico(e.getMessage());
            try {
                Model.getInstance().getAlertsFactory().getERROCRITICO();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        return 0;
    }
}
