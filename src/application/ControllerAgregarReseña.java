/*
 * Clase para especificar las acciones de la ventana
 * Agregar Reseña
 * 
 * @autor Aurora Morales
 * 
 * Version: 4
 * */

package application;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/*
 * Inicio de metodo ControllerAgregarReseña
 * 
 * */
public class ControllerAgregarReseña {

  @FXML
  private Button agregar;  //Boton para confirmar y guardar

  @FXML
  private Button btn1; //Boton para selecionar el calificacion

  @FXML
  private Button btn2; //Boton para selecionar el calificacion

  @FXML
  private Button btn3; //Boton para selecionar el calificacion

  @FXML
  private Button btn4; //Boton para selecionar el calificacion

  @FXML
  private Button btn5; //Boton para selecionar el calificacion

  @FXML
  private Button cancelar; //Boton para cancelar toda la accion

  @FXML
  private TextArea textArea; //Espacio de texto para escribir la reseña
  
  public List<Reseña> lista;
  
  int valoracion = 0; //Variable para 
  
  private Libro lib = new Libro();
  
  @FXML
  void ClickButton1(ActionEvent event) {
	  btn1.setStyle("-fx-background-color: yellow; -fx-text-fill: black;");
	  valoracion = 1;
  }
  
  @FXML
  void ClickButton2(ActionEvent event) {
	  btn1.setStyle("-fx-background-color: yellow; -fx-text-fill: black;");
	  btn2.setStyle("-fx-background-color: yellow; -fx-text-fill: black;");
	  valoracion = 2;
  }
    
  @FXML
  void ClickButton3(ActionEvent event) {
    btn3.setStyle("-fx-background-color: yellow; -fx-text-fill: black;");
    btn2.setStyle("-fx-background-color: yellow; -fx-text-fill: black;");
    btn1.setStyle("-fx-background-color: yellow; -fx-text-fill: black;");
    valoracion = 3;
  }
    
  @FXML
  void ClickButton4(ActionEvent event) {
    btn4.setStyle("-fx-background-color: yellow; -fx-text-fill: black;");
    btn3.setStyle("-fx-background-color: yellow; -fx-text-fill: black;");
    btn2.setStyle("-fx-background-color: yellow; -fx-text-fill: black;");
    btn1.setStyle("-fx-background-color: yellow; -fx-text-fill: black;");
    valoracion = 4;
  }
    
  @FXML
  void ClickButton5(ActionEvent event) {
    btn5.setStyle("-fx-background-color: yellow; -fx-text-fill: black;");
    btn4.setStyle("-fx-background-color: yellow; -fx-text-fill: black;");
    btn3.setStyle("-fx-background-color: yellow; -fx-text-fill: black;");
    btn2.setStyle("-fx-background-color: yellow; -fx-text-fill: black;");
    btn1.setStyle("-fx-background-color: yellow; -fx-text-fill: black;");
    valoracion = 5;
  }
    
  @FXML
  void agregarReseña(ActionEvent event) {
      Reseña res = new Reseña( valoracion, textArea.getText());
      lista.add(res);
      lib.setNumeroReseña(1);
  }
  
  @FXML
  void regresarBoton(ActionEvent event) {
	Node source = (Node)event.getSource();
  	Stage stage = (Stage)source.getScene().getWindow();
  	stage.close();
  }
}