package ram.ewe.englishwithexperts.Controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.mindrot.jbcrypt.BCrypt;
import ram.ewe.englishwithexperts.Controllers.Alerts.Erro.AlertErroCriticoController;
import ram.ewe.englishwithexperts.Data.ConnectionDAO;
import ram.ewe.englishwithexperts.Encrypt.PasswordUtil;
import ram.ewe.englishwithexperts.Models.Model;
import ram.ewe.englishwithexperts.Models.ModelDataDAO;
import ram.ewe.englishwithexperts.Repositories.LanguagesRepository;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
/**
 * @author Ramadan
 */
public class LoginController implements Initializable {
    private static FXMLLoader loaders;
    private Connection _connect = null;
    private PreparedStatement _prepare = null;
    private ResultSet _rs = null;

    public Button btnClose;
    public Button btnLogin;
    public TextField txtUsername;
    public PasswordField txtPassword;
    public TextField fldPassword;
    public FontAwesomeIconView PasswordIcon;
    public FontAwesomeIconView marca;
    public Text info;
    public Label title;
    public Button btnEye;

    public LoginController() throws SQLException
    {
        _connect = ConnectionDAO.connectDAO();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(LanguagesRepository.getPort())
        {
            info.setText("INGLÊS COM ESPECIALISTAS");
            title.setText("Bem-vindo!");
            txtUsername.setPromptText("Usuário");
            txtPassword.setPromptText("Senha");
            fldPassword.setPromptText("Senha");
            btnLogin.setText("Iniciar Sessão");

            btnClose.setTooltip(new Tooltip("Fechar"));
            title.setTooltip(new Tooltip("Bem-vindo ao sistema de gerenciamento do 'INGLÊS COM ESPECIALISTAS'."));
            txtUsername.setTooltip(new Tooltip("Digite seu nome de usuário registrado no sistema."));
            txtPassword.setTooltip(new Tooltip("Digite sua senha."));
            fldPassword.setTooltip(new Tooltip("Digite sua senha."));
            btnLogin.setTooltip(new Tooltip("Clique para acessar sua conta."));
            btnEye.setTooltip(new Tooltip("Mostrar"));
        }
        else
        {
            info.setText("ENGLISH WITH EXPERTS");
            title.setText("Welcome!");
            txtUsername.setPromptText("Username");
            txtPassword.setPromptText("Password");
            fldPassword.setPromptText("Password");
            btnLogin.setText("Login");

            btnClose.setTooltip(new Tooltip("Close"));
            title.setTooltip(new Tooltip("Welcome to the 'ENGLISH WITH EXPERTS' management system."));
            txtUsername.setTooltip(new Tooltip("Enter your registered username in the system."));
            txtPassword.setTooltip(new Tooltip("Enter your password."));
            fldPassword.setTooltip(new Tooltip("Enter your password."));
            btnLogin.setTooltip(new Tooltip("Click to access your account."));
            btnEye.setTooltip(new Tooltip("Show"));
        }
        fldPassword.setVisible(false);
        btnClose.setOnAction(event -> close());
        btnLogin.setOnAction(event -> onLogin());

        animateMarca();
        animateInfo();
        eyes();
    }

    public static void setFXMLLoader(FXMLLoader loader)
    {
        loaders = loader;
    }

    public FXMLLoader getLoader() { return loaders; }

    public void close()
    {
        ModelDataDAO.getInstance().getConnectionDAO().closeAllConnections();
        System.exit(0);
    }

    public void eyes()
    {
        txtPassword.textProperty().bindBidirectional(fldPassword.textProperty());
        PasswordIcon.setOnMouseClicked(MouseEvent ->
        {
            if(!txtPassword.isVisible() && fldPassword.isVisible())
            {
                if(LanguagesRepository.getPort()) { btnEye.setTooltip(new Tooltip("Mostrar")); }
                else { btnEye.setTooltip(new Tooltip("Show")); }
                PasswordIcon.setIcon(FontAwesomeIcon.EYE);
                txtPassword.setVisible(true);
                fldPassword.setVisible(false);
            }
            else if(txtPassword.isVisible() && !fldPassword.isVisible()) {
                if(LanguagesRepository.getPort()) { btnEye.setTooltip(new Tooltip("Ocultar")); }
                else { btnEye.setTooltip(new Tooltip("Hide")); }
                PasswordIcon.setIcon(FontAwesomeIcon.EYE_SLASH);
                txtPassword.setVisible(false);
                fldPassword.setVisible(true);
            }
        });
    }

    public void animateMarca()
    {
        FadeTransition fade = new FadeTransition(Duration.seconds(1), marca);
        fade.setFromValue(1.0);
        fade.setToValue(0.5);
        fade.setCycleCount(Timeline.INDEFINITE);
        fade.setAutoReverse(true);
        fade.play();
    }

