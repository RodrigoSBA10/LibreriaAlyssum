package application;

public class Tabla_venta {
	private int no;
	 private String producto;
	 private String codigo;
	 private float precioBase;
	 private float iva;
	 private float precioConIVA;
	 private float subtotal;
	 private int cantidad;
	
	 public Tabla_venta(String producto, String codigo, float precioConIVA, int cantidad) {
	        this.producto = producto;
	        this.codigo = codigo;
	        this.precioConIVA = precioConIVA;
	        this.cantidad = cantidad;
	        this.precioBase = precioConIVA / 1.16f; // Calcula el precio base
	        this.iva = this.precioConIVA - this.precioBase; // Calcula el IVA
	        this.subtotal = this.precioConIVA * this.cantidad;
	    }

	public float getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
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

	public float getPrecioBase() {
		return precioBase;
	}

	public void setPrecioBase(float precioBase) {
		this.precioBase = precioBase;
	}

	public float getIva() {
		return iva;
	}

	public void setIva(float iva) {
		this.iva = iva;
	}

	public float getPrecioConIVA() {
		return precioConIVA;
	}

	public void setPrecioConIVA(float precioConIVA) {
		this.precioConIVA = precioConIVA;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public float getTotal() {
        return precioConIVA * cantidad; // Total con IVA
    }


}
