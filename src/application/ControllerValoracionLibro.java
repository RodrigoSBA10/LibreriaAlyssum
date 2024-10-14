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


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


/*
 * Cuerpo de la clase ControllerValoracionLibro
 * @author Aurora Morales
 * */
public class ControllerValoracionLibro {

  @FXML
  private TableView<Libro> tablaDeLibros; //Tabla de libros
  
  @FXML
  private TableColumn<Libro, String> ColumnAutor;  //Columana de autores

  @FXML
  private TableColumn<Libro, Integer> ColumnNumReseñas;  //Columna de numero de reseñas

  @FXML
  private TableColumn<Libro, String> ColumnTitulo;  //Columna de titulos

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
  private MenuButton miMenuButton; //
  
  @FXML
  void miMenuButton(ActionEvent event) {  
  }

  @FXML
  void precionarItem1(ActionEvent event) {
    //Crea una lista observable de listas de libros
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
    tablaDeLibros.getColumns().addAll(ColumnTitulo, ColumnAutor, ColumnNumReseñas);
  }
}
