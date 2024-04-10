package org.example.demo3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

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
        stage.setTitle("Home");
        stage.show();
    }


    @FXML
    private void onLoginButtonClick(ActionEvent event) throws IOException {
        //System.out.println("ENTRO");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PostLogin.fxml"));
        Parent root = loader.load();

        // Obtener la escena actual y el stage asociado
        Scene scene = ((Node) event.getSource()).getScene();
        Stage stage = (Stage) scene.getWindow();

        // Cerrar la pantalla actual
        stage.close();

        // Mostrar la nueva pantalla
        stage.setScene(new Scene(root));
        stage.setTitle("Preview");
        stage.show();
    }

    @FXML
    private void onRegistarButtonClick(ActionEvent event) throws IOException {
        //System.out.println("ENTRO");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Registrer.fxml"));
        Parent root = loader.load();

        // Obtener la escena actual y el stage asociado
        Scene scene = ((Node) event.getSource()).getScene();
        Stage stage = (Stage) scene.getWindow();

        // Cerrar la pantalla actual
        stage.close();

        // Mostrar la nueva pantalla
        stage.setScene(new Scene(root));
        stage.setTitle("Registrar");
        stage.show();
    }
}
