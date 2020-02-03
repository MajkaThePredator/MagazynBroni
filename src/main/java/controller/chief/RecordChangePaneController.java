package controller.chief;

import database.dbutils.EditManager;
import database.dbutils.SelectManager;
import javafx.concurrent.Task;
import javafx.scene.layout.StackPane;
import model.Equipment;
import utils.tableUtils.DeleteTableManager;
import utils.tableUtils.InsertTableManager;
import utils.tableUtils.RefreshTableManager;
import utils.tableUtils.TableManager;
import javafx.beans.binding.Bindings;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import model.Armament;
import utils.ComboBoxManager;
import utils.SceneManager;
import java.io.IOException;

public class RecordChangePaneController {





    @FXML private Button addButton, removeRowButton, editButton, confirmEditButton;
    @FXML private Button addEquipmentButton, removeEquipmentRowButton, editEquipmentButton, confirmEquipmentButton;
    @FXML private TableView<Armament> armamentTableView;
    @FXML private  TableView<Equipment> equipmentTableView;
    @FXML private TextField modelTextField, numberTextField, nameTextField, surnameTextField, platoonTextField, notesTextField;
    @FXML private TextField editModelTextField, editNumberTextField, editNameTextField, editSurnameTextField, editPlatoonTextField, editNotesTextField;
    @FXML private TextField equipmentNameTextField, amountTextField, descriptionTextField, editEqNameTextField, editAmountTextField, editNoteTextField;
    @FXML private ComboBox stateComboBox, rankComboBox, editStateComboBox, editRankComboBox;
    @FXML private StackPane armamentStackPane, EquipmentStackPane;

    SceneManager sceneManager = new SceneManager();
    TableManager tableManager = new TableManager();
    ComboBoxManager comboBoxManager = new ComboBoxManager();
    SelectManager selectManager = new SelectManager();
    EditManager editManager = new EditManager();
    DeleteTableManager deleteTableManager = new DeleteTableManager();
    InsertTableManager insertTableManager = new InsertTableManager();
    RefreshTableManager refreshTableManager = new RefreshTableManager();

    public static int armamentRowId, equipmentRowId;

    @FXML
    public void backToChiefPaneAction(ActionEvent event) throws IOException {
        sceneManager.changeScene(event, "fxml/chief/chiefPane.fxml");
    }

    public void initialize(){
        tableManager.createEmptyTable("armaments", armamentTableView);
        tableManager.createEmptyTable("equipments", equipmentTableView);
        initBindings();
        initEquipmentBindings();
        armamentTableView.setOnMouseClicked(mouseEvent -> {
            armamentStackPane.setVisible(true);
            EquipmentStackPane.setVisible(false);
            clearAllFields();
        });

        equipmentTableView.setOnMouseClicked(mouseEvent -> {
            armamentStackPane.setVisible(false);
            EquipmentStackPane.setVisible(true);
            clearAllFields();
        });
    }




    @FXML
    public void addArmamentData() {
        Task insertArmamentTask = new Task() {
            @Override
            protected Object call() throws Exception {
                System.out.println("Thread started - insertArmamentThread");
                insertTableManager.insertArmament(armamentTableView,modelTextField, numberTextField, rankComboBox, nameTextField, surnameTextField, platoonTextField, stateComboBox, notesTextField);
                return null;
            }
        };
        insertArmamentTask.setOnSucceeded(e->{
            System.out.println("Thread executed - insertArmamentThread");
        });
        Thread insertArmamentThread = new Thread(insertArmamentTask);
        insertArmamentThread.start();

    }
    @FXML
    public void addEquipmentData() {
        Task insertEquipmentTask = new Task() {
            @Override
            protected Object call() throws Exception {
                System.out.println("Thread started - insertEquipmentThread");
                insertTableManager.insertEquipment(equipmentTableView, equipmentNameTextField, amountTextField, descriptionTextField);
                return null;
            }
        };
        insertEquipmentTask.setOnSucceeded(e->{
            System.out.println("Thread executed - insertEquipmentThread");
        });
        Thread insertEquipmentThread = new Thread(insertEquipmentTask);
        insertEquipmentThread.start();

    }


