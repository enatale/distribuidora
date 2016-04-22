package negocio;

import java.util.ArrayList;

import appExceptions.ApplicationException;
import datos.dataPersona;
import entidades.Cliente;

public class CtrlPersona {
	dataPersona dper;
	
	public CtrlPersona(){
		dper=new dataPersona();
	}
	
	public ArrayList<Cliente> getCliListadoConfirmar() throws ApplicationException{
		return dper.getCliListadoConfirmar();
	}
	
	public void actualizarEstadoCliente(String estado, int dni) throws ApplicationException{
		dper.actualizarEstadoCliente(estado,dni);
	}
}
