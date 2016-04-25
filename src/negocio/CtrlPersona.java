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
	
	public void agregarCliente(Cliente cli) throws ApplicationException{
		dper.agregarCliente(cli);
	}
	public int buscarUltCodCli() throws ApplicationException{
		return dper.buscarUltCodCli();
	}
	public boolean verificarUser(String usuario) throws ApplicationException{
		return dper.verificarUser(usuario);
	}
	public boolean verificarDni(int dni) throws ApplicationException{
		return dper.verificarDni(dni);
	}
}

