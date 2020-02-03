package utils.tableUtils;

import database.dbutils.InsertManager;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Account;
import model.Armament;
import model.Equipment;

public class InsertTableManager {
    InsertManager insertManager = new InsertManager();
    RefreshTableManager refreshTableManager = new RefreshTableManager();


    @FXML
    public void insertUser(TableView<Account> tableView, TextField imputTextField1, TextField imputTextField2, ComboBox comboBox1, TextField imputTextField3) {
        try {
            insertManager.insertAccount(comboBox1.getSelectionModel().getSelectedItem().toString(), imputTextField1.getText(), imputTextField2.getText(),
                    imputTextField3.getText());

            imputTextField1.clear();
            imputTextField2.clear();
            imputTextField3.clear();
            refreshTableManager.refreshUsersTable(tableView);
        }
        catch (Exception e){
            System.err.println("Error addArmamentData()");
        }

    }


    @FXML
    public void insertEquipment(TableView<Equipment> tableView, TextField imputTextField1, TextField imputTextField2, TextField imputTextField3) {
        try {
            insertManager.insertEquipment(imputTextField1.getText(), Integer.parseInt(imputTextField2.getText()) , imputTextField3.getText());

            imputTextField1.clear();
            imputTextField2.clear();
            imputTextField3.clear();
            refreshTableManager.refreshEquipmentTable(tableView);
        }
        catch (Exception e){
            System.err.println("Error addArmamentData()");
        }

    }


    @FXML
    public void insertArmament(TableView<Armament> tableView, TextField imputTextField1, TextField imputTextField2, ComboBox comboBox1, TextField imputTextField4, TextField imputTextField5, TextField imputTextField6, ComboBox comboBox2, TextField imputTextField7) {
        try {
            insertManager.insertArmament(imputTextField1.getText(), imputTextField2.getText(), comboBox1.getSelectionModel().getSelectedItem().toString(),
                    imputTextField4.getText(), imputTextField5.getText(), Integer.parseInt(imputTextField6.getText()), comboBox2.getSelectionModel().getSelectedItem().toString(), imputTextField7.getText());

            imputTextField1.clear();
            imputTextField2.clear();
            imputTextField4.clear();
            imputTextField5.clear();
            imputTextField6.clear();
            imputTextField7.clear();
            refreshTableManager.refreshArmamentTable(tableView);
        }
        catch (Exception e){
            System.err.println("Error addArmamentData()");
        }

    }
}
