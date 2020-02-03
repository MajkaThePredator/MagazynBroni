package utils.tableUtils;

import database.dbutils.DeleteManager;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import model.Account;
import model.Armament;
import model.Equipment;

public class DeleteTableManager {
    private static int rowId;
    DeleteManager deleteManager = new DeleteManager();
    TableManager tableManager = new TableManager();
    RefreshTableManager refreshTableManager = new RefreshTableManager();

    @FXML
    public void deleteArmamentRow(TableView<Armament> tableView, String tableName) {
        try {
            rowId = tableView.getSelectionModel().getSelectedItem().getId();
            deleteManager.deleteArmament(rowId, tableName);
            refreshTableManager.refreshArmamentTable(tableView);
        }
        catch (Exception e){
            System.err.println("Wybierz wiersz do usunięcia");
        }

    }


    @FXML
    public void deleteEquipmentRow(TableView<Equipment> tableView, String tableName) {
        try {
            rowId = tableView.getSelectionModel().getSelectedItem().getId();
            deleteManager.deleteEquipment(rowId, tableName);
            refreshTableManager.refreshEquipmentTable(tableView);
        }
        catch (Exception e){
            System.err.println("Wybierz wiersz do usunięcia");
        }

    }


    @FXML
    public void deleteAccountsRow(TableView<Account> tableView, String tableName) {
        try {
            rowId = tableView.getSelectionModel().getSelectedItem().getId();
            deleteManager.deleteAccount(rowId, tableName);
            refreshTableManager.refreshUsersTable(tableView);
        }
        catch (Exception e){
            System.err.println("Wybierz wiersz do usunięcia");
        }

    }
}
