package controller.nco;

import database.dbutils.EditManager;
import database.dbutils.SelectManager;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import model.Armament;
import utils.SceneManager;
import utils.tableUtils.RefreshTableManager;
import utils.tableUtils.TableManager;

import java.io.IOException;

public class WithdrawPaneController {


    @FXML private TableView<Armament> armementTableView;
    @FXML private Button withdrawButton;
    SceneManager sceneManager = new SceneManager();
    TableManager tableManager = new TableManager();
    SelectManager selectManager = new SelectManager();
    EditManager editManager = new EditManager();
    RefreshTableManager refreshTableManager = new RefreshTableManager();
    public static int rowId;

    public void initialize(){
        tableManager.createEmptyTable("armaments", armementTableView);

        armementTableView.setOnMouseClicked(mouseEvent -> {
            rowId = armementTableView.getSelectionModel().getSelectedItem().getId();
            System.out.println(rowId);

            Task<String> selectArmamentTask = new Task<String >() {
                @Override
                protected String call() throws Exception {
                    System.out.println("Thread started - selectArmament1");
                    String state = (String) selectManager.selectArmament(rowId).get(6);
                    return state;
                }
            };
            selectArmamentTask.setOnSucceeded(e->{
                if (selectArmamentTask.getValue().equals("W magazynie")) {
                    withdrawButton.setText("Wydaj");
                } else if (selectArmamentTask.getValue().equals("Wydana")) {
                    withdrawButton.setText("Przyjmij");
                }
                withdrawButton.setDisable(false);
                System.out.println("Thread executed - selectArmament1");

            });
            Thread selectArmamentThread = new Thread(selectArmamentTask);
            selectArmamentThread.start();



        });
    }

    @FXML
    void backButtonAction(ActionEvent event) throws IOException {
        sceneManager.changeScene(event, "fxml/nco/ncoPane.fxml");
    }

    public void withdrawButtonAction(){
        Task<Armament> selectArmamentTask = new Task<Armament>() {
            @Override
            protected Armament call() throws Exception {
                System.out.println("Thread started - selectArmament2");
                String state = selectManager.selectArmament(rowId).get(6).toString();
                if(state.equals("W magazynie")){
                    editManager.editRow("UPDATE armaments SET state = 'Wydana' WHERE id_armament = "+rowId);
                }
                else if(state.equals("Wydana")){
                    editManager.editRow("UPDATE armaments SET state = 'W magazynie' WHERE id_armament = "+rowId);
                }
                return null;
            }
        };
        selectArmamentTask.setOnSucceeded(e->{
            try {
                refreshTableManager.refreshArmamentTable(armementTableView);
            }catch (Exception ex){
                System.err.println("withdrawButtonAction");
                ex.printStackTrace();
            }
            System.out.println("Thread executed - selectArmament2");

        });
        Thread selectArmamentThread = new Thread(selectArmamentTask);
        selectArmamentThread.start();
    }

}
