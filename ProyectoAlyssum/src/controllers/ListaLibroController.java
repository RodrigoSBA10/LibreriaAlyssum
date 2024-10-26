package controllers;

import java.io.IOException;

import conexion.BusquedaBD;
import conexion.ConectarBD;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import poo.Libro;

public class ListaLibroController{

    @FXML
    private TextField txtTitulo;
    @FXML
    private TextField txtAutor;
    @FXML
    private TextField txtISBN;
    @FXML
    private TextField txtEditorial;
    @FXML
    private TextField txtPrecio;
    @FXML
    private TextField txtAnio;
    @FXML
    private ComboBox<String> cbGenero;
    @FXML
    private ComboBox<String> cbCategoria;
    @FXML
    private Slider sldPrecio;
    @FXML
    private TableView<Libro> jtTablaLibros;
    @FXML
    private Button btnBuscar; 
    @FXML
    private Button btnAgregar; 
    @FXML
    private Button btnBorrar; 
    @FXML
    private Button btnEditar;
    @FXML
    private TableColumn<Libro, String> colTitulo;
    @FXML
    private TableColumn<Libro, String> colAutor;
    @FXML
    private TableColumn<Libro, String> colIsbn;
    @FXML
    private TableColumn<Libro, String> colEditorial;
    @FXML
    private TableColumn<Libro, String> colGenero;
    @FXML
    private TableColumn<Libro, Double> colPrecio;
    @FXML
    private TableColumn<Libro, String> colCategoria;
    @FXML
    private TableColumn<Libro, Double> colAnioPublicacion;
    //Lista observable para mostrar los libros
    
    private ObservableList<Libro> listaLibros;
    private Libro libroSeleccionado;
    ConectarBD con= new ConectarBD();
    BusquedaBD cargar = new BusquedaBD();
    
