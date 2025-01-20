package ram.ewe.englishwithexperts.Controllers;

import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ram.ewe.englishwithexperts.Enums.DashboardEnum;
import ram.ewe.englishwithexperts.Models.Model;
import ram.ewe.englishwithexperts.Models.ModelViewsDAO;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable
{
    private Stage _stage;
    public BorderPane ramBorder;
    public StackPane ramCenter;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        ramBorder.setTop(ModelViewsDAO.getInstance().getViewsDAOFactory().getDashboardTop());
        ramBorder.setLeft(ModelViewsDAO.getInstance().getViewsDAOFactory().getDashboardLef());
        /*btnUser.setOnAction(evt -> {
            ramCenter.getChildren().addAll(ModelViewsDAO.getInstance().getViewsDAOFactory().getUsersViews());
            ramBorder.setCenter(ramCenter);
            //ramBorder.getLeft().setVisible(false);
        });*/
        ModelViewsDAO.getInstance().getDashboardEnum().addListener((observableValue, oldValue, newValue) ->
        {
            if(newValue == DashboardEnum.DESCONECTAR)
            {
                Model.getInstance().getAlertsFactory().getOnLogOutApp();
            }
            if(newValue == DashboardEnum.USUARIOS)
            {
                ramCenter.getChildren().clear();
                ramCenter.getChildren().addAll(ModelViewsDAO.getInstance().getViewsDAOFactory().getUsersViews());
                ramBorder.setCenter(ramCenter);
            }
            if(newValue == DashboardEnum.INSCRICOES)
            {
                ramCenter.getChildren().clear();
                ramCenter.getChildren().addAll(ModelViewsDAO.getInstance().getViewsDAOFactory().getRegistrationsViews());
                ramBorder.setCenter(ramCenter);
            }

            Platform.runLater(() -> {
                ModelViewsDAO.getInstance().getDashboardEnum().setValue(DashboardEnum.DEFAULT);
            });
        });
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
