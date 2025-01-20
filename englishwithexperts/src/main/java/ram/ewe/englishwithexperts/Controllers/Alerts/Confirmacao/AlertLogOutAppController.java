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

public class AlertLogOutAppController implements Initializable {
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
            info1.setText("Tem certeza de que deseja sair?");
            btnExit_sim.setText("Sim");
            btnExit_nao.setText("Não");
        }
        else
        {
            title.setText("Confirmation Message");
            info1.setText("Are you sure you want to log out?");
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
        ModelDataDAO.getInstance().getUserDataModel().setUserLogs(ModelDataDAO.getInstance().getUserDataModel().getUserName(), ModelDataDAO.getInstance().getUserDataModel().getProfile(), "Saiu da conta.");
        ModelControllers.getInstance().getDashboardController().hideDash();
        ModelDataDAO.getInstance().getConnectionDAO().restartAllConnections();
        try {
            Model.getInstance().getViewsFactory().showLoginWindow();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        btnExit_sim.getScene().getWindow().hide();
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
}
