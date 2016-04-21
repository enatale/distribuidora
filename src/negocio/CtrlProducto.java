package negocio;

import appExceptions.ApplicationException;
import datos.dataProducto;
import entidades.Producto;

public class CtrlProducto {
	
	dataProducto dprod;
	
	public CtrlProducto(){
		dprod = new dataProducto();
	}
	
	
	public void agregarProducto(Producto pr) throws ApplicationException{
		dprod.agregarProducto(pr);
	}

	public Producto getByCodigo(int codigo) throws ApplicationException{
		return dprod.getByCodigo(codigo);
	}
	public void actualizarStock(Producto pr) throws ApplicationException{
		dprod.ActualizarStock(pr);
	}
	
	public void actualizarPrecio(Producto pr) throws ApplicationException{
		dprod.ActualizarPrecio(pr);
	}
	public void modificarProducto(Producto pr) throws ApplicationException{
		dprod.modificarProducto(pr);
	}
}
