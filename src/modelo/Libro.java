/*
 * Clase Libro para generar libros
 * 
 * @autor Aurora Morales
 * 
 * Fecha: 11/10/2024
 * 
 * Version: 2
 * 
 * */

package modelo;

/*Libro class
 * Return nada*/
public class Libro {
  //Constantes
  String titulo;
  String autor;
  String genero;
  int numReseña;
  
  
  public Libro() {
	  
  }

  /*
  * Constructor para recibir la informacion del libro...
  */
  public Libro(String tit, String aut, String gen, int num) {
    this.titulo = tit;
    this.autor = aut;
    this.genero = gen;
    this.numReseña = num;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getAutor() {
    return autor;
  }

  public void setAutor(String autor) {
    this.autor = autor;
  }

  public String getGenero() {
    return genero;
  }

  public void setGenero(String genero) {
    this.genero = genero;
  }

  public int getNumeroReseña() {
    return numReseña;
  }

  public void setNumeroReseña(int numero_Reseña) {
    numReseña += numero_Reseña;
  }
  
  @Override
  public String toString() {
	  return this.titulo + "||" + this.autor + "||" + this.genero + "||" + this.numReseña;
  }
}