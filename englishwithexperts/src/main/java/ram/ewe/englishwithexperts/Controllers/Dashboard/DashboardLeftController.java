package ram.ewe.englishwithexperts.Controllers.Dashboard;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import ram.ewe.englishwithexperts.Enums.DashboardEnum;
import ram.ewe.englishwithexperts.Models.Model;
import ram.ewe.englishwithexperts.Models.ModelDataDAO;
import ram.ewe.englishwithexperts.Models.ModelViewsDAO;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardLeftController implements Initializable {
    public Label lblUsername;
    public Button btnHome;
    public Button btnInscricao;
    public Button btnPagamentos;
    public Button btnEstudantes;
    public Button btnFinancas;
    public Button btnLogOut;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblUsername.setText(ModelDataDAO.getInstance().getUserDataModel().getFullName());
        btnInscricao.setOnAction(evt -> {
            ModelViewsDAO.getInstance().getDashboardEnum().set(DashboardEnum.INSCRICOES);
            ModelDataDAO.getInstance().getUserDataModel().setUserLogs(ModelDataDAO.getInstance().getUserDataModel().getUserName(), ModelDataDAO.getInstance().getUserDataModel().getProfile(), "Tela : [Inscrições] -- aberto.");
        });
        btnLogOut.setOnAction(evt -> {
            ModelViewsDAO.getInstance().getDashboardEnum().set(DashboardEnum.DESCONECTAR);
        });
    }
}
