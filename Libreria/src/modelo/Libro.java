package modelo;

public class Libro {
	private int idLibro;
	private int idAutor;
	private int idEditorial;
	private int idGenero;
	private String titulo;
	private double precio;
	private int cantidad;

	public Libro(int idLibro, int idAutor, int idEditorial, int idGenero, String titulo, double precio, int cantidad) {
		this.idLibro = idLibro;
		this.idAutor = idAutor;
		this.idEditorial = idEditorial;
		this.idGenero = idGenero;
		this.titulo = titulo;
		this.precio = precio;
		this.cantidad = cantidad;
	}

	public int getIdLibro() {
		return idLibro;
	}

	public void setIdLibro(int idLibro) {
		this.idLibro = idLibro;
	}

	public int getIdAutor() {
		return idAutor;
	}

	public void setIdAutor(int idAutor) {
		this.idAutor = idAutor;
	}

	public int getIdEditorial() {
		return idEditorial;
	}

	public void setIdEditorial(int idEditorial) {
		this.idEditorial = idEditorial;
	}

	public int getIdGenero() {
		return idGenero;
	}

	public void setIdGenero(int idGenero) {
		this.idGenero = idGenero;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public void imprimirCantidad() {
		System.out.println("El libro: " + this.titulo + " tiene es stoke: " + this.cantidad);
	}
}
