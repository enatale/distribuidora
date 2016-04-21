package ui.ajax;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import appExceptions.ApplicationException;
import entidades.Producto;
import negocio.CtrlPedidos;

/**
 * Servlet implementation class ajaxBusquedaCod
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/ajaxBusquedaCod" })
public class ajaxBusquedaCod extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ajaxBusquedaCod() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CtrlPedidos ctrl = new CtrlPedidos();
		int codigo = Integer.valueOf(request.getParameter("codigo"));
		Producto producto;
		try {
			producto = ctrl.getByCodigo(codigo);
			response.getWriter().write(new Gson().toJson(producto));
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
