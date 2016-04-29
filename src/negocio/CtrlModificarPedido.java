package negocio;

import java.util.ArrayList;

import appExceptions.ApplicationException;
import datos.dataPedidos;
import entidades.Pedidos;

public class CtrlModificarPedido {
	
	dataPedidos dped;
	
	public CtrlModificarPedido(){
		dped = new dataPedidos();
	}
	
	public ArrayList<Integer> getNrosPedidosPendientes(int dni) throws ApplicationException{
		return dped.getNrosPedidosPendientes(dni);
	}
	public Pedidos getByNro(int nroPedido) throws ApplicationException{
		
		return dped.getByNroPedido(nroPedido);
	}

	public void borrarLinea(int nroPedido, int codProducto, int cantidad) throws ApplicationException {
		Pedidos ped=dped.getByNroPedido(nroPedido);
		if(ped.getEstado().getId_estado_pedido()==1){
			dped.deleteLinea(nroPedido,codProducto, cantidad);
		} else throw new ApplicationException("El estado del pedido que quiere modificar no es \"pendiente\"", null);
	}

	public void cancelarPedido(int nroPedido) throws ApplicationException {
		Pedidos ped=dped.getByNroPedido(nroPedido);
		if(ped.getEstado().getId_estado_pedido()==1){
			dped.cancelarPedido(nroPedido);
		} else throw new ApplicationException("El estado del pedido que quiere modificar no es \"pendiente\"", null);
		
	}

	public void modificarLinea(int nroPedido, int codProducto, int cantidad) throws ApplicationException {
		dped.updateLinea(nroPedido,codProducto,cantidad);
	}

	public void agregarLinea(int nroPedido, int codigo, int cantidad) throws ApplicationException {
		dped.insertLinea(nroPedido, codigo, cantidad);		
	}
}
