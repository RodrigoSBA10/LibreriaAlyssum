/*
 * Cuerpo de la clase de ver y agregar reseña
 * 
 * Fecha: 13/10/2024
 * 
 * @autor Aurora Morales
 * 
 * Version: 5
 * */

package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/*
 * Metodo  para controlar las reselas de los libros
 * */

public class ControllerReseñaLibro {
  @FXML
    private Button agregar;

  @FXML
  private Button regresar;
  
  @FXML
  private TableColumn<?, ?> columnEstrellas;

  @FXML
  private TableColumn<?, ?> columnDescripcion;
  
  @FXML
  private TableView<?> tablaResena;

  
  private ObservableList<Reseña> lista  = FXCollections.observableArrayList();
  
  private Libro lib = new Libro();
  
  int id;
  
  /*Metodo que inicializa los valores de la tabla
   * */
  public void initialize() {
	//Liga la propiedad titulo de la clase Libro
	  columnEstrellas.setCellValueFactory(new PropertyValueFactory<>("valoracion"));
	//Liga la propiedad autor de la clase Libro
	  columnDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
	  
	  ObservableList<Reseña> colum = agregarTabla();
	  //Imprimir(colum);
	  //tablaResena.setItems(colum);
  }
  
  
  public void setLibro(Libro lib) {
	  this.lib = lib;
  }
  
  public ObservableList<Reseña> agregarTabla() {
	  ObservableList<Reseña> columnas = FXCollections.observableArrayList();
	    
	    //Informacion de mi base de datos
	    String url = "jdbc:oracle:thin:@//localhost:1521/ORCL";
		String usuario = "System";
		String contraseña = "aurelio666";
		
		try {
        // Establecer conexión con la base de datos
        Connection connection = DriverManager.getConnection(url, usuario, contraseña);

        // Crear el statement y ejecutar la consulta
        Statement statement = connection.createStatement();
        String sql = "SELECT ID_LIBRO FROM LIBRO WHERE TITULO = '" + lib.getTitulo()+ "' ";
        ResultSet resultado = statement.executeQuery(sql);
        
        // Recorrer los resultados y añadirlos a la lista
        while (resultado.next()) {
            id = resultado.getInt("ID_LIBRO"); 
            System.out.println(id + " ");
        }
        
        String sql2 = "SELECT R.VALORACION AS ESTRELLAS, R.DESCRIPCION AS DESCRIPCION FROM LIBRO L JOIN RESENA R ON L.ID_LIBRO = R.ID_LIBRO";
        ResultSet resultado2 = statement.executeQuery(sql2);
        while(resultado2.next()) {
      	  int val = resultado2.getInt("ESTRELLAS");
      	  String descrip = resultado2.getString("DESCRIPCION");
      	  
      	  columnas.add(new Reseña(val, descrip));
      	  System.out.println(val + "||" + descrip);
        }
     // Cerrar la conexión
        connection.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
		return columnas;
  }
  
  public void listaReseña(List<Reseña> lis) {
	  lista.addAll(lis);
  }

  @FXML
  void aceptarButton(ActionEvent event) {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("AgregarReseña.fxml"));
      Parent root = loader.load();
      Scene scene = new Scene(root);
      Stage stage = new Stage();
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.setScene(scene);
      stage.showAndWait();
      stage.setTitle("Agregar reseña");
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @FXML
  void regresarButton(ActionEvent event) {
    Node source = (Node) event.getSource();
    Stage stage = (Stage) source.getScene().getWindow();
    stage.close();
  } 
}
