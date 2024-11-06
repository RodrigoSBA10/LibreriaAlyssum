/*
 * Clase Reseña para generar reseñas
 * 
 * @autor Aurora Morales
 * 
 * Fecha: 26/10/2024
 * 
 * Version: 2
 * 
 * */

package modelo;

public class Reseña {
	
	String descripcion;
	int valoracion;
	
	public Reseña(int valoracion, String descripcion) {
		this.descripcion = descripcion;
		this.valoracion = valoracion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getValoracion() {
		return valoracion;
	}

	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}
	
	public String toString() {
		return this.valoracion + "||" + this.descripcion + "\n";
	}
}
