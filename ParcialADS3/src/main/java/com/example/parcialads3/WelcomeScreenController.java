package com.example.parcialads3;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeScreenController {

    @FXML
    private void handleIniciarButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CargarNomina.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Cargar Nómina");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
