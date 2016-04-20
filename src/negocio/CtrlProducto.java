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

}
