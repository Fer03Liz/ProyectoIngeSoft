package org.example.demo3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

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
    private void onVolverButtonClick(ActionEvent event) throws IOException {
        // Cargar la nueva pantalla (LoginScreen.fxml)
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = loader.load();

        // Obtener la escena actual y el stage asociado
        Scene scene = ((Node) event.getSource()).getScene();
        Stage stage = (Stage) scene.getWindow();

        // Cerrar la pantalla actual
        stage.close();

        // Mostrar la nueva pantalla
        stage.setScene(new Scene(root));
        stage.setTitle("LOGIN");
        stage.show();
    }

    @FXML
    private void onHomeButtonClick(ActionEvent event) throws IOException {
        //System.out.println("ENTRO");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root = loader.load();

        // Obtener la escena actual y el stage asociado
        Scene scene = ((Node) event.getSource()).getScene();
        Stage stage = (Stage) scene.getWindow();

        // Cerrar la pantalla actual
        stage.close();

        // Mostrar la nueva pantalla
        stage.setScene(new Scene(root));
        stage.setTitle("LOGIN");
        stage.show();
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