    public void animateInfo()
    {
        if(LanguagesRepository.getPort())
        {
            TranslateTransition transition = new TranslateTransition();
            transition.setNode(info);
            transition.setDuration(Duration.seconds(1));
            transition.setFromX(0);
            transition.setToX(70);
            transition.setCycleCount(TranslateTransition.INDEFINITE);
            transition.setAutoReverse(true);
            transition.play();
        }
        else
        {
            TranslateTransition transition = new TranslateTransition();
            transition.setNode(info);
            transition.setDuration(Duration.seconds(1));
            transition.setFromX(0);
            transition.setToX(110);
            transition.setCycleCount(TranslateTransition.INDEFINITE);
            transition.setAutoReverse(true);
            transition.play();
        }
    }

    private void onLogin()
    {
        if(_connect == null)
        {
            Model.getInstance().getAlertsFactory().getOnErroDataBase();
        }
        else
        {
            String sql = "SELECT * FROM tbUser WHERE USUÁRIO = ?";
            try
            {
                validarCampos();
                if(!txtUsername.getText().isEmpty() && !txtPassword.getText().isEmpty() && !fldPassword.getText().isEmpty())
                {
                    _prepare = _connect.prepareStatement(sql);
                    _prepare.setString(1, txtUsername.getText());
                    _rs = _prepare.executeQuery();
                    if(_rs.next())
                    {
                        if(PasswordUtil.checkPassword(fldPassword.getText(), _rs.getString("SENHA")) && _rs.getBoolean("ESTADO"))
                        {
                            ModelDataDAO.getInstance().getUserDataModel().setUserDataModel(_rs.getString(2), _rs.getString(3), _rs.getString(5));
                            btnLogin.getScene().getWindow().hide();
                            if(_rs.getString("PERFIL").equals("admin"))
                            {
                                //System.out.println("Logado como admin...");
                                Model.getInstance().getProgressFactory().getOnProgressLoginDashboard();
                            }
                            else
                            {
                                System.out.println("Logado como user...");
                            }
                            ModelDataDAO.getInstance().getUserDataModel().setUserLogs(ModelDataDAO.getInstance().getUserDataModel().getUserName(), ModelDataDAO.getInstance().getUserDataModel().getProfile(), "O sistema foi aberto com sucesso.");
                            //System.out.println("LOGADO com "+_rs.getString(2)+"\nUsuário : "+_rs.getString(3)+"\nPerfil : "+_rs.getString(5));
                        }
                        else
                        {
                            Model.getInstance().getAlertsFactory().getOnLoginError();
                            txtUsername.getStyleClass().removeAll("textGray");
                            txtPassword.getStyleClass().removeAll("textGray");
                            fldPassword.getStyleClass().removeAll("textGray");
                            txtUsername.getStyleClass().add("textEmptyRed");
                            txtPassword.getStyleClass().add("textEmptyRed");
                            txtPassword.clear();
                            fldPassword.clear();
                        }
                    }
                    else
                    {
                        Model.getInstance().getAlertsFactory().getOnLoginError();
                        txtUsername.getStyleClass().removeAll("textGray");
                        txtPassword.getStyleClass().removeAll("textGray");
                        fldPassword.getStyleClass().removeAll("textGray");
                        txtUsername.getStyleClass().add("textEmptyRed");
                        txtPassword.getStyleClass().add("textEmptyRed");
                        fldPassword.getStyleClass().add("textEmptyRed");
                        txtPassword.clear();
                        fldPassword.clear();
                    }
                }
            }
            catch (Exception e)
            {
                AlertErroCriticoController controller = getLoader().getController();
                controller.info3.setText(e.getMessage());
            }
        }
    }

    private void validarCampos()
    {
        if(txtUsername.getText().isEmpty())
        {
            txtUsername.getStyleClass().removeAll("textGray");
            txtUsername.getStyleClass().add("textEmptyRed");
        }
        if(txtPassword.getText().isEmpty() && fldPassword.getText().isEmpty())
        {
            txtPassword.getStyleClass().removeAll("textGray");
            fldPassword.getStyleClass().removeAll("textGray");

            txtPassword.getStyleClass().add("textEmptyRed");
            fldPassword.getStyleClass().add("textEmptyRed");
        }

        if(txtUsername.getText().isEmpty() || (txtPassword.getText().isEmpty() && fldPassword.getText().isEmpty())) { Model.getInstance().getAlertsFactory().getOnEmptyView(); }

        blackValidation();
    }

    private void blackValidation()
    {
        if(!txtUsername.getText().isEmpty())
        {
            txtUsername.getStyleClass().removeAll("textEmptyRed");
            txtUsername.getStyleClass().add("textGray");
        }
        if(!txtPassword.getText().isEmpty() && !fldPassword.getText().isEmpty())
        {
            txtPassword.getStyleClass().removeAll("textEmptyRed");
            fldPassword.getStyleClass().removeAll("textEmptyRed");

            txtPassword.getStyleClass().add("textGray");
            fldPassword.getStyleClass().add("textGray");
        }
    }
}
