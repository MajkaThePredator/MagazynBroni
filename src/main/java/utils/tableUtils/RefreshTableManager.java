package utils.tableUtils;

import database.dbutils.SelectManager;
import javafx.collections.FXCollections;
import javafx.scene.control.TableView;
import model.Account;
import model.Armament;
import model.Equipment;

public class RefreshTableManager {
    SelectManager selectManager = new SelectManager();


    public void refreshArmamentTable(TableView<Armament> tableView){
        try {
            tableView.setItems(FXCollections.observableArrayList(selectManager.selectArmament()));
        }catch (Exception e){
            e.printStackTrace();
            System.err.println("Error refreshTable()");
        }
    }


    public void refreshUsersTable(TableView<Account> tableView){


        try {
            tableView.setItems(FXCollections.observableArrayList(selectManager.selectAccount()));
        }catch (Exception e){
            e.printStackTrace();
            System.err.println("Error refreshTable()");
        }
    }

    public void refreshEquipmentTable(TableView<Equipment> tableView){
        try {
            tableView.setItems(FXCollections.observableArrayList(selectManager.selectEquipment()));
        }catch (Exception e){
            e.printStackTrace();
            System.err.println("Error refreshTable()");
        }
    }
}
