package modelo;

public class Motivo {
	private int idMotivo;
	private String nombre;

	public Motivo(int idMotivo, String nombre) {
		this.idMotivo = idMotivo;
		this.nombre = nombre;
	}

	public int getIdMotivo() {
		return idMotivo;
	}

	public void setIdMotivo(int idMotivo) {
		this.idMotivo = idMotivo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public String toString() {
		return nombre;
	}
}
