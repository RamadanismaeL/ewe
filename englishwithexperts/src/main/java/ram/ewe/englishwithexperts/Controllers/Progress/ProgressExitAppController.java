package ram.ewe.englishwithexperts.Controllers.Progress;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressIndicator;
import javafx.stage.Stage;
import javafx.util.Duration;
import ram.ewe.englishwithexperts.Models.ModelControllers;
import ram.ewe.englishwithexperts.Models.ModelDataDAO;

import java.net.URL;
import java.util.ResourceBundle;

public class ProgressExitAppController implements Initializable {
    private Stage _stage;
    public ProgressIndicator progressRestartApp;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ModelControllers.getInstance().getAlertExitController().hideDash();
        Timeline timeline = new Timeline();

        for(int i = 0; i <= 1; i++)
        {
            timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(i), event ->
            {
                progressRestartApp.setProgress(-1);
            }));
        }

        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event ->
        {
            ModelDataDAO.getInstance().getConnectionDAO().restartAllConnections();
            System.exit(0);
        }));

        timeline.play();
    }
}
