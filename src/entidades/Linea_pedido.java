package entidades;

public class Linea_pedido {
	
	private int cantidad;
	private Producto producto;
	
	public Linea_pedido(Producto producto, int cantidad) {
		this.setCantidad(cantidad);
		this.setProducto(producto);
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	
}
