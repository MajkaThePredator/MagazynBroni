package controller.chief;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import utils.SceneManager;
import java.io.IOException;

public class ChiefPaneController {
    SceneManager sceneManager = new SceneManager();


    @FXML
    public void backToMainMenuAction(ActionEvent event) throws IOException {
        sceneManager.changeScene(event,"fxml/mainPane.fxml");
    }

    @FXML
    public void armoryControlAction(ActionEvent event) throws IOException{
        sceneManager.changeScene(event, "fxml/chief/armoryControl/recordPane.fxml");
    }

    @FXML
    public void recordChangeAction(ActionEvent event) throws IOException{
        sceneManager.changeScene(event, "fxml/chief/recordChangePane.fxml");
    }

    @FXML
    public void usersAction(ActionEvent event) throws IOException{
        sceneManager.changeScene(event, "fxml/chief/usersPane.fxml");
    }



}
