/**
 * @author Ramadan ismaeL
 */

package ram.ewe.englishwithexperts.Views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ram.ewe.englishwithexperts.Repositories.ThemeRepository;

import java.io.IOException;

public class ProgressFactory {
    private Stage _stage;
    public void getOnProgressLoginDashboard() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ram/ewe/englishwithexperts/Controllers/Progress/ProgressLoginDashboard.fxml"));
        Parent root = loader.load();
        if(_stage == null || !_stage.isShowing()) {
            _stage = new Stage();
            Scene scene = new Scene(root);

            if(ThemeRepository.getClaro())
            {
                scene.getStylesheets().add(getClass().getResource("/ram/ewe/englishwithexperts/Temas/Claro/ProgressClaro.css").toExternalForm());
            }
            else
            {
                scene.getStylesheets().add(getClass().getResource("/ram/ewe/englishwithexperts/Temas/Escuro/ProgressEscuro.css").toExternalForm());
            }

            _stage.initStyle(StageStyle.UNDECORATED);
            _stage.setScene(scene);
            _stage.show();
        } else {
            _stage.toFront();
        }
    }

    public void getOnProgressRestartApp() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ram/ewe/englishwithexperts/Controllers/Progress/ProgressRestartApp.fxml"));
        Parent root = loader.load();
        if(_stage == null || !_stage.isShowing()) {
            _stage = new Stage();
            Scene scene = new Scene(root);

            if(ThemeRepository.getClaro())
            {
                scene.getStylesheets().add(getClass().getResource("/ram/ewe/englishwithexperts/Temas/Claro/ProgressRestartAppClaro.css").toExternalForm());
            }
            else
            {
                scene.getStylesheets().add(getClass().getResource("/ram/ewe/englishwithexperts/Temas/Escuro/ProgressRestartAppEscuro.css").toExternalForm());
            }

            _stage.initStyle(StageStyle.UNDECORATED);
            _stage.setScene(scene);
            _stage.show();
        } else {
            _stage.toFront();
        }
    }

    public void getOnProgressExitApp() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ram/ewe/englishwithexperts/Controllers/Progress/ProgressExitApp.fxml"));
        Parent root = loader.load();
        if(_stage == null || !_stage.isShowing()) {
            _stage = new Stage();
            Scene scene = new Scene(root);

            if(ThemeRepository.getClaro())
            {
                scene.getStylesheets().add(getClass().getResource("/ram/ewe/englishwithexperts/Temas/Claro/ProgressRestartAppClaro.css").toExternalForm());
            }
            else
            {
                scene.getStylesheets().add(getClass().getResource("/ram/ewe/englishwithexperts/Temas/Escuro/ProgressRestartAppEscuro.css").toExternalForm());
            }

            _stage.initStyle(StageStyle.UNDECORATED);
            _stage.setScene(scene);
            _stage.show();
        } else {
            _stage.toFront();
        }
    }

    public void getOnProgressDesconectExit() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/englishtraingcenter/Controllers/Progress/ProgressDesconect.fxml"));
        //createStage(loader);
    }
}
