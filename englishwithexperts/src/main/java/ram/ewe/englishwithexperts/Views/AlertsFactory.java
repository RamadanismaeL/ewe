package ram.ewe.englishwithexperts.Views;

/**
 * @author Ramadan ismaeL
 */

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ram.ewe.englishwithexperts.Controllers.Alerts.Sucesso.AlertRegisterPaymentsController;
import ram.ewe.englishwithexperts.Controllers.Ferramentas.FerramentasController;
import ram.ewe.englishwithexperts.Controllers.LoginController;
import ram.ewe.englishwithexperts.Controllers.Progress.ProgressLoginDashboardController;
import ram.ewe.englishwithexperts.Controllers.Receipt.PaymentsController;
import ram.ewe.englishwithexperts.Controllers.Users.UsersController;
import ram.ewe.englishwithexperts.Models.ModelControllers;
import ram.ewe.englishwithexperts.Repositories.RegistrationsRepository;
import ram.ewe.englishwithexperts.Repositories.ThemeRepository;

import java.io.IOException;

/**
 * @author Ramadan Ismael
 */
public class AlertsFactory {
    private  Stage _stage;
    /*
        _stage.toFront();
        _stage.show();
     */

    // A V I S O
    public void getOnEmptyView() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ram/ewe/englishwithexperts/Controllers/Alerts/Aviso/AlertEmpty.fxml"));
        createStage(loader);
    }

    public void getOnEmptyResetFieldsView() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ram/ewe/englishwithexperts/Controllers/Alerts/Aviso/AlertEmptyResetFields.fxml"));
        createStage(loader);
    }


    // C O N F I R M A Ç Ã O
    public void getOnExitDashboardView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ram/ewe/englishwithexperts/Controllers/Alerts/Confirmacao/AlertExitDashboard.fxml"));
        Parent root = loader.load();
        if(_stage == null || !_stage.isShowing()) {
            _stage = new Stage();
            Scene scene = new Scene(root);

            if(ThemeRepository.getClaro())
            {
                scene.getStylesheets().add(getClass().getResource("/ram/ewe/englishwithexperts/Temas/Claro/AlertaClaro.css").toExternalForm());
            }
            else
            {
                scene.getStylesheets().add(getClass().getResource("/ram/ewe/englishwithexperts/Temas/Escuro/AlertaEscuro.css").toExternalForm());
            }

            _stage.initStyle(StageStyle.UNDECORATED);
            _stage.setScene(scene);
            _stage.show();
            _stage.toFront();
            _stage.setAlwaysOnTop(true);
            ModelControllers.getInstance().getAlertExitController().setStageDash(_stage);
        } else {
            _stage.toFront();
            _stage.setAlwaysOnTop(true);
        }
    }

    public void getOnRestartApp() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ram/ewe/englishwithexperts/Controllers/Alerts/Confirmacao/AlertRestart.fxml"));
        Parent root = loader.load();
        if(_stage == null || !_stage.isShowing()) {
            _stage = new Stage();
            Scene scene = new Scene(root);

            if(ThemeRepository.getClaro())
            {
                scene.getStylesheets().add(getClass().getResource("/ram/ewe/englishwithexperts/Temas/Claro/AlertaClaro.css").toExternalForm());
            }
            else
            {
                scene.getStylesheets().add(getClass().getResource("/ram/ewe/englishwithexperts/Temas/Escuro/AlertaEscuro.css").toExternalForm());
            }

            _stage.initStyle(StageStyle.UNDECORATED);
            _stage.setScene(scene);
            _stage.show();
            _stage.toFront();
            _stage.setAlwaysOnTop(true);
            ModelControllers.getInstance().getAlertRestartController().setStageDash(_stage);
        } else {
            _stage.toFront();
            _stage.setAlwaysOnTop(true);
        }
    }

    public void getOnLogOutApp() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ram/ewe/englishwithexperts/Controllers/Alerts/Confirmacao/AlertLogOutApp.fxml"));
        createStage(loader);
    }

    public void getOnQ_RemoveUser() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ram/ewe/englishwithexperts/Controllers/Alerts/Confirmacao/AlertQ_RemoveData.fxml"));
        createStage(loader);
    }

    public void getOnQQ_RemoveUser() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ram/ewe/englishwithexperts/Controllers/Alerts/Confirmacao/AlertQQ_RemoveData.fxml"));
        createStage(loader);
    }

    public void getOnQ_RemoveNDM() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ram/ewe/englishwithexperts/Controllers/Alerts/Confirmacao/AlertQ_RemoveNDM.fxml"));
        createStage(loader);
    }

    public void getOnQQ_RemoveNDM() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ram/ewe/englishwithexperts/Controllers/Alerts/Confirmacao/AlertQQ_RemoveNDM.fxml"));
        createStage(loader);
    }

    public void getOnQ_RemoveHM() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ram/ewe/englishwithexperts/Controllers/Alerts/Confirmacao/AlertQ_RemoveHM.fxml"));
        createStage(loader);
    }

    public void getOnQQ_RemoveHM() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ram/ewe/englishwithexperts/Controllers/Alerts/Confirmacao/AlertQQ_RemoveHM.fxml"));
        createStage(loader);
    }

    public void getOnQ_RemoveDP() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ram/ewe/englishwithexperts/Controllers/Alerts/Confirmacao/AlertQ_RemoveDP.fxml"));
        createStage(loader);
    }

    public void getOnQQ_RemoveDP() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ram/ewe/englishwithexperts/Controllers/Alerts/Confirmacao/AlertQQ_RemoveDP.fxml"));
        createStage(loader);
    }

    public void getOnQ_RemoveGenero() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ram/ewe/englishwithexperts/Controllers/Alerts/Confirmacao/AlertQ_RemoveGenero.fxml"));
        createStage(loader);
    }

    public void getOnQQ_RemoveGenero() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ram/ewe/englishwithexperts/Controllers/Alerts/Confirmacao/AlertQQ_RemoveGenero.fxml"));
        createStage(loader);
    }

    public void getOnQ_RemoveCivil() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ram/ewe/englishwithexperts/Controllers/Alerts/Confirmacao/AlertQ_RemoveCivil.fxml"));
        createStage(loader);
    }

    public void getOnQQ_RemoveCivil() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ram/ewe/englishwithexperts/Controllers/Alerts/Confirmacao/AlertQQ_RemoveCivil.fxml"));
        createStage(loader);
    }

    public void getOnQ_RemoveRelacao() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ram/ewe/englishwithexperts/Controllers/Alerts/Confirmacao/AlertQ_RemoveRelacao.fxml"));
        createStage(loader);
    }

    public void getOnQQ_RemoveRelacao() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ram/ewe/englishwithexperts/Controllers/Alerts/Confirmacao/AlertQQ_RemoveRelacao.fxml"));
        createStage(loader);
    }

    public void getOnQ_RemoveMetodo() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ram/ewe/englishwithexperts/Controllers/Alerts/Confirmacao/AlertQ_RemoveMetodo.fxml"));
        createStage(loader);
    }

    public void getOnQQ_RemoveMetodo() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ram/ewe/englishwithexperts/Controllers/Alerts/Confirmacao/AlertQQ_RemoveMetodo.fxml"));
        createStage(loader);
    }

    public void getOnExitPaymentRegistrationView() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ram/ewe/englishwithexperts/Controllers/Alerts/Confirmacao/AlertExitPaymentRegistration.fxml"));
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
                scene.getStylesheets().add(getClass().getResource("/ram/ewe/englishwithexperts/Temas/Claro/AlertaClaro.css").toExternalForm());
            }
            else
            {
                scene.getStylesheets().add(getClass().getResource("/ram/ewe/englishwithexperts/Temas/Escuro/AlertaEscuro.css").toExternalForm());
            }

            _stage.initStyle(StageStyle.UNDECORATED);
            _stage.setScene(scene);
            _stage.show();
            _stage.toFront();
            _stage.setAlwaysOnTop(true);
            ModelControllers.getInstance().getAlertExitPaymentRegistrationController().setStageDash(_stage);
        } else {
            _stage.toFront();
            _stage.setAlwaysOnTop(true);
        }
    }




    // E R R O
    public void getOnErroDataBase() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ram/ewe/englishwithexperts/Controllers/Alerts/Erro/AlertDataBase.fxml"));
        createStage(loader);
    }

    public void getOnLoginError() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ram/ewe/englishwithexperts/Controllers/Alerts/Erro/AlertLoginError.fxml"));
        createStage(loader);
    }

    public void getOnPassword6View() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ram/ewe/englishwithexperts/Controllers/Alerts/Erro/AlertPassword6.fxml"));
        createStage(loader);
    }

    public void getOnPasswordEqualView() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ram/ewe/englishwithexperts/Controllers/Alerts/Erro/AlertPasswordEqual.fxml"));
        createStage(loader);
    }

    public void getOnUserExistView() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ram/ewe/englishwithexperts/Controllers/Alerts/Erro/AlertUserExist.fxml"));
        createStage(loader);
    }

    public void getOnPasswordIncorrectView() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ram/ewe/englishwithexperts/Controllers/Alerts/Erro/AlertPasswordIncorrect.fxml"));
        createStage(loader);
    }

    public void getOnUserNotExistView() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ram/ewe/englishwithexperts/Controllers/Alerts/Erro/AlertUserNotExist.fxml"));
        createStage(loader);
    }

    public void getERROCRITICO() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ram/ewe/englishwithexperts/Controllers/Alerts/Erro/ERROCRITICO.fxml"));

        Parent root = loader.load();
        if(_stage == null || !_stage.isShowing()) {
            _stage = new Stage();
            Scene scene = new Scene(root);

            if(ThemeRepository.getClaro())
            {
                scene.getStylesheets().add(getClass().getResource("/ram/ewe/englishwithexperts/Temas/Claro/AlertaClaro.css").toExternalForm());
            }
            else
            {
                scene.getStylesheets().add(getClass().getResource("/ram/ewe/englishwithexperts/Temas/Escuro/AlertaEscuro.css").toExternalForm());
            }

            _stage.initStyle(StageStyle.UNDECORATED);
            _stage.setScene(scene);
            _stage.show();
            _stage.toFront();
            _stage.setAlwaysOnTop(true);
            ModelControllers.getInstance().getAlertRestartController().setStageDash(_stage);
        } else {
            _stage.toFront();
            _stage.setAlwaysOnTop(true);
        }
    }

    public void getOnDataExistView() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ram/ewe/englishwithexperts/Controllers/Alerts/Erro/AlertDataExist.fxml"));
        createStage(loader);
    }

    public void getOnDataNotExistView() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ram/ewe/englishwithexperts/Controllers/Alerts/Erro/AlertDataNotExist.fxml"));
        createStage(loader);
    }


    // I N F O R M A Ç Ã O
    public void getOnWelcome() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ram/ewe/englishwithexperts/Controllers/Alerts/Informacao/AlertWelcome.fxml"));
        createStage(loader);
    }

    public void getOnIdiomaSelected() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ram/ewe/englishwithexperts/Controllers/Alerts/Informacao/AlertIdiomaS.fxml"));
        createStage(loader);
    }

    public void getOnThemeSelected() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ram/ewe/englishwithexperts/Controllers/Alerts/Informacao/AlertTemaS.fxml"));
        createStage(loader);
    }

    public void getOnNoChanges() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ram/ewe/englishwithexperts/Controllers/Alerts/Informacao/AlertNoChanges.fxml"));
        createStage(loader);
    }


    // S U C E S S O
    public void getOnIdioma() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ram/ewe/englishwithexperts/Controllers/Alerts/Sucesso/AlertIdioma.fxml"));
        createStage(loader);
    }

    public void getOnTheme() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ram/ewe/englishwithexperts/Controllers/Alerts/Sucesso/AlertTema.fxml"));
        createStage(loader);
    }

    public void getOnRegister() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ram/ewe/englishwithexperts/Controllers/Alerts/Sucesso/AlertRegister.fxml"));
        createStage(loader);
    }

    public void getOnResetFields() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ram/ewe/englishwithexperts/Controllers/Alerts/Sucesso/AlertResetFields.fxml"));
        createStage(loader);
    }

    public void getOnUpdate() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ram/ewe/englishwithexperts/Controllers/Alerts/Sucesso/AlertUpdate.fxml"));
        createStage(loader);
    }

    public void getOnCorrectPassword() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ram/ewe/englishwithexperts/Controllers/Alerts/Sucesso/AlertCorrectPassword.fxml"));
        createStage(loader);
    }

    public void getOnRemove() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ram/ewe/englishwithexperts/Controllers/Alerts/Sucesso/AlertRemove.fxml"));
        Parent root = loader.load();
        if(_stage == null || !_stage.isShowing()) {
            _stage = new Stage();
            Scene scene = new Scene(root);

            if(ThemeRepository.getClaro())
            {
                scene.getStylesheets().add(getClass().getResource("/ram/ewe/englishwithexperts/Temas/Claro/AlertaClaro.css").toExternalForm());
            }
            else
            {
                scene.getStylesheets().add(getClass().getResource("/ram/ewe/englishwithexperts/Temas/Escuro/AlertaEscuro.css").toExternalForm());
            }

            _stage.initStyle(StageStyle.UNDECORATED);
            _stage.setScene(scene);
            _stage.show();
            _stage.toFront();
            _stage.setAlwaysOnTop(true);
            ModelControllers.getInstance().getAlertRestartController().setStageDash(_stage);
        } else {
            _stage.toFront();
            _stage.setAlwaysOnTop(true);
        }
    }

    public void getOnTableUpdate() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ram/ewe/englishwithexperts/Controllers/Alerts/Sucesso/AlertTableUpdate.fxml"));
        createStage(loader);
    }

    public void getOnExitPaymentSuccess() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ram/ewe/englishwithexperts/Controllers/Alerts/Sucesso/AlertExitPaymentSuccess.fxml"));
        Parent root = loader.load();
        if(_stage == null || !_stage.isShowing()) {
            _stage = new Stage();
            Scene scene = new Scene(root);

            if(ThemeRepository.getClaro())
            {
                scene.getStylesheets().add(getClass().getResource("/ram/ewe/englishwithexperts/Temas/Claro/AlertaClaro.css").toExternalForm());
            }
            else
            {
                scene.getStylesheets().add(getClass().getResource("/ram/ewe/englishwithexperts/Temas/Escuro/AlertaEscuro.css").toExternalForm());
            }

            _stage.initStyle(StageStyle.UNDECORATED);
            _stage.setScene(scene);
            _stage.show();
            _stage.toFront();
            _stage.setAlwaysOnTop(true);
            ModelControllers.getInstance().getAlertRestartController().setStageDash(_stage);
        } else {
            _stage.toFront();
            _stage.setAlwaysOnTop(true);
        }
    }

    public void getOnRegisterPaymentSuccess() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ram/ewe/englishwithexperts/Controllers/Alerts/Sucesso/AlertRegisterPayments.fxml"));
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
                scene.getStylesheets().add(getClass().getResource("/ram/ewe/englishwithexperts/Temas/Claro/AlertaClaro.css").toExternalForm());
            }
            else
            {
                scene.getStylesheets().add(getClass().getResource("/ram/ewe/englishwithexperts/Temas/Escuro/AlertaEscuro.css").toExternalForm());
            }

            _stage.initStyle(StageStyle.UNDECORATED);
            _stage.setScene(scene);
            _stage.show();
            PaymentsController.setStage(_stage);
            _stage.toFront();
        } else {
            _stage.toFront();
        }
    }


    // C R E A T E   S T A G E
    private void createStage(FXMLLoader root)
    {
        if(_stage == null || !_stage.isShowing())
        {
            _stage = new Stage();
            try
            {
                Scene scene = new Scene(root.load());

                if(ThemeRepository.getClaro())
                {
                    scene.getStylesheets().add(getClass().getResource("/ram/ewe/englishwithexperts/Temas/Claro/AlertaClaro.css").toExternalForm());
                }
                else
                {
                    scene.getStylesheets().add(getClass().getResource("/ram/ewe/englishwithexperts/Temas/Escuro/AlertaEscuro.css").toExternalForm());
                }

                _stage.initStyle(StageStyle.UNDECORATED);
                _stage.setScene(scene);
                _stage.show();
                _stage.toFront();
                _stage.setAlwaysOnTop(true);
            }
            catch(Exception error)
            {
                error.printStackTrace();
            }
            _stage.toFront();
            _stage.setAlwaysOnTop(true);
        }
        else
        {
            _stage.toFront();
            _stage.setAlwaysOnTop(true);
        }
    }
}
