package database.dbutils;

import javafx.collections.FXCollections;
import model.*;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;


public class SelectManager {
    private static Statement statement;
    private static Connection connection;
    DbManager dbManager = new DbManager();


    public SelectManager(){
        connection = dbManager.getConnection();
        statement = dbManager.getStatement();
    }



    public List<Armament> selectArmament()  {
        List<Armament> armament = new LinkedList<>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM armaments");
            while (resultSet.next()){
                armament.add(new Armament(
                        resultSet.getInt("id_armament"),
                        resultSet.getString("model"),
                        resultSet.getString("number"),
                        resultSet.getString("rank"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getInt("platoon"),
                        resultSet.getString("state"),
                        resultSet.getString("note")));
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("cannot select from uzbrojenies");
            return null;
        }
        return FXCollections.observableList(armament);
    }


    public List<Armament> selectArmament(String whereQuery)  {
        List<Armament> armament = new LinkedList<Armament>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM armaments "+whereQuery);
            while (resultSet.next()){
                armament.add(new Armament(
                        resultSet.getInt("id_armament"),
                        resultSet.getString("model"),
                        resultSet.getString("number"),
                        resultSet.getString("rank"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getInt("platoon"),
                        resultSet.getString("state"),
                        resultSet.getString("note")));
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("nie mozna pobra danych");
            return null;
        }
        return FXCollections.observableList(armament);
    }

    public List<Object> selectArmament(int rowId){
        List<Object> armament = new LinkedList<>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM armaments WHERE id_armament = "+rowId);

            armament.add(resultSet.getString("model"));
            armament.add(resultSet.getString("number"));
            armament.add(resultSet.getString("rank"));
            armament.add(resultSet.getString("name"));
            armament.add(resultSet.getString("surname"));
            armament.add(resultSet.getInt("platoon"));
            armament.add(resultSet.getString("state"));
            armament.add(resultSet.getString("note"));
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Błąd przy pobieraniu nazw kolumn");
        }
        return armament;
    }


    public Account selectAccount(String whereQuery)  {
        Account account;
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM accounts_new "+whereQuery);
            account = new Account(
                    resultSet.getInt("id_account"),
                    resultSet.getString("rank"),
                    resultSet.getString("name"),
                    resultSet.getString("surname"),
                    resultSet.getString("password"));
            statement.close();
        } catch (SQLException e) {
            return null;
        }
        return account;
    }


    public List<Account> selectAccount()  {
        List<Account> accounts = new LinkedList<>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM accounts_new");
            while (resultSet.next()){
                accounts.add(new Account(
                        resultSet.getInt("id_account"),
                        resultSet.getString("rank"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("password")));
            }
            statement.close();
        } catch (SQLException e) {
            return null;
        }
        return FXCollections.observableList(accounts);
    }



    public List<Account> selectFunctionAccount()  {
        List<Account> accounts = new LinkedList<>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM accounts_tmp_new");
            while (resultSet.next()){
                accounts.add(new Account(
                        resultSet.getInt("id_account"),
                        resultSet.getString("rank"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("password")));
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("nie mozna pobrac danych");
            return null;
        }
        return FXCollections.observableList(accounts);
    }


    public List<Equipment> selectEquipment()  {
        List<Equipment> equipment = new LinkedList<Equipment>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM equipments");
            while (resultSet.next()){
                equipment.add(new Equipment(
                        resultSet.getInt("id_equipment"),
                        resultSet.getString("name"),
                        resultSet.getInt("amount"),
                        resultSet.getString("description")));
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("nie mozna pobrac danych");
            return null;
        }
        return FXCollections.observableList(equipment);
    }





    public Equipment selectEquipmentRow(int rowId){
        Equipment equipment = null;
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM equipments WHERE id_equipment = "+rowId);

            equipment = new Equipment(
                    resultSet.getInt("id_equipment"),
                    resultSet.getString("name"),
                    resultSet.getInt("amount"),
                    resultSet.getString("description"));
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Błąd przy pobieraniu wiersza");
        }
        return equipment;
    }


    public Note selectLastRowFromNotes(){
        Note note = null;
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM notes ORDER BY id_note DESC LIMIT 1");
            note = new Note(
                    resultSet.getInt("id_note"),
                    resultSet.getTimestamp("timestapm"),
                    resultSet.getString("notes"));

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Błąd przy pobieraniu nazw kolumn");
        }
        return note;
    }


    public List<Report> selectReport() throws SQLException {
        List<Report> reports = new LinkedList<>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM reports ");
            while (resultSet.next()){
                reports.add(new Report(
                        resultSet.getInt("id_report"),
                        resultSet.getString("report")));
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("nie mozna pobrac danych");
            return null;
        }
        return FXCollections.observableList(reports);
    }


    public List<String> getColumnNames(String tableName){
        List<String> columnNamesList = new LinkedList<>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM "+tableName);
            int columnCount = resultSet.getMetaData().getColumnCount();
            for(int i=1; i<=columnCount; i++){
                String columnName = resultSet.getMetaData().getColumnName(i);
                columnNamesList.add(columnName);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Błąd przy pobieraniu nazw kolumn");
        }
        return columnNamesList;
    }

    public String getIdColumnName(String tableName, Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT * FROM "+tableName);
        String columnName = resultSet.getMetaData().getColumnName(1);
        System.out.println(columnName);
        return columnName;
    }
}
