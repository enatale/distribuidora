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
	
	public ArrayList<Integer> getPedidosPendientes(Cliente cliente) throws ApplicationException{
		return dped.getNrosPedidosPendientes(cliente.getDni());
	}
}
