package ui;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import appExceptions.ApplicationException;
import entidades.Producto;
import negocio.CtrlPedidos;

/**
 * Servlet implementation class Productos
 */
@WebServlet("/Productos")
public class Productos extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Productos() {
        // 
    	//super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int inicio,pagina,totalProductos,totalPaginas;
		CtrlPedidos ctrl = new CtrlPedidos();
		ArrayList<Producto> productos = new ArrayList<Producto>();
		
		int cant_por_pagina=10;
		if(request.getParameter("pagina")!=null){
			pagina = Integer.parseInt(request.getParameter("pagina"));
		} else pagina=0;
		
		if(pagina==0){
			inicio=0;
			pagina=1;
		} else{
			inicio = (pagina-1)*cant_por_pagina;
		}
		try {
			totalProductos= ctrl.getCantProductos();
			totalPaginas=  (int) Math.ceil((float)totalProductos/(float)cant_por_pagina);
			productos=ctrl.getAll(inicio, cant_por_pagina);
			request.setAttribute("totalProductos", totalProductos);
			request.setAttribute("totalPaginas", totalPaginas);
			request.setAttribute("productos", productos);
			request.setAttribute("pagina", pagina);
			request.getRequestDispatcher("productos.jsp").forward(request, response);
			
		} catch (ApplicationException e) {
			request.setAttribute("mensajeError", e.getMessage());
			request.getRequestDispatcher("productos.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);			

	}

}


