package controller.reportsBook;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import utils.SceneManager;
import utils.tableUtils.TableManager;
import java.io.IOException;

public class ReportsBookPaneController {

    @FXML private TableView<?> reporsTableView;
    SceneManager sceneManager = new SceneManager();
    TableManager tableManager = new TableManager();

    @FXML
    void backToChiefPaneAction(ActionEvent event) throws IOException {
        sceneManager.changeScene(event, "fxml/mainPane.fxml");
    }

    public void initialize(){
        tableManager.createEmptyTable("reports", reporsTableView);
    }
}
