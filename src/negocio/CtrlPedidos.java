package negocio;

import java.util.ArrayList;

import appExceptions.ApplicationException;
import datos.*;
import entidades.Cliente;
import entidades.Pedidos;
import entidades.Persona;
import entidades.Producto;

public class CtrlPedidos {
	
	dataPersona dper;
	dataProducto dprod;
	dataPedidos dped;
	
	public CtrlPedidos(){
		dper = new dataPersona();
		dprod = new dataProducto();
		dped = new dataPedidos();
	}
	
	public Persona identificarPersona(String usuario, String contraseña) throws ApplicationException {
		Persona p = dper.getByUsuario(usuario, contraseña);
		if(p instanceof Cliente && ((Cliente) p).getEstado().getDescripcion().equals("Pendiente")){
			throw new ApplicationException("Su cuenta aún no ha sido aprobada para utilizar el sistema", null);
		}
		return p;
	}

	public ArrayList<Producto> getAll(int desde, int hasta) throws ApplicationException{
		return dprod.getAll(desde, hasta);
	}

	public int getCantProductos() throws ApplicationException {
		return dprod.getCantProductos();
	}

	public ArrayList<Producto> getByDescripcion(String descripcion,int desde,int hasta) throws ApplicationException {
		return dprod.getByDescripcion(descripcion,desde,hasta);
	}

	public Producto getByCodigo(int codigo) throws ApplicationException {
		return dprod.getByCodigo(codigo);
	}

	public boolean cantidadSuficiente(Producto producto, int cantidad) throws ApplicationException {
		try{
			int stock=dprod.getStock(producto.getCodProducto());
			if(stock>=cantidad){
				dprod.descontarStock(cantidad,producto.getCodProducto());
				return true;
			} else{
				return false;
			}
		} catch(NullPointerException e) {
			throw new ApplicationException("No se encontró producto con el código ingresado", e.getCause());
		}
	}

	public void aumentarStock(Producto producto, int cantidad) throws ApplicationException {
		dprod.aumentarStock(producto.getCodProducto(),cantidad);
		
	}

	public void confirmarPedido(Pedidos pedido, Cliente cliente) throws ApplicationException {
		dped.registrarPedido(pedido,cliente.getDni());
	}
}