    @FXML
    private void initialize() {
        // Inicialización del controlador
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        colIsbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        colEditorial.setCellValueFactory(new PropertyValueFactory<>("editorial"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria")); // Asegúrate de que coincida
        colGenero.setCellValueFactory(new PropertyValueFactory<>("genero")); // Asegúrate de que coincida
        colAnioPublicacion.setCellValueFactory(new PropertyValueFactory<>("anio"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        // Inicializamos la lista de libros desde la base de datos
        listaLibros = cargar.cargarLibros();
        jtTablaLibros.setItems(listaLibros);

        // Cargar categorías y géneros desde la base de datos
        cbCategoria.setItems(cargar.cargarCategorias());
        cbGenero.setItems(cargar.cargarGeneros());

        // Seleccionar un valor por defecto
        if (!cbCategoria.getItems().isEmpty()) {
            cbCategoria.getSelectionModel().selectFirst();
        }
        if (!cbGenero.getItems().isEmpty()) {
            cbGenero.getSelectionModel().selectFirst();
        }

        // Configuración del slider de precio
        sldPrecio.valueProperty().addListener((observable, oldValue, newValue) -> {
            txtPrecio.setText(String.format("%.02f", newValue.doubleValue()));
        });

        txtPrecio.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                double precio = Double.parseDouble(newValue);
                sldPrecio.setValue(precio);
            } catch (NumberFormatException e) {
                // Manejo de error
            }
        });
    }
    
    
    @FXML
    private void buscarLibros(ActionEvent event) {
        String titulo = txtTitulo.getText().trim(); // Quitamos espacios innecesarios
        String autor = txtAutor.getText().trim();
        String isbn = txtISBN.getText().trim();
        String editorial = txtEditorial.getText().trim();
        String anio = txtAnio.getText().trim();
        Double precio = null;

        // Validación del campo de precio solo si hay un valor
        String precioTexto = txtPrecio.getText().trim();
        if (!precioTexto.isEmpty()) {
            try {
                precio = Double.parseDouble(precioTexto);
            } catch (NumberFormatException e) {
                showAlert("El precio ingresado no es válido");
                return; // Salir del método si el precio no es válido
            }
        }

        // Obtener categorías y géneros
        Integer categoriaSeleccionada = null;
        if (cbCategoria.getValue() != null) {
            String nombreCategoria = cbCategoria.getValue();
            categoriaSeleccionada = cargar.getCategoriaIdPorNombre(nombreCategoria);
        }

        Integer generoSeleccionado = null;
        if (cbGenero.getValue() != null) {
            String nombreGenero = cbGenero.getValue();
            generoSeleccionado = cargar.getGeneroIdPorNombre(nombreGenero);
        }

        // Llama al método buscarLibros
        ObservableList<Libro> librosFiltrados = cargar.buscarLibros(titulo, autor, isbn, editorial, categoriaSeleccionada, generoSeleccionado, anio, precio);
        // Muestra los libros filtrados
        jtTablaLibros.setItems(librosFiltrados);
        jtTablaLibros.refresh();
    }

    private void showAlert(String message) {
        Alert alerta = new Alert(AlertType.WARNING);
        alerta.setTitle("Advertencia");
        alerta.setContentText(message);
        alerta.show();
    }
	
    
    @FXML
    private void agregarLibro(ActionEvent event) {
    	try {
            // Cargar el archivo FXML de la nueva ventana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/AgregarLibros.fxml"));
            Parent root = loader.load();

            // Obtener el controlador de la nueva ventana
            AgregarLibroController agregarLibrosController = loader.getController();
            
            // Pasar la lista de libros al nuevo controlador
            agregarLibrosController.setListaLibros(listaLibros);
            
            // Crear una nueva ventana (Stage) para la ventana de agregar libros
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Agregar Libro");
            stage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    	
    }
    
    @FXML
    private void borrarLibro(ActionEvent event) {
    	// Seleccionar un libro a borrar
        Libro libroSeleccionado = jtTablaLibros.getSelectionModel().getSelectedItem();
        
        // Si el libro existe para borrar
        if (libroSeleccionado != null) {
            // Confirmar la acción de eliminación
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, 
                "¿Estás seguro de que deseas borrar este libro?", 
                ButtonType.YES, ButtonType.NO);
            
            alerta.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    // Eliminar el libro de la base de datos
                    cargar.eliminarLibro(libroSeleccionado);
                    
                    // Remover el libro de la lista
                    listaLibros.remove(libroSeleccionado);
                    jtTablaLibros.refresh();
                }
            });
        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            // Si no existe, manda una alerta
            alerta.setContentText("No hay libro seleccionado para borrar");
            alerta.show();
        }
    	
    }
    
    @FXML
    private void editarLibro(ActionEvent event) {
        // Obtener el libro seleccionado
        Libro libroSeleccionado = jtTablaLibros.getSelectionModel().getSelectedItem();

        // Verificar si se ha seleccionado un libro
        if (libroSeleccionado == null) {
            Alert alerta = new Alert(AlertType.WARNING);
            alerta.setTitle("Advertencia");
            alerta.setContentText("Por favor, selecciona un libro para editar.");
            alerta.show();
            return; // Salir del método si no hay selección
        }

        try {
            // Cargar el archivo FXML de la nueva ventana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/EditarLibros.fxml"));
            Parent root = loader.load();

            // Obtener el controlador de la nueva ventana
            EditarLibroController editarLibrosController = loader.getController();

            // Pasar la lista de libros al nuevo controlador
            editarLibrosController.setListaLibros(listaLibros);
            // Pasar el libro que se va a editar
            editarLibrosController.setLibro(libroSeleccionado);
            editarLibrosController.setTableView(jtTablaLibros);

            // Crear una nueva ventana (Stage) para la ventana de edición de libros
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Editar Libro");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public Libro getLibroSeleccionado() {
		return libroSeleccionado;
	}

	public void setLibroSeleccionado(Libro libroSeleccionado) {
		this.libroSeleccionado = libroSeleccionado;
	}    
}