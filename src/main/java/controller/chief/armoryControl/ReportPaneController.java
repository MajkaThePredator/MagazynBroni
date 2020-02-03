package controller.chief.armoryControl;

import database.dbutils.InsertManager;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import utils.ReportManager;
import utils.SceneManager;
import java.io.IOException;
import java.util.Optional;

public class ReportPaneController {

    @FXML private TextArea reportTextArea;
    @FXML private ProgressIndicator progressIndicator;
    SceneManager sceneManager = new SceneManager();
    public final  String person = "st. chor. sztab. Marcin KAZIMIEROWSKI referentka nr. SE 148";

    @FXML
    public void backButtonAction(ActionEvent event) throws IOException {
        sceneManager.changeScene(event, "fxml/chief/armoryControl/recordPane.fxml");
    }

    ReportManager reportManager = new ReportManager();
    InsertManager insertManager = new InsertManager();

    public void initialize(){
        createReport();
    }


    public void createReport() {

        Task<String > createReportTask = new Task<String>() {
            @Override
            protected String  call() throws Exception {
                System.out.println("Thread started - createReport");
                Thread.sleep(2000);
                return reportManager.createReport(person);
            }
        };
        createReportTask.setOnSucceeded(e->{
            reportTextArea.setText(createReportTask.getValue());
            System.out.println("Thread executed - createReport");
        });
        Thread createReportThread = new Thread(createReportTask);
        createReportThread.start();
    }



    public void confirmButtonAction(ActionEvent event) throws IOException {

        Task insertReportTask = new Task() {
            @Override
            protected Object call() throws Exception {
                System.out.println("Thread started - insertReport");
                Thread.sleep(3000);
                insertManager.insertReport(reportManager.createReport(person));
                return null;
            }
        };
        insertReportTask.setOnSucceeded(e->{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Utworzono report");
            alert.setHeaderText(null);
            alert.setContentText("Report został wygenerowany");

            ButtonType wyjdzButton = new ButtonType("Wyjscie");
            ButtonType wróćDoMenuButton = new ButtonType("Wróć do menu głównego");

            alert.getButtonTypes().setAll(wróćDoMenuButton, wyjdzButton);
            Optional<ButtonType> result = alert.showAndWait();

            if(result.get()==wróćDoMenuButton){
                try {
                    sceneManager.changeScene(event, "fxml/mainPane.fxml");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            else if(result.get()==wyjdzButton){
                ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
            }
            System.out.println("Thread executed - insertReport");

        });
        Thread insertReportThread = new Thread(insertReportTask);
        insertReportThread.start();

    }





}
