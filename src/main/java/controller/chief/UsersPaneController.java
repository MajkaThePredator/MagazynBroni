package controller.chief;

import database.dbutils.EditManager;
import database.dbutils.SelectManager;
import javafx.beans.binding.Bindings;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Account;
import utils.ComboBoxManager;
import utils.SceneManager;
import utils.tableUtils.DeleteTableManager;
import utils.tableUtils.InsertTableManager;
import utils.tableUtils.RefreshTableManager;
import utils.tableUtils.TableManager;

import java.io.IOException;

public class UsersPaneController {

    public static int rowId;


    @FXML private TextField nameTextField, surnameTextField, passwordTextField, editNameTextField, editSurnameTextField, editPasswordTextField;
    @FXML private Button addButton, removeRowButton, editButton, confirmEditButton;
    @FXML private ComboBox<?> rankComboBox, editRankComboBox;
    @FXML private TableView<Account> accountsTableView;

    DeleteTableManager deleteTableManager = new DeleteTableManager();
    InsertTableManager insertTableManager = new InsertTableManager();
    RefreshTableManager refreshTableManager = new RefreshTableManager();
    SceneManager sceneManager = new SceneManager();
    TableManager tableManager = new TableManager();
    ComboBoxManager comboBoxManager = new ComboBoxManager();
    SelectManager selectManager = new SelectManager();
    EditManager editManager = new EditManager();

    @FXML
    void backToChiefPaneAction(ActionEvent event) throws IOException {
        sceneManager.changeScene(event, "fxml/chief/chiefPane.fxml");
    }

    @FXML
    void deleteRow(ActionEvent event) {
        Task deleteAccountsRowTask = new Task() {
            @Override
            protected Object call() throws Exception {
                System.out.println("Thread started - deleteAccountRow");
                Thread.sleep(1000);
                deleteTableManager.deleteAccountsRow(accountsTableView, "accounts_new");
                return null;
            }
        };
        deleteAccountsRowTask.setOnSucceeded(e->{
            System.out.println("Thread executed - deleteAccountRow");
        });
        Thread deleteAccountsRowThread = new Thread(deleteAccountsRowTask);
        deleteAccountsRowThread.start();

    }

    @FXML
    public void addData() {
        Task insertUserTask = new Task() {
            @Override
            protected Object call() throws Exception {
                System.out.println("Thread started - insertUser");
                Thread.sleep(3000);
                insertTableManager.insertUser(accountsTableView, nameTextField, surnameTextField, rankComboBox, passwordTextField);
                return null;
            }
        };
        insertUserTask.setOnSucceeded(e->{
            System.out.println("Thread executed - insertUser");
        });
        Thread insertUserThread = new Thread(insertUserTask);
        insertUserThread.start();
    }


    public void initialize() {
        Task createTableTask = new Task() {
            @Override
            protected Object call() throws Exception {
                System.out.println("Thread Started - createTable");
                tableManager.createEmptyTable("accounts_new", accountsTableView);
                return null;
            }
        };
        createTableTask.setOnSucceeded(e->{
            initBindings();
            System.out.println("Thread executed - createTable");
        });
        Thread createTableThread = new Thread(createTableTask);
        createTableThread.start();

        accountsTableView.setOnMouseClicked(mouseEvent -> {
            clearAllFields();
        });
    }


    public void initBindings() {
        try {
            addButton.disableProperty().bind(nameTextField.textProperty().isEmpty().or(
                    surnameTextField.textProperty().isEmpty()).or(
                    passwordTextField.textProperty().isEmpty()));
            confirmEditButton.disableProperty().bind(editNameTextField.textProperty().isEmpty().or(
                    editSurnameTextField.textProperty().isEmpty()).or(
                    editPasswordTextField.textProperty().isEmpty()));
            removeRowButton.disableProperty().bind(Bindings.isEmpty(accountsTableView.getSelectionModel().getSelectedItems()));
            editButton.disableProperty().bind(Bindings.isEmpty(accountsTableView.getSelectionModel().getSelectedItems()));
            String[] stopienList = new String[]{"szer. pchor", "st. szer. pchor.", "kpr. pchor.", "st. kpr. pchor.", "plut. pchor.", "sierż. pchor."};
            comboBoxManager.setComboBox(stopienList, rankComboBox);
            comboBoxManager.setComboBox(stopienList, editRankComboBox);

        } catch (Exception e) {
            System.err.println("error initBindings()");
        }
    }


    public void clearAllFields() {
        nameTextField.clear();
        surnameTextField.clear();
        passwordTextField.clear();
        editNameTextField.clear();
        editSurnameTextField.clear();
        editPasswordTextField.clear();
    }

    public void editSelectedRowAction() {
        try {
            rowId = accountsTableView.getSelectionModel().getSelectedItem().getId();
            fillFields(rowId);
            editButton.setVisible(false);
            confirmEditButton.setVisible(true);
            refreshTableManager.refreshUsersTable(accountsTableView);
            setVisible(true);

        } catch (Exception e) {
            System.err.println("nie zaznaczono wiersza do edycji");
        }
    }

    public void editRowInTable(){

        Task editRowTask = new Task() {
            @Override
            protected Object call() throws Exception {
                System.out.println("Thread started - editRow");
                editManager.editRow("UPDATE accounts_new SET  rank = '" + editRankComboBox.getSelectionModel().getSelectedItem().toString() + "', name = '" + editNameTextField.getText() + "', surname = '" + editSurnameTextField.getText() + "', password = '" + editPasswordTextField.getText() + "' WHERE id_account = " + rowId);
                return null;
            }
        };
        editRowTask.setOnSucceeded(e->{
            try {
                refreshTableManager.refreshUsersTable(accountsTableView);
                confirmEditButton.setVisible(false);
                editButton.setVisible(true);
                setVisible(false);
                System.out.println("Thread executed - editRow");
            } catch (Exception ex) {
                System.err.println("blad edycji");
            }
        });
        Thread editRowThread = new Thread(editRowTask);
        editRowThread.start();
    }

    public void fillFields(int rowId) {
        editNameTextField.clear();
        editSurnameTextField.clear();
        editPasswordTextField.clear();

        editNameTextField.setText(String.valueOf(selectManager.selectAccount("WHERE id_account = " + rowId).getName()));
        editSurnameTextField.setText(String.valueOf(selectManager.selectAccount("WHERE id_account = " + rowId).getSurname()));
        editPasswordTextField.setText(String.valueOf(selectManager.selectAccount("WHERE id_account = " + rowId).getPassword()));
    }

    public void setVisible(boolean isVisible){
        editPasswordTextField.setVisible(isVisible);
        editNameTextField.setVisible(isVisible);
        editSurnameTextField.setVisible(isVisible);
        editRankComboBox.setVisible(isVisible);
    }

}