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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
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
  private TableColumn<Libro, String> reseñas;
    
  @FXML
  private AnchorPane panel;

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
