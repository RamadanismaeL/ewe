package ram.ewe.englishwithexperts.Controllers.Dashboard;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import ram.ewe.englishwithexperts.Enums.DashboardEnum;
import ram.ewe.englishwithexperts.Models.Model;
import ram.ewe.englishwithexperts.Models.ModelDataDAO;
import ram.ewe.englishwithexperts.Models.ModelViewsDAO;
import ram.ewe.englishwithexperts.Repositories.LanguagesRepository;
import ram.ewe.englishwithexperts.Repositories.ThemeRepository;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardTopController implements Initializable {
    public StackPane ramMain;
    public Button minimize;
    public Button btnClose;

    /// IDIOMA
    public CheckMenuItem langIngles;
    public CheckMenuItem langPortugues;

    /// TEMA
    public CheckMenuItem themeClaro;
    public CheckMenuItem themeEscuro;

    public MenuItem btnConfig;
    public MenuItem btnUser;

    public MenuItem restartApp;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        restartApp.setOnAction(evt -> RestartApp());
        minimize.setOnAction(evt -> minimize());
        btnClose.setOnAction(evt -> close());

        btnConfig.setOnAction(evt -> { ModelViewsDAO.getInstance().getViewsDAOFactory().getFerramentas(); });
        btnUser.setOnAction(evt -> {
            ModelViewsDAO.getInstance().getDashboardEnum().set(DashboardEnum.USUARIOS);
            ModelDataDAO.getInstance().getUserDataModel().setUserLogs(ModelDataDAO.getInstance().getUserDataModel().getUserName(), ModelDataDAO.getInstance().getUserDataModel().getProfile(), "Tela : [Definições] > [Usuários] -- aberto.");
        });

        Language();
        Theme();
    }

    public void close()
    {
        try {
            Model.getInstance().getAlertsFactory().getOnExitDashboardView();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void minimize()
    {
        Stage _stage = (Stage) ramMain.getScene().getWindow();
        _stage.setIconified(true);
        ModelDataDAO.getInstance().getUserDataModel().setUserLogs(ModelDataDAO.getInstance().getUserDataModel().getUserName(), ModelDataDAO.getInstance().getUserDataModel().getProfile(), "O sistema foi minimizado.");
    }

    private void RestartApp()
    {
        try {
            Model.getInstance().getAlertsFactory().getOnRestartApp();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void Language()
    {
        langIngles.setSelected(LanguagesRepository.getEng());
        langPortugues.setSelected(LanguagesRepository.getPort());

        langIngles.setOnAction(evt ->
        {
            if(langIngles.isSelected())
            {
                ModelDataDAO.getInstance().getUserDataModel().setUserLogs(ModelDataDAO.getInstance().getUserDataModel().getUserName(), ModelDataDAO.getInstance().getUserDataModel().getProfile(), "Idioma alterado para o Inglês.");
                langIngles.setSelected(true);
                langPortugues.setSelected(false);
                LanguagesRepository.setEng(true);
                LanguagesRepository.setPort(false);
                Model.getInstance().getAlertsFactory().getOnIdioma();
            }
            else
            {
                langIngles.setSelected(true);
                langPortugues.setSelected(false);
                Model.getInstance().getAlertsFactory().getOnIdiomaSelected();
            }
        });

        langPortugues.setOnAction(evt ->
        {
            if(langPortugues.isSelected())
            {
                ModelDataDAO.getInstance().getUserDataModel().setUserLogs(ModelDataDAO.getInstance().getUserDataModel().getUserName(), ModelDataDAO.getInstance().getUserDataModel().getProfile(), "Idioma alterado para o Português.");
                langIngles.setSelected(false);
                langPortugues.setSelected(true);
                LanguagesRepository.setEng(false);
                LanguagesRepository.setPort(true);
                Model.getInstance().getAlertsFactory().getOnIdioma();
            }
            else
            {
                langIngles.setSelected(false);
                langPortugues.setSelected(true);
                Model.getInstance().getAlertsFactory().getOnIdiomaSelected();
            }
        });
    }

    private void Theme()
    {
        themeClaro.setSelected(ThemeRepository.getClaro());
        themeEscuro.setSelected(ThemeRepository.getEscuro());

        themeClaro.setOnAction(evt ->
        {
            if(themeClaro.isSelected())
            {
                ModelDataDAO.getInstance().getUserDataModel().setUserLogs(ModelDataDAO.getInstance().getUserDataModel().getUserName(), ModelDataDAO.getInstance().getUserDataModel().getProfile(), "Tema alterado para o modo claro.");
                themeClaro.setSelected(true);
                themeEscuro.setSelected(false);
                ThemeRepository.setClaro(true);
                ThemeRepository.setEscuro(false);
                Model.getInstance().getAlertsFactory().getOnTheme();
            }
            else
            {
                themeClaro.setSelected(true);
                themeEscuro.setSelected(false);
                Model.getInstance().getAlertsFactory().getOnThemeSelected();
            }
        });

        themeEscuro.setOnAction(evt ->
        {
            if(themeEscuro.isSelected())
            {
                ModelDataDAO.getInstance().getUserDataModel().setUserLogs(ModelDataDAO.getInstance().getUserDataModel().getUserName(), ModelDataDAO.getInstance().getUserDataModel().getProfile(), "Tema alterado para o modo escuro.");
                themeClaro.setSelected(false);
                themeEscuro.setSelected(true);
                ThemeRepository.setClaro(false);
                ThemeRepository.setEscuro(true);
                Model.getInstance().getAlertsFactory().getOnTheme();
            }
            else
            {
                themeClaro.setSelected(false);
                themeEscuro.setSelected(true);
                Model.getInstance().getAlertsFactory().getOnThemeSelected();
            }
        });
    }
}