    public void initBindings(){
        try {
            addButton.disableProperty().bind(modelTextField.textProperty().isEmpty().or(
                    numberTextField.textProperty().isEmpty()).or(
                    nameTextField.textProperty().isEmpty()).or(
                    surnameTextField.textProperty().isEmpty().or(
                            platoonTextField.textProperty().isEmpty())));
            confirmEditButton.disableProperty().bind(editModelTextField.textProperty().isEmpty().or(
                    editNumberTextField.textProperty().isEmpty()).or(
                    editNameTextField.textProperty().isEmpty()).or(
                    editSurnameTextField.textProperty().isEmpty().or(
                            editPlatoonTextField.textProperty().isEmpty())));
            removeRowButton.disableProperty().bind(Bindings.isEmpty(armamentTableView.getSelectionModel().getSelectedItems()));
            editButton.disableProperty().bind(Bindings.isEmpty(armamentTableView.getSelectionModel().getSelectedItems()));

            String[] stateList = new String[]{"W magazynie", "Wydana"};
            String[] rankList = new String[]{"szer. pchor", "st. szer. pchor.", "kpr. pchor.", "st. kpr. pchor.","plut. pchor.", "sierż. pchor."};
            comboBoxManager.setComboBox(stateList, stateComboBox);
            comboBoxManager.setComboBox(stateList, editStateComboBox);
            comboBoxManager.setComboBox(rankList, rankComboBox);
            comboBoxManager.setComboBox(rankList, editRankComboBox);

        }catch (Exception e){
            System.err.println("error initBindings()");
        }
    }

    public void initEquipmentBindings(){
        try {
            addEquipmentButton.disableProperty().bind(equipmentNameTextField.textProperty().isEmpty().or(
                    amountTextField.textProperty().isEmpty()));
            removeEquipmentRowButton.disableProperty().bind(Bindings.isEmpty(equipmentTableView.getSelectionModel().getSelectedItems()));
            editEquipmentButton.disableProperty().bind(Bindings.isEmpty(equipmentTableView.getSelectionModel().getSelectedItems()));

        }catch (Exception e){
            System.err.println("error initBindings()");
        }
    }

    @FXML
    private void deleteRow() {
        Task deleteRowTask = new Task() {
            @Override
            protected Object call() throws Exception {
                System.out.println("Thread started - deleteRow");
                deleteTableManager.deleteArmamentRow(armamentTableView, "armaments");
                return null;
            }
        };
        deleteRowTask.setOnSucceeded(e->{
            System.out.println("Thread executed - deleteRow");
        });
        Thread deleteRowThread = new Thread(deleteRowTask);
        deleteRowThread.start();
    }

    @FXML
    private void deleteEquipmentRow() {
        Task deleteRowTask = new Task() {
            @Override
            protected Object call() throws Exception {
                System.out.println("Thread started - deleteRow");
                deleteTableManager.deleteEquipmentRow(equipmentTableView, "equipments");
                return null;
            }
        };
        deleteRowTask.setOnSucceeded(e->{
            System.out.println("Thread executed - deleteRow");
        });
        Thread deleteRowThread = new Thread(deleteRowTask);
        deleteRowThread.start();
    }

    public void editSelectedArmamentRow(){
        try {
            armamentRowId = armamentTableView.getSelectionModel().getSelectedItem().getId();
            fillFields(armamentRowId);
            setArmamentVisibility(true);
            editButton.setVisible(false);
            confirmEditButton.setVisible(true);
            refreshTableManager.refreshArmamentTable(armamentTableView);
        }catch (Exception e){
            System.err.println("nie zaznaczono wiersza do edycji");
        }
    }

    public void editSelectedEquipmentRow(){
        try {
            equipmentRowId = equipmentTableView.getSelectionModel().getSelectedItem().getId();
            fillEquipmentFields(equipmentRowId);
            setEquipmentVisibility(true);
            editEquipmentButton.setVisible(false);
            confirmEquipmentButton.setVisible(true);
            refreshTableManager.refreshEquipmentTable(equipmentTableView);
        }catch (Exception e){
            System.err.println("nie zaznaczono wiersza do edycji");
        }
    }




