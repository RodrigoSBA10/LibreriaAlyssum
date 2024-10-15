/**
 * Controlador para la pantalla Inicio.
 * Esta clase maneja la lógica de la interfaz de usuario para permitir a los clientes ver la lista de libros
 * y poder hacer una busqueda de ellos
 * 
 * Autor: Rodrigo Slavador
 * Fecha: 12 de octubre de 2024
 */
package application;


import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * Clase ListaLibroController.
 * Esta clase controla la interfaz gráfica relacionada con la lista de libros,
 * permitiendo mostrar, filtrar y buscar libros. 
 * También gestiona la carga inicial de libros y el comportamiento de los controles.
 */

public class ListaLibroController {
	// Campo de texto para ingresar el autor del libro a buscar.
    @FXML
    private TextField autor;
    // Imagenes que sirven como banners en la interfaz gráfica.
    @FXML
    private ImageView banner1;

    @FXML
    private ImageView banner2;
    
    @FXML
    private ImageView banner3;
    // Imagen para mostrar una miniatura en la interfaz gráfica.
    @FXML
    private ImageView cartaImagne;
    // ComboBox para seleccionar la categoría del libro.
    @FXML
    private ComboBox<String> categoria;
    // Columnas de la tabla donde se mostrarán los libros.
    @FXML
    private TableColumn<Libro, Void> columnaAcciones; // Columna de acciones (vacía por ahora).

    @FXML
    private TableColumn<Libro, String> columnaAutor; // Columna para mostrar el autor del libro.

    @FXML
    private TableColumn<Libro, String> columnaEditorial; // Columna para mostrar la editorial del libro.

    @FXML
    private TableColumn<Libro, Double> columnaPrecio; // Columna para mostrar el precio del libro.

    @FXML
    private TableColumn<Libro, Integer> columnaStock; // Columna para mostrar el stock disponible.

    @FXML
    private TableColumn<Libro, String> columnaTitulo; // Columna para mostrar el título del libro.
    // Campo de texto para ingresar el ISBN del libro a buscar.
    @FXML
    private TextField isbn;
    // Slider para filtrar libros por precio.
    @FXML
    private Slider sliderPrecio;
    // Tabla para mostrar la lista de libros.
    @FXML
    private TableView<Libro> tablaDeLibros;
    // Campo de texto para ingresar el título del libro a buscar.
    @FXML
    private TextField titulo;
    // Etiqueta para mostrar el valor seleccionado del slider de precio.
    @FXML
    private Label valor;
    // Instancia de Libreria que almacena la lista de libros.
    private Libreria libreria;
    // Lista observable para conectar con la tabla de libros en la interfaz gráfica.
    private ObservableList<Libro> listaLibros= FXCollections.observableArrayList();
    // Indice de la imagen actual que se muestra en el banner
    private int indiceImagen=0;
    
    private int indice2=5;
    
    private int indice3=2;
    
