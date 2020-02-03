package controller.nco;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import utils.SceneManager;
import utils.tableUtils.TableManager;

import java.io.IOException;

public class RecordPaneController {

    @FXML private TableView<?> armamentTableView, equipmentTableView;
    SceneManager sceneManager = new SceneManager();
    TableManager tableManager = new TableManager();

    @FXML
    void backButtonAction(ActionEvent event) throws IOException {
        sceneManager.changeScene(event, "fxml/nco/ncoPane.fxml");
    }


    public void initialize(){
        tableManager.createEmptyTable("armaments", armamentTableView);
        tableManager.createEmptyTable("equipments", equipmentTableView);

    }

}
