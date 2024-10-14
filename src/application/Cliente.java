package application;

/**
 * @author Jesus Alfredo
 * Esta es la clase cliente
 */
public class Cliente {
	private String Nombre;
	private String Correo;
	private String Numero_Telefonico;
    
	public Cliente(String nombre) {
		this.Nombre = nombre;
		this.Correo = null;
		this.Numero_Telefonico = null;
	}

	public Cliente(String nombre, String correo, String telefono) {
		this.Nombre = nombre;
		this.Correo = correo;
		this.Numero_Telefonico = telefono;
	}

	public String getNombre() {
		return Nombre;
	}
	

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getCorreo() {
		return Correo;
	}

	public void setCorreo(String correo) {
		Correo = correo;
	}

	public String getNumero_Telefonico() {
		return Numero_Telefonico;
	}

	public void setNumero_Telefonico(String numero_Telefonico) {
		Numero_Telefonico = numero_Telefonico;
	}

}