    private int tiempo=5; // Tiempo en segundos para la rotacion
    //Lista de imagenes que se mostraran en el banner
    private List<Image> imagenesBanner = List.of(
    new Image(getClass().getResourceAsStream("/images/libro1.jpg")),
    new Image(getClass().getResourceAsStream("/images/libro2.jpg")),
    new Image(getClass().getResourceAsStream("/images/libro3.jpg")),
    new Image(getClass().getResourceAsStream("/images/libro4.jpg")),
    new Image(getClass().getResourceAsStream("/images/libro5.jpg")),
    new Image(getClass().getResourceAsStream("/images/libro6.jpg")),
    new Image(getClass().getResourceAsStream("/images/libro7.jpg")),
    new Image(getClass().getResourceAsStream("/images/libro8.jpg")),
    new Image(getClass().getResourceAsStream("/images/libro9.jpg")),
    new Image(getClass().getResourceAsStream("/images/libro10.jpg"))
    );
    /**
     * Método initialize.
     * Este método se ejecuta automáticamente al inicializar la vista.
     * Carga los libros de un archivo y configura la tabla de libros.
     */
    @FXML
    public void initialize() {
    	libreria=new Libreria(); // Crea una nueva instancia de la librería.
    	cargarLibros();	// Carga los libros desde un archivo.
    	tablaDeLibros.setItems(listaLibros); // Establece los libros en la tabla.
    	configurarColumnas(); // Configura cómo se mostrarán los datos en las columnas.
    	cargarBanner();
    	iniciarRotacion();
    	 // Añade un listener al slider de precio para actualizar el valor visualmente.
    	sliderPrecio.valueProperty().addListener((observable, oldValue, newValue)->{
    		Double costo1=newValue.doubleValue(); // Obtiene el valor seleccionado en el slider.
    		String costo= String.format("%.2f", costo1); // Formatea el valor como precio.
    		valor.setText("$"+costo); // Actualiza la etiqueta para mostrar el precio seleccionado.
    	});
    }
    /**
     * Inicia la rotacion de las imagenes del banner
     * Las imagenes cambian cada tiempo en segundos
     */
    private void iniciarRotacion() {
    Timeline timeline=new Timeline( 
    new KeyFrame(Duration.seconds ( tiempo ), event -> cambiarBanner())); // Cambiar la imagen del banner cada tiempo en segundos
	timeline.setCycleCount(Timeline.INDEFINITE); //Repetir indefinidamente
	timeline.play(); // Iniciar la animacion
	}
    /**
     * Cambia la imagen del banner a la siguiente en la lista
     * si se llega al final de la lista, vuelve al inicio.
     */
    private void cambiarBanner() {
    indiceImagen=(indiceImagen + 1)%imagenesBanner.size();// Actualizar el indice de la imagen
   	indice2=(indice2+1)%imagenesBanner.size();
   	indice3=(indice3+1)%imagenesBanner.size();
    cargarBanner(); // Cargar la nueva imagen del banner
     }
    /*
     * Cargar la imagen actual del banner en el imageView
     */
    private void cargarBanner() {
    	banner1.setImage(imagenesBanner.get(indiceImagen)); // Establecer la imagen del banner
    	banner2.setImage(imagenesBanner.get(indice2));
    	banner3.setImage(imagenesBanner.get(indice3));
	}
    /**
     * Método para cargar los libros desde un archivo serializado.
     * Utiliza la clase Libreria para cargar la lista de libros desde un archivo.
     */
    private void cargarLibros() {
		try {
			libreria.cargarLibros("libros.ser");  // Carga los libros desde el archivo "libros.ser".
			listaLibros=FXCollections.observableArrayList(libreria.getLibros());	// Crea una lista observable con los libros cargados.
			tablaDeLibros.setItems(listaLibros); // Establece la lista de libros en la tabla.
			System.out.print("Cargue Libros");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();	// Muestra cualquier error en la consola.
		}
	}
    /**
     * Método para configurar las columnas de la tabla.
     * Establece cómo se deben mostrar los atributos de cada libro en las columnas correspondientes.
     */
    private void configurarColumnas() {
    	// Configura la columna del título para que muestre el valor de getTitulo() del objeto Libro.
    	columnaTitulo.setCellValueFactory(celData -> new SimpleStringProperty(celData.getValue().getTitulo()));
    	 // Configura la columna del autor para que muestre el valor de getAutor() del objeto Libro.
    	columnaAutor.setCellValueFactory(celData -> new SimpleStringProperty(celData.getValue().getAutor()));
    	// Configura la columna de la editorial para que muestre el valor de getEditorial() del objeto Libro.
    	columnaEditorial.setCellValueFactory(celData -> new SimpleStringProperty(celData.getValue().getEditorial()));
    	// Configura la columna del precio para que muestre el valor de getPrecio() del objeto Libro como un objeto Double.
    	columnaPrecio.setCellValueFactory(celData -> new SimpleDoubleProperty(celData.getValue().getPrecio()).asObject());
    	// Configura la columna del stock para que muestre el valor de getStock() del objeto Libro como un objeto Integer.
    	columnaStock.setCellValueFactory(celData -> new SimpleIntegerProperty(celData.getValue().getStock()).asObject());
		
	}

    // Métodos vacíos para los botones que filtran por categoría.
    @FXML
    void btnEducativo(ActionEvent event) {

    }

    @FXML
    void btnFiccion(ActionEvent event) {

    }

    @FXML
    void btnInfantil(ActionEvent event) {

    }

    @FXML
    void buscarLibro(ActionEvent event) {

    }

}