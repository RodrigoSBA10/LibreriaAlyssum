package application;

public class Descuento {
	private String codigo;
	private String descuento;
	private String fecha_Validesini;
	private String fecha_Validesfin;

	public Descuento(String codigo, String descuento, String fechaini, String fechafin) {
		this.codigo = codigo;
		this.descuento = descuento;
		this.fecha_Validesini = fechaini;
		this.fecha_Validesfin = fechafin;
		
		
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescuento() {
		return descuento;
	}

	public void setDescuento(String descuento) {
		this.descuento = descuento;
	}

	public String getFecha_Validesini() {
		return fecha_Validesini;
	}

	public String getFecha_Validesfin() {
		return fecha_Validesfin;
	}

	public void setFecha_Validesfin(String fecha_Validesfin) {
		this.fecha_Validesfin = fecha_Validesfin;
	}

	public void setFecha_Validesini(String fecha_Valides) {
		this.fecha_Validesini = fecha_Valides;
	}

}
