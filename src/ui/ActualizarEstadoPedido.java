package ui;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import appExceptions.ApplicationException;
import entidades.Empleado;
import entidades.Pedidos;
import negocio.CtrlPedidos;

/**
 * Servlet implementation class ActualizarEstadoPedido
 */
@WebServlet("/ActualizarEstadoPedido")
public class ActualizarEstadoPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActualizarEstadoPedido() {
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
	CtrlPedidos ctrl = new CtrlPedidos();
	String mensaje="";
	Pedidos ped;
	Empleado empleado = (Empleado) request.getSession().getAttribute("usuario");
		try {
			if(empleado!=null && empleado.getLegajo()!=0){
				if(request.getParameter("Estado").equals("")){
					mensaje+= "El estado del pedido no puede estar vacio\n";
				}
				if(request.getParameter("txtNro").equals("")){
					mensaje+= "El numero del pedido a agregar a pedir no puede estar vacia\n";
				}
				if(!mensaje.equals("")){
					throw new ApplicationException(mensaje, null);
				}else{
				
					int numero = Integer.parseInt(request.getParameter("txtNro"));
					String estado = (String) request.getParameter("Estado");
					ctrl.actualizarEstadoPedido(numero,estado);
					ped=ctrl.getByNroPedido(numero);
					request.setAttribute("pedido", ped);
					request.setAttribute("mensajeConfirmacion", "actualizado");
					request.getRequestDispatcher("actualizarEstadoPedido.jsp").forward(request, response);
				}	
			}else{
				throw new ApplicationException("debe estar logueado como empleado para actualizar el stock", null);
			}
		} catch (NumberFormatException e) {
			String msj="";
			if(!esEntero(request.getParameter("txtNro"))) msj+="El c�digo de producto debe ser un n�mero entero. \n";
			request.setAttribute("mensaje", msj);
			request.getRequestDispatcher("actualizarEstadoPedido.jsp").forward(request, response);
		} catch (ApplicationException e){
			request.setAttribute("mensaje", e.getMessage());
			request.getRequestDispatcher("actualizarEstadoPedido.jsp").forward(request, response);
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
