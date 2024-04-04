package com.example.parcialads3;

import com.example.parcialads3.Entidades.Asignatura;
import com.example.parcialads3.Entidades.Empleado;
import com.example.parcialads3.Entidades.Monitor;
import com.example.parcialads3.Entidades.Profesor;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CargarNominaController {
    List<Empleado> nomina;

    {
        nomina = new ArrayList<>();
    }

    @FXML
    private Button cargarArchivoButton;

    @FXML
    private void handleCargarArchivoButton() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Archivo de Nómina");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Archivos de Texto", "*.txt"),
                new FileChooser.ExtensionFilter("Archivos CSV", "*.csv"),
                new FileChooser.ExtensionFilter("Archivos XML", "*.xml")
        );

        Stage stage = (Stage) cargarArchivoButton.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            try {
                if (selectedFile.getName().endsWith(".txt")) {
                    procesarArchivoTxt(selectedFile);
                } else if (selectedFile.getName().endsWith(".csv")) {
                    procesarArchivoCsv(selectedFile);
                } else if (selectedFile.getName().endsWith(".xml")) {
                    procesarArchivoXml(selectedFile);
                } else {
                    mostrarAlerta("Error", "Tipo de archivo no compatible", "Seleccione un archivo de texto (.txt), un archivo CSV (.csv) o un archivo XML (.xml).");
                }
            } catch (Exception e) {
                mostrarAlerta("Error", "Error al procesar el archivo", "Ocurrió un error al procesar el archivo.");
            }
        }
    }

    private void procesarArchivoXml(File file) {
        //Falta
        abrirNuevaVentana();
    }

    private void procesarArchivoTxt(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            String Name;
            String ID;
            String Cargo;
            while ((line = br.readLine()) != null && !line.equals("FIN")) {
                String[] parts = line.trim().split("%");
                Name = parts[0];
                ID = parts[1];
                Cargo = parts[2];
                int ValorHora = 0;
                int SalarioMin=0;
                String Escalafon = null;
                switch (Cargo.toLowerCase()) {
                    case "profesor":
                        Escalafon = br.readLine();
                        switch (Escalafon.toLowerCase()) {
                            case "titular", "asociado":
                                ValorHora = 4;
                                SalarioMin=12500;
                                break;
                            case "asistente":
                                ValorHora = 3;
                                SalarioMin=10000;
                                break;
                            case "instructor":
                                ValorHora = 2;
                                SalarioMin=7500;
                                break;
                            case "catedra" :
                                ValorHora = 1;
                                SalarioMin=5000;
                                break;
                            default :
                                ValorHora = 0;
                                SalarioMin=0;
                                break;
                        };
                        List<Asignatura> materias = new ArrayList<>();
                        String lineaMateria = br.readLine();
                        // Continuar leyendo las materias hasta encontrar el marcador de fin (#)
                        while (lineaMateria != null && !lineaMateria.equals("#")) {
                            String[] materiaInfo = lineaMateria.split(",");
                            if (materiaInfo.length >= 2) {
                                String nombreMateria = materiaInfo[0];
                                int horasMateria = Integer.parseInt(materiaInfo[1].trim());
                                // Crear objeto Asignatura y agregarlo a la lista de materias del profesor
                                Asignatura asignatura = new Asignatura();
                                asignatura.setNombre(nombreMateria);
                                asignatura.setHoras(horasMateria);
                                materias.add(asignatura);
                            }
                            // Leer la siguiente línea
                            lineaMateria = br.readLine();
                        }
                        Profesor p = new Profesor(Name, ID, null, Cargo, ValorHora, materias, Escalafon,SalarioMin);
                        nomina.add(p);
                        break;
                    case "empleado":
                        String sala = br.readLine();
                        int salariosM = Integer.parseInt(sala);
                        Empleado e = new Empleado(Name, ID, Cargo, Cargo, salariosM);
                        nomina.add(e);
                        lineaMateria = br.readLine();
                        break;
                    case "monitor":
                        int valorHora = 2000;
                        List<Asignatura> asignaturas = new ArrayList<>();
                        String lineaAsignatura = br.readLine();
                        // Continuar leyendo las materias hasta encontrar el marcador de fin (#)
                        while (lineaAsignatura != null && !lineaAsignatura.equals("#")) {
                            String[] AsigInfo = lineaAsignatura.split(",");
                            if (AsigInfo.length >= 2) {
                                String nombreMateria = AsigInfo[0];
                                int horasMateria = Integer.parseInt(AsigInfo[1].trim());
                                // Crear objeto Asignatura y agregarlo a la lista de materias del profesor
                                Asignatura asignatura = new Asignatura();
                                asignatura.setNombre(nombreMateria);
                                asignatura.setHoras(horasMateria);
                                asignaturas.add(asignatura);
                            }
                            // Leer la siguiente línea
                            lineaAsignatura = br.readLine();
                        }
                        Monitor m = new Monitor(Name, ID, Cargo, Cargo, valorHora, asignaturas);
                        nomina.add(m);
                        break;
                }
            }
            abrirNuevaVentana();
        } catch (IOException ex) {
            // Manejar errores de lectura de archivo
            ex.printStackTrace();
        }
    }
    private void procesarArchivoCsv(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            boolean skipHeader = true; // Variable para saltar la primera línea
            while ((line = br.readLine()) != null) {
                if (skipHeader) {
                    skipHeader = false;
                    continue; // Salta la primera línea
                }
                // Procesar cada línea según el formato de archivo CSV
                String[] parts = line.split(",");
                String nombre=parts[0];
                String ID=parts[1];
                String cargo=parts[2];
                int salariosMin=Integer.parseInt(parts[3]);
                if (parts.length >4) {//Tiene asignaturas
                    switch (cargo.toLowerCase()){
                        case "profesor":
                            String Escalafon =parts[4];
                            int ValorHora=0;
                            int SalarioMin=0;
                            switch (Escalafon.toLowerCase()) {
                                case "titular", "asociado":
                                    ValorHora = 4;
                                    SalarioMin = 12500;
                                    break;
                                case "asistente":
                                    ValorHora = 3;
                                    SalarioMin = 10000;
                                    break;
                                case "instructor":
                                    ValorHora = 2;
                                    SalarioMin = 7500;
                                    break;
                                case "catedra":
                                    ValorHora = 1;
                                    SalarioMin = 5000;
                                    break;
                                default:
                                    ValorHora = 0;
                                    SalarioMin = 0;
                                    break;
                            }
                            List<Asignatura> materias = new ArrayList<>();
                            int i=5;
                            while (!parts[i].equals("#")) {
                                    String nombreMateria = parts[i];
                                    int horasMateria = Integer.parseInt(parts[i+1].trim());
                                    Asignatura asignatura = new Asignatura(horasMateria, nombreMateria);
                                    materias.add(asignatura);
                                    i+=2;
                            }
                            Profesor p = new Profesor(nombre, ID, null, cargo, ValorHora, materias, Escalafon,SalarioMin);
                            nomina.add(p);
                            break;
                        case "monitor":
                            int valorHora = 2000;
                            List<Asignatura> asignaturas = new ArrayList<>();
                            int j=5;
                            while (!parts[j].equals("#")) {
                                String nombreMateria = parts[j];
                                int horasMateria = Integer.parseInt(parts[j+1].trim());
                                Asignatura asignatura = new Asignatura(horasMateria, nombreMateria);
                                asignaturas.add(asignatura);
                                j+=2;
                            }
                            Monitor m = new Monitor(nombre, ID, null, cargo, valorHora, asignaturas);
                            nomina.add(m);
                            break;

                    }
                } else {//No tiene asignaturas

                   Empleado e= new Empleado(nombre,ID,null,cargo,salariosMin);
                   nomina.add(e);
                }
            }
            abrirNuevaVentana();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void abrirNuevaVentana() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
            Parent root = loader.load();

            MenuController menuController = loader.getController();
            menuController.initializeData(nomina); // Este método espera una lista de Empleado

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            // Cerrar la ventana actual si es necesario
            Stage currentStage = (Stage) cargarArchivoButton.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void mostrarAlerta(String titulo, String encabezado, String contenido) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(titulo);
            alert.setHeaderText(encabezado);
            alert.setContentText(contenido);
            alert.showAndWait();
            }
    public List<Empleado> getNomina() {
        return nomina;
    }

    }
