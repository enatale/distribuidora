package ui;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.CtrlPedidos;
import negocio.CtrlPersona;
import appExceptions.ApplicationException;
import entidades.Cliente;
import entidades.Empleado;
import entidades.Producto;

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
		
		int dniInt;
		CtrlPersona ctrl = new CtrlPersona();
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		Empleado empleado = (Empleado) request.getSession().getAttribute("usuario");
		String estado = (String) request.getParameter("Estado");
		String dni = (String) request.getParameter("txtDni");
		if(dni==null) { dniInt=0;}
		else{dniInt = Integer.parseInt(request.getParameter("txtDni"));}
		
		try {
		if(empleado!=null && empleado.getLegajo()!=0){
			if(estado!=null && dniInt!=0){
			ctrl.actualizarEstadoCliente(estado,dniInt);
			}
			clientes= ctrl.getCliListadoConfirmar();
			request.setAttribute("clientes", clientes);
			request.getRequestDispatcher("listadoCliPendientes.jsp").forward(request, response);
			
		}else{
			throw new ApplicationException("debe estar logueado como empleado para actualizar el stock", null);
		}
		}catch (ApplicationException e){
			request.setAttribute("mensaje", e.getMessage());
			request.getRequestDispatcher("actualizarStock.jsp").forward(request, response);
		}
	
	}

}
