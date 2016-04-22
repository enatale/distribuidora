package ui.pedido;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import appExceptions.ApplicationException;
import entidades.Linea_pedido;
import entidades.Pedidos;
import negocio.CtrlPedidos;

/**
 * Servlet implementation class BorrarPedido
 */
@WebServlet("/pedido/borrarPedido")
public class BorrarPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BorrarPedido() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			CtrlPedidos ctrl= new CtrlPedidos();
			Pedidos pedido = (Pedidos) request.getSession().getAttribute("pedido");
			for (Linea_pedido lp : pedido.getLineas()) {
				ctrl.aumentarStock(lp.getProducto(),lp.getCantidad());
			}
			request.getSession().removeAttribute("pedido");
			response.sendRedirect("../pedido.jsp");
		} catch (ApplicationException e) {
			request.setAttribute("mensajeError", e.getMessage());
			request.getRequestDispatcher("mensajePedido.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
