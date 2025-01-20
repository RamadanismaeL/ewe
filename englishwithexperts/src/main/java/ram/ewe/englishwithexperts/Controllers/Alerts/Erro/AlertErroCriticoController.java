package ram.ewe.englishwithexperts.Controllers.Alerts.Erro;

import javafx.animation.*;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import ram.ewe.englishwithexperts.Repositories.LanguagesRepository;

import java.net.URL;
import java.util.ResourceBundle;

public class AlertErroCriticoController implements Initializable {
    private Stage _stage;
    private static String _erro;

    public Button btnOK;
    public ImageView iconWarning;
    public ImageView iconWarning1;
    public Label title;
    public Label info1;
    public Label info2;
    public Label info3;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(LanguagesRepository.getPort())
        {
            title.setText("MENSAGEM DE ERRO CRÍTICO");
            info1.setText("Erro crítico!!! A aplicação encontrou um problema inesperado.");
            info2.setText("Por favor, contacte o Administrador do Sistema. URGENTE.");
            info3.setText(getErroCritico());
        }
        else
        {
            title.setText("CRITICAL ERROR MESSAGE");
            info1.setText("Critical Error!!! The application found an unexpected problem.");
            info2.setText("Please contact the System Administrator. URGENT.");
            info3.setText(getErroCritico());
        }
        btnOK.setOnAction(evt -> btnOK());
        animateIcon();
        animateIcon1();
    }

    public static void setErroCritico(String erro) { _erro = erro; }
    public static String getErroCritico() { return _erro; }

    public void animateIcon() {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(iconWarning);
        transition.setDuration(Duration.seconds(0.1));;
        transition.setFromX(0);
        transition.setToX(591);

        FadeTransition fadeOut = new FadeTransition();
        fadeOut.setDuration(Duration.seconds(0.3));
        fadeOut.setNode(iconWarning);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);

        FadeTransition fadeIn = new FadeTransition();
        fadeIn.setDuration(Duration.seconds(0.3));
        fadeIn.setNode(iconWarning);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);

        SequentialTransition sequentialTransition = new SequentialTransition(fadeOut, transition, fadeIn);
        sequentialTransition.setCycleCount((SequentialTransition.INDEFINITE));
        sequentialTransition.setAutoReverse(true);

        sequentialTransition.play();
    }

    public void animateIcon1() {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(iconWarning1);
        transition.setDuration(Duration.seconds(0.1));;
        transition.setFromX(0);
        transition.setToX(-591);

        FadeTransition fadeOut = new FadeTransition();
        fadeOut.setDuration(Duration.seconds(0.3));
        fadeOut.setNode(iconWarning1);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);

        FadeTransition fadeIn = new FadeTransition();
        fadeIn.setDuration(Duration.seconds(0.3));
        fadeIn.setNode(iconWarning1);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);

        SequentialTransition sequentialTransition = new SequentialTransition(fadeOut, transition, fadeIn);
        sequentialTransition.setCycleCount((SequentialTransition.INDEFINITE));
        sequentialTransition.setAutoReverse(true);

        sequentialTransition.play();
    }

    public void btnOK()
    {
        _stage = (Stage) btnOK.getScene().getWindow();
        _stage.close();
    }
}
