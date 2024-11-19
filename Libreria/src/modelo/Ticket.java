package modelo;

public class Ticket {
	private int idTicket;
	private int idLibro;
	private int idCliente;
	private int numero;
	private int cantidad;
	private String fecha;
	private double monto;
	
	public Ticket(int idTicket, int idLibro, int idCliente, int numero, int cantidad, String fecha, double monto) {
		this.idTicket = idTicket;
		this.idLibro = idLibro;
		this.idCliente = idCliente;
		this.numero = numero;
		this.cantidad = cantidad;
		this.fecha = fecha;
		this.monto = monto;
	}

	public int getIdTicket() {
		return idTicket;
	}

	public void setIdTicket(int idTicket) {
		this.idTicket = idTicket;
	}

	public int getIdLibro() {
		return idLibro;
	}

	public void setIdLibro(int idLibro) {
		this.idLibro = idLibro;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}
	
	public boolean isNumber(int numero) {
		return this.numero == numero;
	}
}
