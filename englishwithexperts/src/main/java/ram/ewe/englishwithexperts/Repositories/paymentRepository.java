package ram.ewe.englishwithexperts.Repositories;

import ram.ewe.englishwithexperts.Controllers.Alerts.Erro.AlertErroCriticoController;
import ram.ewe.englishwithexperts.Data.ConnectionDAO;
import ram.ewe.englishwithexperts.Data.ConnectionLITERegistrations;
import ram.ewe.englishwithexperts.Models.Model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class paymentRepository {
    /// R E C E I P T
    public static void set_receipt_Add(String reciboNr, String tipo, String estudanteID, String exmo_sr, int valor, int desconto, int valorPago, String quantia, String metodo, String dia, String mes, int ano, String horas)
    {
        String sql = "INSERT INTO tbReceiptDetail(reciboNr, tipo, estudanteID, exmo_sr, valor, desconto, valorPago, quantia, metodo, dia, mes, ano, horas) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try(Connection _connect = ConnectionDAO.connectDAO(); PreparedStatement _prepare = _connect.prepareStatement(sql))
        {
            _prepare.setString(1, reciboNr);
            _prepare.setString(2, tipo);
            _prepare.setString(3, estudanteID);
            _prepare.setString(4, exmo_sr);
            _prepare.setInt(5, valor);
            _prepare.setInt(6, desconto);
            _prepare.setInt(7, valorPago);
            _prepare.setString(8, quantia);
            _prepare.setString(9, metodo);
            _prepare.setString(10, dia);
            _prepare.setString(11, mes);
            _prepare.setInt(12, ano);
            _prepare.setString(13, horas);
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
}
