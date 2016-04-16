package negocio;

import datos.dataPersona;
import entidades.Cliente;
import entidades.Empleado;
import entidades.Persona;

public class CtrlInicioSesion {

	public static Persona identificarPersona(String usuario, String contraseña) {
		// TODO Datps harcodeados de prueba
		/*if(usuario.equals("esteban")&&contraseña.equals("lalala")){
			Cliente cli = new Cliente();
			cli.setContraseña(contraseña);
			cli.setUsuario(usuario);
			return cli;
		}
		if(usuario.equals("topa")&&contraseña.equals("lalala")){
			Empleado emp = new Empleado();
			emp.setContraseña(contraseña);
			emp.setUsuario(usuario);
			return emp;
		}*/
		dataPersona dp = new dataPersona();
		return dp.getByUsuario(usuario, contraseña);
	}
	
}
