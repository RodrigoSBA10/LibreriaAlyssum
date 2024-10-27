package application;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
    private TableColumn<?, ?> columna_cantidad;

    @FXML
    private TableColumn<?, ?> columna_codigo;

    @FXML
    private TableColumn<?, ?> columna_libro;

    @FXML
    private TableColumn<?, ?> columna_precio;

    @FXML
    private TableColumn<?, ?> columna_total;

    @FXML
    private TableView<?> tabla_venta;

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
    void Forma_depago(ActionEvent event) {
    	try {
			Pane root = FXMLLoader.load(this.getClass().getResource("FormadePago.fxml"));
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
                String precio= resultSet.getString("precio_unitario");
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

 


}
