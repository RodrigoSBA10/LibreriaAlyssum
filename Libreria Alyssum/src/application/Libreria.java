
/**
 * Es la responsable de manejar la lista de libros y que impplementa seializable
 * 
 * Autor: Rodrigo Slavador
 * Fecha: 13 de octubre de 2024
 */

package application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * La clase Libreria gestiona una lista de objetos de tipo Libro.
 * Implementa la interfaz Serializable para permitir la serialización
 * de la lista de libros.
 */
public class Libreria implements Serializable {
	// Versión de la clase para asegurar la compatibilidad en la serialización.
	private static final long serialVersionUID= 1L;
	// Lista de libros que pertenecen a la librería.
	private List<Libro> libros;
	/**
     * Constructor de la clase Libreria.
     * Inicializa una nueva lista de libros vacía.
     */
	public Libreria() {
		this.libros=new ArrayList<>(); // Inicializa la lista vacía.
	}
	/**
     * Método para agregar un nuevo libro a la librería.
     * Si un libro con el mismo ISBN ya existe, simplemente se aumenta su stock.
     * 
     * @param nuevoLibro el nuevo libro a agregar o actualizar en la librería
     */
	public void agregarLibros(Libro nuevoLibro) {
		// Recorre la lista de libros en busca de un libro con el mismo ISBN.
		for(Libro libro : libros) {
			if (libro.getIsbn().equals(nuevoLibro.getIsbn())) {
				// Si el libro ya existe, aumenta su stock.
				libro.setStock(libro.getStock() + nuevoLibro.getStock());
				return;// Si el libro ya existe, aumenta su stock.
			}
		}
		// Si el libro no existe, se agrega a la lista.
		libros.add(nuevoLibro);
	}
	/**
     * Método para obtener la lista de libros de la librería.
     * Retorna una nueva lista de libros para evitar modificaciones directas
     * a la lista original.
     * 
     * @return una nueva lista que contiene los libros de la librería
     */
	public List<Libro> getLibros(){
		// Retorna una copia de la lista de libros.
		return new ArrayList<Libro>(libros);
	}
	/**
     * Método para cargar libros desde un archivo.
     * Lee un archivo serializado y carga los libros en la lista.
     * 
     * @param archivo la ruta del archivo de donde se cargan los libros
     * @throws IOException            si ocurre un error durante la lectura del archivo
     * @throws ClassNotFoundException si la clase no es encontrada durante la deserialización
     */
	public void cargarLibros(String archivo)throws IOException, ClassNotFoundException{
		 // Intenta leer el archivo de entrada y cargar los libros.
		try(FileInputStream fileOut= new FileInputStream(archivo); ObjectInputStream in = new ObjectInputStream(fileOut)){
			 // Asigna la lista de libros leída desde el archivo.
			this.libros=(List<Libro>) in.readObject();
		}	
	}

    /**
     * Método para guardar los libros de la librería en un archivo.
     * Serializa la lista de libros y la guarda en el archivo especificado.
     * 
     * @param archivo la ruta del archivo donde se guardarán los libros
     * @throws IOException si ocurre un error durante la escritura del archivo
     */
	public void guardarLibros(String archivo)throws IOException{
		// Intenta abrir el archivo de salida y guardar los libros.
		try(FileOutputStream fileout = new FileOutputStream(archivo);
				ObjectOutputStream out= new ObjectOutputStream(fileout)){
			  // Escribe la lista de libros en el archivo.	
				out.writeObject(libros);
				}
	}
}
