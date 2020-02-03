package controller.nco.armoryOvertake;

import database.dbutils.InsertManager;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import utils.SceneManager;
import utils.tableUtils.TableManager;

import java.io.IOException;

public class RecordPaneController {

    public static String notes;
    @FXML private TableView<?> riflesTable, grenadesTable, equipmentTable;
    @FXML private Button nextStepAction;
    @FXML private Label notesLabel;
    @FXML private TextArea notesTextArea;
    @FXML private CheckBox trueCheckBox, falseCheckBox;

    TableManager tableManager = new TableManager();
    SceneManager sceneManager = new SceneManager();
    InsertManager insertManager = new InsertManager();


    @FXML
    public void backButton(ActionEvent event) throws IOException {
        sceneManager.changeScene(event, "fxml/nco/ncoPane.fxml");
    }



    public void initialize(){
        tableManager.createEmptyTable("armaments", riflesTable, "WHERE state = 'W magazynie' AND (model = 'Beryl' OR model = 'AK-47' OR model = 'SWD')");
            tableManager.createEmptyTable("armaments", grenadesTable, "WHERE state = 'W magazynie' AND (model = 'RG-42' OR model = 'UZRGM'  OR model = 'F1')");
            tableManager.createEmptyTable("equipments", equipmentTable);

        trueCheckBox.setOnAction(event -> {
            if(trueCheckBox.isSelected()) nextStepAction.setDisable(false);
            else if(!trueCheckBox.isSelected()) nextStepAction.setDisable(true);
            falseCheckBox.setSelected(false);
            notesTextArea.clear();
            notesTextArea.setDisable(true);
            notesLabel.setDisable(true);
        });

        falseCheckBox.setOnAction(event -> {
            nextStepAction.setDisable(true);
            if(falseCheckBox.isSelected()) {
                notesLabel.setDisable(false);
                notesTextArea.setDisable(false);
            }
            else if(!falseCheckBox.isSelected()) {
                nextStepAction.setDisable(true);
                notesLabel.setDisable(true);
                notesTextArea.clear();
                notesTextArea.setDisable(true);
            }
            trueCheckBox.setSelected(false);

        });
        notesTextArea.setOnKeyPressed(keyEvent -> {
            nextStepAction.setDisable(false);
            if(notesTextArea.getText().isEmpty()) {
                nextStepAction.setDisable(true);
            }
        });
    }


    public void createReport() {
        notes = notesTextArea.getText();

        Task insertNotesTask = new Task() {
            @Override
            protected Object call() throws Exception {
                System.out.println("Thread started  - insertNotes");
                Thread.sleep(2000);
                insertManager.insertNotes(notes);
                return null;
            }
        };
        insertNotesTask.setOnSucceeded(e->{
            System.out.println("Thread succeded  - insertNotes");
        });
        Thread insertNotesthread = new Thread(insertNotesTask);
        insertNotesthread.start();

    }

    public void nextStepAction(ActionEvent event){
        createReport();
        try {
                sceneManager.changeScene(event, "fxml/nco/armoryOvertake/reportPane.fxml");
            }catch (IOException e){
                e.printStackTrace();
                System.err.println("nastepny krok action");
            }
    }

    public static String getNotes() {
        return notes;
    }
}
