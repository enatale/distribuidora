package entidades;

public class Cliente extends Persona {

	private int codCliente;
	private int CUIT;
	private Estado_cliente estado;
	
	public int getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(int codCliente) {
		this.codCliente = codCliente;
	}
	public int getCUIT() {
		return CUIT;
	}
	public void setCUIT(int cUIT) {
		CUIT = cUIT;
	}
	public Estado_cliente getEstado() {
		return estado;
	}
	public void setEstado(Estado_cliente estado) {
		this.estado = estado;
	}
	
 
}
