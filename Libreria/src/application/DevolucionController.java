/*
 * @(#)DevolucionControlle.class		1.0 14/10/2024
 * 
 */
package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Esta clase es el controlador de la interfaz Devolución.
 * Solicita y adquiere los datos necesarios para Registrar una
 * devolución. Para registrar una devolución se utiliza el método
 * registrarDevolución().
 * 
 * @version		1.0 14/10/2024
 * @author		Picazo Reyes Ulises Nioolas
 */
public class DevolucionController {

    @FXML
    private TextArea areaTexto;

    @FXML
    private Button btnRegistrar;

    @FXML
    private CheckBox checkNo;

    @FXML
    private CheckBox checkSi;

    @FXML
    private ComboBox<?> comboBox;

    @FXML
    private TextField txtNumeroPedido;

    @FXML
    void registrarDevolucion(ActionEvent event) {

    }

}
