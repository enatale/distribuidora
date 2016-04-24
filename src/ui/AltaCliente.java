package ui;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.CtrlPersona;
import appExceptions.ApplicationException;
import entidades.Cliente;
import entidades.Empleado;

/**
 * Servlet implementation class altaCliente
 */
@WebServlet("/AltaCliente")
public class AltaCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AltaCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	Persona persona = (Persona) request.getSession().getAttribute("usuario");
		Cliente cli;
		CtrlPersona ctrl = new CtrlPersona();
		String nombre = ((String)request.getParameter("txtNombre")).trim();
		String apellido = ((String)request.getParameter("txtApellido")).trim();
		String direccion =((String)request.getParameter("txtDireccion")).trim();
		String mail = ((String)request.getParameter("txtMail")).trim();
		String usuario = ((String)request.getParameter("txtUsuario")).trim();
		String pass = ((String)request.getParameter("txtPass")).trim();
		
		String mensaje ="";
		try{
				if(nombre.equals("")){
					mensaje+="El campo nombre no puede estar en blanco\n";
				}
				if(apellido.equals("")){
					mensaje+="El campo apellido no puede estar en blanco\n";
				}
				if(direccion.equals("")){
					mensaje+="El campo direccion no puede estar en blanco\n";
				}
				if(mail.equals("")){
					mensaje+="El campo mail no puede estar en blanco\n";
				}
				if(usuario.equals("")){
					mensaje+="El campo usuario no puede estar en blanco\n";
				}
				if(pass.equals("")){
					mensaje+="El campo password no puede estar en blanco\n";
				}
				if (request.getParameter("txtDni").equals("")) {
					mensaje+="El campo dni no puede estar en blanco\n";
				}
				if (request.getParameter("txtTelefono").equals("")) { 
					mensaje+="El campo telefono no puede estar en blanco\n";
				}
				if(ctrl.verificarDni(Integer.parseInt(request.getParameter("txtDni")))){
					mensaje+="Existe cliente registrado con el dni ingresado\n";
				}
				if (request.getParameter("txtCUIT").equals("")) {
					mensaje+="El campo CUIT no puede estar en blanco\n";
				}
				if(ctrl.verificarUser(usuario)){
					mensaje+="El nombre de usuario ya existe, intente con otro\n";
				}
					if (!mensaje.equals("")){
						request.setAttribute("mensaje", mensaje);
						request.getRequestDispatcher("registro.jsp").forward(request, response);
					} else{
						
						int dni = Integer.parseInt(request.getParameter("txtDni"));
						int tel = Integer.parseInt(request.getParameter("txtTelefono"));
						int cuit = Integer.parseInt(request.getParameter("txtCUIT"));
						cli = new Cliente();
						
						int codCliente = ctrl.buscarUltCodCli();
						cli.setNombre(nombre);
						cli.setApellido(apellido);
						cli.setDni(dni);
						cli.setDireccion(direccion);
						cli.setMail(mail);
						cli.setTelefono(tel);
						cli.setCUIT(cuit);
						cli.setUsuario(usuario);
						cli.setContraseña(pass);
						cli.setCodCliente(codCliente);
						ctrl.agregarCliente(cli);
					    request.setAttribute("mensajeConfirmacion", "Cliente fue registrado con exito");
					    request.getRequestDispatcher("altaProducto.jsp").forward(request, response);
					
					}
	
		}catch (NumberFormatException e) {
			String msj="";
			if(!esEntero(request.getParameter("txtDni"))) msj+="El código de producto debe ser un número entero. \n";
			if(!esEntero(request.getParameter("txtTelefono"))) msj+="La cantidad debe ser un número entero. \n";
			if(!esEntero(request.getParameter("txtCUIT"))) msj+="La cantidad debe ser un número entero. \n";
			request.setAttribute("mensaje", msj);
			request.getRequestDispatcher("registro.jsp").forward(request, response);
		} catch (ApplicationException e){
			request.setAttribute("mensaje", e.getMessage());
			request.getRequestDispatcher("registro.jsp").forward(request, response);
		}

	}

	private boolean esEntero(String cadena){
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException e2) {
			return false;
		}
	}
}
