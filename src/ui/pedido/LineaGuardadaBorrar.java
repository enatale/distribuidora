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
 * Servlet implementation class BorrarLineaGuardada
 */
@WebServlet("/pedido/borrarLineaGuardada")
public class LineaGuardadaBorrar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LineaGuardadaBorrar() {
        super();
        // 
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if(request.getParameter("nroPedido")==null){
				throw new ApplicationException("Error al recibir el numero de pedido", null);
			}else if(request.getParameter("codProducto")==null){
				throw new ApplicationException("Error al recibir el codigo de producto a borrar", null);
			}else if(request.getParameter("cantidad")==null){
				throw new ApplicationException("Error al recibir cantidad de producto a borrar", null);
			} else {
				int nroPedido = Integer.parseInt(request.getParameter("nroPedido"));
				int codProducto =  Integer.parseInt(request.getParameter("codProducto"));
				int cantidad = Integer.parseInt(request.getParameter("cantidad"));
				new CtrlModificarPedido().borrarLinea(nroPedido,codProducto, cantidad);
				response.sendRedirect("../pedidoModificar2.jsp?txtNroPedido="+nroPedido);
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
