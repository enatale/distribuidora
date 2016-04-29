package ui.pedido;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import appExceptions.ApplicationException;
import entidades.Cliente;
import entidades.Pedidos;
import negocio.CtrlPedidos;

/**
 * Servlet implementation class ConfirmarPedido
 */
@WebServlet("/pedido/confirmarPedido")
public class ConfirmarPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmarPedido() {
        super();
        // 
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if(request.getSession().getAttribute("pedido")==null){
				throw new ApplicationException("No se encontr� un pedido en la sesi�n", null);
			} else {
				Pedidos pedido = (Pedidos)request.getSession().getAttribute("pedido");
				Cliente cliente = (Cliente)request.getSession().getAttribute("usuario");
				if(cliente==null||cliente.getCodCliente()==0){
					throw new ApplicationException("Para confirmar el pedido debe iniciar sesi�n como cliente", null);
				} else {
					CtrlPedidos ctrl = new CtrlPedidos();
					ctrl.confirmarPedido(pedido,cliente);
					request.getSession().removeAttribute("pedido");
					request.setAttribute("mensajeConfirmacion", "Su pedido ha sido registrado con �xito. "
							+ "El n�mero asignado a su pedido es "+pedido.getNumero_pedido());
					request.getRequestDispatcher("mensajePedido.jsp").forward(request, response);
				}	
			}
		} catch (ApplicationException e) {
			request.setAttribute("mensajeError", e.getMessage());
			request.getRequestDispatcher("mensajePedido.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 
		doGet(request, response);
	}

}
