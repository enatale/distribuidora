package entidades;

import java.util.*;

public class Pedidos {
	
	private int numero_pedido;
	private Date fecha_pedido;
	private Date fecha_cancelacion;
	private Persona cliente;
	private Estado_pedido estado;
	private ArrayList<Linea_pedido> lineas;
	
	public ArrayList<Linea_pedido> getLineas() {
		return lineas;
	}
	public void setLineas(ArrayList<Linea_pedido> lineas) {
		this.lineas = lineas;
	} 
	
	public Estado_pedido getEstado() {
		return estado;
	}
	public void setEstado(Estado_pedido estado) {
		this.estado = estado;
	}
	public Persona getCliente() {
		return cliente;
	}
	public void setCliente(Persona cliente) {
		this.cliente = cliente;
	}
	public int getNumero_pedido() {
		return numero_pedido;
	}
	public void setNumero_pedido(int numero_pedido) {
		this.numero_pedido = numero_pedido;
	}
	public Date getFecha_pedido() {
		return fecha_pedido;
	}
	public void setFecha_pedido(Date fecha_pedido) {
		this.fecha_pedido = fecha_pedido;
	}
	public Date getFecha_cancelacion() {
		return fecha_cancelacion;
	}
	public void setFecha_cancelacion(Date fecha_cancelacion) {
		this.fecha_cancelacion = fecha_cancelacion;
	}
	
	
	
}
