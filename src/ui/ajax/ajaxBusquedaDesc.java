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
 * Servlet implementation class ajaxBusquedaDesc
 */
@WebServlet(asyncSupported = true, description = "Codigo para realizar busqueda parcial de producto y devolverlo por ajax", urlPatterns = { "/ajaxBusquedaDesc" })
public class ajaxBusquedaDesc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ajaxBusquedaDesc() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int inicio=0;
		CtrlPedidos ctrl = new CtrlPedidos();
		//TODO aumentar cant_por_pagima
		int cant_por_pagina=5;
		String descripcion = request.getParameter("descripcion");
		ArrayList<Producto> productos;
		try {
			productos = ctrl.getByDescripcion(descripcion,inicio,cant_por_pagina);
			response.getWriter().write(new Gson().toJson(productos));
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
