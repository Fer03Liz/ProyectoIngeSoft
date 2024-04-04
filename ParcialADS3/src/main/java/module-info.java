module com.example.parcialads3 {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.parcialads3 to javafx.fxml;
    exports com.example.parcialads3;
}