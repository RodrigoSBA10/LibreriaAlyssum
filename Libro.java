package application;

import java.io.Serializable;

public class Libro implements  Serializable{
	private static final long serialVersioUIDiL=1L;
	private String titulo;
	private String autor;
	private String isbn;
	private String editorial;
	private String categoria;
	private String genero;
	private int anioPublicacion;
	private double precio;
	private int stock;
	private String descripcion;
	
	public Libro(String titulo, String autor, String isbn, String editorial, String categroia, String genero,
			int anioPublicaion, double precio, int stock, String descripcion) {
		this.titulo = titulo;
		this.autor = autor;
		this.isbn = isbn;
		this.editorial = editorial;
		this.categoria = categroia;
		this.genero = genero;
		this.anioPublicacion = anioPublicaion;
		this.precio = precio;
		this.stock = stock;
		this.descripcion = descripcion;
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

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categroia) {
		this.categoria = categroia;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getAnioPublicacion() {
		return anioPublicacion;
	}

	public void setAnioPublicaion(int anioPublicaion) {
		this.anioPublicacion = anioPublicaion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
