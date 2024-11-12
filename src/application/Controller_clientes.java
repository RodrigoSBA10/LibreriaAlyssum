package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Controller_clientes {

    @FXML
    private Label Etiqueta_descuentos;

    @FXML
    private Label Etiqueta_nueva_venta;

    @FXML
    private Label Etiqueta_productos;

    @FXML
    private Label Etiqueta_usuarios;

    @FXML
    private Button btn_agregar;

    @FXML
    private Button btn_cancelar;

    @FXML
    private Label txt_listaclientes;

    @FXML
    private TextField txtf_correocliente;

    @FXML
    private TextField txtf_nombrecliente;

    @FXML
    private TextField txtf_telefonocliente;

    @FXML
    void Cambiar_mouse(MouseEvent event) {
    	Etiqueta_descuentos.setCursor(Cursor.HAND);
		Etiqueta_nueva_venta.setCursor(Cursor.HAND);
		Etiqueta_productos.setCursor(Cursor.HAND);
		Etiqueta_usuarios.setCursor(Cursor.HAND);
    }

    @FXML
    void Cambio_Vistadescuentos(MouseEvent event) {
    	try {
			Pane root = FXMLLoader.load(this.getClass().getResource("Vista_descuentos.fxml"));
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Stage currentStage = (Stage) Etiqueta_descuentos.getScene().getWindow();
		currentStage.close();

    }

    @FXML
    void Cambio_Vistanuevaventa(MouseEvent event) {
    	try {
			Pane root = FXMLLoader.load(this.getClass().getResource("Vista_venta.fxml"));
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Stage currentStage = (Stage) Etiqueta_descuentos.getScene().getWindow();
		currentStage.close();

    }

    @FXML
    void Cambio_Vistaproductos(MouseEvent event) {
    	try {
			Pane root = FXMLLoader.load(this.getClass().getResource("Vista_productos.fxml"));
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Stage currentStage = (Stage) Etiqueta_descuentos.getScene().getWindow();
		currentStage.close();

    }
    
    @FXML
	void agregar_cupon(ActionEvent event) {
		String nombre = txtf_nombrecliente.getText();
		String correo = txtf_correocliente.getText();
		String numero = txtf_telefonocliente.getText();
		
		if (!nombre.isEmpty() && !correo.isEmpty() && !numero.isEmpty()) {
			Cliente nuevo = new Cliente(nombre, correo,numero);
			String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
	        String username = "SYSTEM";
	        String password = "Admin-2812";
	        // Consulta SQL para insertar un registro
	        String sql = "INSERT INTO CLIENTE (NOMBRE,CORREO,TELEFONO) VALUES (?, ?, ?)";
	        try {
	            // Establecer conexión con la base de datos
	            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

	            // Preparar el statement
	            PreparedStatement preparedStatement = connection.prepareStatement(sql);
	            
	            // Asignar valores a los placeholders (los símbolos ? en el SQL)
	            preparedStatement.setString(1, nuevo.getNombre()); // Valor para NOMBRE
	            preparedStatement.setString(2, nuevo.getCorreo());       // Valor para CORREO
	            preparedStatement.setString(3, nuevo.getNumero_Telefonico()); // Valor para TELEFONO
	           

	            // Ejecutar la inserción
	            int rowsInserted = preparedStatement.executeUpdate();

	            if (rowsInserted > 0) {
	                System.out.println("¡Inserción exitosa! Se ha agregado un nuevo cliente.");
	            }

	            // Cerrar la conexión
	            connection.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Alerta");
			alert.setHeaderText(null);
			alert.setContentText("¡Favor de llenar todos los campos!");
			alert.showAndWait();
		}
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("INFO");
		alert.setHeaderText(null);
		alert.setContentText("¡Cliente agregado correctamente!");
		alert.showAndWait();

	}
    
    @FXML
    void Cancelar_cupon(ActionEvent event) {
		txtf_nombrecliente.clear();
		txtf_correocliente.clear();
		txtf_telefonocliente.clear();
		

    }
    @FXML
    void Cambio_vistaTablaclientes(MouseEvent event) {
    	try {
			Pane root = FXMLLoader.load(this.getClass().getResource("Vista_tablaclientes.fxml"));
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Stage currentStage = (Stage) Etiqueta_descuentos.getScene().getWindow();
		currentStage.close();

    }

}
