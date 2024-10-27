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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Controller_descuentos {

	@FXML
	private Label Etiqueta_descuentos;

	@FXML
	private Label Etiqueta_nueva_venta;

	@FXML
	private Label Etiqueta_productos;

	@FXML
	private Label Etiqueta_usuarios;

	@FXML
	private Button btn_agregarcupon;

	@FXML
	private Button btn_cancelar;

	@FXML
	private TextField textf_codigocupon;

	@FXML
	private Label txt_historialcupones;

	@FXML
	private TextField txtf_fechadevalidezini;

	@FXML
	private TextField txtf_fechavalidezfin;

	@FXML
	private TextField txtf_porcentajedescuento;

	@FXML
	void Cambiar_mouse(MouseEvent event) {
		Etiqueta_descuentos.setCursor(Cursor.HAND);
		Etiqueta_nueva_venta.setCursor(Cursor.HAND);
		Etiqueta_productos.setCursor(Cursor.HAND);
		Etiqueta_usuarios.setCursor(Cursor.HAND);
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
	    void Cambio_Vistacupones(MouseEvent event) {
		 try {
				Pane root = FXMLLoader.load(this.getClass().getResource("Vista_cupones.fxml"));
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
		String codigo = textf_codigocupon.getText();
		String descuento = txtf_porcentajedescuento.getText();
		String fechaini = txtf_fechadevalidezini.getText();
		String fechafin = txtf_fechavalidezfin.getText();
		if (!codigo.isEmpty() && !descuento.isEmpty() && !fechaini.isEmpty() && !fechafin.isEmpty()) {
			Descuento nuevo = new Descuento(codigo, descuento, fechaini, fechafin);
			String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
	        String username = "SYSTEM";
	        String password = "Admin-2812";
	        // Consulta SQL para insertar un registro
	        String sql = "INSERT INTO CUPON (CODIGO, DESCUENTO, FECHA_INICIO, FECHA_FIN) VALUES (?, ?, ?, ?)";
	        try {
	            // Establecer conexión con la base de datos
	            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

	            // Preparar el statement
	            PreparedStatement preparedStatement = connection.prepareStatement(sql);
	            
	            // Asignar valores a los placeholders (los símbolos ? en el SQL)
	            preparedStatement.setString(1, nuevo.getCodigo()); // Valor para CODIGO
	            preparedStatement.setString(2, nuevo.getDescuento());       // Valor para DESCUENTO
	            preparedStatement.setString(3, nuevo.getFecha_Validesini()); // Valor para FECHA_INICIO
	            preparedStatement.setString(4, nuevo.getFecha_Validesfin()); // Valor para FECHA_FIN

	            // Ejecutar la inserción
	            int rowsInserted = preparedStatement.executeUpdate();

	            if (rowsInserted > 0) {
	                System.out.println("¡Inserción exitosa! Se ha agregado un nuevo cupón.");
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
		alert.setContentText("¡cupon generado correctamente!");
		alert.showAndWait();

	}
	
	@FXML
    void Cancelar_cupon(ActionEvent event) {
		textf_codigocupon.clear();
		txtf_fechadevalidezini.clear();
		txtf_fechavalidezfin.clear();
		txtf_porcentajedescuento.clear();

    }

}
