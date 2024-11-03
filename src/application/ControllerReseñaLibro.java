/*
 * Cuerpo de la clase de ver y agregar reseña
 * 
 * Fecha: 13/10/2024
 * 
 * @autor Aurora Morales
 * 
 * Version: 5
 * */

package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControllerReseñaLibro {
    @FXML
    private Button agregar;

    @FXML
    private Button regresar;

    @FXML
    private TableColumn<Reseña, Integer> columnEstrellas;

    @FXML
    private TableColumn<Reseña, String> columnDescripcion;

    @FXML
    private TableView<Reseña> tabla;

    private ObservableList<Reseña> lista = FXCollections.observableArrayList();
    private Libro lib = new Libro();
    int id;

    @FXML
    public void initialize() {
        System.out.println("Tabla es null? " + (tabla == null));
        configurarColumnas();
    }
    
    public void agregar(Reseña res) {
    	lista.add(res);
    }

    public void configurarColumnas() {
        columnEstrellas.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getValoracion()).asObject());
        columnDescripcion.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDescripcion()));
    }

    public void inicio() {
        lista.clear();
        agregarTabla();
    }

    public void setLibro(Libro lib) {
        this.lib = lib;
        inicio();
    }

    public void agregarTabla() {
        ObservableList<Reseña> columnas = FXCollections.observableArrayList();
        String url = "jdbc:oracle:thin:@//localhost:1521/ORCL";
        String usuario = "System";
        String contraseña = "aurelio666";

        String sql = "SELECT ID_LIBRO FROM LIBRO WHERE TITULO = ?";
        String sql2 = "SELECT VALORACION, DESCRIPCION FROM RESENA WHERE ID_LIBRO = ?";

        try (Connection connection = DriverManager.getConnection(url, usuario, contraseña);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
            preparedStatement.setString(1, lib.getTitulo());
            ResultSet resultado = preparedStatement.executeQuery();

            if (resultado.next()) {
                id = resultado.getInt("ID_LIBRO");
            } else {
                System.out.println("No se encontró el libro con el título: " + lib.getTitulo());
                return; // Salir si no se encuentra el libro
            }

            try (PreparedStatement preparedStatement2 = connection.prepareStatement(sql2)) {
                preparedStatement2.setInt(1, id);
                ResultSet resultado2 = preparedStatement2.executeQuery();

                while (resultado2.next()) {
                    int val = resultado2.getInt("VALORACION");
                    String descrip = resultado2.getString("DESCRIPCION");
                    Reseña re = new Reseña(val, descrip);
                    columnas.add(re);
                    System.out.println("Reseña agregada: " + re);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Número de reseñas cargadas: " + columnas.size());
        listaReseña(columnas);
    }

    public void listaReseña(ObservableList<Reseña> lis) {
        tabla.setItems(lis);
    }

    @FXML
    void aceptarButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AgregarReseña.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
            stage.setTitle("Agregar reseña");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void regresarButton(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
