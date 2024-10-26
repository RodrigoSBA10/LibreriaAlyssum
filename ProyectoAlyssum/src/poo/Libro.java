package poo;


public class Libro {
	private String titulo;
	private String autor;
	private String isbn;
	private String editorial;
	private String categoria;
	private String genero;
	private String anio;
	private double precio;
	private int stock;
	private int idGenero;
	private int idCategoria;
	
	public Libro() {
		super();
	}
	public Libro(String titulo, String autor, String isbn, String editorial, int idGenero, int idCategoria, String anio, double precio, int stock) {
	    this.titulo = titulo;
	    this.autor = autor;
	    this.isbn = isbn;
	    this.editorial = editorial;
	    this.idGenero = idGenero;
	    this.idCategoria = idCategoria;
	    this.anio = anio;
	    this.precio = precio;
	    this.stock = stock;
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
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getAnio() {
		return anio;
	}
	public void setAnio(String anio) {
		this.anio = anio;
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
	public int getIdGenero() {
		return idGenero;
	}
	public void setIdGenero(int idGenero) {
		this.idGenero = idGenero;
	}
	public int getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}
	@Override
	public String toString() {
		return "Libro [titulo=" + titulo + ", autor=" + autor + ", isbn=" + isbn + ", editorial=" + editorial
				+ ", categoria=" + categoria + ", genero=" + genero + ", anio=" + anio + ", precio=" + precio
				+ ", stock=" + stock + ", idGenero=" + idGenero + ", idCategoria=" + idCategoria + "]";
	}
	
	
	
}
