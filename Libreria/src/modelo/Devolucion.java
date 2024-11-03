package modelo;

public class Devolucion {
	private int id;
	private int numTicket;
	private String nombre;
	private String titulo;
	private String fecha;
	private String motivo;
	private String detalle;
	private String cambio;

	public Devolucion(int id, int num, String name, String t, String f, String m, String d, String c) {
		this.id = id;
		numTicket = num;
		nombre = name;
		titulo = t;
		fecha = f;
		motivo = m;
		detalle = d;
		cambio = c;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumTicket() {
		return numTicket;
	}

	public void setNumTicket(int numTicket) {
		this.numTicket = numTicket;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public String getCambio() {
		return cambio;
	}

	public void setCambio(String cambio) {
		this.cambio = cambio;
	}
	
	public boolean isNumero(int numero) {
		return this.numTicket == numero;
	}

}
