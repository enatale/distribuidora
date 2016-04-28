package negocio;

import java.util.ArrayList;
import java.util.Date;

import appExceptions.ApplicationException;
import datos.*;
import entidades.Cliente;
import entidades.Linea_pedido;
import entidades.Pedidos;
import entidades.Persona;
import entidades.Producto;

public class CtrlPedidos {
	
	dataProducto dprod;
	dataPedidos dped;
	
	public CtrlPedidos(){
		dprod = new dataProducto();
		dped = new dataPedidos();
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

	public int obtenerStock(Producto producto) throws ApplicationException{
		int stock = 0;
		try{
			stock=dprod.getStock(producto.getCodProducto());
		} catch(NullPointerException e) {
			throw new ApplicationException("No se encontró producto con el código ingresado", e.getCause());
		}
		return stock;		
	}
	
	public void aumentarStock(Producto producto, int cantidad) throws ApplicationException {
		dprod.aumentarStock(producto.getCodProducto(),cantidad);
		
	}

	public void confirmarPedido(Pedidos pedido, Cliente cliente) throws ApplicationException {
		String mensaje ="";
		for (Linea_pedido lp : pedido.getLineas()) {
			int stock = dprod.getStock(lp.getProducto().getCodProducto());
			if(stock<lp.getCantidad()){
				mensaje+="<br> Se ha agotado el stock del producto "+lp.getProducto().getCodProducto()+" "+lp.getProducto().getDescripcion();
				mensaje+=" Eliminar la linea de pedido correspondiente para poder confirmar el pedido";
			}
		}
		if(mensaje.equals("")){
			dped.registrarPedido(pedido,cliente.getDni());
		} else throw new ApplicationException(mensaje, null);
	}
	
	public Pedidos getByNroPedido(int nroPedido) throws ApplicationException{
		return dped.getByNroPedido(nroPedido);
	}
	
	
	public void actualizarEstadoPedido(int numPed,String estado) throws ApplicationException{

		 dped.actualizarEstadoPedido(numPed, estado);
	}

	public ArrayList<Producto> getAll(int inicio, int cant_por_pagina, Date fecha) throws ApplicationException {
		return dprod.getAll(inicio, cant_por_pagina, fecha);
	}

	public ArrayList<Pedidos> buscarPedidosFecha(Date fInicio, Date fFin,
			String estado) throws ApplicationException {
		return dped.buscarPedidosFecha(fInicio,fFin,estado);
	}

}
