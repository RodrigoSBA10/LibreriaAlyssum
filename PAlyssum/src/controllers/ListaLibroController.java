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
        // Inicialización de la tabla
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        colIsbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        colEditorial.setCellValueFactory(new PropertyValueFactory<>("editorial"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria")); 
        colGenero.setCellValueFactory(new PropertyValueFactory<>("genero")); 
        colAnioPublicacion.setCellValueFactory(new PropertyValueFactory<>("anio"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        // Inicializamos la lista de libros desde la base de datos
        listaLibros = cargar.cargarLibros();
        jtTablaLibros.setItems(listaLibros);

        // Cargar categorías y géneros desde la base de datos
        ObservableList<String> categorias = cargar.cargarCategorias();
        ObservableList<String> generos = cargar.cargarGeneros();

        // Agregar las opciones predeterminadas
        categorias.add(0, "Selecciona categoria");  // Insertamos "Selecciona categoría" al principio
        generos.add(0, "Selecciona genero");        // Insertamos "Selecciona género" al principio

        // Asignar las listas con valores predeterminados a los ComboBox
        cbCategoria.setItems(categorias);
        cbGenero.setItems(generos);

        // Seleccionar el primer valor, que es el valor predeterminado
        cbCategoria.getSelectionModel().selectFirst();
        cbGenero.getSelectionModel().selectFirst();


        // Listener para cambios en el campo de texto de precio
        txtPrecio.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                if (!newValue.isEmpty()) {
                    // Intentamos parsear el valor ingresado
                    double precio = Double.parseDouble(newValue);

                    // Limitar el precio a 5000 (o el máximo permitido)
                    if (precio > sldPrecio.getMax()) {
                        precio = sldPrecio.getMax();
                    }

                    // Actualizar el valor del Slider con el precio ingresado
                    sldPrecio.setValue(precio);

                    // No formateamos el número en el TextField mientras se escribe
                    txtPrecio.setText(newValue);  // Mantener el valor tal como fue ingresado
                }
            } catch (NumberFormatException e) {
                // Si el valor ingresado no es válido (por ejemplo, texto), restauramos el valor anterior
                txtPrecio.setText(oldValue);  // Restaurar el valor anterior si el nuevo valor no es un número válido
            }
        });

        // Listener para cambios en el Slider
        sldPrecio.valueProperty().addListener((observable, oldValue, newValue) -> {
            // Formatear el valor del slider a dos decimales y actualizar el TextField
            txtPrecio.setText(String.format("%.2f", newValue.doubleValue()));
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