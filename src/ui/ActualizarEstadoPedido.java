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
	Pedidos ped;
	int codigo;
	Empleado empleado = (Empleado) request.getSession().getAttribute("usuario");
	String estado = (String) request.getParameter("Estado");
	String cod = (String) request.getParameter("txtCodigo");
	if(cod==null) { codigo=0;}
	else{codigo = Integer.parseInt(request.getParameter("txtCodigo"));}
	if(estado==null) estado="";
		try {
			if(empleado!=null && empleado.getLegajo()!=0){
				if(estado.equals("")){
					ped = new Pedidos();
					ped=ctrl.getByNroPedido(codigo);
					request.setAttribute("pedido", ped);
					request.getRequestDispatcher("actualizarEstadoPedido.jsp").forward(request, response);
				}else{
					ctrl.actualizarEstadoPedido(codigo,estado);
					ped=ctrl.getByNroPedido(codigo);
					request.setAttribute("pedido", ped);
					request.setAttribute("mensajeConfirmacion", "actualizado");
					request.getRequestDispatcher("actualizarEstadoPedido.jsp").forward(request, response);
				}
			}else{
				throw new ApplicationException("debe estar logueado como empleado para actualizar el stock", null);
			}
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
