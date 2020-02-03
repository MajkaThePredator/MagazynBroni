package database.dbutils;

import java.sql.*;

public class   DbManager {

    public static final String DRIVER = "org.sqlite.JDBC";
    public static final String DB_URL = "jdbc:sqlite:Armory.db";
    private Connection connection;
    private Statement statement;

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }

    public DbManager(){
        try{
            Class.forName(DbManager.DRIVER);
        }catch (ClassNotFoundException e){
            System.err.println("Brak sterownika JDBC");
            e.printStackTrace();
        }
        try {
            connection=DriverManager.getConnection(DB_URL);
            statement=connection.createStatement();
        } catch (SQLException e) {
            System.err.println("Problem z otwarciem polaczenia");
            e.printStackTrace();
        }
    }

    public void closeConnection(){
        try {
            connection.close();
        }catch (SQLException e){
            System.err.println("Problem z zamknieciem polaczenia");
            e.printStackTrace();
        }
    }


    public boolean createTables(String query){
        try {
            statement.execute("CREATE TABLE IF NOT EXISTS "+query);
            statement.close();
            //connection.close();
        }catch (SQLException e){
            System.err.println("Błąd przy tworzeniu tabeli");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteTables(String tableName){
        try{
            statement.execute("DROP TABLE "+tableName);
            statement.close();
        } catch (SQLException e){
            System.err.println("Błąd przy usuwaniu tabeli");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void createBasicAccounts(){
        InsertManager insertManager = new InsertManager();
        //deleteTables("accounts_tmp");
        createTables("accounts_tmp_new (id_account INTEGER PRIMARY KEY AUTOINCREMENT, function varchar(255), rank varchar(255), name varchar(255), surname varchar(255), password varchar(6))");
        insertManager.insertAccountTmp("Chief", "","","","");
        insertManager.insertAccountTmp("newNco", "","","","");
        insertManager.insertAccountTmp("oldNco", "","","","");

    }



}
