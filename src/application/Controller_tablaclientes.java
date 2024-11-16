package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Controller_tablaclientes {

	@FXML
	private TableColumn<Cliente, String> Columna_correo;

	@FXML
	private TableColumn<Cliente, String> Columna_nombre;

	@FXML
	private TableColumn<Cliente, String> Columna_telefono;

	@FXML
	private Button btn_atras;

	@FXML
	private TableView<Cliente> tabla_cupones;

	@FXML
	void atras(ActionEvent event) {
		try {
			Pane root = FXMLLoader.load(this.getClass().getResource("Vista_agregarClientes.fxml"));
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Stage currentStage = (Stage) tabla_cupones.getScene().getWindow();
		currentStage.close();

	}

	@FXML
	public void initialize() {
		// Enlazar las columnas con los atributos de la clase Descuento
		Columna_nombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
		Columna_correo.setCellValueFactory(new PropertyValueFactory<>("Correo"));
		Columna_telefono.setCellValueFactory(new PropertyValueFactory<>("Numero_Telefonico"));

		// Obtener los datos de la base de datos y agregarlos al TableView
		ObservableList<Cliente> cupones = getCuponesFromDatabase();
		tabla_cupones.setItems(cupones);
	}

	public ObservableList<Cliente> getCuponesFromDatabase() {

		ObservableList<Cliente> cupones = FXCollections.observableArrayList();
		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "SYSTEM";
		String password = "Admin-2812";

		try {
			// Establecer conexión con la base de datos
			Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

			// Crear el statement y ejecutar la consulta
			Statement statement = connection.createStatement();
			String query = "SELECT NOMBRE, CORREO, TELEFONO FROM CLIENTE"; // Cambia por tu consulta real
			ResultSet resultSet = statement.executeQuery(query);

			// Recorrer los resultados y añadirlos a la lista
			while (resultSet.next()) {
				String nombre = resultSet.getString("NOMBRE");
				String correo = resultSet.getString("CORREO");
				String telefono = resultSet.getString("TELEFONO");

				cupones.add(new Cliente(nombre, correo, telefono));
			}

			// Cerrar la conexión
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return cupones;
	}

}
