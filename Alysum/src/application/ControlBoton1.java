package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ControlBoton1 {

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnLimpiar;

    @FXML
    private Label lblApellido;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblNombre;

    @FXML
    private Label lblTelefono;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtTelefono;

    @FXML
    void LimpiaInf(ActionEvent event) {
    	txtNombre.setText(null);
    	txtApellido.setText(null);
    	txtEmail.setText(null);
    	txtTelefono.setText(null);
    }

    @FXML
    void RegistroUsuario(ActionEvent event) {
        String nombre= txtNombre.getText();
        String apellido= txtApellido.getText();
        String email= txtEmail.getText();
        String telefono = txtTelefono.getText();
        
        Inscripcion inscripcion= new Inscripcion(nombre, apellido, email, telefono); 
    }

}
