package main;

import database.dbutils.DbManager;
import javafx.application.Application;
import javafx.stage.Stage;
import utils.SceneManager;
import java.sql.SQLException;

public class Main extends Application {
    SceneManager sceneManager = new SceneManager();


    public static void main(String[] args) throws SQLException {
        DbManager dbManager = new DbManager();
        //dbManager.createBasicAccounts();
        dbManager.createTables("armaments (id_armament INTEGER PRIMARY KEY AUTOINCREMENT, model varchar(255), number varchar(255), rank varchar(255), name varchar(255), surname varchar(255), platoon int, state varchar(255), note varchar(255))");
        dbManager.createTables("equipments (id_equipment INTEGER PRIMARY KEY AUTOINCREMENT, name varchar(255), amount integer, description varchar(255))");
        dbManager.createTables("notes (id_note INTEGER PRIMARY KEY AUTOINCREMENT, timestapm DATETIME, notes varchar(800))");
        dbManager.createTables("reports (id_report INTEGER PRIMARY KEY AUTOINCREMENT, report varchar(2048))");
        dbManager.createTables("accounts_new (id_account INTEGER PRIMARY KEY AUTOINCREMENT, rank varchar(255), name varchar(255), surname varchar(255), password varchar(6))");
        launch(args);
        dbManager.closeConnection();
    }

    @Override
    public void start(Stage stage) throws Exception{
        sceneManager.setMainScene(stage);
    }
}
