package com.example.parcialads3;

import com.example.parcialads3.Entidades.Asignatura;
import com.example.parcialads3.Entidades.Empleado;
import com.example.parcialads3.Entidades.Monitor;
import com.example.parcialads3.Entidades.Profesor;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class MenuController {
    @FXML
    private ListView<Empleado> nominaListView;

    @FXML
    private TextField NombreAsig;

    @FXML
    private TextField HorasAsig;

    @FXML
    private Button AgregarAsignaturas;

    @FXML
    private Label Salario;

    @FXML
    private TextField NombreEmpleado;

    @FXML
    private Label Salario1;

    @FXML
    private Button GenerarReporte;

    @FXML
    private TableView<Empleado> reporteTableView;

    @FXML
    private TableColumn<Empleado, String> nombreColumn;

    @FXML
    private TableColumn<Empleado, String> idColumn;

    @FXML
    private TableColumn<Empleado, String> salarioColumn;

    public void initializeData(List<Empleado> nomina) {
        // Inicializar la ListView con la lista de empleados
        nominaListView.getItems().setAll(nomina);
    }

    @FXML
    private void initialize() {
        // Configura las columnas de la TableView
        idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getID()));
        nombreColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        salarioColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().calcularSalario())));
        }

    @FXML
    private void handleAgregarAsignaturasButton() {
        // Obtener el profesor o monitor seleccionado en el ListView
        Empleado empleadoSeleccionado = nominaListView.getSelectionModel().getSelectedItem();

        if (empleadoSeleccionado instanceof Profesor) {
            // Agregar asignatura al profesor
            Profesor profesor = (Profesor) empleadoSeleccionado;
            String nombreAsignatura = NombreAsig.getText();
            int horasAsignatura = Integer.parseInt(HorasAsig.getText());

            Asignatura asignatura = new Asignatura();
            asignatura.setNombre(nombreAsignatura);
            asignatura.setHoras(horasAsignatura);

            profesor.agregarAsignatura(asignatura);

        } else if (empleadoSeleccionado instanceof Monitor) {
            // Agregar asignatura al monitor
            Monitor monitor = (Monitor) empleadoSeleccionado;
            String nombreAsignatura = NombreAsig.getText();
            int horasAsignatura = Integer.parseInt(HorasAsig.getText());

            Asignatura asignatura = new Asignatura();
            asignatura.setNombre(nombreAsignatura);
            asignatura.setHoras(horasAsignatura);

            monitor.agregarAsignatura(asignatura);
        }

        // Limpiar los campos de texto después de agregar la asignatura
        NombreAsig.clear();
        HorasAsig.clear();

        // Actualizar el ListView
        actualizarListView();
    }

    private void actualizarListView() {
        // Obtener la lista actual de empleados
        ObservableList<Empleado> empleados = FXCollections.observableArrayList(nominaListView.getItems());

        // Limpiar el ListView
        nominaListView.getItems().clear();

        // Agregar los empleados actualizados
        nominaListView.getItems().addAll(empleados);
    }

    private void mostrarAlerta(String titulo, String encabezado, String contenido) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(encabezado);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    @FXML
    private void calcularSalarioProfesor(Profesor profesor) {
        float salarioCalculado = profesor.calcularSalario();
        // Actualizar el Label con el salario calculado
        Salario.setText(String.valueOf(salarioCalculado));
        Salario1.setText(String.valueOf(salarioCalculado));
    }

    private void calcularSalarioEmpleado(Empleado empleado) {
        float salarioCalculado = empleado.calcularSalario();
        // Actualizar el Label con el salario calculado
        Salario.setText(String.valueOf(salarioCalculado));
        Salario1.setText(String.valueOf(salarioCalculado));
    }

    private void calcularSalarioMonitor(Monitor monitor) {
        float salarioCalculado = monitor.calcularSalario();
        // Actualizar el Label con el salario calculado
        Salario.setText(String.valueOf(salarioCalculado));
        Salario1.setText(String.valueOf(salarioCalculado));
    }

    // Evento del botón "Calcular Salario"
    @FXML
    private void handleCalcularSalarioButton() {
        // Obtener el elemento seleccionado en el ListView
        Object selectedItem = nominaListView.getSelectionModel().getSelectedItem();
        // Verificar el tipo de empleado seleccionado y llamar al método correspondiente
        if (selectedItem instanceof Profesor) {
            calcularSalarioProfesor((Profesor) selectedItem);
        } else if (selectedItem instanceof Empleado) {
            calcularSalarioEmpleado((Empleado) selectedItem);
        } else if (selectedItem instanceof Monitor) {
            calcularSalarioMonitor((Monitor) selectedItem);
        } else {
            Salario.setText("Seleccione un empleado");
        }
    }

    @FXML
    private void handleCalcularSalarioXNombreButton() {
        String nombreBuscado = NombreEmpleado.getText();

        for (Empleado e : nominaListView.getItems()) {
            if (e.getNombre().equals(nombreBuscado)) {
                double salario = e.calcularSalario();
                Salario1.setText(String.valueOf(salario));
                Salario.setText(String.valueOf(salario));
                NombreEmpleado.setText("");
                return; // Terminar la búsqueda después de encontrar al empleado
                }
            }
    // Si llegamos aquí, el nombre no fue encontrado
        Salario1.setText("Empleado no encontrado");
    }

    @FXML
    private void handleGenerarReporteButton() {
        ObservableList<Empleado> listaEmpleados = nominaListView.getItems();

        // Nombre del archivo de reporte
        String nombreArchivo = "reporte_nomina.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            // Encabezado
            writer.write("Nombre\tID\tSalario");
            writer.newLine();

            // Datos de los empleados
            for (Empleado empleado : listaEmpleados) {
                String linea = empleado.getNombre() + "\t" + empleado.getID() + "\t$" + empleado.calcularSalario();
                writer.write(linea);
                writer.newLine();
            }

            System.out.println("Reporte generado correctamente.");

            // Actualizar la TableView
            reporteTableView.getItems().clear();
            reporteTableView.getItems().addAll(listaEmpleados);

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al generar el reporte.");
        }
    }
    @FXML
    private Button salirButton;

// ...

    @FXML
    private void handleSalirButton() {
        // Cerrar todas las pestañas abiertas y salir de la aplicación
        Platform.exit();
    }
}





