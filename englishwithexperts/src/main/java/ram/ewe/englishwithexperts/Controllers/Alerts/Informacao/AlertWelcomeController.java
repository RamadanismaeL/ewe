package ram.ewe.englishwithexperts.Controllers.Alerts.Informacao;

import javafx.animation.*;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import ram.ewe.englishwithexperts.Models.ModelDataDAO;
import ram.ewe.englishwithexperts.Repositories.LanguagesRepository;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Ramadan Ismael
 */
public class AlertWelcomeController implements Initializable
{
    private Stage _stage;
    public Button btnOK;
    public ImageView iconInfo;
    public Label lblInfo;
    public Label welcome;
    public Label title;
    public Label info1;
    public Label info2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(LanguagesRepository.getPort())
        {
            title.setText("Mensagem de Informação");
            info1.setText("Bem-vindo(a) de volta!");
            info2.setText("Ficamos felizes em te ver novamente.");
            btnOK.setText("Obrigado(a)!");
        }
        else
        {
            title.setText("Information Message");
            info1.setText("Welcome back!");
            info2.setText("We are glad to see you again,");
            btnOK.setText("Thank you!");
        }

        welcome.setText(ModelDataDAO.getInstance().getUserDataModel().getFullName()+".");
        btnOK.setOnAction(evt -> btnOK());
        animateIcon();
        closeAuto();
    }

    public void animateIcon()
    {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(iconInfo);
        transition.setDuration(Duration.seconds(0.1));
        transition.setFromX(0);
        transition.setToX(330);

        FadeTransition fadeOut = new FadeTransition();
        fadeOut.setDuration(Duration.seconds(1));
        fadeOut.setNode(iconInfo);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);

        FadeTransition fadeIn = new FadeTransition();
        fadeIn.setDuration(Duration.seconds(1));
        fadeIn.setNode(iconInfo);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);

        SequentialTransition sequentialTransition = new SequentialTransition(fadeOut, transition, fadeIn);
        sequentialTransition.setCycleCount(SequentialTransition.INDEFINITE);
        sequentialTransition.setAutoReverse(true);

        sequentialTransition.play();
    }

    public void btnOK()
    {
        _stage = (Stage) btnOK.getScene().getWindow();
        _stage.close();
    }

    public void closeAuto()
    {
        boolean language = LanguagesRepository.getPort();
        Timeline timeline = new Timeline();

        for(int i = 15; i >= 0; i--)
        {
            int seconds = i;
            timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(15 - i), event
            ->
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
            _stage = (Stage) btnOK.getScene().getWindow();
            _stage.close();
        }));

        timeline.play();
    }
}
