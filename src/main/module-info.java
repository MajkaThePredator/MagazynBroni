open module MagazynBroni{
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    exports main;
    opens controller to javafx.fxml;
    opens controller to java.base;
}