package ram.ewe.englishwithexperts.Controllers.Alerts.Sucesso;

import javafx.animation.*;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import ram.ewe.englishwithexperts.Controllers.Receipt.PaymentsController;
import ram.ewe.englishwithexperts.Controllers.Registrations.RegistrationsController;
import ram.ewe.englishwithexperts.Models.ModelViewsDAO;
import ram.ewe.englishwithexperts.Repositories.LanguagesRepository;
import ram.ewe.englishwithexperts.Repositories.RegistrationsRepository;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AlertRegisterPaymentsController implements Initializable {
    private static FXMLLoader _loader;
    private static FXMLLoader _receipt;
    private Stage _stage;
    public Button btnOK;
    public ImageView iconWarning;
    public Label lblInfo;
    public Label title;
    public Label info1;
    public Hyperlink reciboPagamento;

    public static FXMLLoader getFXMLoader() { return _loader; }
    public static void setFXMLoader(FXMLLoader loader) { _loader = loader; }
    public static FXMLLoader getReceiptPayment() { return _receipt; }
    public static void setReceiptPayment(FXMLLoader loader) { _receipt = loader; }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ModelViewsDAO.getInstance().getViewsDAOFactory().getOnReceiptPayment();
        PaymentsController controlPay = getReceiptPayment().getController();
        RegistrationsController controller = getFXMLoader().getController();
        controller.hidePayment();
        if(LanguagesRepository.getPort())
        {
            title.setText("Mensagem de Sucesso");
            info1.setText("Inscrição realizada com sucesso.");
            reciboPagamento.setText("Clique aqui para baixar RECIBO DE PAGAMENTO.");
        }
        else
        {
            title.setText("Success Message");
            info1.setText("Registration completed successfully.");
            reciboPagamento.setText("Click here to download PAYMENT RECEIPT.");
        }
        reciboPagamento.setOnAction(evt -> {
            controlPay.generatePDF();
            System.out.println("Recibo gerado com sucesso!");
        });
        btnOK.setOnAction(evt -> btnOK());
        animateIcon();
        closeAuto();
    }

    public void animateIcon() {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(iconWarning);
        transition.setDuration(Duration.seconds(0.1));;
        transition.setFromX(0);
        transition.setToX(530);

        FadeTransition fadeOut = new FadeTransition();
        fadeOut.setDuration(Duration.seconds(1));
        fadeOut.setNode(iconWarning);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);

        FadeTransition fadeIn = new FadeTransition();
        fadeIn.setDuration(Duration.seconds(1));
        fadeIn.setNode(iconWarning);
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

    public void closeAuto()
    {
        boolean language = LanguagesRepository.getPort();
        Timeline timeline = new Timeline();

        for(int i = 60; i >= 0; i--)
        {
            int seconds = i;
            timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(60 - i), event ->
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

        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(60), event ->
        {
            _stage = (Stage) btnOK.getScene().getWindow();
            _stage.close();
        }));

        timeline.play();
    }
}
