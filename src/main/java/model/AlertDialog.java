package model;

import database.dbutils.DbManager;
import database.dbutils.EditManager;
import database.dbutils.SelectManager;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import utils.SceneManager;


public class AlertDialog extends Dialog<String> {

    private PasswordField passwordField;
    public static Account accountTmp;
    SelectManager selectManager = new SelectManager();
    EditManager editManager = new EditManager();



    public AlertDialog(){
        setTitle("WERYFIKACJA");
        setHeaderText("Podaj identyfikator.");
        ButtonType passwordButtonType = new ButtonType("Zatwierdz", ButtonBar.ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().addAll(passwordButtonType, ButtonType.CANCEL);

        passwordField = new PasswordField();
        passwordField.setPromptText("Identyfikator");

        HBox hBox = new HBox();
        hBox.getChildren().add(passwordField);
        hBox.setPadding(new Insets(20));
        HBox.setHgrow(passwordField, Priority.ALWAYS);
        getDialogPane().setContent(hBox);

        Platform.runLater(()-> passwordField.requestFocus());
         setResultConverter(dialogButton -> {
                if (dialogButton == passwordButtonType) {
                    String password = passwordField.getText();
                    accountTmp = selectManager.selectFunctionAccount().get(0);
                    if(password.equals(accountTmp.getPassword())){
                        return accountTmp.getPassword();
                    }
                }
                return null;
            });
    }



    public AlertDialog(String funkcja){
        setTitle("WERYFIKACJA");
        setHeaderText("Podaj identyfikator.");
        ButtonType passwordButtonType = new ButtonType("Zatwierdz", ButtonBar.ButtonData.OK_DONE);

        getDialogPane().getButtonTypes().addAll(passwordButtonType, ButtonType.CANCEL);

        passwordField = new PasswordField();
        passwordField.setPromptText("Identyfikator");


        HBox hBox = new HBox();
        hBox.getChildren().add(passwordField);
        hBox.setPadding(new Insets(20));
        HBox.setHgrow(passwordField, Priority.ALWAYS);
        getDialogPane().setContent(hBox);

        Platform.runLater(()-> passwordField.requestFocus());
        setResultConverter(dialogButton -> {
            if (dialogButton == passwordButtonType) {
                String password = passwordField.getText();
                accountTmp = selectManager.selectAccount(" WHERE password = '" + password +"'");
                if(accountTmp!=null){

                    // jesli zle dac zeby przycisk byl niewidoczny

                    editManager.updateAccountTmp(accountTmp, funkcja);
                    System.out.println("update " + funkcja);
                    return String.valueOf(accountTmp.getPassword());
                }
                else {
                    return null;
                }
            }
            else {
                System.out.println("Dede");
                return null;

            }
        });
    }


    public PasswordField getPasswordField(){
        return passwordField;
    }
}
