module com.quadcore.connectfour {
    requires javafx.controls;
    requires javafx.fxml;


    exports com.quadcore.connectfour.view;
    opens com.quadcore.connectfour.view to javafx.fxml;
}
