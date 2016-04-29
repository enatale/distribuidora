package ui;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import appExceptions.ApplicationException;
import entidades.Cliente;
import entidades.Empleado;
import entidades.Persona;
import negocio.CtrlPersona;

/**
 * Servlet implementation class IniciarSesion
 */
@WebServlet("/iniciarSesion")
public class IniciarSesion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IniciarSesion() {
        super();
        // 
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usuario = ((String)request.getParameter("txtUsuario")).trim();
		String contraseña = ((String)request.getParameter("txtPass")).trim();
		String mensaje ="";
		if(usuario.equals("")){
			mensaje+="El campo nombre de usuario no puede estar en blanco\n";
		}
		if (contraseña.equals("")) {
			mensaje+="El campo contraseña no puede estar en blanco";
		}
		if (!mensaje.equals("")){
			request.setAttribute("mensaje", mensaje);
			request.getRequestDispatcher("iniciarSesion.jsp").forward(request, response);
		} else{
			Persona persona;
			try {
				persona = new CtrlPersona().identificarPersona(usuario,contraseña);
				if(persona==null){
					request.setAttribute("mensaje", "Usuario y/o contraseña incorrectos");
					request.getRequestDispatcher("iniciarSesion.jsp").forward(request, response);
				}
				if(persona instanceof Cliente){
					request.getSession().setAttribute("usuario", persona);
					response.sendRedirect("index.jsp");
				}
				if (persona instanceof Empleado) {
					request.getSession().setAttribute("usuario", persona);
					response.sendRedirect("inicioEmp.jsp");
				}
			} catch (ApplicationException e) {
				request.setAttribute("mensaje", e.getMessage());
				request.getRequestDispatcher("iniciarSesion.jsp").forward(request, response);
			}
			
		}
	}

}
