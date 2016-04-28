package negocio;

import java.util.ArrayList;

import appExceptions.ApplicationException;
import datos.dataPersona;
import entidades.Cliente;
import entidades.Persona;

public class CtrlPersona {
	dataPersona dper;
	
	public CtrlPersona(){
		dper=new dataPersona();
	}
	
	public Persona identificarPersona(String usuario, String contraseña) throws ApplicationException {
		Persona p = dper.getByUsuario(usuario, contraseña);
		if(p instanceof Cliente && ((Cliente) p).getEstado().getDescripcion().equals("Pendiente")){
			throw new ApplicationException("Su cuenta aún no ha sido aprobada para utilizar el sistema", null);
		}
		return p;
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

