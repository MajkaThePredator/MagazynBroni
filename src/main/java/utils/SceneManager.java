package utils;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;

public class SceneManager {

    final double initialSceneWidth = 1000;
    final double initialSceneHeight = 640;

    @FXML
    public void changeScene(javafx.event.ActionEvent event, String fxml) throws IOException {

        Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource(fxml));
        Scene scene = new Scene(parent, initialSceneWidth, initialSceneHeight);
        Stage oldStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        oldStage.setScene(scene);
    }

    @FXML
    public void setMainScene(Stage stage) throws IOException {
        Pane mainPane = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/mainPane.fxml"));
        Scene scene = new Scene(mainPane, initialSceneWidth, initialSceneHeight);
        stage.setScene(scene);
        stage.setTitle("Magazyn Broni");
        stage.show();
    }
}
