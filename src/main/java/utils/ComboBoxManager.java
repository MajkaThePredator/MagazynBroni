package utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

import java.util.ArrayList;

public class ComboBoxManager {


    public void setComboBox(String[] arrayList, ComboBox comboBox){
        comboBox.setItems(FXCollections.observableArrayList(arrayList));
        comboBox.getSelectionModel().selectFirst();
    }
}
