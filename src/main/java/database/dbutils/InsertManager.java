package database.dbutils;

import java.sql.*;

public class InsertManager {
    private static Statement statement;
    private static Connection connection;
    DbManager dbManager = new DbManager();


    public InsertManager(){
        connection = dbManager.getConnection();
        statement = dbManager.getStatement();
    }

    public boolean insertArmament(String model, String number, String rank, String name, String surname, int platoon, String state, String note){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into armaments values(NULL, ?, ?, ?, ?, ?, ?, ?, ?);");
            preparedStatement.setString(1,model);
            preparedStatement.setString(2, number);
            preparedStatement.setString(3, rank);
            preparedStatement.setString(4, name);
            preparedStatement.setString(5, surname);
            preparedStatement.setInt(6, platoon);
            preparedStatement.setString(7, state);
            preparedStatement.setString(8, note);
            preparedStatement.execute();
        } catch (SQLException e){
            System.err.println("Błąd przy dodawaniu uzbrojenia");
            e.printStackTrace();
            return false;
        }
        return true;
    }



    public boolean insertAccount(String rank, String name, String surname, String password){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into accounts_new values(NULL, ?, ?, ?, ?);");
            preparedStatement.setString(1, rank);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, surname);
            preparedStatement.setString(4, password);
            preparedStatement.execute();
        } catch (SQLException e){
            System.err.println("Błąd przy dodawaniu uzbrojenia");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean insertAccountTmp(String function, String rank, String name, String surname, String password){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into accounts_tmp_new values(NULL,?, ?, ?, ?, ?);");
            preparedStatement.setString(1, function);
            preparedStatement.setString(2, rank);
            preparedStatement.setString(3, name);
            preparedStatement.setString(4, surname);
            preparedStatement.setString(5, password);
            preparedStatement.execute();
        } catch (SQLException e){
            System.err.println("Błąd przy dodawaniu uzbrojenia");
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public boolean insertEquipment(String name, int amount, String description){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into equipments values(NULL, ?, ?, ?);");
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, amount);
            preparedStatement.setString(3, description);
            preparedStatement.execute();
        } catch (SQLException e){
            System.err.println("Błąd przy dodawaniu wyposazenia");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean insertNotes(String notes){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into notes values(NULL, ?, ?)");
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            preparedStatement.setTimestamp(1, timestamp);
            preparedStatement.setString(2, notes);
            preparedStatement.execute();
        } catch (SQLException  e){
            System.err.println("Błąd przy dodawaniu notes do db");
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public boolean insertReport(String report){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into reports values(NULL, ?)");
            //Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            //preparedStatement.setTimestamp(1, timestamp);
            preparedStatement.setString(1, report);
            preparedStatement.execute();
        } catch (SQLException  e){
            System.err.println("Błąd przy dodawaniu meldunku do bd");
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
