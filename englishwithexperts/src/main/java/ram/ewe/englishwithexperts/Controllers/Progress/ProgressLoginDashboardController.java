package ram.ewe.englishwithexperts.Controllers.Progress;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import ram.ewe.englishwithexperts.Controllers.Alerts.Erro.AlertErroCriticoController;
import ram.ewe.englishwithexperts.Models.Model;

import java.net.URL;
import java.util.ResourceBundle;

public class ProgressLoginDashboardController implements Initializable {
    private static FXMLLoader loaders;
    public ProgressBar progressLogin;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Task<Void> task = new Task<Void>() {

                @Override
                protected Void call() throws Exception {
                    for(int i = 0; i <= 100; i++) {
                        Thread.sleep(50);

                        final int progress = i;
                        Platform.runLater(() -> load(progress/100.0));
                    }
                    Thread.sleep(100);
                    Platform.runLater(() -> {
                        try{
                            Model.getInstance().getViewsFactory().showDashboardWindow();
                        }catch(Exception e) {
                            AlertErroCriticoController controller = getLoader().getController();
                            controller.info3.setText(e.getMessage());
                        }
                        progressLogin.getScene().getWindow().hide();
                        Model.getInstance().getAlertsFactory().getOnWelcome();
                    });
                    return null;
                }
            };
            new Thread(task).start();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void setFXMLLoader(FXMLLoader loader)
    {
        loaders = loader;
    }

    public FXMLLoader getLoader() { return loaders; }

    public void load(double progress) {
        progressLogin.setProgress(progress);
    }
}
