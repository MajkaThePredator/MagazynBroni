package controller.nco.armoryOvertake;

import database.dbutils.InsertManager;
import database.dbutils.SelectManager;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Account;
import utils.ReportManager;
import utils.SceneManager;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ReportPaneController {

    @FXML private TextArea reportTextArea;
    SceneManager sceneManager = new SceneManager();

    @FXML
    public void backButtonAction(ActionEvent event) throws IOException {
        sceneManager.changeScene(event, "fxml/nco/armoryOvertake/recordPane.fxml");
    }



    SelectManager selectManager = new SelectManager();
    ReportManager reportManager = new ReportManager();
    InsertManager insertManager = new InsertManager();
    public void initialize()  {
        createReport();
    }


    public void createReport() {

        Task<List<Account>> selectFunctionAccountTask = new Task<List<Account>>() {
            @Override
            protected List<Account> call() throws Exception {
                System.out.println("Thread started - select function account");
                List<Account> accounts = new LinkedList<>();
                accounts.add(selectManager.selectFunctionAccount().get(1));
                //Thread.sleep(2000);
                accounts.add(selectManager.selectFunctionAccount().get(2));
                return accounts;
            }
        };
        selectFunctionAccountTask.setOnSucceeded(e->{
            if(selectFunctionAccountTask.getValue()!=null){
                reportTextArea.setText(reportManager.createReport(selectFunctionAccountTask.getValue().get(0), selectFunctionAccountTask.getValue().get(1)));
            }
            else {
                System.out.println("Error CreateReport");
            }
            System.out.println("Thread succeded - select function account");
        });
        Thread selectFunctionAccountThread = new Thread(selectFunctionAccountTask);
        selectFunctionAccountThread.start();
    }



    public void confirmButtonAction(ActionEvent event) throws IOException {


        Task insertReportTask = new Task() {
            @Override
            protected Object call() throws Exception {
                System.out.println("Thread started - insertReport");
                Thread.sleep(3000);
                insertManager.insertReport(reportTextArea.getText());
                return null;
            }
        };
        insertReportTask.setOnSucceeded(e->{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Utworzono report");
            alert.setHeaderText(null);
            alert.setContentText("Report został wygenerowany");

            ButtonType exitButton = new ButtonType("Wyjscie");
            ButtonType backToMenuButton = new ButtonType("Wróć do menu głównego");

            alert.getButtonTypes().setAll(backToMenuButton, exitButton);
            Optional<ButtonType> result = alert.showAndWait();

            if(result.get()== backToMenuButton){
                try {
                    sceneManager.changeScene(event, "fxml/mainPane.fxml");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            else if(result.get()== exitButton){
                ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
            }
            System.out.println("Thread executed - insertReport");

        });
        Thread insertReportThread = new Thread(insertReportTask);
        insertReportThread.start();



    }
}
