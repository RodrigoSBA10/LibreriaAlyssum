package application;

import javafx.fxml.FXML;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class Controller_cupon extends Application{

    @FXML
    private TableColumn<Descuento,String> Columna_codigo;

    @FXML
    private TableColumn<Descuento,String> Columna_descuento;

    @FXML
    private TableColumn<Descuento,String> Columna_fin;

    @FXML
    private TableColumn<Descuento,String> Columna_ini;

    @FXML
    private TableView<Descuento> tabla_cupones;

    @Override
    public void start(Stage stage) throws Exception {
     
    }
    
    @FXML
    public void initialize() {
        // Enlazar las columnas con los atributos de la clase Descuento
        Columna_codigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        Columna_descuento.setCellValueFactory(new PropertyValueFactory<>("descuento"));
        Columna_ini.setCellValueFactory(new PropertyValueFactory<>("fecha_Validesini"));
        Columna_fin.setCellValueFactory(new PropertyValueFactory<>("fecha_Validesfin"));

        // Obtener los datos de la base de datos y agregarlos al TableView
        ObservableList<Descuento> cupones = getCuponesFromDatabase();
        tabla_cupones.setItems(cupones);
    }
	
	public ObservableList<Descuento> getCuponesFromDatabase() {
		
		ObservableList<Descuento> cupones = FXCollections.observableArrayList();
		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
        String username = "SYSTEM";
        String password = "Admin-2812";
        
        try {
            // Establecer conexión con la base de datos
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            // Crear el statement y ejecutar la consulta
            Statement statement = connection.createStatement();
            String query = "SELECT CODIGO, DESCUENTO, FECHA_INICIO, FECHA_FIN FROM CUPON";  // Cambia por tu consulta real
            ResultSet resultSet = statement.executeQuery(query);
           

            // Recorrer los resultados y añadirlos a la lista
            while (resultSet.next()) {
                String codigo = resultSet.getString("CODIGO");
                String descuento = resultSet.getString("DESCUENTO");
                String fecha_inicio = resultSet.getString("FECHA_INICIO");
                String fecha_fin = resultSet.getString("FECHA_FIN");

                cupones.add(new Descuento(codigo, descuento, fecha_inicio,fecha_fin));
            }

            // Cerrar la conexión
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cupones;
    }
		
	}



