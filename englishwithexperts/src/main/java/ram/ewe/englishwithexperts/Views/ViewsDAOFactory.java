package ram.ewe.englishwithexperts.Views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ram.ewe.englishwithexperts.Controllers.Alerts.Confirmacao.*;
import ram.ewe.englishwithexperts.Controllers.Alerts.Sucesso.AlertRegisterPaymentsController;
import ram.ewe.englishwithexperts.Controllers.Receipt.PaymentsController;
import ram.ewe.englishwithexperts.Controllers.Users.UsersChangePasswordController;
import ram.ewe.englishwithexperts.Models.Model;
import ram.ewe.englishwithexperts.Models.ModelControllers;
import ram.ewe.englishwithexperts.Repositories.ThemeRepository;

import java.io.IOException;

/**
 * @author Ramadan Ismael
 */
public class ViewsDAOFactory
{
    private StackPane _stackPane;
    private Stage _stage;

    // D A S H B O A R D
    public StackPane getDashboardTop() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ram/ewe/englishwithexperts/Controllers/Dashboard/DashboardTop.fxml"));
        try {
            _stackPane = loader.load();
            if(ThemeRepository.getClaro())
            {
                _stackPane.getStylesheets().add(getClass().getResource("/ram/ewe/englishwithexperts/Temas/Claro/DashboardsClaro.css").toExternalForm());
            }
            else
            {
                _stackPane.getStylesheets().add(getClass().getResource("/ram/ewe/englishwithexperts/Temas/Escuro/DashboardsEscuro.css").toExternalForm());
            }

            return _stackPane;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public StackPane getDashboardLef() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ram/ewe/englishwithexperts/Controllers/Dashboard/DashboardLeft.fxml"));
        try {
            _stackPane = loader.load();
            if(ThemeRepository.getClaro())
            {
                _stackPane.getStylesheets().add(getClass().getResource("/ram/ewe/englishwithexperts/Temas/Claro/DashboardsLeftClaro.css").toExternalForm());
            }
            else
            {
                _stackPane.getStylesheets().add(getClass().getResource("/ram/ewe/englishwithexperts/Temas/Escuro/DashboardsLeftEscuro.css").toExternalForm());
            }

            return _stackPane;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    //F E R R A M E N T A S

    public void getFerramentas() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ram/ewe/englishwithexperts/Controllers/Ferramentas/Ferramentas.fxml"));
        AlertQ_RemoveNDMController.setFXMLLoader(loader);
        AlertQQ_RemoveNDMController.setFXMLLoader(loader);
        AlertQ_RemoveHMController.setFXMLLoader(loader);
        AlertQQ_RemoveHMController.setFXMLLoader(loader);
        AlertQ_RemoveDPController.setFXMLLoader(loader);
        AlertQQ_RemoveDPController.setFXMLLoader(loader);
        AlertQ_RemoveGeneroController.setFXMLLoader(loader);
        AlertQQ_RemoveGeneroController.setFXMLLoader(loader);
        AlertQ_RemoveCivilController.setFXMLLoader(loader);
        AlertQQ_RemoveCivilController.setFXMLLoader(loader);
        AlertQ_RemoveRelacaoController.setFXMLLoader(loader);
        AlertQQ_RemoveRelacaoController.setFXMLLoader(loader);
        AlertQ_RemoveMetodoController.setFXMLLoader(loader);
        AlertQQ_RemoveMetodoController.setFXMLLoader(loader);
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(_stage == null || !_stage.isShowing()) {
            _stage = new Stage();
            Scene scene = new Scene(root);

            if(ThemeRepository.getClaro())
            {
                scene.getStylesheets().add(getClass().getResource("/ram/ewe/englishwithexperts/Temas/Claro/FerramentasLight.css").toExternalForm());
            }
            else
            {
                //scene.getStylesheets().add(getClass().getResource("/ram/ewe/englishwithexperts/Temas/Escuro/DashboardsEscuro.css").toExternalForm());
            }

            _stage.initStyle(StageStyle.UNDECORATED);
            _stage.setScene(scene);
            _stage.show();
            ModelControllers.getInstance().getDashboardController().setStageDash(_stage);
        } else {
            _stage.toFront();
        }
    }


    // U S E R S
    public StackPane getUsersViews() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ram/ewe/englishwithexperts/Controllers/Users/Users.fxml"));
        UsersChangePasswordController.setFXMLLoader(loader);
        AlertQ_RemoveUserController.setFXMLLoader(loader);
        AlertQQ_RemoveUserController.setFXMLLoader(loader);
        try {
            _stackPane = loader.load();
            if(ThemeRepository.getClaro())
            {
                _stackPane.getStylesheets().add(getClass().getResource("/ram/ewe/englishwithexperts/Temas/Claro/UsersLight.css").toExternalForm());
                _stackPane.getStylesheets().add(getClass().getResource("/ram/ewe/englishwithexperts/Temas/Claro/TableLight.css").toExternalForm());
            }
            else
            {
                _stackPane.getStylesheets().add(getClass().getResource("/ram/ewe/englishwithexperts/Temas/Escuro/UsersDark.css").toExternalForm());
                _stackPane.getStylesheets().add(getClass().getResource("/ram/ewe/englishwithexperts/Temas/Escuro/TableDark.css").toExternalForm());
            }

            return _stackPane;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getUsersChangePassword() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ram/ewe/englishwithexperts/Controllers/Users/UsersChangePassword.fxml"));
        Parent root = loader.load();
        if(_stage == null || !_stage.isShowing()) {
            _stage = new Stage();
            Scene scene = new Scene(root);

            if(ThemeRepository.getClaro())
            {
                scene.getStylesheets().add(getClass().getResource("/ram/ewe/englishwithexperts/Temas/Claro/MudarSenhaClaro.css").toExternalForm());
            }
            else
            {
                scene.getStylesheets().add(getClass().getResource("/ram/ewe/englishwithexperts/Temas/Escuro/MudarSenhaEscuro.css").toExternalForm());
            }

            _stage.initStyle(StageStyle.UNDECORATED);
            _stage.setScene(scene);
            _stage.show();
            _stage.toFront();
            ModelControllers.getInstance().getUsersChangePasswordController().setStageDash(_stage);
        } else {
            _stage.toFront();
        }
    }

    // R E G I S T R A T I O N S
    public StackPane getRegistrationsViews() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ram/ewe/englishwithexperts/Controllers/Registrations/Registrations.fxml"));
        AlertExitPaymentRegistrationController.setFXMLLoader(loader);
        AlertRegisterPaymentsController.setFXMLoader(loader);
        PaymentsController.setLoader(loader);
        try {
            _stackPane = loader.load();
            if(ThemeRepository.getClaro())
            {
                _stackPane.getStylesheets().add(getClass().getResource("/ram/ewe/englishwithexperts/Temas/Claro/RegistrationsLight.css").toExternalForm());
            }
            else
            {
                _stackPane.getStylesheets().add(getClass().getResource("/ram/ewe/englishwithexperts/Temas/Escuro/RegistrationsDark.css").toExternalForm());
            }
            return _stackPane;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /// RECEIPT PAYMENT - REGISTRATIONS
    public void getOnReceiptPayment() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ram/ewe/englishwithexperts/Controllers/Receipt/Payments.fxml"));
        AlertRegisterPaymentsController.setReceiptPayment(loader);
        try {
            Parent root = loader.load();
            _stage = new Stage();
            _stage.initStyle(StageStyle.UNDECORATED);
            _stage.setScene(new Scene(root));
            _stage.show();
            _stage.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
 }
