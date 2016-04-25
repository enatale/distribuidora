package negocio;

import java.util.Date;

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
	public void actualizarStock(Producto pr,int cantidad) throws ApplicationException{
		dprod.actualizarStock(pr,cantidad);
	}
	
	public void actualizarPrecio(Producto pr,float importe,Date fecha_desde) throws ApplicationException{
		dprod.actualizarPrecio(pr, importe, fecha_desde);
	}
	public void modificarProducto(Producto pr, String descripcion,int stock, java.util.Date fecha, float importe) throws ApplicationException{
		dprod.modificarProducto(pr,descripcion,stock, fecha, importe);
	}

	
}