    public void editArmamentRowAction(){
        Task editRowTask = new Task() {
            @Override
            protected Object call() throws Exception {
                System.out.println("Thread started - editRow");
                editManager.editRow(armamentRowId,"armaments",editModelTextField.getText(), editNumberTextField.getText(), editRankComboBox.getSelectionModel().getSelectedItem().toString(), editNameTextField.getText(), editSurnameTextField.getText(),Integer.parseInt(editPlatoonTextField.getText()), editStateComboBox.getSelectionModel().getSelectedItem().toString(), editNotesTextField.getText());
                Thread.sleep(4000);
                return null;
            }
        };
        editRowTask.setOnSucceeded(e->{
            try{
                refreshTableManager.refreshArmamentTable(armamentTableView);
                setArmamentVisibility(false);
                confirmEditButton.setVisible(false);
                editButton.setVisible(true);
                System.out.println("Thread executed - editRow");
            }catch (Exception ex){
                System.err.println("blad edycji");
            }
        });
        Thread editRowThread = new Thread(editRowTask);
        editRowThread.start();


    }
    public void editEquipmentRowAction(){
        Task editEquipmentRow = new Task() {
            @Override
            protected Object call() throws Exception {
                System.out.println("Thread started - editRow");
                editManager.editEquipmentRow(equipmentRowId,"equipments", editEqNameTextField.getText(),Integer.parseInt(editAmountTextField.getText()), editNoteTextField.getText());
                return null;
            }
        };
        editEquipmentRow.setOnSucceeded(e->{
            try{
                refreshTableManager.refreshEquipmentTable(equipmentTableView);
                setEquipmentVisibility(false);
                confirmEquipmentButton.setVisible(false);
                editEquipmentButton.setVisible(true);
                System.out.println("Thread executed - editRow");

            }catch (Exception ex){
                System.err.println("blad edycji");
            }

        });
        Thread editEquipmentThread = new Thread(editEquipmentRow);
        editEquipmentThread.start();


    }


    public void setArmamentVisibility(boolean visibility){
        editButton.setVisible(visibility);
        editModelTextField.setVisible(visibility);
        editNumberTextField.setVisible(visibility);
        editRankComboBox.setVisible(visibility);
        editNameTextField.setVisible(visibility);
        editSurnameTextField.setVisible(visibility);
        editPlatoonTextField.setVisible(visibility);
        editStateComboBox.setVisible(visibility);
        editNotesTextField.setVisible(visibility);
    }

    public void setEquipmentVisibility(boolean visibility){
        editEquipmentButton.setVisible(visibility);
        editEqNameTextField.setVisible(visibility);
        editAmountTextField.setVisible(visibility);
        editNoteTextField.setVisible(visibility);
    }

    public void fillFields(int rowId) {
        editModelTextField.clear();
        editNumberTextField.clear();
        editNameTextField.clear();
        editSurnameTextField.clear();
        editPlatoonTextField.clear();
        editNotesTextField.clear();
        editModelTextField.setText(String.valueOf(selectManager.selectArmament(rowId).get(0)));
        editNumberTextField.setText(String.valueOf(selectManager.selectArmament(rowId).get(1)));
        editRankComboBox.getSelectionModel().select(String.valueOf(selectManager.selectArmament(rowId).get(2)));
        editNameTextField.setText(String.valueOf(selectManager.selectArmament(rowId).get(3)));
        editSurnameTextField.setText(String.valueOf(selectManager.selectArmament(rowId).get(4)));
        editPlatoonTextField.setText(String.valueOf(selectManager.selectArmament(rowId).get(5)));
        editStateComboBox.getSelectionModel().select(String.valueOf(selectManager.selectArmament(rowId).get(6)));
        editNotesTextField.setText(String.valueOf(selectManager.selectArmament(rowId).get(7)));


    }

    public void fillEquipmentFields(int rowId) {
        editEqNameTextField.clear();
        editAmountTextField.clear();
        editNoteTextField.clear();
        editEqNameTextField.setText(String.valueOf(selectManager.selectEquipmentRow(rowId).getName()));
        editAmountTextField.setText(String.valueOf(selectManager.selectEquipmentRow(rowId).getAmount()));
        editNoteTextField.setText(String.valueOf(selectManager.selectEquipmentRow(rowId).getDescription()));
    }

    public void clearAllFields(){
        modelTextField.clear();
        numberTextField.clear();
        nameTextField.clear();
        surnameTextField.clear();
        platoonTextField.clear();
        notesTextField.clear();
        equipmentNameTextField.clear();
        amountTextField.clear();
        descriptionTextField.clear();
        editModelTextField.clear();
        editNumberTextField.clear();
        editNameTextField.clear();
        editSurnameTextField.clear();
        editPlatoonTextField.clear();
        editNotesTextField.clear();
        editEqNameTextField.clear();
        editAmountTextField.clear();
        editNoteTextField.clear();
    }
}
