package controllers;

import conexion.BusquedaBD;
import conexion.ConectarBD;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import poo.Libro;

public class EditarLibroController {

	@FXML
    private Button btnCancelar;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnLimpiar;
    @FXML
    private ComboBox<String> cbCategoriaE;
    @FXML
    private ComboBox<String> cbGeneroE;
    @FXML
    private TextField txtAnioE;
    @FXML
    private TextField txtAutorE;
    @FXML
    private TextField txtEditorialE;
    @FXML
    private TextField txtISBNE;
    @FXML
    private TextField txtPrecioE;
    @FXML
    private TextField txtTituloE;
    @FXML
    private TableView<Libro> jtTablaLibros;

    // Referencia a la lista de libros de la clase principal
    @SuppressWarnings("unused")
	private ObservableList<Libro> listaLibros;
    private Libro libroActual; // Libro que se está editando
    //Conectar a la base de datos
    ConectarBD con= new ConectarBD();
    BusquedaBD cargar = new BusquedaBD();

    // Método para recibir la lista de libros desde la clase principal
    public void setListaLibros(ObservableList<Libro> listaLibros) {
        this.listaLibros = listaLibros;
    }
    //Método para pasar una referencia de la instancia de TableView
    public void setTableView(TableView<Libro> tableView) {
        this.jtTablaLibros = tableView;
    }
    
    @FXML
    private void initialize() {
        // Cargar categorías y géneros desde la base de datos
        cbCategoriaE.setItems(cargar.cargarCategorias());
        cbGeneroE.setItems(cargar.cargarGeneros());
        //No se puede editar el ISBN 
        txtISBNE.setEditable(false);
    }
    
    // Método para recibir el libro que se va a editar
    public void setLibro(Libro libro) {
        this.setLibroActual(libro);
        // Cargar los datos del libro en los campos de texto
        txtTituloE.setText(libro.getTitulo());
        txtAutorE.setText(libro.getAutor());
        txtISBNE.setText(libro.getIsbn());
        txtEditorialE.setText(libro.getEditorial());
        txtAnioE.setText(libro.getAnio()); 
        txtPrecioE.setText(Double.toString(libro.getPrecio()));
        // Establecer el género y la categoría
        cbCategoriaE.setValue(libro.getCategoria());
        cbGeneroE.setValue(libro.getGenero());
  
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
        txtTituloE.clear();
        txtAutorE.clear();
        txtEditorialE.clear();
        cbCategoriaE.setValue(null);
        cbGeneroE.setValue(null);
        txtAnioE.clear();
        txtPrecioE.clear();
    }

    @FXML
    void GuardarCambios(ActionEvent event) {
        if (jtTablaLibros == null) {
            Alert alerta= new Alert(AlertType.WARNING);
            alerta.setContentText("jtTablaLibros es null.");
            alerta.show();
            return;
        }

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
            libroSeleccionado.setTitulo(txtTituloE.getText());
            libroSeleccionado.setAutor(txtAutorE.getText());
            libroSeleccionado.setIsbn(txtISBNE.getText());
            libroSeleccionado.setEditorial(txtEditorialE.getText());
            libroSeleccionado.setGenero(cbGeneroE.getValue());
            libroSeleccionado.setCategoria(cbCategoriaE.getValue());
            libroSeleccionado.setAnio(txtAnioE.getText());
            
            String precioTexto = txtPrecioE.getText();
            if (!precioTexto.isEmpty()) {
                double precio = Double.parseDouble(precioTexto);
                libroSeleccionado.setPrecio(precio);
            } else {
                libroSeleccionado.setPrecio(0.0); // 
            }

            // Llamar al método para actualizar en la base de datos
            BusquedaBD busquedaBD = new BusquedaBD();
            boolean actualizado = busquedaBD.actualizarLibro(libroSeleccionado);
            if (actualizado) {
                jtTablaLibros.refresh();
                Alert alerta = new Alert(AlertType.INFORMATION);
                alerta.setTitle("Éxito");
                alerta.setContentText("Los cambios se han guardado exitosamente.");
                alerta.show();
            } else {
                Alert alerta = new Alert(AlertType.ERROR);
                alerta.setTitle("Error");
                alerta.setContentText("No se pudo guardar los cambios. Verifica los datos.");
                alerta.show();
            }

        } catch (NumberFormatException e) {
            Alert alerta = new Alert(AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setContentText("Por favor, introduce un precio válido.");
            alerta.show();
        }
    }


	public Libro getLibroActual() {
		return libroActual;
	}

	public void setLibroActual(Libro libroActual) {
		this.libroActual = libroActual;
	}

}
