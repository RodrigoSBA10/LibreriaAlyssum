package application;

public class Tabla_venta {
	private int no;
	private String producto;
	private String codigo;
	private float precio;
	private int cantidad;
	private float total;
	
	public Tabla_venta(String pro,String co, float pre, int cant) {
		this.producto = pro;
		this.codigo = co;
		this.precio = pre;
		this.cantidad = cant;
		this.total = pre*cant;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

}
