package entidades;

public class Estado_cliente {

	private int id_estado_cliente;
	private String descripcion;
	
	public Estado_cliente(int id, String descripcion) {
		this.setId_estado_cliente(id);
		this.setDescripcion(descripcion);
		
	}
	public int getId_estado_cliente() {
		return id_estado_cliente;
	}
	public void setId_estado_cliente(int id_estado_cliente) {
		this.id_estado_cliente = id_estado_cliente;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
