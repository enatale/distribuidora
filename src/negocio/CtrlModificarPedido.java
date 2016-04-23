package negocio;

import java.util.ArrayList;

import appExceptions.ApplicationException;
import datos.dataPedidos;
import entidades.Cliente;
import entidades.Pedidos;

public class CtrlModificarPedido {
	
	dataPedidos dped;
	
	public CtrlModificarPedido(){
		dped = new dataPedidos();
	}
	
	public ArrayList<Integer> getNrosPedidosPendientes(Cliente cliente) throws ApplicationException{
		return dped.getNrosPedidosPendientes(cliente.getDni());
	}
	public Pedidos getByNro(int nroPedido) throws ApplicationException{
		
		return dped.getByNroPedido(nroPedido);
	}

	public void borrarLinea(int nroPedido, int codProducto, int cantidad) throws ApplicationException {
		dped.deleteLinea(nroPedido,codProducto, cantidad);		
	}

	public void cancelarPedido(int nroPedido) throws ApplicationException {
		dped.cancelarPedido(nroPedido);
		
	}

	public void modificarLinea(int nroPedido, int codProducto, int cantidad) throws ApplicationException {
		dped.updateLinea(nroPedido,codProducto,cantidad);
	}

	public void agregarLinea(int nroPedido, int codigo, int cantidad) throws ApplicationException {
		dped.insertLinea(nroPedido, codigo, cantidad);		
	}
}
