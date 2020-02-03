package database.dbutils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteManager {

    private static Statement statement;
    private static Connection connection;
    DbManager dbManager = new DbManager();


    public DeleteManager(){
        connection = dbManager.getConnection();
        statement = dbManager.getStatement();
    }


    public void deleteArmament(int rowId, String tableName)  {
        try {
            String query="delete from "+tableName+" where id_armament = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, rowId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Nie udało sie usunac wiersza");
        }
    }


    public void deleteEquipment(int rowId, String tableName)  {
        String query="delete from "+tableName+" where id_equipment = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, rowId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Nie udało sie usunac wiersza");
        }
    }

    public void deleteAccount(int rowId, String tableName)  {
        try {
            String query="delete from "+tableName+" where id_account = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, rowId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Nie udało sie usunac wiersza");
        }
    }
}
