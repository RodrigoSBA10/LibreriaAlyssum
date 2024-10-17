package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Controller_Sistema {

	@FXML
	private Label Etiqueta_descuentos;

	@FXML
	private Label Etiqueta_nueva_venta;

	@FXML
	private Label Etiqueta_productos;

	@FXML
	private Label Etiqueta_usuarios;
	
	//Texto de prueba

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

}
