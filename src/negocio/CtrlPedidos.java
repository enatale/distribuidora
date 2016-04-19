package negocio;

import java.util.ArrayList;

import appExceptions.ApplicationException;
import datos.*;
import entidades.Cliente;
import entidades.Persona;
import entidades.Producto;

public class CtrlPedidos {
	
	dataPersona dper;
	dataProducto dped;
	
	public CtrlPedidos(){
		dper = new dataPersona();
		dped = new dataProducto();
	}
	
	public Persona identificarPersona(String usuario, String contraseña) throws ApplicationException {
		Persona p = dper.getByUsuario(usuario, contraseña);
		if(p instanceof Cliente && ((Cliente) p).getEstado().getDescripcion().equals("Pendiente")){
			throw new ApplicationException("Su cuenta aún no ha sido aprobada para utilizar el sistema", null);
		}
		return p;
	}


	public ArrayList<Producto> getAll(int desde, int hasta) throws ApplicationException{
		return new dataProducto().getAll(desde, hasta);
	}

	public int getCantProductos() throws ApplicationException {
		return dped.getCantProductos();
	}
}
