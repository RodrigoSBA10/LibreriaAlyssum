/*
 * Cuerpo de la clase de ver y agregar reseña
 * 
 * Fecha: 13/10/2024
 * 
 * @autor Aurora Morales
 * 
 * Version: 29
 * */

package controladores;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Libro;
import modelo.Reseña;

/**
 * Representa el controlador de el control de las reseñas.
 */
public class ControllerReseñaLibro {
  @FXML
  private Button agregar;  //Boton para agregar una reseña

  @FXML
  private Button regresar;  //Boton para regresar a la ventana anterior
  
  @FXML
  private Button refrescar;

  @FXML
  private TableColumn<Reseña, Integer> columnEstrellas;

  @FXML
  private TableColumn<Reseña, String> columnDescripcion;

  @FXML
  private TableView<Reseña> tabla;
    
  @FXML
  private Label titulo;

  private ObservableList<Reseña> lista = FXCollections.observableArrayList();
  private Libro lib = new Libro();
  int id;

  @FXML
  public void initialize() {
    configurarColumnas();
  }

  /**
   * Representa la configuracin de las columnas.
   */
  public void configurarColumnas() {
    columnEstrellas.setCellValueFactory(data -> 
        new SimpleIntegerProperty(data.getValue().getValoracion()).asObject());

    columnDescripcion.setCellValueFactory(data -> 
        new SimpleStringProperty(data.getValue().getDescripcion())); 
  }
    
  public void agregar(Reseña res) {
    lista.add(res);
  }
  
  /**
   * Recibe los datos del libro seleccionado.
   * 
   * @param lib libro que resibe.
   */
  public void setLibro(Libro lib) {
    this.lib = lib;
    titulo.setText(lib.getTitulo()); //Optengo el titulo del libro y lo agrego a la etiqueta
  }
  
  /**
   * Representa el agregado de las reseñas a la tabla.
   */
  public void agregarTabla() {
    ObservableList<Reseña> columnas = FXCollections.observableArrayList();
        
    //Informacion para acceder a la base de datos
    String url = "jdbc:oracle:thin:@//localhost:1521/ORCL";
    String usuario = "System";
    String contrasena = "aurelio666";
        
    //Consulta para encontrar el libro seleccionado
    String sql = "SELECT ID_LIBRO FROM LIBRO WHERE TITULO = ?";
    //Consulta para encontrar la valoracion y descripcion del libro seleccionado
    String sql2 = "SELECT NOMBRE_USUARIO, VALORACION, DESCRIPCION FROM RESENA WHERE ID_LIBRO = ?";

    //Se conecta a la base de datos
    try (Connection connection = DriverManager.getConnection(url, usuario, contrasena);
         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
      preparedStatement.setString(1, lib.getTitulo());
      ResultSet resultado = preparedStatement.executeQuery();

      //Busca en el resultado de la consulta el identificador del libro
      if (resultado.next()) {
        id = resultado.getInt("ID_LIBRO");
      } else {
        return; // Salir si no se encuentra el libro
      }

      //Conectar por segunda vez con el identificador optenido de la primera
      //consulta
      try (PreparedStatement preparedStatement2 = connection.prepareStatement(sql2)) {
        preparedStatement2.setInt(1, id);
        ResultSet resultado2 = preparedStatement2.executeQuery();
                
        //Buscar con el identificador del libro en el resultado de la consulta
        //para optener la descripcion y valoracion
        while (resultado2.next()) {
          String nom = resultado2.getString("NOMBRE_USUARIO");
          int val = resultado2.getInt("VALORACION");
          String descrip = resultado2.getString("DESCRIPCION");
          Reseña re = new Reseña(nom, val, descrip);
          columnas.add(re);
          System.out.println("Reseña agregada: " + re);
                    
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    System.out.println("Número de reseñas cargadas: " + columnas.size());
    listaReseña(columnas); //Se agregan las columnas a la lista de reseñas
  }

  /**
   * Establece la lista observable de reseñas en la tabla.
   * 
   * @param lis la lista observable de reseñas que se mostrará en la tabla.
   */
  public void listaReseña(ObservableList<Reseña> lis) {
    tabla.setItems(lis);
  }

  @FXML
  void aceptarButton(ActionEvent event) {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AgregarReseña.fxml"));
      Parent root = loader.load();
            
      ControllerAgregarReseña controlados = loader.getController();
      controlados.setLibro(lib);
            
      Scene scene = new Scene(root);
      Stage stage = new Stage();
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.setScene(scene);
      stage.showAndWait();
            
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  void regresarButton(ActionEvent event) {
    Node source = (Node) event.getSource();
    Stage stage = (Stage) source.getScene().getWindow();
    stage.close();
  }
  
  
  @FXML
  void refrescarTabla(ActionEvent event) {
    agregarTabla();
  }
}
