package org.example.demo3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;




public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("HOME");
        stage.setScene(scene);
        stage.show();
    }
    public static Connection ConectarBD(String bd ){//el string es para saber el nombre de la base de datos
        // siempre y cuando esté dentro del mismo host y tenga los permisos (usuario y contraseña)
        Connection conexion;
        String host="jdbc:mysql://localhost/";
        String user="root";//usuario de la base de datos (por defecto es root)
        String pass= "Tinto56Ñ*52#";//contraseña de la base de datos
        System.out.println("Conectando...");
        try {
            conexion= DriverManager.getConnection(host+bd,user,pass);
            System.out.println("Coneccion exitosa!!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return conexion;
    }

    public static void main(String[] args) {
        launch();
        Connection bd=ConectarBD("world");
    }
}