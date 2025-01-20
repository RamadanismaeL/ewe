package ram.ewe.englishwithexperts.Views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ram.ewe.englishwithexperts.Controllers.Alerts.Confirmacao.AlertExitPaymentRegistrationController;
import ram.ewe.englishwithexperts.Controllers.Receipt.PaymentsController;
import ram.ewe.englishwithexperts.Controllers.Registrations.RegistrationsController;
import ram.ewe.englishwithexperts.Models.ModelControllers;
import ram.ewe.englishwithexperts.Repositories.ThemeRepository;

import java.io.IOException;

/**
 * @author Ramadan ismael
 */
public class ViewsFactory
{
    private Stage _stage;

    public void showLoginWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ram/ewe/englishwithexperts/Controllers/Login.fxml"));
        Parent root = loader.load();
        if(_stage == null || !_stage.isShowing()) {
            _stage = new Stage();
            Scene scene = new Scene(root);

            if(ThemeRepository.getClaro())
            {
                scene.getStylesheets().add(getClass().getResource("/ram/ewe/englishwithexperts/Temas/Claro/LoginClaro.css").toExternalForm());
                scene.getStylesheets().add(getClass().getResource("/ram/ewe/englishwithexperts/Temas/Claro/tooltipLight.css").toExternalForm());
            }
            else
            {
                scene.getStylesheets().add(getClass().getResource("/ram/ewe/englishwithexperts/Temas/Escuro/LoginEscuro.css").toExternalForm());
                scene.getStylesheets().add(getClass().getResource("/ram/ewe/englishwithexperts/Temas/Escuro/tooltipDark.css").toExternalForm());
            }

            _stage.initStyle(StageStyle.UNDECORATED);
            _stage.setScene(scene);
            _stage.show();
            ModelControllers.getInstance().getDashboardController().setStageDash(_stage);
        } else {
            _stage.toFront();
        }
    }

    public void showDashboardWindow() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ram/ewe/englishwithexperts/Controllers/Dashboard.fxml"));
        Parent root = loader.load();
        if(_stage == null || !_stage.isShowing()) {
            _stage = new Stage();
            Scene scene = new Scene(root);

            if(ThemeRepository.getClaro())
            {
                scene.getStylesheets().add(getClass().getResource("/ram/ewe/englishwithexperts/Temas/Claro/DashboardsClaro.css").toExternalForm());
            }
            else
            {
                scene.getStylesheets().add(getClass().getResource("/ram/ewe/englishwithexperts/Temas/Escuro/DashboardsEscuro.css").toExternalForm());
            }

            _stage.initStyle(StageStyle.UNDECORATED);
            _stage.setScene(scene);
            _stage.show();
            ModelControllers.getInstance().getDashboardController().setStageDash(_stage);
        } else {
            _stage.toFront();
        }
    }
}
