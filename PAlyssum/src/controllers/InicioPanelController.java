package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


public class InicioPanelController {

    @FXML
    private Button btnGestionLibros;

    @FXML
    private Button btnIrGrafica;

    @FXML
    private ImageView lblTitulo;

    @FXML
    void IrAGrafica(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/Grafica.fxml"));
    	Parent root = loader.load();
    	Stage primaryStage = new Stage();
    	primaryStage.setTitle("Gráfica");
    	primaryStage.setScene(new Scene(root));
    	primaryStage.show();
    	Stage stage = (Stage) btnIrGrafica.getScene().getWindow();
    	stage.close();
    }

    @FXML
    void IrGestionLibros(ActionEvent event) throws IOException {
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/ListaLibros.fxml"));
    	Parent root = loader.load();
    	Stage primaryStage= new Stage();
    	primaryStage.setTitle("Lista de Libros");
    	primaryStage.setScene(new Scene(root));
    	primaryStage.show();
    	Stage stage = (Stage) btnGestionLibros.getScene().getWindow();
    	stage.close();
    }

}