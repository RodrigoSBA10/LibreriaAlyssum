/*
 * Clase para ver las valoracines de los libros y seleccionar por genero
 * 
 * Fecha: 13/10/2024
 * 
 * @autor Aurora Morales
 * 
 * Version: 2
 * */

package application;

import java.io.IOException;

import application.modelo.Libro;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

/*
 * Cuerpo de la clase ControllerValoracionLibro
 * @author Aurora Morales
 * */
public class ControllerValoracionLibro {

  @FXML
  private TableView<?> tablaDeLibros; //Tabla de libros
  
  FilteredList<Libro> listaLibro = null;  //lista de libros
  
  @FXML
  private MenuItem item1;  //Item de Novela historica
  
  @FXML
  private MenuItem item2;  //Item de Novela negra, thriller o suspense 
  
  @FXML
  private MenuItem item3;  //Item de Ciencia Ficción
  
  @FXML
  private MenuItem item4;  //Item de Distopía
  
  @FXML
  private MenuItem item5;  //Item de Aventuras
  
  @FXML
  private MenuItem item6;  //Item de Fantasía
  
  @FXML
  private MenuItem item7;  //Item de Contemporáneo
  
  @FXML
  private MenuItem item8;  //Item de Terror
  
  @FXML
  private MenuItem item9;  //Item de Paranormal
  
  @FXML
  private MenuItem item10; //Item de Poesía

  @FXML
  private MenuItem item11; //Item de Juvenil

  @FXML
  private MenuItem item12;  //Item de Infantil
  
  @FXML
  private MenuItem item13;  //Item de Autoayuda y superación personal
  
  @FXML
  private MenuItem item14;  //Item de Salud y deporte
  
  @FXML
  private MenuItem item15;  //Item de Libros prácticos o manuales
  
  @FXML
  private MenuItem item16;  //Item de Memorias
  
  @FXML
  private MenuItem item17;  //Item de Bibliografias
  
  @FXML
  private MenuItem item18;  //Item de Cocina
  
  @FXML
  private MenuItem item19;  //Item de Viajes
  
  @FXML
  private MenuItem item20;  //Item de Libros técnicos y especialezados
  
  @FXML
  private MenuItem item21;  //Item de De consulta y referencia
  
  @FXML
  private MenuItem item22;  //Item de Divulgacion
  
  @FXML
  private MenuItem item23;  //Item de Libros de texto
  
  @FXML
  private MenuItem item24;  //Item de Arte

  @FXML
  private MenuButton miMenuButton;

  @FXML
  private Button provicional;

  
  
  @FXML
  void miMenuButton(ActionEvent event) { 
  }
  

  @FXML
  void precionarItem1(ActionEvent event) {
	  miMenuButton.setText(item1.getText());
  }
  
  @FXML
  void precionarItem2(ActionEvent event) {
	  miMenuButton.setText(item2.getText());
  }
  
  @FXML
  void precionarItem3(ActionEvent event) {
	  miMenuButton.setText(item3.getText());
  }
  
  @FXML
  void precionarItem4(ActionEvent event) {
	  miMenuButton.setText(item4.getText());
  }
  
  @FXML
  void precionarItem5(ActionEvent event) {
	  miMenuButton.setText(item5.getText());
  }
  
  @FXML
  void precionarItem6(ActionEvent event) {
	  miMenuButton.setText(item6.getText());
  }
  
  @FXML
  void precionarItem7(ActionEvent event) {
	  miMenuButton.setText(item7.getText());
  }
  
  @FXML
  void precionarItem8(ActionEvent event) {
	  miMenuButton.setText(item8.getText());
  }
  
  @FXML
  void precionarItem9(ActionEvent event) {
	  miMenuButton.setText(item9.getText());
  }
  
  @FXML
  void precionarItem10(ActionEvent event) {
	  miMenuButton.setText(item10.getText());
  }
  
  @FXML
  void precionarItem11(ActionEvent event) {
	  miMenuButton.setText(item11.getText());
  }
  
  @FXML
  void precionarItem12(ActionEvent event) {
	  miMenuButton.setText(item12.getText());
  }
  
  @FXML
  void precionarItem13(ActionEvent event) {
	  miMenuButton.setText(item13.getText());
  }
  
  @FXML
  void precionarItem14(ActionEvent event) {
	  miMenuButton.setText(item14.getText());
  }
  
  @FXML
  void precionarItem15(ActionEvent event) {
	  miMenuButton.setText(item15.getText());
  }
  
  @FXML
  void precionarItem16(ActionEvent event) {
	  miMenuButton.setText(item16.getText());
  }
  
  @FXML
  void precionarItem17(ActionEvent event) {
	  miMenuButton.setText(item17.getText());
  }
  
  @FXML
  void precionarItem18(ActionEvent event) {
	  miMenuButton.setText(item18.getText());
  }
  
  @FXML
  void precionarItem19(ActionEvent event) {
	  miMenuButton.setText(item19.getText());
  }
  
  @FXML
  void precionarItem20(ActionEvent event) {
	  miMenuButton.setText(item20.getText());
  }
  
  @FXML
  void precionarItem21(ActionEvent event) {
	  miMenuButton.setText(item21.getText());
  }
  
  @FXML
  void precionarItem22(ActionEvent event) {
	  miMenuButton.setText(item22.getText());
  }
  
  @FXML
  void precionarItem23(ActionEvent event) {
	  miMenuButton.setText(item23.getText());
  }
  
  @FXML
  void precionarItem24(ActionEvent event) {
	  miMenuButton.setText(item24.getText());
  }
  
  void leerLibro() {
	/*Crea una lista observable de listas de libros
	    ObservableList<Libro> columnas = FXCollections.observableArrayList(
	        new Libro("El nombre de las rosas", "Umberto Eco", "Novela historica", 0));  //Libro 1
	    tablaDeLibros.setItems(columnas);

	    //Liga la propiedad titulo de la clase Libro
	    ColumnTitulo.setCellValueFactory(
	        new PropertyValueFactory<Libro, String>("titulo"));
	    //Liga la propiedad autor de la clase Libro
	    ColumnAutor.setCellValueFactory(
	       new PropertyValueFactory<Libro, String>("autor"));

	    //Liga la propiedad numero de reseñas de la clase Libro
	    ColumnNumReseñas.setCellValueFactory(
	       new PropertyValueFactory<Libro, Integer>("numReseña"));
	    
	    //Agregar las columnas a la tabla
	    tablaDeLibros.getColumns().addAll(ColumnTitulo, ColumnAutor, ColumnNumReseñas);*/
	  
  }
  
  @FXML
  void push(ActionEvent event) {
	  try {
	      FXMLLoader loader = new FXMLLoader(getClass().getResource("ReseñaLibro.fxml"));
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
}
