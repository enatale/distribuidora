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
 * Servlet implementation class LineaGuardadaAgregar
 */
@WebServlet("/agregarLineaGuardada")
public class LineaGuardadaAgregar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LineaGuardadaAgregar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nroPedido, codigo, cantidad;
		nroPedido=Integer.valueOf(request.getParameter("nroPedido"));
		String mensaje="";
		try {			
			if(request.getParameter("codigo").equals("")){
				mensaje += "El código de producto no puede estar en blanco. ";
			}
			if(request.getParameter("cantidad").equals("")){
				mensaje += "La cantidad no puede estar en blanco. ";
			} 
			if (!mensaje.equals("")) {
				throw new ApplicationException(mensaje, null);			
			} else {
				codigo=Integer.valueOf(request.getParameter("codigo"));
				cantidad=Integer.valueOf(request.getParameter("cantidad"));
				new CtrlModificarPedido().agregarLinea(nroPedido,codigo,cantidad);
				response.sendRedirect("pedidoModificar2.jsp?txtNroPedido="+nroPedido);
			}
		}  catch (NumberFormatException e) {
			String msj="";
			if(!esEntero(request.getParameter("codProducto"))) msj+="El código de producto debe ser un número entero. \n";
			if(!esEntero(request.getParameter("cantidad"))) msj+="La cantidad debe ser un número entero. \n";
			request.setAttribute("mensajeAgregar", msj);
			request.getRequestDispatcher("pedidoModificar2.jsp?txtNroPedido="+nroPedido).forward(request, response);
		} catch (ApplicationException e) {
			request.setAttribute("mensajeAgregar", e.getMessage());
			request.getRequestDispatcher("pedidoModificar2.jsp?txtNroPedido="+nroPedido).forward(request, response);
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
