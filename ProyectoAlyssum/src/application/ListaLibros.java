package application;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;

public class ListaLibros{

    @FXML
    private TextField txtTitulo;
    @FXML
    private TextField txtAutor;
    @FXML
    private TextField txtISBN;
    @FXML
    private TextField txtEditorial;
    @FXML
    private TextField txtGenero;
    @FXML
    private TextField txtPrecio;
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
    //Lista observable para mostrar los libros
    
    private ObservableList<Libro> listaLibros;

    @FXML
    private void initialize() {
        // Inicialización del controlador
    	//Configuramos la tabla
    	colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
    	colAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
    	colIsbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
    	colEditorial.setCellValueFactory(new PropertyValueFactory<>("editorial"));
    	colGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
    	colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
    	
    	//Inicializamos la lista
    	listaLibros = FXCollections.observableArrayList();
    	//Asignamos la lista a la tabla
    	jtTablaLibros.setItems(listaLibros);
    	
    	sldPrecio.valueProperty().addListener((observable, oldValue, newValue) ->{
    		txtPrecio.setText(String.format("%.02f", newValue.doubleValue()));
    		}	
    	);
    	
    }
    
    
    @FXML
    private void buscarLibros(ActionEvent event) {
    	//Logica para buscar los libros
    	String titulo = txtTitulo.getText().toLowerCase();
    	String autor = txtAutor.getText().toLowerCase();
    	String isbn= txtISBN.getText().toLowerCase();
    	String editorial= txtEditorial.getText().toLowerCase();
    	String genero = txtGenero.getText().toLowerCase();
    	Double precio = null;
    	
    	try {
    		precio=Double.parseDouble(txtPrecio.getText());
    		
    	}catch(NumberFormatException e){
    		Alert alerta=new Alert(AlertType.WARNING);
    		alerta.setContentText("El precio ingresado no es valido");
    		alerta.show();
    		}
    	//Vamos a filtrar los libros que coincidan con la busqueda
    	ObservableList<Libro> librosFiltrados = FXCollections.observableArrayList();
    	
    	for (Libro libro : listaLibros) {
			boolean coincideTitulo= titulo.isEmpty()|| libro.getTitulo().toLowerCase().contains(titulo);
			boolean coincideAutor= autor.isEmpty()|| libro.getAutor().toLowerCase().contains(autor);
			boolean coincideISBN = isbn.isEmpty()||libro.getIsbn().toLowerCase().contains(isbn);
			boolean coincideEditorial= editorial.isEmpty()||libro.getEditorial().toLowerCase().contains(editorial);
			boolean coincideGenero= genero.isEmpty()||libro.getGenero().toLowerCase().contains(genero);
			boolean coincidePrecio=precio==null||libro.getPrecio()<= precio;
			
			if(coincideTitulo||coincideAutor||coincideISBN||coincideEditorial||coincideGenero||coincidePrecio){
				//si coincide al menos uno con la busqueda entonces lo agregamos
				librosFiltrados.add(libro);
			}
			
		}
    	//Mostramos los libros que coincidan con la busqueda
    	jtTablaLibros.setItems(librosFiltrados);
    }
	

    @FXML
    private void agregarLibro(ActionEvent event) {
    	try {
            // Cargar el archivo FXML de la nueva ventana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/AgregarLibros.fxml"));
            Parent root = loader.load();

            // Obtener el controlador de la nueva ventana
            AgregarLibrosController agregarLibrosController = loader.getController();
            
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
    	//para seleccionar un libro a borrar
    	Libro libroSeleccionado= jtTablaLibros.getSelectionModel().getSelectedItem();
    	//si el libro existe para borrar
    	if(libroSeleccionado!= null) {
    		//remueve el libro
    		listaLibros.remove(libroSeleccionado);
    		jtTablaLibros.refresh();
    	}else {
    		Alert alerta= new Alert(AlertType.CONFIRMATION);
    		//si no existe manda una alerta 
    		alerta.setContentText("No hay libro seleccionado para borrar");
    		alerta.show();
    		
    	}
    	
    }
    
    @FXML
    private void editarLibro(ActionEvent event) {
    	try {
            // Cargar el archivo FXML de la nueva ventana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/EditarLibros.fxml"));
            Parent root = loader.load();

            // Obtener el controlador de la nueva ventana
            EditarLibrosController editarLibrosController= loader.getController();
            
            // Pasar la lista de libros al nuevo controlador
            editarLibrosController.setListaLibros(listaLibros);
            
            // Crear una nueva ventana (Stage) para la ventana de agregar libros
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Editar Libro");
            stage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    	
    }
    
    
}