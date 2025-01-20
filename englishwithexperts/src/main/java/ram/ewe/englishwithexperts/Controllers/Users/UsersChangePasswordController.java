package ram.ewe.englishwithexperts.Controllers.Users;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ram.ewe.englishwithexperts.Encrypt.PasswordUtil;
import ram.ewe.englishwithexperts.Models.Model;
import ram.ewe.englishwithexperts.Models.ModelDataDAO;
import ram.ewe.englishwithexperts.Repositories.LanguagesRepository;

import java.net.URL;
import java.util.ResourceBundle;

public class UsersChangePasswordController implements Initializable {
    private Stage _stage;
    private static FXMLLoader loaders;

    public StackPane formMain;
    public Label title;
    public Button btnCancelar;
    public Button btnConfirmar;
    public Label info1;
    public TextField fldSenha;
    public PasswordField txtSenha;
    public FontAwesomeIconView eyeSenha;

    public static String getCheck()
    {
        String check;
        return check = UsersController.getSenhaAtual();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(LanguagesRepository.getPort())
        {
            title.setText("Mudar Senha");
            info1.setText("Senha Atual");
            fldSenha.setPromptText("Digite sua senha atual");
            txtSenha.setPromptText("Digite sua senha atual");
            btnConfirmar.setText("Confirmar");
            btnCancelar.setText("Cancelar");
        }
        else
        {
            title.setText("Change Password");
            info1.setText("Current Password");
            fldSenha.setPromptText("Enter your current password");
            txtSenha.setPromptText("Enter your current password");
            btnConfirmar.setText("Confirm");
            btnCancelar.setText("Cancel");
        }

        btnCancelar.setOnAction(evt -> btnCancelar());
        btnConfirmar.setOnAction(evt -> btnConfirmar());

        eyePassword();
    }

    public static void setFXMLLoader(FXMLLoader loader)
    {
        loaders = loader;
    }

    public FXMLLoader getLoader() { return loaders; }

    public void setStageDash(Stage stage) { this._stage = stage; }

    public void hideDash()
    {
        if(this._stage != null)
        {
            this._stage.hide();
        }
    }

    public void btnCancelar()
    {
        _stage = (Stage) btnCancelar.getScene().getWindow();
        _stage.close();
    }

    private void eyePassword()
    {
        //System.out.println("isDisable = "+txtSenha.isDisabled()+" e !isDisable = "+!txtSenha.isDisabled());
        fldSenha.setVisible(false);
        txtSenha.setVisible(true);
        txtSenha.textProperty().bindBidirectional(fldSenha.textProperty());

        eyeSenha.setOnMouseClicked(MouseEvent ->
        {
            if(!txtSenha.isVisible() && fldSenha.isVisible())
            {
                eyeSenha.setIcon(FontAwesomeIcon.EYE);
                txtSenha.setVisible(true);
                fldSenha.setVisible(false);
            }
            else {
                eyeSenha.setIcon(FontAwesomeIcon.EYE_SLASH);
                txtSenha.setVisible(false);
                fldSenha.setVisible(true);
            }
        });
    }

    public void btnConfirmar()
    {
        validation();
        String senhaAtual = fldSenha.getText();
        //System.out.println("\nVisível ? - "+UsersController.getHabilitar());
        //System.out.println("Senha digitado = "+txtSenha.getText());
        //System.out.println("Senha por Mudar = "+ UserChangePasswordRepository.getSenhaDB());
        //System.out.println("Senha por Mudar = "+getCheck());
        //System.out.println("Senha descriptogrado = "+PasswordUtil.checkPassword(senhaAtual, getCheck()));

        if(PasswordUtil.checkPassword(senhaAtual, getCheck()))
        {
            //System.out.println("FXMLLoader = "+getLoader());
            UsersController usersController = getLoader().getController();
            usersController.txtSenha.clear();
            usersController.fldSenha.clear();
            usersController.txtConfirmarSenha.clear();
            usersController.fldConfirmarSenha.clear();
            usersController.txtSenha.setDisable(false);
            usersController.fldSenha.setDisable(false);
            usersController.txtConfirmarSenha.setDisable(false);
            usersController.fldConfirmarSenha.setDisable(false);
            usersController.eyeSenha.setFill(Color.rgb(67, 69, 67));
            usersController.eyeConfirmarSenha.setFill(Color.rgb(67, 69, 67));
            usersController.mudarSenha.setVisible(false);

            ModelDataDAO.getInstance().getUserDataModel().setUserLogs(ModelDataDAO.getInstance().getUserDataModel().getUserName(), ModelDataDAO.getInstance().getUserDataModel().getProfile(), "Tela : [Definições] > [Usuários] > [Mudar Senha] -- Mudou a senha de acesso.");
            Model.getInstance().getAlertsFactory().getOnCorrectPassword();
        }
        else
        {
            Model.getInstance().getAlertsFactory().getOnPasswordIncorrectView();
        }
    }

    private void validation()
    {
        if(txtSenha.getText().isEmpty() && fldSenha.getText().isEmpty())
        {
            txtSenha.getStyleClass().removeAll("textGray");
            fldSenha.getStyleClass().removeAll("textGray");

            txtSenha.getStyleClass().add("textEmptyRed");
            fldSenha.getStyleClass().add("textEmptyRed");
            Model.getInstance().getAlertsFactory().getOnEmptyView();
        }
        if(!txtSenha.getText().isEmpty() && !fldSenha.getText().isEmpty())
        {
            if(txtSenha.getText().length() >= 6)
            {
                txtSenha.getStyleClass().removeAll("textEmptyRed");
                fldSenha.getStyleClass().removeAll("textEmptyRed");

                txtSenha.getStyleClass().add("textGray");
                fldSenha.getStyleClass().add("textGray");
            }
            else
            {
                txtSenha.getStyleClass().removeAll("textGray");
                fldSenha.getStyleClass().removeAll("textGray");

                txtSenha.getStyleClass().add("textEmptyRed");
                fldSenha.getStyleClass().add("textEmptyRed");
                Model.getInstance().getAlertsFactory().getOnPassword6View();
            }
        }
    }
}
