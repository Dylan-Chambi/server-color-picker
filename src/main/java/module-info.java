module com.example.colorpickerserver {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.colorpickerserver to javafx.fxml;
    exports com.example.colorpickerserver;
}