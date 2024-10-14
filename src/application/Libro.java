package application;

public class Libro {
	private String Titulo;
	private String Autor;
	private String ISBN;
	private float Precio_Unitario;
	private int Stock;

	public Libro(String titulo, String autor, String isbn, float precio, int stock) {
		this.Autor = autor;
		this.ISBN = isbn;
		this.Precio_Unitario = precio;
		this.Stock = stock;
	}

	public String getTitulo() {
		return Titulo;
	}

	public void setTitulo(String titulo) {
		Titulo = titulo;
	}

	public String getAutor() {
		return Autor;
	}

	public void setAutor(String autor) {
		Autor = autor;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public float getPrecio_Unitario() {
		return Precio_Unitario;
	}

	public void setPrecio_Unitario(float precio_Unitario) {
		Precio_Unitario = precio_Unitario;
	}

	public int getStock() {
		return Stock;
	}

	public void setStock(int stock) {
		Stock = stock;
	}

}
