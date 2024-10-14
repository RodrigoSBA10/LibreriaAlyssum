package application;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class EditarLibrosController {

    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnEditarLibros;
    @FXML
    private Button btnLimpiar;
    @FXML
    private Button btnGuardar;
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
    @FXML
    private TableView<Libro> jtTablaLibros;

    // Referencia a la lista de libros de la clase principal
    @SuppressWarnings("unused")
	private ObservableList<Libro> listaLibros;

    // Método para recibir la lista de libros desde la clase principal
    public void setListaLibros(ObservableList<Libro> listaLibros) {
        this.listaLibros = listaLibros;
    }

    @FXML
    void EditarLibro(ActionEvent event) {
        // Seleccionar el libro de la tabla
        Libro libroSeleccionado = jtTablaLibros.getSelectionModel().getSelectedItem();
        if (libroSeleccionado == null) {
            Alert alerta = new Alert(AlertType.WARNING);
            alerta.setTitle("Advertencia");
            alerta.setContentText("Por favor, selecciona un libro de la tabla.");
            alerta.show();
            return; // Salir del método para evitar el NullPointerException
        }

        // Llena los campos con los datos del libro seleccionado
        txtTitulo.setText(libroSeleccionado.getTitulo());
        txtAutor.setText(libroSeleccionado.getAutor());
        txtISBN.setText(libroSeleccionado.getIsbn());
        txtEditorial.setText(libroSeleccionado.getEditorial());
        txtGenero.setText(libroSeleccionado.getGenero());
        txtPrecio.setText(Double.toString(libroSeleccionado.getPrecio()));
    }

    @FXML
    void Cancelar(ActionEvent event) {
        // Cerrar la ventana
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void LimpiarCampos(ActionEvent event) {
        // Limpiar los campos
        txtTitulo.clear();
        txtAutor.clear();
        txtISBN.clear();
        txtEditorial.clear();
        txtGenero.clear();
        txtPrecio.clear();
    }

    @FXML
    void GuardarCambios(ActionEvent event) {
    	
    	if (jtTablaLibros == null) {
            Alert alerta = new Alert(AlertType.ERROR);
            alerta.setTitle("Error");
            System.out.println("HOlans");
            alerta.setContentText("La tabla de libros no está inicializada.");
            alerta.show();
            return;
        }
    	
        // Seleccionar el libro de la tabla
        Libro libroSeleccionado = jtTablaLibros.getSelectionModel().getSelectedItem();
        if (libroSeleccionado == null) {
            Alert alerta = new Alert(AlertType.WARNING);
            alerta.setTitle("Advertencia");
            alerta.setContentText("Por favor, selecciona un libro de la tabla.");
            alerta.show();
            return;
        }

        try {
            // Actualizar datos del libro
            libroSeleccionado.setTitulo(txtTitulo.getText());
            libroSeleccionado.setAutor(txtAutor.getText());
            libroSeleccionado.setIsbn(txtISBN.getText());
            libroSeleccionado.setEditorial(txtEditorial.getText());
            libroSeleccionado.setGenero(txtGenero.getText());
            double precio = Double.parseDouble(txtPrecio.getText());
            libroSeleccionado.setPrecio(precio);
            
            // Actualizar la tabla (esto puede variar dependiendo de cómo manejes la tabla)
            jtTablaLibros.refresh();
        } catch (NumberFormatException e) {
            Alert alerta = new Alert(AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setContentText("Por favor, introduce un precio válido.");
            alerta.show();
        }
    }
}
