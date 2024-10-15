/*
 * Clase para agregar las columnas a la tabla
 * 
 * @author Aurora Morales
 * 
 * Fecha: 14/10/2024
 * 
 * Version: 1
 * */

package application.modelo;

import application.persistencia.LibroArchivo;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class LibroModelo {
  //Declaracion de una lista de Libros
  ObservableList<Libro> listProductos = null;

  //Constructor que no recibe parametros
  public LibroModelo() {
    LibroArchivo.load();
    listProductos = LibroArchivo.getAll();
  }

  //Regresa la lista de libros
  public ObservableList<Libro> getListaLibros() {
    return listProductos;
  }

  //Columna Titulo
  public static TableColumn<Libro, String> getTituloColum() {
    TableColumn<Libro, String> codigoCol = new TableColumn<>("Titulo");
    codigoCol.setCellValueFactory(new PropertyValueFactory<Libro, String>("titulo"));
    return codigoCol;
  }

  //Columna Autor
  public static TableColumn<Libro, String> getAutorColum() {
    TableColumn<Libro, String> nombreCol = new TableColumn<>("Autor");
    nombreCol.setCellValueFactory(new PropertyValueFactory<Libro, String>("autor"));
    return nombreCol;
  }

  //Columna Genero
  public static TableColumn<Libro, String> getGeneroColum(){
    TableColumn<Libro, String> precioCol = new TableColumn<>("Genero");
    precioCol.setCellValueFactory(new PropertyValueFactory<Libro, String>("Genero"));
    return precioCol;
  }
  
  //Columna Numero de reseñas
  public static TableColumn<Libro, String> getNumReseñaColum(){
    TableColumn<Libro, String> tipoCol = new TableColumn<>("Numero reseñas");
    tipoCol.setCellValueFactory(new PropertyValueFactory<Libro, String>("numReseña"));
    return tipoCol;
  }

  //Agrega
  public void agregar(Libro p) {
    listProductos.add(p);
  }

  //Elimina
  public void eliminar(Libro seleccionado) {
    listProductos.remove(seleccionado);
  }
}
