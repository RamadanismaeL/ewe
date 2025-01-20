package ram.ewe.englishwithexperts.Controllers.Progress;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.stage.Stage;
import javafx.util.Duration;
import ram.ewe.englishwithexperts.Models.ModelControllers;
import ram.ewe.englishwithexperts.Models.Model;
import ram.ewe.englishwithexperts.Models.ModelDataDAO;
import ram.ewe.englishwithexperts.Repositories.LanguagesRepository;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProgressRestartAppController implements Initializable {
    private Stage _stage;
    public ProgressIndicator progressRestartApp;
    public Label mesage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ModelControllers.getInstance().getAlertRestartController().hideDash();
        if(LanguagesRepository.getPort())
        {
            mesage.setText("Reiniciando o sistema, aguarde...");
        }
        else
        {
            mesage.setText("Restarting the system, please wait...");
        }
        Timeline timeline = new Timeline();

        for(int i = 0; i <= 6; i++)
        {
            timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(i), event ->
            {
                progressRestartApp.setProgress(-1);
            }));
        }

        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(6), event ->
        {
            ModelDataDAO.getInstance().getConnectionDAO().restartAllConnections();
            try {
                Model.getInstance().getViewsFactory().showLoginWindow();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            progressRestartApp.getScene().getWindow().hide();
        }));

        timeline.play();
    }
}
