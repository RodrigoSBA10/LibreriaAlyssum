package application;

public class Factura {
	private int Numero_Factura;
	private String Fecha_Emicion;
	private float SubTotal;
	private float Impuestos;
	private float Total;

	public Factura(int numerofactura, String fecha, float subtotal, float impuesto, float total) {
		this.Fecha_Emicion = fecha;
		this.Impuestos = impuesto;
		this.Numero_Factura = numerofactura;
		this.SubTotal = subtotal;
		this.Total = total;
	}

	public int getNumero_Factura() {
		return Numero_Factura;
	}

	public void setNumero_Factura(int numero_Factura) {
		Numero_Factura = numero_Factura;
	}

	public String getFecha_Emicion() {
		return Fecha_Emicion;
	}

	public void setFecha_Emicion(String fecha_Emicion) {
		Fecha_Emicion = fecha_Emicion;
	}

	public float getSubTotal() {
		return SubTotal;
	}

	public void setSubTotal(float subTotal) {
		SubTotal = subTotal;
	}

	public float getImpuestos() {
		return Impuestos;
	}

	public void setImpuestos(float impuestos) {
		Impuestos = impuestos;
	}

	public float getTotal() {
		return Total;
	}

	public void setTotal(float total) {
		Total = total;
	}

}
