package entidades;

public class Estado_pedido {

	private int id_estado_pedido;
	private String descripcion;
	
	public Estado_pedido(int id, String desc) {
		this.setDescripcion(desc);
		this.setId_estado_pedido(id);
	}
	public int getId_estado_pedido() {
		return id_estado_pedido;
	}
	public void setId_estado_pedido(int id_estado_pedido) {
		this.id_estado_pedido = id_estado_pedido;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
}
