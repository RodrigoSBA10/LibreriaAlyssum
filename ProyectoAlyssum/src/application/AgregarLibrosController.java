package application;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AgregarLibrosController {

    @FXML
    private Button btnAgregarLibros;
    @FXML
    private Button btnCancelar;
    @FXML
    private TextField txtAutor;
    @FXML
    private TextField txtEditorial;
    @FXML
    private TextField txtGenero;
    @FXML
    private TextField txtISBN;
    @FXML
    private TextField txtPrecio;
    @FXML
    private TextField txtTitulo;
    
    //Referencia a la lista de libros de la clase principal
    private ObservableList<Libro> listaLibros;

    // Método para recibir la lista de libros desde la clase principal
    public void setListaLibros(ObservableList<Libro> listaLibros) {
        this.listaLibros = listaLibros;
    }
        
    @FXML
    void GuardarNuevoLibro(ActionEvent event) {
        String titulo = txtTitulo.getText();
        String autor = txtAutor.getText();
        String isbn = txtISBN.getText();
        String editorial = txtEditorial.getText(); 
        String genero = txtGenero.getText();
        Double precio = Double.parseDouble(txtPrecio.getText());

        //Creamos un nuevo libro y guardamos los valores
        Libro nuevoLibro = new Libro(titulo, autor, isbn, editorial, genero, precio);

        //Agregamos el libro a la lista
        listaLibros.add(nuevoLibro);

        //Limpiamos los campos para un nuevo registro
        txtTitulo.clear();
        txtAutor.clear();
        txtISBN.clear();
        txtEditorial.clear(); 
        txtGenero.clear();
        txtPrecio.clear();

        //Cerramos la ventana
        Stage stage = (Stage) txtTitulo.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void cancelar(ActionEvent event) {
        // Cerrar la ventana
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
