package application;

public class Descuento {
	private String codigo;
	private float descuento;
	private String fecha_Valides;

	public Descuento(String codigo, float descuento, String fecha) {
		this.codigo = codigo;
		this.descuento = descuento;
		this.fecha_Valides = fecha;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public float getDescuento() {
		return descuento;
	}

	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}

	public String getFecha_Valides() {
		return fecha_Valides;
	}

	public void setFecha_Valides(String fecha_Valides) {
		this.fecha_Valides = fecha_Valides;
	}

}
