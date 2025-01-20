package ram.ewe.englishwithexperts;

import javafx.application.Application;
import javafx.stage.Stage;
import ram.ewe.englishwithexperts.Controllers.Receipt.PaymentsController;
import ram.ewe.englishwithexperts.Data.ConnectionLITE;
import ram.ewe.englishwithexperts.Models.Model;
import ram.ewe.englishwithexperts.Models.ModelViewsDAO;
import ram.ewe.englishwithexperts.Repositories.RegistrationsRepository;
import ram.ewe.englishwithexperts.Repositories.valorPorExtenso;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Model.getInstance().getViewsFactory().showLoginWindow();
        Model.getInstance().getViewsFactory().showDashboardWindow();

        //ModelViewsDAO.getInstance().getViewsDAOFactory().getOnReceiptPayment();
        //Model.getInstance().getAlertsFactory().getOnRegisterPaymentSuccess();
        //for(int i = 100000; i <= 500000; i += 100000) { System.out.println(valorPorExtenso.setValor(i)+" - "+i+"\n"); }
        //System.out.println(valorPorExtenso.setValor(999999));
        //ModelViewsDAO.getInstance().getViewsDAOFactory().getFerramentas();
        //RegistrationsRepository.set_ndm_Delete(3);
        //RegistrationsRepository.setUpdateTaxaDeInscricao(21);
        //ModelViewsDAO.getInstance().getViewsDAOFactory().getUsersViews();
        //ConnectionLITE.connectLite();
        //Model.getInstance().getAlertsFactory().getERROCRITICO();
        //Model.getInstance().getAlertsFactory().getOnRemove();
        //Model.getInstance().getAlertsFactory().getOnQQ_RemoveData();
        //Model.getInstance().getAlertsFactory().getOnCorrectPassword();
        //Model.getInstance().getAlertsFactory().getOnEmptyView();
        //Model.getInstance().getAlertsFactory().getOnLoginError();
        //Model.getInstance().getAlertsFactory().getOnExitView();
        //Model.getInstance().getAlertsFactory().getOnErroDataBase();
        //Model.getInstance().getAlertsFactory().getOnWelcome();
        //Model.getInstance().getAlertsFactory().getOnIdioma();
        //Model.getInstance().getAlertsFactory().getOnIdiomaSelected();
        //Model.getInstance().getAlertsFactory().getOnTheme();
        //Model.getInstance().getAlertsFactory().getOnThemeSelected();
        //Model.getInstance().getAlertsFactory().getOnRestartApp();
        //Model.getInstance().getAlertsFactory().getOnExitDashboardView();
        //Model.getInstance().getAlertsFactory().getOnPassword6View();
        //Model.getInstance().getAlertsFactory().getOnPasswordEqualView();
        //Model.getInstance().getAlertsFactory().getOnUserExistView();
        //Model.getInstance().getAlertsFactory().getOnRegister();
        //Model.getInstance().getAlertsFactory().getOnResetFields();
        /*try {
            ModelViewsDAO.getInstance().getViewsDAOFactory().getUsersChangePassword();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
        //Model.getInstance().getProgressFactory().getOnProgressLoginDashboard();
        //Model.getInstance().getProgressFactory().getOnProgressRestartApp();
        //Model.getInstance().getAlertsFactory().getOnWelcome();
        //new Languages();
        //System.out.println(getClass().getResource("/ram/ewe/englishwithexperts/Temas/Claro/AlertaEscuro.css"));
        //ModelViewsDAO.getInstance().getViewsDAOFactory().getUsersViews();
    }
}
