package controllers;


import conexion.BusquedaBD;
import conexion.ConectarBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import poo.Libro;

public class AgregarLibroController {
    @FXML
    private Button btnAgregarLibroA;
    @FXML
    private Button btnCancelarA;
    @FXML
    private ComboBox<String> cbCategoriaA;
    @FXML
    private ComboBox<String> cbGeneroA;
    @FXML
    private TextField txtAnioA;
    @FXML
    private TextField txtAutorA;
    @FXML
    private TextField txtEditorialA;
    @FXML
    private TextField txtISBNA;
    @FXML
    private TextField txtPrecioA;
    @FXML
    private TextField txtTituloA;
    @FXML
    private TextField txtStockA;
    
    // Referencia a la lista de libros de la clase principal
    private ObservableList<Libro> listaLibros;
    //Creamos el objeto con para conectar la base de datos
    ConectarBD con = new ConectarBD();
    BusquedaBD cargar = new BusquedaBD();
    
    // Método para recibir la lista de libros desde la clase principal
    public void setListaLibros(ObservableList<Libro> listaLibros) {
        this.listaLibros = listaLibros;
    }

    @FXML
    private void initialize() {
        // Cargar categorías y géneros desde la base de datos
        cbCategoriaA.setItems(cargar.cargarCategorias());
        cbGeneroA.setItems(cargar.cargarGeneros());
        
        // Selecciona un valor por defecto
        if (!cbCategoriaA.getItems().isEmpty()) {
            cbCategoriaA.getSelectionModel().selectFirst();
        }
        if (!cbGeneroA.getItems().isEmpty()) {
            cbGeneroA.getSelectionModel().selectFirst();
        }
    }
  
    @FXML
    void GuardarNuevoLibro(ActionEvent event) {
        // Conectar a la base de datos
        Connection conection = con.ConectarBDOracle();
        
        // Validar que los campos no estén vacíos
        if (txtTituloA.getText().isEmpty() || txtAutorA.getText().isEmpty() || txtISBNA.getText().isEmpty() ||
            txtEditorialA.getText().isEmpty() || cbCategoriaA.getValue() == null || cbGeneroA.getValue() == null ||
            txtAnioA.getText().isEmpty() || txtPrecioA.getText().isEmpty() || txtStockA.getText().isEmpty()) {
            System.out.println("Por favor, completa todos los campos");
            return;
        }

        // Intentar convertir los campos numéricos
        try {
            Double.parseDouble(txtPrecioA.getText());
            Integer.parseInt(txtStockA.getText());
        } catch (NumberFormatException e) {
            System.out.println("El precio o el stock no son válidos");
            return;
        }
        
        // Capturamos los datos del formulario
        String titulo = txtTituloA.getText();
        String autor = txtAutorA.getText();
        String isbn = txtISBNA.getText();
        String editorial = txtEditorialA.getText(); 
        String categoriaSeleccionada = cbCategoriaA.getValue();
        String generoSeleccionado = cbGeneroA.getValue();
        String anio = txtAnioA.getText();
        Double precio = Double.parseDouble(txtPrecioA.getText());
        int stock = Integer.parseInt(txtStockA.getText());

        // Obtener los IDs de categoría y género
        int idCategoria = cargar.obtenerIdCategoria(categoriaSeleccionada);
        int idGenero = cargar.obtenerIdGenero(generoSeleccionado);

        // Sentencia SQL para insertar los datos
        String sql = "INSERT INTO libro (id, titulo, autor, isbn, editorial, id_categoria, id_genero, anio, precio, stock) VALUES (seq_libro.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        // Agregar los valores correspondientes a la base de datos
        try (Connection conn = conection; PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, titulo);
            pstmt.setString(2, autor);
            pstmt.setString(3, isbn);
            pstmt.setString(4, editorial);
            pstmt.setInt(5, idCategoria);
            pstmt.setInt(6, idGenero);
            pstmt.setString(7, anio);
            pstmt.setDouble(8, precio);
            pstmt.setInt(9, stock);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // Creamos un nuevo libro y guardamos los valores
        Libro libro = new Libro(titulo, autor, isbn, editorial, idGenero, idCategoria, anio, precio, stock);

        // Agregamos el libro a la lista
        listaLibros.add(libro);

        // Limpiamos los campos para un nuevo registro
        txtTituloA.clear();
        txtAutorA.clear();
        txtISBNA.clear();
        txtEditorialA.clear();
        cbCategoriaA.setValue(null);
        cbGeneroA.setValue(null);
        txtPrecioA.clear();
        txtStockA.clear();

        // Cerramos la ventana
        Stage stage = (Stage) txtTituloA.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void cancelar(ActionEvent event) {
        // Cerrar la ventana
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }
    
    
    
    
}