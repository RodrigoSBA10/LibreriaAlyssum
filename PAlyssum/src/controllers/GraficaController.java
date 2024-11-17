package controllers;

import java.io.IOException;

import conexion.BusquedaBD;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import poo.Libro;

public class GraficaController {

    @FXML
    private BarChart<String, Number> GraficoStock;  // Gráfico de barras

    @FXML
    private CategoryAxis GraficoCategoria; // Eje X - Libro (categoría y género)

    @FXML
    private NumberAxis GraficoNoStock; // Eje Y - Stock
    
    @FXML
    private Button btnRegresar;

    private BusquedaBD cargar = new BusquedaBD();  // Cargar los metodos para acceder a la base de datos
    
    // Este método se invoca al inicializar la vista
    public void initialize() {
        // Cargar los libros desde la base de datos
        ObservableList<Libro> listaLibros = cargar.cargarLibros(); // Método que carga los libros desde la base de datos
        
        // Crear las series para las categorías y los géneros
        XYChart.Series<String, Number> serieCategorias = new XYChart.Series<>();
        XYChart.Series<String, Number> serieGeneros = new XYChart.Series<>();
        
        // Definir los colores para categoría y género
        String colorCategoria = "Red";  // Color Rojo
        String colorGenero = "Orange";  // Color Cyan

        // Añadir nombre a las series para identificación en el gráfico
        serieCategorias.setName("Categoría");
        serieGeneros.setName("Género");

        // Recorremos los libros y agregamos los datos a las series
        for (Libro libro : listaLibros) {
            String titulo = libro.getTitulo();
            int stock = libro.getStock();
            String categoria = libro.getCategoria();
            String genero = libro.getGenero();

            // Añadir datos a la serie de categorías
            XYChart.Data<String, Number> dataCategoria = new XYChart.Data<>(titulo, stock);
            serieCategorias.getData().add(dataCategoria);
            
            // Añadir datos a la serie de géneros
            XYChart.Data<String, Number> dataGenero = new XYChart.Data<>(titulo, stock);
            serieGeneros.getData().add(dataGenero);

            // Listener para asegurar que el nodo está disponible
            dataCategoria.nodeProperty().addListener((observable, oldNode, newNode) -> {
                if (newNode != null) {
                    newNode.setStyle("-fx-bar-fill:" +colorCategoria+ ";" );
                }
            });

            // Listener para asegurar que el nodo está disponible
            dataGenero.nodeProperty().addListener((observable, oldNode, newNode) -> {
                if (newNode != null) {
                    newNode.setStyle("-fx-bar-fill:" +colorGenero + ";" );
                }
            });
        }

        // Añadir las series al gráfico
        GraficoStock.getData().addAll(serieCategorias, serieGeneros);
    }
    
    @FXML
    void RegresarPanelPrincipal(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/InicioPanel.fxml"));
        Parent root = loader.load();
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Inicio");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        // Cerrar la ventana actual
        Stage stage = (Stage) btnRegresar.getScene().getWindow();
        stage.close();

    }
}
