package utils.tableUtils;

import database.dbutils.SelectManager;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.Armament;
import java.sql.SQLException;
import java.util.List;

public class TableManager {
    SelectManager selectManager = new SelectManager();

    public void createEmptyTable(String tableName, TableView tableView, String whereQuery) {
        try {
            List<String> list = selectManager.getColumnNames(tableName);
            for(int i=0; i<list.size(); i++)
                if (i == 0) {
                    TableColumn numberColumn = new TableColumn( "Lp." );
                    numberColumn.setCellFactory( new Callback<TableColumn, TableCell>()
                    {
                        @Override
                        public TableCell call( TableColumn p )
                        {
                            return new TableCell()
                            {
                                @Override
                                public void updateItem( Object item, boolean empty )
                                {
                                    super.updateItem( item, empty );
                                    setGraphic( null );
                                    setText( empty ? null : getIndex() + 1 + "" );
                                }
                            };
                        }
                    });
                    tableView.getColumns().add(numberColumn);

                } else {
                    String name = list.get(i);
                    String newName = name.substring(0, 1).toUpperCase() + name.toLowerCase().substring(1);
                    TableColumn<Armament, String> tableColumn = new TableColumn<>(newName);
                    tableColumn.setCellValueFactory(new PropertyValueFactory<>(list.get(i)));
                    tableView.getColumns().add(tableColumn);
                }
            fillTable(tableView, tableName, whereQuery);
        }catch (Exception e){
            System.err.println("error createTable() wherequery");
        }

    }


    public void createEmptyTable(String tableName, TableView tableView) {
        try {
            List<String> list = selectManager.getColumnNames(tableName);
            for(int i=0; i<list.size(); i++) {
                if (i == 0) {
                    TableColumn numberColumn = new TableColumn("Lp.");
                    numberColumn.setCellFactory(new Callback<TableColumn, TableCell>() {
                        @Override
                        public TableCell call(TableColumn p) {
                            return new TableCell() {
                                @Override
                                public void updateItem(Object item, boolean empty) {
                                    super.updateItem(item, empty);
                                    setGraphic(null);
                                    setText(empty ? null : getIndex() + 1 + "");
                                }
                            };
                        }
                    });
                    tableView.getColumns().add(numberColumn);

                } else {
                    String name = list.get(i);
                    String newName = name.substring(0, 1).toUpperCase() + name.toLowerCase().substring(1);
                    TableColumn<Object, String> tableColumn = new TableColumn<>(newName);
                    tableColumn.setCellValueFactory(new PropertyValueFactory<>(list.get(i)));
                    tableView.getColumns().add(tableColumn);
                }
            }
            fillTable(tableView, tableName);
            tableView.isEditable();
        }catch (Exception e){
            System.err.println("error createTable() "+tableName);
            e.printStackTrace();
        }

    }

    public void fillTable(TableView tableView, String tableName) throws SQLException {
        if(tableName == "armaments"){
            tableView.setItems(FXCollections.observableArrayList(selectManager.selectArmament()));
        }
        else if(tableName == "equipments"){
            tableView.setItems(FXCollections.observableArrayList(selectManager.selectEquipment()));
        }
        else if(tableName == "reports"){
            tableView.setItems(FXCollections.observableArrayList(selectManager.selectReport()));

        }
        else if(tableName == "accounts_new"){
            tableView.setItems(FXCollections.observableArrayList(selectManager.selectAccount()));

        }


    }

    public void fillTable(TableView tableView, String tableName, String whereQuery){
        if(tableName == "armaments"){
            tableView.setItems(FXCollections.observableArrayList(selectManager.selectArmament(whereQuery)));
        }

    }
}
