package ram.ewe.englishwithexperts.Controllers.Alerts.Confirmacao;

import javafx.animation.*;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import ram.ewe.englishwithexperts.Models.ModelControllers;
import ram.ewe.englishwithexperts.Models.Model;
import ram.ewe.englishwithexperts.Models.ModelDataDAO;
import ram.ewe.englishwithexperts.Repositories.LanguagesRepository;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AlertRestartAppController implements Initializable {
    private Stage _stage;

    public Label lblInfo;
    public ImageView iconExit;
    public Button btnExit_sim;
    public Button btnExit_nao;
    public Label title;
    public Label info1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(LanguagesRepository.getPort())
        {
            title.setText("Mensagem de Confirmação");
            info1.setText("Tem certeza de que deseja reiniciar?");
            btnExit_sim.setText("Sim");
            btnExit_nao.setText("Não");
        }
        else
        {
            title.setText("Confirmation Message");
            info1.setText("Are you sure you want to restart?");
            btnExit_sim.setText("Yes");
            btnExit_nao.setText("No");
        }
        btnExit_sim.setOnAction(evt -> btnExitSim());
        btnExit_nao.setOnAction(evt -> btnExitNao());

        animateIcon();
        closeAuto();
    }

    public void animateIcon() {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(iconExit);
        transition.setDuration(Duration.seconds(0.1));;
        transition.setFromX(0);
        transition.setToX(330);

        FadeTransition fadeOut = new FadeTransition();
        fadeOut.setDuration(Duration.seconds(1));
        fadeOut.setNode(iconExit);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);

        FadeTransition fadeIn = new FadeTransition();
        fadeIn.setDuration(Duration.seconds(1));
        fadeIn.setNode(iconExit);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);

        SequentialTransition sequentialTransition = new SequentialTransition(fadeOut, transition, fadeIn);
        sequentialTransition.setCycleCount((SequentialTransition.INDEFINITE));
        sequentialTransition.setAutoReverse(true);

        sequentialTransition.play();
    }

    public void btnExitSim()
    {
        try {
            ModelDataDAO.getInstance().getUserDataModel().setUserLogs(ModelDataDAO.getInstance().getUserDataModel().getUserName(), ModelDataDAO.getInstance().getUserDataModel().getProfile(), "O sistema foi reiniciado.");
            ModelControllers.getInstance().getDashboardController().hideDash();
            Model.getInstance().getProgressFactory().getOnProgressRestartApp();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnExitNao()
    {
        _stage = (Stage) btnExit_nao.getScene().getWindow();
        _stage.close();
    }

    public void closeAuto()
    {
        boolean language = LanguagesRepository.getPort();
        Timeline timeline = new Timeline();

        for(int i = 15; i >= 0; i--)
        {
            int seconds = i;
            timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(15 - i), event ->
            {
                if(language)
                {
                    lblInfo.setText("Fechando em "+seconds+" segundos...");
                }
                else
                {
                    lblInfo.setText("Auto close after "+seconds+" seconds...");
                }
            }));
        }

        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(15), event ->
        {
            _stage = (Stage) btnExit_nao.getScene().getWindow();
            _stage.close();
        }));

        timeline.play();
    }

    public void setStageDash(Stage stage) { this._stage = stage; }

    public void hideDash()
    {
        if(this._stage != null)
        {
            this._stage.hide();
        }
    }
}
