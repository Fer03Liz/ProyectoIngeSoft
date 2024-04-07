package org.example.demo3;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistrerController {

    @FXML
    private TextField nombresField;

    @FXML
    private TextField correoField;

    @FXML
    private PasswordField contraseñaField;

    @FXML
    private Button registrarButton;

    @FXML
    private Button volverButton;

    @FXML
    private void initialize() {
        // Inicialización del controlador
    }

    @FXML
    private void registrar() {
        String nombres = nombresField.getText();
        String correo = correoField.getText();
        String contraseña = contraseñaField.getText();

        // Lógica para registrar al usuario con los datos proporcionados
    }

    @FXML
    private void volver() {
        // Lógica para volver a la pantalla de inicio de sesión
    }
}
