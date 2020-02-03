package controller.nco;

import model.AlertDialog;
import utils.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;
import java.util.Optional;

public class NcoPaneController {

    SceneManager sceneManager = new SceneManager();

    @FXML
    public void backToMainMenuAction(ActionEvent event) throws IOException {
        sceneManager.changeScene(event,"fxml/mainPane.fxml");
    }

    @FXML
    public void overtakeArmoryAction(ActionEvent event){

        AlertDialog alertDialog = new AlertDialog("newNco");
        Optional<String> result = alertDialog.showAndWait();
        result.ifPresent(passwd -> {
            try {
                sceneManager.changeScene(event, "fxml/nco/armoryOvertake/recordPane.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    public void armorWithdrawAction(ActionEvent event) throws IOException{
        sceneManager.changeScene(event, "fxml/nco/withdrawPane.fxml");
    }

    @FXML
    public void recordAction(ActionEvent event) throws IOException{
        sceneManager.changeScene(event, "fxml/nco/recordPane.fxml");
    }

}
