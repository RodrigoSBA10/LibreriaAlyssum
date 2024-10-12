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

package application;

/*
 * Libro class
 * @return 0
 * */
public class Libro {
	
	//Contantes
	
	String titulo;
	String autor;
	String genero;
	int numReseña = 0;
	
	/*
	 * Constructor para recibir la informacion del libro
	 * 
	 * @param tit  titulo del libro
	 * @param aut  nombre del autor
	 * @param gen  genero del libro
	 * @param num  numero de reseñas
	 * @return 0
	 * */
	public Libro(String tit, String aut, String gen, int num) {
		this.titulo = tit;
		this.autor = aut;
		this.genero = gen;
		this.numReseña = num;
	}
	
	/*
	 * Metodo para obtener el titulo del libro
	 * 
	 * */
	public String getTitulo() {
		return titulo;
	}
	
	/*
	 * 
	 * */
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
}
