package negocio;

import datos.dataPersona;
import entidades.Cliente;
import entidades.Empleado;
import entidades.Persona;

public class CtrlInicioSesion {

	public static Persona identificarPersona(String usuario, String contrase�a) {
		// TODO Datps harcodeados de prueba
		/*if(usuario.equals("esteban")&&contrase�a.equals("lalala")){
			Cliente cli = new Cliente();
			cli.setContrase�a(contrase�a);
			cli.setUsuario(usuario);
			return cli;
		}
		if(usuario.equals("topa")&&contrase�a.equals("lalala")){
			Empleado emp = new Empleado();
			emp.setContrase�a(contrase�a);
			emp.setUsuario(usuario);
			return emp;
		}*/
		dataPersona dp = new dataPersona();
		return dp.getByUsuario(usuario, contrase�a);
	}
	
}
