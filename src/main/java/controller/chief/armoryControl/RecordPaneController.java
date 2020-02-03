package controller.chief.armoryControl;

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
    @FXML private Button nextStepButton;
    @FXML private Label notesLabel;
    @FXML private TextArea notesTextArea;
    @FXML private CheckBox trueCheckBox, falseCheckBox;

    TableManager tableManager = new TableManager();
    SceneManager sceneManager = new SceneManager();
    InsertManager insertManager = new InsertManager();




    @FXML
    public void backToChiefPaneAction(ActionEvent event) throws IOException {
        sceneManager.changeScene(event, "fxml/chief/chiefPane.fxml");
    }



    public void initialize(){
        tableManager.createEmptyTable("armaments", riflesTable, "WHERE state = 'W magazynie' AND (model = 'Beryl' OR model = 'AK-47' OR model = 'SWD')");
            tableManager.createEmptyTable("armaments", grenadesTable, "WHERE state = 'W magazynie' AND (model = 'RG-42' OR model = 'UZRGM'  OR model = 'F1')");
             tableManager.createEmptyTable("equipments", equipmentTable);

        trueCheckBox.setOnAction(event -> {
            if(trueCheckBox.isSelected()) nextStepButton.setDisable(false);
            else if(!trueCheckBox.isSelected()) nextStepButton.setDisable(true);
            falseCheckBox.setSelected(false);
            notesTextArea.clear();
            notesTextArea.setDisable(true);
            notesLabel.setDisable(true);
        });

        falseCheckBox.setOnAction(event -> {
            nextStepButton.setDisable(true);
            if(falseCheckBox.isSelected()) {
                notesLabel.setDisable(false);
                notesTextArea.setDisable(false);
            }
            else if(!falseCheckBox.isSelected()) {
                    nextStepButton.setDisable(true);
                    notesLabel.setDisable(true);
                    notesTextArea.clear();
                    notesTextArea.setDisable(true);
            }
            trueCheckBox.setSelected(false);

        });

        notesTextArea.setOnKeyPressed(keyEvent -> {
            nextStepButton.setDisable(false);
            if(notesTextArea.getText().isEmpty()) {
                nextStepButton.setDisable(true);
            }
        });


    }



    public void nextStepAction(ActionEvent event){
        notes = notesTextArea.getText();

        Task insertNotesTask = new Task() {
            @Override
            protected Object call() throws Exception {
                System.out.println("Thread statred - insertNotes");
                insertManager.insertNotes(notes);
                return null;
            }
        };
        insertNotesTask.setOnSucceeded(e->{
            try {
                sceneManager.changeScene(event, "fxml/chief/armoryControl/reportPane.fxml");
                System.out.println("Thread executed - insertNotes");
            }catch (IOException ex){
                ex.printStackTrace();
                System.err.println("nastepny krok action");
            }
        });
        Thread insertNotesThread = new Thread(insertNotesTask);
        insertNotesThread.start();
    }

    public static String getNotes() {
        return notes;
    }
}
