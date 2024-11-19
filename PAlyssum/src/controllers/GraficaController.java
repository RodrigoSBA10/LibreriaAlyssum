package controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
        
        // Mapas para contar los libros por categoría y género
        Map<String, Integer> conteoPorCategoria = new HashMap<>();
        Map<String, Integer> conteoPorGenero = new HashMap<>();

        // Recorremos los libros y contamos cuántos hay por categoría y por género
        for (Libro libro : listaLibros) {
            String categoria = libro.getCategoria();
            String genero = libro.getGenero();

            // Contamos los libros por categoría
            conteoPorCategoria.put(categoria, conteoPorCategoria.getOrDefault(categoria, 0) + 1);

            // Contamos los libros por género
            conteoPorGenero.put(genero, conteoPorGenero.getOrDefault(genero, 0) + 1);
        }

        // Crear las series para las categorías y los géneros
        XYChart.Series<String, Number> serieCategorias = new XYChart.Series<>();
        XYChart.Series<String, Number> serieGeneros = new XYChart.Series<>();

        // Añadir nombre a las series para identificación en el gráfico
        serieCategorias.setName("Categorías");
        serieGeneros.setName("Géneros");

        // Añadir los totales de libros por categoría al gráfico
        for (Map.Entry<String, Integer> entry : conteoPorCategoria.entrySet()) {
            String categoria = entry.getKey();
            int totalPorCategoria = entry.getValue();

            // Crear un dato para la categoría con el total de libros
            XYChart.Data<String, Number> dataCategoria = new XYChart.Data<>(categoria, totalPorCategoria);
            serieCategorias.getData().add(dataCategoria);
        }

        // Añadir los totales de libros por género al gráfico
        for (Map.Entry<String, Integer> entry : conteoPorGenero.entrySet()) {
            String genero = entry.getKey();
            int totalPorGenero = entry.getValue();

            // Crear un dato para el género con el total de libros
            XYChart.Data<String, Number> dataGenero = new XYChart.Data<>(genero, totalPorGenero);
            serieGeneros.getData().add(dataGenero);
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
