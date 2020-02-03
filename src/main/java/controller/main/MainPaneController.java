package controller.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.AlertDialog;
import utils.SceneManager;
import java.io.IOException;
import java.util.Optional;

public class MainPaneController {

    SceneManager sceneManager = new SceneManager();
    @FXML private Button exitButton;

    public void loadCeoMenuAction(ActionEvent event)  {
        AlertDialog alertDialog = new AlertDialog("oldNco");
        Optional<String> result = alertDialog.showAndWait();
        result.ifPresent(passwd -> {
            try {
                sceneManager.changeScene(event, "fxml/nco/ncoPane.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void loadChiefMenuAction(ActionEvent event) {
        AlertDialog alertDialog = new AlertDialog();
        Optional<String> result = alertDialog.showAndWait();
        result.ifPresent(passwd -> {
            try {
                sceneManager.changeScene(event, "fxml/chief/chiefPane.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void reportsBookButtonAction(ActionEvent event) throws IOException{
        sceneManager.changeScene(event, "fxml/reportsBookPane.fxml");
    }

    public void closeApplication(){
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}
