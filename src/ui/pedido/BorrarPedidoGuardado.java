package ui.pedido;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import appExceptions.ApplicationException;
import negocio.CtrlModificarPedido;

/**
 * Servlet implementation class BorrarPedidoGuardado
 */
@WebServlet("/pedido/borrarPedidoGuardado")
public class BorrarPedidoGuardado extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BorrarPedidoGuardado() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nroPedido;
		try {
			if(request.getParameter("nroPedido")==null){
				throw new ApplicationException("Error al recuperar el número de pedido", null);
			} else {
				nroPedido= Integer.parseInt(request.getParameter("nroPedido"));
				new CtrlModificarPedido().cancelarPedido(nroPedido);
				request.setAttribute("mensajeConfirmacion", "Su pedido ha sido cancelado");
				request.getRequestDispatcher("mensajePedidoModificar.jsp").forward(request, response);
			}
		} catch (ApplicationException e) {
			request.setAttribute("mensajeError", e.getMessage());
			request.getRequestDispatcher("mensajePedidoModificar.jsp").forward(request, response);
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
