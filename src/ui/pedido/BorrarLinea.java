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
 * Servlet implementation class BorrarLinea
 */
@WebServlet("/pedido/borrarLinea")
public class BorrarLinea extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BorrarLinea() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if(request.getParameter("nro")==null){
				throw new ApplicationException("Error al recibir el numero de linea a borrar", null);
			} else {
				int nro = Integer.parseInt(request.getParameter("nro"));
				Pedidos pedido = (Pedidos)request.getSession().getAttribute("pedido");
				pedido.getLineas().remove(nro-1);
				request.getSession().setAttribute("pedido", pedido);
				response.sendRedirect("../pedido.jsp");
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
