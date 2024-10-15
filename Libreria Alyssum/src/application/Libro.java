/**
 * Clase Libro.
 * Esta clase es para identificar nuestro objeto libro
 */
package application;

import java.io.Serializable;


/**
 * La clase Libro implementa Serializable para permitir la serialización de
 * objetos de esta clase.
 */
public class Libro implements  Serializable{
	// Versión de la clase para asegurar la compatibilidad en la serialización.
	private static final long serialVersioUIDiL=1L;
	// Atributos del libro.
	private String titulo;  // Título del libro
	private String autor;   // Autor del libro
	private String isbn;	// ISBN del libro
	private String editorial;	 // Editorial del libro
	private String categoria;	// Categoría a la que pertenece el libro
	private String genero;	// Género literario del libro
	private int anioPublicacion;  // Año de publicación del libro
	private double precio;	// Precio del libro
	private int stock;	// Cantidad de unidades disponibles en stock
	private String descripcion; // Descripción breve del contenido del libro
	
	 /**
     * Constructor de la clase Libro.
     * Inicializa un nuevo libro con la información proporcionada.
     * 
     * @param titulo          el título del libro
     * @param autor           el autor del libro
     * @param isbn            el ISBN del libro
     * @param editorial       la editorial del libro
     * @param categoria       la categoría del libro
     * @param genero          el género del libro
     * @param anioPublicacion el año de publicación del libro
     * @param precio          el precio del libro
     * @param stock           la cantidad de unidades en stock
     * @param descripcion     una breve descripción del libro
     */
	public Libro(String titulo, String autor, String isbn, String editorial, String categroia, String genero,
			int anioPublicaion, double precio, int stock, String descripcion) {
		this.titulo = titulo;	// Asigna el título del libro
		this.autor = autor;	// Asigna el autor del libro
		this.isbn = isbn;	// Asigna el ISBN del libro
		this.editorial = editorial; // Asigna la editorial del libro
		this.categoria = categroia;  // Asigna la categoría del libro
		this.genero = genero;  // Asigna el género del libro
		this.anioPublicacion = anioPublicaion; // Asigna el año de publicación del libro
		this.precio = precio; // Asigna el precio del libro
		this.stock = stock; // Asigna la cantidad de unidades disponibles en stock
		this.descripcion = descripcion; // Asigna una breve descripción del libro
	}
	 // Métodos getter y setter para acceder y modificar los atributos del libro.

	/**
     * Obtiene el título del libro.
     * 
     * @return el título del libro
     */
	public String getTitulo() {
		return titulo;
	}
	/**
     * Establece el título del libro.
     * 
     * @param titulo el nuevo título del libro
     */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	  /**
     * Obtiene el autor del libro.
     * 
     * @return el autor del libro
     */
	public String getAutor() {
		return autor;
	}
	 /**
     * Establece el autor del libro.
     * 
     * @param autor el nuevo autor del libro
     */
	public void setAutor(String autor) {
		this.autor = autor;
	}
	 /**
     * Obtiene el ISBN del libro.
     * 
     * @return el ISBN del libro
     */
	public String getIsbn() {
		return isbn;
	}

    /**
     * Establece el ISBN del libro.
     * 
     * @param isbn el nuevo ISBN del libro
     */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	/**
     * Obtiene la editorial del libro.
     * 
     * @return la editorial del libro
     */
	public String getEditorial() {
		return editorial;
	}
	/**
     * Establece la editorial del libro.
     * 
     * @param editorial la nueva editorial del libro
     */
	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}
	/**
     * Obtiene la categoría del libro.
     * 
     * @return la categoría del libro
     */
	public String getCategoria() {
		return categoria;
	}
	/**
     * Establece la categoría del libro.
     * 
     * @param categoria la nueva categoría del libro
     */
	public void setCategoria(String categroia) {
		this.categoria = categroia;
	}

    /**
     * Obtiene el género del libro.
     * 
     * @return el género del libro
     */
	public String getGenero() {
		return genero;
	}
	/**
     * Establece el género del libro.
     * 
     * @param genero el nuevo género del libro
     */
	public void setGenero(String genero) {
		this.genero = genero;
	}
	 /**
     * Obtiene el año de publicación del libro.
     * 
     * @return el año de publicación del libro
     */
	public int getAnioPublicacion() {
		return anioPublicacion;
	}
	/**
     * Establece el año de publicación del libro.
     * 
     * @param anioPublicacion el nuevo año de publicación del libro
     */
	public void setAnioPublicaion(int anioPublicaion) {
		this.anioPublicacion = anioPublicaion;
	}
	/**
     * Obtiene el precio del libro.
     * 
     * @return el precio del libro
     */
	public double getPrecio() {
		return precio;
	}
	/**
     * Establece el precio del libro.
     * 
     * @param precio el nuevo precio del libro
     */
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	/**
     * Obtiene la cantidad de unidades en stock del libro.
     * 
     * @return el stock del libro
     */
	public int getStock() {
		return stock;
	}
	/**
     * Establece la cantidad de unidades en stock del libro.
     * 
     * @param stock la nueva cantidad de stock del libro
     */
	public void setStock(int stock) {
		this.stock = stock;
	}
	/**
     * Obtiene la descripción del libro.
     * 
     * @return la descripción del libro
     */
	public String getDescripcion() {
		return descripcion;
	}
	/**
     * Establece la descripción del libro.
     * 
     * @param descripcion la nueva descripción del libro
     */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
