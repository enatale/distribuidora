package ui;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.CtrlPersona;
import appExceptions.ApplicationException;
import entidades.Empleado;

/**
 * Servlet implementation class ListadoCliPendientes
 */
@WebServlet("/ListadoCliPendientes")
public class ListadoCliPendientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListadoCliPendientes() {
        super();
        // 
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 
		String mensaje="";
		int dniInt;
		CtrlPersona ctrl = new CtrlPersona();
		Empleado empleado = (Empleado) request.getSession().getAttribute("usuario");
		String estado = (String) request.getParameter("Estado");
		
		try {
		if(empleado!=null && empleado.getLegajo()!=0){
			
			
			if (request.getParameter("txtDni").equals("")) { mensaje+="El campo dni no puede estar en blanco";
			}
			if (estado.equals("")) {
				mensaje+="El campo estado no puede estar en blanco";
			}
			
			if (!mensaje.equals("")){
				request.setAttribute("mensaje", mensaje);
				request.getRequestDispatcher("listadoCliPendientes.jsp").forward(request, response);
			}
			else{
				dniInt=Integer.parseInt(request.getParameter("txtDni"));
				ctrl.actualizarEstadoCliente(estado,dniInt);
				response.sendRedirect("listadoCliPendientes.jsp");
			}
			
		}else{
			throw new ApplicationException("debe estar logueado como empleado para actualizar el stock", null);
		}
		}catch (NumberFormatException e) {
			String msj="";
			if(!esEntero(request.getParameter("txtStock"))) msj+="El código de producto debe ser un número entero. \n";
			request.setAttribute("mensaje", msj);
			request.getRequestDispatcher("ListadoCliPendientes.jsp").forward(request, response);
		}catch (ApplicationException e){
			request.setAttribute("mensaje", e.getMessage());
			request.getRequestDispatcher("ListadoCliPendientes.jsp").forward(request, response);
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
