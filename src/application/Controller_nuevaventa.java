package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Controller_nuevaventa {

	@FXML
	private Label Etiqueta_descuentos;

	@FXML
	private Label Etiqueta_nueva_venta;

	@FXML
	private Label Etiqueta_productos;

	@FXML
	private Label Etiqueta_usuarios;

	@FXML
	private Button boton_agregarproducto;

	@FXML
	private TableColumn<Tabla_venta, Integer> columna_cantidad;

	@FXML
	private TableColumn<Tabla_venta, String> columna_codigo;

	@FXML
	private TableColumn<Tabla_venta, String> columna_libro;

	@FXML
	private TableColumn<Tabla_venta, Float> columna_precio;

	@FXML
	private TableColumn<Tabla_venta, Float> columna_total;
	@FXML
	 private TableColumn<Tabla_venta,Float> columna_iva;

	@FXML
	private TableView<Tabla_venta> tabla_venta;

	@FXML
	private TextField textf_idcliente;

	@FXML
	private TextField txtf_cantidad;

	@FXML
	private TextField txtf_idlibro;

	@FXML
	private TextField txtf_nombrecliente;

	@FXML
	private TextField txtf_nombrelibro;

	@FXML
	private TextField txtf_precio;
	@FXML
	private Button btn_buscacodigo;
	@FXML
	private TextField total_txtf;
    @FXML
    private Label label_iva;

	private ObservableList<Tabla_venta> tl_venta = FXCollections.observableArrayList();

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
    void Cambio_viistacli(MouseEvent event) {
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
	void Forma_depago(ActionEvent event) {
		try {
	        // Carga el archivo FXML de la nueva vista
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("FormadePago.fxml"));
	        Pane root = loader.load();

	        // Obtén el controlador de la nueva vista
	        Controller_FormadePagoTarjeta controladorDestino = loader.getController();

	        // Pasa el dato del total al controlador de la nueva vista
	        float total = Float.parseFloat(total_txtf.getText());
	        controladorDestino.setTotal(total);

	        // Cambia de escena
	        Scene scene = new Scene(root);
	        Stage stage = new Stage();
	        stage.setScene(scene);
	        stage.show();

	        // Cierra la ventana actual
	        Stage currentStage = (Stage) txtf_cantidad.getScene().getWindow();
	        currentStage.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	@FXML
	void busca_libroid(ActionEvent event) {
		try {
			// Verificar que el campo no esté vacío
			if (txtf_idlibro.getText().isEmpty()) {
				System.out.println("El campo de ID está vacío.");
				return;
			}

			// Convertir el texto a entero
			int id = Integer.parseInt(txtf_idlibro.getText());
			System.out.println("ID del libro: " + id);

			// Información de conexión a la base de datos
			String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
			String username = "SYSTEM";
			String password = "Admin-2812";
			String query = "SELECT * FROM LIBRO WHERE id_libro = ?";

			// Establecer conexión con la base de datos
			Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

			// Preparar la consulta y establecer el valor del parámetro
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);

			// Ejecutar la consulta y procesar el resultado
			ResultSet resultSet = preparedStatement.executeQuery();

			// Procesar los resultados
			if (resultSet.next()) {
				// Puedes obtener los valores de las columnas de esta forma:
				String titulo = resultSet.getString("titulo"); // Cambia "titulo" por el nombre de tu columna real
				txtf_nombrelibro.setText(titulo);
				System.out.println("Título del libro: " + titulo);
				String precio = resultSet.getString("precio_unitario");
				txtf_precio.setText(precio);
				System.out.println("Pecio del libro: " + precio);

				// Agrega aquí cualquier otra acción que necesites hacer con los datos
			} else {
				System.out.println("No se encontró un libro con el ID especificado.");
			}

			// Cerrar el ResultSet, PreparedStatement y la conexión

			resultSet.close();
			preparedStatement.close();
			connection.close();

		} catch (NumberFormatException e) {
			System.out.println("Por favor, ingrese un número válido en el campo de ID.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void initialize() {
		// Configura las columnas solo una vez, por ejemplo, en el método initialize
		columna_libro.setCellValueFactory(new PropertyValueFactory<>("Producto"));
		columna_codigo.setCellValueFactory(new PropertyValueFactory<>("Codigo"));
		columna_precio.setCellValueFactory(new PropertyValueFactory<>("PrecioBase"));
		columna_cantidad.setCellValueFactory(new PropertyValueFactory<>("Cantidad"));
		columna_total.setCellValueFactory(new PropertyValueFactory<>("Subtotal"));
		columna_iva.setCellValueFactory(new PropertyValueFactory<>("Iva"));
		
		

		// Establece la lista en el TableView
		tabla_venta.setItems(tl_venta);
	}

	@FXML
	void Agregar_libro_tabla(ActionEvent event) {
		// Agrega el libro a la lista persistente
		agregarLibro();
		calcularTotal();
		calcularTotaliva(); 
	}

	public void agregarLibro() {
	    String pro = txtf_nombrelibro.getText();
	    String co = txtf_idlibro.getText();
	    Float precioBase = Float.parseFloat(txtf_precio.getText());
	    int cant = Integer.parseInt(txtf_cantidad.getText());

	    // Calcula el IVA (por ejemplo, 16%)
	    float iva = precioBase * 0.16f;
	    float precioConIVA = precioBase + iva;

	    // Calcula el total considerando el IVA
	    Tabla_venta nuevoLibro = new Tabla_venta(pro, co, precioConIVA, cant);
	    tl_venta.add(nuevoLibro); // Agrega el nuevo libro a la lista persistente
	}

	@FXML
	void cancelar_venta(ActionEvent event) {
		tl_venta.clear();
		calcularTotal();
		calcularTotaliva(); 
		tabla_venta.refresh();
	}
	
	@FXML
	void calcularTotal() {

	    float sumaTotal = 0;

	    // Recorre cada elemento en la lista de datos del TableView
	    for (Tabla_venta item : tabla_venta.getItems()) {
	        sumaTotal += item.getTotal(); // Suma el valor de la columna "total"
	    }

	    // Muestra el resultado en el TextField
	    total_txtf.setText(String.format("%.2f", sumaTotal));
	}
	void calcularTotaliva() {
		float sumaTotaliva = 0;

	    // Recorre cada elemento en la lista de datos del TableView
	    for (Tabla_venta item : tabla_venta.getItems()) {
	        sumaTotaliva += item.getIva(); // Suma el valor de la columna "total"
	    }

	    // Muestra el resultado en el TextField
	    label_iva.setText(String.format("%.2f", sumaTotaliva));
		
	}
	
	  @FXML
	    void busca_clienteid(ActionEvent event) {
		  try {
				// Verificar que el campo no esté vacío
				if (textf_idcliente.getText().isEmpty()) {
					System.out.println("El campo de ID está vacío.");
					return;
				}

				// Convertir el texto a entero
				int id = Integer.parseInt(textf_idcliente.getText());
				System.out.println("ID del cliente: " + id);

				// Información de conexión a la base de datos
				String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
				String username = "SYSTEM";
				String password = "Admin-2812";
				String query = "SELECT NOMBRE FROM CLIENTE WHERE id_cliente = ?";

				// Establecer conexión con la base de datos
				Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

				// Preparar la consulta y establecer el valor del parámetro
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, id);

				// Ejecutar la consulta y procesar el resultado
				ResultSet resultSet = preparedStatement.executeQuery();

				// Procesar los resultados
				if (resultSet.next()) {
					// Puedes obtener los valores de las columnas de esta forma:
					String Nombre = resultSet.getString("nombre"); // Cambia "titulo" por el nombre de tu columna real
					txtf_nombrecliente.setText(Nombre);
					System.out.println("el nombre del cliente: " + Nombre);
				/*	String precio = resultSet.getString("precio_unitario");
					txtf_precio.setText(precio);
					System.out.println("Pecio del libro: " + precio); */

					// Agrega aquí cualquier otra acción que necesites hacer con los datos
				} else {
					System.out.println("No se encontró un cliente con el ID especificado.");
				}

				// Cerrar el ResultSet, PreparedStatement y la conexión

				resultSet.close();
				preparedStatement.close();
				connection.close();

			} catch (NumberFormatException e) {
				System.out.println("Por favor, ingrese un número válido en el campo de ID.");
			} catch (Exception e) {
				e.printStackTrace();
			}

	    }

}
