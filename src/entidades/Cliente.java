package entidades;

public class Cliente extends Persona {

	private int codCliente;
	private long CUIT;
	private Estado_cliente estado;
	
	public int getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(int codCliente) {
		this.codCliente = codCliente;
	}
	public long getCUIT() {
		return CUIT;
	}
	public void setCUIT(long l) {
		CUIT = l;
	}
	public Estado_cliente getEstado() {
		return estado;
	}
	public void setEstado(Estado_cliente estado) {
		this.estado = estado;
	}
	
 
}
