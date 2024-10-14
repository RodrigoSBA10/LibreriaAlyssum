package application;

public class Inscripcion {
	
	private String nombre;
	private String apellido;
	private String email;
	private String telefono;
	public Inscripcion() {
		super();
	}
	public Inscripcion(String nombre, String apellido, String email, String telefono) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.telefono = telefono;
	}
	private String getNombre() {
		return nombre;
	}
	private void setNombre(String nombre) {
		this.nombre = nombre;
	}
	private String getApellido() {
		return apellido;
	}
	private void setApellido(String apellido) {
		this.apellido = apellido;
	}
	private String getEmail() {
		return email;
	}
	private void setEmail(String email) {
		this.email = email;
	}
	private String getTelefono() {
		return telefono;
	}
	private void setTelefono(String telefono) {
		this.telefono = telefono;
	}
}
