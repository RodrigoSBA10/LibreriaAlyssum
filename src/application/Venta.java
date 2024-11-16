package application;

import java.util.List;

public class Venta {
	private List<Libro> lista_libros;
	private int cantidad;
	private float subtotal;

	public Venta(List<Libro> lista, int cantidad, float subtotal) {
		this.cantidad = cantidad;
		this.lista_libros = lista;
		this.subtotal = subtotal;
	}

	public List<Libro> getLista_libros() {
		return lista_libros;
	}

	public void setLista_libros(List<Libro> lista_libros) {
		this.lista_libros = lista_libros;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public float getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}
}
