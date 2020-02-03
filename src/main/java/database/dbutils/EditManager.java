package database.dbutils;

import model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class EditManager {

    private static Statement statement;
    private static Connection connection;
    DbManager dbManager = new DbManager();


    public EditManager(){
        connection = dbManager.getConnection();
        statement = dbManager.getStatement();
    }


    public void editEquipmentRow(int rowId, String tableName, String name, int amount, String description){
        try{
            String query="UPDATE "+tableName+" SET name = '"+ name+"', amount = '"+ amount +"', description ='"+description + "' WHERE id_equipment = "+rowId;
            System.out.println(query);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
            statement.close();

        }catch (SQLException e){
            e.printStackTrace();
            System.err.println();
        }

    }


    public void editRow(int rowId, String tableName, String model, String number, String rank, String name, String surname, int platoon, String state, String note){
        try{
            String query="UPDATE "+tableName+" SET model = '"+model+ "', number = '"+number+"', rank = '"+rank+ "', name = '"+name+ "', surname = '"+surname+ "', platoon = '"+platoon+ "', state = '" +state +"', note = '"+ note+"' WHERE id_armament = "+rowId;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
            statement.close();

        }catch (SQLException e){
            e.printStackTrace();
            System.err.println();
        }

    }


    public void updateAccountTmp(Account account, String function){
        try{

            String query="UPDATE accounts_tmp_new SET rank = '"+account.getRank()+ "', name = '"+account.getName()+"', surname = '"+account.getSurname()+ "', password = '"+account.getPassword()+ "' WHERE function = '"+function+"'";
            System.out.println(query);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
            statement.close();

        }catch (SQLException e){
            e.printStackTrace();
            System.err.println();
        }

    }


    public void editRow(String query){
        try{
            System.out.println(query);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
            statement.close();

        }catch (SQLException e){
            e.printStackTrace();
            System.err.println();
        }

    }


    public void editUwagiRow(int rowId, String tableName,String uwagi){
        try{
            String query="UPDATE "+tableName+" SET notes = '"+ uwagi+"' WHERE id_note = "+rowId;
            System.out.println(query);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
            statement.close();

        }catch (SQLException e){
            e.printStackTrace();
            System.err.println();
        }

    }














}
